package kr.lul.urs.spring.jpa.enumeration;

import java.lang.annotation.Annotation;

import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * 타임스탬프를 찍는 이벤트의 종류.
 *
 * @author justburrow
 */
public enum TimestampTrigger {
  CREATE(PrePersist.class),
  READ(PostLoad.class),
  UPDATE(PreUpdate.class);

  private Class<? extends Annotation> handler;

  private TimestampTrigger(Class<? extends Annotation> handler) {
    this.handler = handler;
  }

  /**
   * 트리거에 대응하는 JPA 엔티티 이벤트.
   *
   * @return 트리거에 대응하는 JPA 엔티티 이벤트.
   */
  public Class<? extends Annotation> getHandler() {
    return this.handler;
  }
}
