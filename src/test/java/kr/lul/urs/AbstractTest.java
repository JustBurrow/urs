/**
 *
 */
package kr.lul.urs;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;

import kr.lul.urs.spring.jpa.timestamp.Creatable;
import kr.lul.urs.spring.jpa.timestamp.Updatable;

/**
 * 애플리케이션에 독립적은 테스트 도구를 제공한다.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 3.
 */
public abstract class AbstractTest {
  private final static EmailValidator    EMAIL_VALIDATOR = new EmailValidator();

  public static final Condition<Boolean> IS_TRUE         = new Condition<>((b) -> true == b, "boolean is not true.");
  public static final Condition<Boolean> IS_FALSE        = new Condition<>((b) -> false == b, "boolean is not false.");

  /**
   * @see Assertions
   */
  public static final Condition<String>  IS_EMAIL        = new Condition<>(
      (email) -> EMAIL_VALIDATOR.isValid(email, null), "string is not an e-mail address.");

  /**
   * @param creatable
   * @param instant
   * @since 2016. 5. 12.
   */
  public static void assertTimestamp(Creatable creatable, Instant instant) {
    assertThat(creatable).isNotNull();
    assertThat(instant).isNotNull();
    assertThat(creatable.getCreate()).isGreaterThanOrEqualTo(instant);
  }

  /**
   * @param updatable
   * @param create
   * @since 2016. 5. 12.
   */
  public static void assertTimestamp(Updatable updatable, Instant create) {
    assertThat(updatable).isNotNull();
    assertThat(create).isNotNull();

    assertThat(updatable.getCreate()).isGreaterThanOrEqualTo(create);
    assertThat(updatable.getUpdate()).isGreaterThanOrEqualTo(create);
  }

  /**
   * @param updatable
   * @param create
   * @param updata
   * @since 2016. 5. 12.
   */
  public static void assertTimestamp(Updatable updatable, Instant create, Instant updata) {
    assertThat(updatable).isNotNull();
    assertThat(create).isNotNull();
    assertThat(updata).isNotNull();

    assertThat(updatable.getCreate()).isGreaterThanOrEqualTo(create);
    assertThat(updatable.getUpdate()).isGreaterThanOrEqualTo(updata);
  }

  protected Instant now;

  /**
   * 호출할 때마다 호출된 시각을 {@link #now}에 저장한다.
   *
   * @since 2016. 5. 5.
   */
  protected void setNow() {
    this.now = Instant.now();
    assertThat(this.now).isNotNull();
  }

  /**
   * {@link #now} 이후 생성한 {@link Creatable} 인스턴스의 타임스탬프 검사.
   *
   * @param creatable
   * @since 2016. 5. 12.
   */
  public void assertTimestamp(Creatable creatable) {
    assertThat(creatable).isNotNull();
    assertThat(creatable.getCreate()).isGreaterThanOrEqualTo(this.now);
  }

  /**
   * {@link #now} 이후 생성한 {@link Updatable} 인스턴스의 타임스탬프 검사.
   *
   * @param updatable
   * @since 2016. 5. 12.
   */
  public void assertTimestamp(Updatable updatable) {
    assertThat(updatable).isNotNull();
    assertThat(updatable.getCreate()).isGreaterThanOrEqualTo(this.now);
    assertThat(updatable.getUpdate()).isGreaterThanOrEqualTo(updatable.getCreate());
  }
}
