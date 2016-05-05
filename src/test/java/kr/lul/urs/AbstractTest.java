/**
 *
 */
package kr.lul.urs;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;

/**
 * 애플리케이션에 독립적은 테스트 도구를 제공한다.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 3.
 */
public abstract class AbstractTest {
  public static final Condition<Boolean> IS_TRUE  = new Condition<>((b) -> true == b, "boolean is not true.");

  public static final Condition<Boolean> IS_FALSE = new Condition<>((b) -> false == b, "boolean is not false.");

  /**
   * @see Assertions
   */
  public static final Condition<String>  IS_EMAIL = new Condition<>(
      (email) -> new EmailValidator().isValid(email, null),
      "string is not an e-mail address.");

  protected Instant                      now;

  /**
   * 호출할 때마다 호출된 시각을 {@link #now}에 저장한다.
   *
   * @since 2016. 5. 5.
   */
  protected void setNow() {
    this.now = Instant.now();
    assertThat(this.now).isNotNull();
  }
}
