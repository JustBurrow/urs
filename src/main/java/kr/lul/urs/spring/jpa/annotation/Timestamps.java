package kr.lul.urs.spring.jpa.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 하나의 엔티티가 여러개의 타임스탬프를 가지고 있을 때 사용한다.
 *
 * @author justburrow
 */
@Target({ TYPE })
@Retention(RUNTIME)
public @interface Timestamps {
  /**
   * 각 타임스탬프의 정보.
   *
   * @return 타임스탬프 정보.
   */
  Timestamp[] value();
}
