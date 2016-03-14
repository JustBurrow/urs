package kr.lul.urs.spring.jpa.listener;

import static java.lang.String.format;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import kr.lul.urs.spring.jpa.annotation.Timestamp;
import kr.lul.urs.spring.jpa.annotation.Timestamps;
import kr.lul.urs.util.MapBuilder;

/**
 * 어노테이션 기반의 JPA 2.1 규격의 엔티티에 {@link Timestamp}, {@link Timestamps}를 사용한 엔티티에 타임스탬프를 찍는 {@link EntityListeners}.
 * 상속을 사용한 경우, {@link MappedSuperclass}의 타임스탬프 속성을 찾아서 시각을 찍는다.
 *
 * @author justburrow
 */
public class Timestamper {
  /**
   * 각 이벤트별로 분리한 처리 로직.
   *
   * @author justburrow
   */
  public class InternalHandler {
    private Class<? extends Annotation> event;

    final private Object                excludeLock;
    private Set<Class<?>>               excludes;

    final private Object                cacheLock;
    /**
     * {@link Map}&lt;<code>entity type, timestamp fields</code>%gt;
     */
    private Map<Class<?>, Set<Field>>   cache;

    private InternalHandler(Class<? extends Annotation> event) {
      this.event = event;

      this.excludeLock = new Object();
      this.excludes = new HashSet<>();

      this.cacheLock = new Object();
      this.cache = new HashMap<>();
    }

    /**
     * 캐시에서 엔티티의 타임스탬프 대상 필드를 반환한다.
     *
     * @param entityType
     * @return
     */
    private Set<Field> checkCache(Class<?> entityType) {
      return this.cache.containsKey(entityType) ? this.cache.get(entityType) : new HashSet<>();
    }

    /**
     * 엔티티의 {@link Timestamps} 어노테이션을 분석해서 대상 필드를 찾는다.
     *
     * @param entityType
     *          엔티티 타입.
     * @param targetFields
     *          필드 반환용.
     * @throws IllegalJpaSupportConfigurationException
     */
    private void checkTimestamps(final Class<?> entityType, final Set<Field> targetFields)
        throws IllegalJpaSupportConfigurationException {
      // TODO @MappedSuperclass
      Class<?> target = entityType;
      do {
        if (null != target.getAnnotation(Entity.class) || null != target.getAnnotation(MappedSuperclass.class)) {
          final Timestamps timstamps = target.getAnnotation(Timestamps.class);
          if (null != timstamps) {
            for (Timestamp timestamp : timstamps.value()) {
              if (this.event != timestamp.trigger()) {
                continue;
              } else if ("".equals(timestamp.name())) {
                throw new IllegalJpaSupportConfigurationException(
                    format("%s(via %s) configuration for class[%s via %s] could not use default name.",
                        Timestamp.class.getSimpleName(), Timestamps.class.getSimpleName(), entityType, target));
              } else {
                try {
                  targetFields.add(target.getDeclaredField(timestamp.name()));
                } catch (NoSuchFieldException e) {
                  throw new IllegalJpaSupportConfigurationException(e);
                }
              }
            }
          }
        }
        target = target.getSuperclass();
      } while (Object.class != target);
    }

    /**
     * 엔티티 타입에 설정한 {@link Timestamp} 어노테이션을 분석해서 대상 필드를 찾는다.
     *
     * @param entityType
     *          엔티티 타입.
     * @param targetFields
     *          필드 반환용.
     * @throws IllegalJpaSupportConfigurationException
     */
    private void checkTypeTimestamp(final Class<?> entityType, final Set<Field> targetFields)
        throws IllegalJpaSupportConfigurationException {
      Class<?> target = entityType;
      do {
        if (null != target.getAnnotation(Entity.class) || null != target.getAnnotation(MappedSuperclass.class)) {
          Timestamp timestamp = target.getAnnotation(Timestamp.class);
          if (null != timestamp && this.event == timestamp.trigger()) {
            if ("".equals(timestamp.name())) {
              throw new IllegalJpaSupportConfigurationException(
                  format("%s configuration for class[%s] could not use default name.",
                      Timestamp.class.getSimpleName(), entityType));
            } else {
              try {
                targetFields.add(target.getDeclaredField(timestamp.name()));
              } catch (NoSuchFieldException e) {
                throw new IllegalJpaSupportConfigurationException(e);
              }
            }
          }
        }
        target = target.getSuperclass();
      } while (Object.class != target);
    }

