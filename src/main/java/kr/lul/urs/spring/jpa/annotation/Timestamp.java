/**
 *
 */
package kr.lul.urs.spring.jpa.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import kr.lul.urs.spring.jpa.enumeration.TimestampTrigger;

/**
 * 엔티티가 언제 타임스탬프를 찍는지 설정한다.
 *
 * @author justburrow
 */
@Target({ TYPE, FIELD })
@Retention(RUNTIME)
public @interface Timestamp {
  /**
   * 타임스탬프를 찍어야 하는 트리거 정보.
   *
   * @return 타임스탬프 트리거.
   */
  TimestampTrigger trigger();

  /**
   * 타임스탬프를 찍을 속성의 이름.
   *
   * @return 대상 속성 이름.
   */
  String name();
}
