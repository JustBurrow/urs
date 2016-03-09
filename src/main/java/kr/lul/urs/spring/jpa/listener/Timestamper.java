package kr.lul.urs.spring.jpa.listener;

import static kr.lul.urs.util.Asserts.notNull;

import java.lang.annotation.Annotation;
import java.util.Map;

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

    public InternalHandler(Class<? extends Annotation> event) {
      this.event = event;
    }

    protected void doTimestamp(Object entity) {
      notNull(entity);
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
    notNull(entity);
    notNull(entity.getClass().getAnnotation(Entity.class));
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
    this.handlerMap.get(PrePersist.class).doTimestamp(entity);
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
    this.handlerMap.get(PostLoad.class).doTimestamp(entity);
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
    this.handlerMap.get(PreUpdate.class).doTimestamp(entity);
  }
}