    /**
     * 필드에 설정한 {@link Timestamp} 어노테이션을 분석해서 대상 필드를 찾는다.
     *
     * @param entityType
     *          엔티티 타입.
     * @param targetFields
     *          필드 반환용.
     */
    private void checkFieldTimestamp(final Class<?> entityType, final Set<Field> targetFields) {
      Class<?> target = entityType;
      do {
        if (null != target.getAnnotation(Entity.class) || null != target.getAnnotation(MappedSuperclass.class)) {
          for (Field field : target.getDeclaredFields()) {
            Timestamp timestamp = field.getAnnotation(Timestamp.class);
            if (null == timestamp || this.event != timestamp.trigger()) {
              continue;
            } else {
              targetFields.add(field);
            }
          }
        }
        target = target.getSuperclass();
      } while (Object.class != target);
    }

    /**
     * 캐시를 업데이트한다.
     *
     * @param entityType
     * @param targetFields
     */
    private void updateCache(Class<?> entityType, Set<Field> targetFields) {
      synchronized (this.cacheLock) {
        this.cache.put(entityType, new HashSet<>(targetFields));
      }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 엔티티에 타임스탬프를 설정한다.
     *
     * @param entity
     */
    private void doTimestamp(final Object entity, final Instant now) {
      final Class<?> clz = entity.getClass();
      if (this.excludes.contains(clz)) {
        return;
      }

      Set<Field> targetFields = this.checkCache(clz);
      if (targetFields.isEmpty()) {
        this.checkTimestamps(clz, targetFields);
        this.checkTypeTimestamp(clz, targetFields);
        this.checkFieldTimestamp(clz, targetFields);

        if (targetFields.isEmpty()) {
          synchronized (this.excludeLock) {
            this.excludes.add(clz);
            return;
          }
        } else {
          this.updateCache(clz, targetFields);
        }
      }

      // timestamp
      for (Field f : targetFields) {
        boolean accessable = f.isAccessible();
        if (!accessable) {
          f.setAccessible(true);
        }

        try {
          f.set(entity, now);
        } catch (IllegalArgumentException | IllegalAccessException e) {
          throw new IllegalJpaSupportConfigurationException(e);
        }

        if (!accessable) {
          f.setAccessible(false);
        }
      }
    }
  }

  /**
   * 이벤트 종류별로 등록한 이벤트 리스너.
   */
  private Map<Class<? extends Annotation>, InternalHandler> handlerMap;

  public Timestamper() {
    this.handlerMap = MapBuilder.<Class<? extends Annotation>, InternalHandler>hash()
        .put(PrePersist.class, new InternalHandler(PrePersist.class))
        .put(PostLoad.class, new InternalHandler(PostLoad.class))
        .put(PreUpdate.class, new InternalHandler(PreUpdate.class))
        .build();
  }

  /**
   * 인스턴스가 JPA 엔티티인지 확인한다.
   *
   * @param entity
   *          엔티티 인스턴스.
   */
  private void isEntity(Object entity) {
    if (null == entity) {
      throw new IllegalArgumentException("entity is null.");
    } else if (null == entity.getClass().getAnnotation(Entity.class)) {
      throw new IllegalArgumentException(format("entity[%s] is not annotated %s.", entity.getClass(), Entity.class));
    }
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * <code>INSERT</code>를 실행하기 전 타임스탬프를 찍는다.
   *
   * @param entity
   *          엔티티 인스턴스.
   */
  @PrePersist
  public void prePersist(Object entity) {
    this.isEntity(entity);
    Instant now = Instant.now();
    this.handlerMap.get(PrePersist.class).doTimestamp(entity, now);
    this.handlerMap.get(PreUpdate.class).doTimestamp(entity, now);
  }

  /**
   * <code>SELECT</code>를 실행한 후 타임스탬프를 찍는다.
   *
   * @param entity
   *          엔티티 인스턴스.
   */
  @PostLoad
  public void postLoad(Object entity) {
    this.isEntity(entity);
    this.handlerMap.get(PostLoad.class).doTimestamp(entity, Instant.now());
  }

  /**
   * <code>UPDATE</code>를 실행하기 전 타임스탬프를 찍는다.
   *
   * @param entity
   *          엔티티 인스턴스.
   */
  @PreUpdate
  public void preUpdate(Object entity) {
    this.isEntity(entity);
    this.handlerMap.get(PreUpdate.class).doTimestamp(entity, Instant.now());
  }
}
