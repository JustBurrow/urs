/**
 *
 */
package kr.lul.urs.spring.jpa.timestamp;

import static kr.lul.urs.spring.jpa.timestamp.UpdatableMapping.UpdatableEntity.CREATE;
import static kr.lul.urs.spring.jpa.timestamp.UpdatableMapping.UpdatableEntity.UPDATE;
import static kr.lul.urs.spring.jpa.timestamp.UpdatableMapping.UpdatableTable.CREATE_UTC;
import static kr.lul.urs.spring.jpa.timestamp.UpdatableMapping.UpdatableTable.UPDATE_UTC;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * 타임스탬프 필드와 메서드를 구현해둔 추상 클래스.
 * 이 클래스를 상속한 엔티티 클래스는 엔티티 리스너를 설정하는 것 만으로 타임스탬프를 사용할 수 있다.
 *
 * <pre>
 * <code>
 * &#64;javax.persistence.Entity
 * &#64;javax.persistence.EntityListeners({ kr.lul.urs.spring.jpa.timestamp.Timestamper.class })
 * public class SomeEntity extends AbstractUpdatable {
 *   ...
 * }
 * </code>
 * </pre>
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 23.
 */
@MappedSuperclass
@Timestamps({ @Timestamp(trigger = PrePersist.class, name = CREATE),
    @Timestamp(trigger = PreUpdate.class, name = UPDATE) })
public abstract class AbstractUpdatable implements Updatable {
  @Column(name = CREATE_UTC, nullable = false, updatable = false)
  private Instant create;
  @Column(name = UPDATE_UTC, nullable = false)
  private Instant update;

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>Updatable
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public Instant getCreate() {
    return this.create;
  }

  @Override
  public Instant getUpdate() {
    return this.update;
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Object
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public String toString() {
    return String.format("create=%s, update=%s", this.create, this.update);
  }
}
