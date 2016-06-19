/**
 *
 */
package kr.lul.urs.core;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.time.Instant;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ClassUtils;
import org.assertj.core.api.Condition;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;

import kr.lul.urs.spring.jpa.timestamp.Creatable;
import kr.lul.urs.spring.jpa.timestamp.Updatable;
import kr.lul.urs.util.Asserts;

/**
 * @since 2016. 6. 19.
 * @author Just Burrow just.burrow@lul.kr
 */
public abstract class AbstractCoreTest {
  private final static EmailValidator   EMAIL_VALIDATOR = new EmailValidator();
  public static final Condition<String> IS_EMAIL        = new Condition<>(
      (email) -> EMAIL_VALIDATOR.isValid(email, null), "string is not an e-mail address.");

  /**
   * 테스트용 파일을 반환한다. 각 테스트 클래스마다 전용 디렉토리를 가지며, 그 안에서 파일을 찾는다.
   *
   * @param filename
   * @return
   * @since 2016. 5. 24.
   */
  public static File getTestFile(String filename) {
    Asserts.hasLength(filename, "filename is null or empty.");

    return FileUtils.getFile(CoreTestConfig.TEST_RESOURCE_BASE_PATH,
        ClassUtils.getShortClassName(new Exception().getStackTrace()[1].getClassName()), filename);
  }

  protected Instant now;

  /**
   * @since 2016. 6. 19.
   */
  protected void setNow() {
    this.now = Instant.now();
  }

  /**
   * @param creatable
   * @since 2016. 6. 19.
   */
  protected void assertTimestamp(Creatable creatable) {
    assertThat(this.now).as("now(test start instant) did not set.").isNotNull();

    assertThat(creatable).isNotNull();
    assertThat(creatable.getCreate()).isNotNull()
        .isGreaterThanOrEqualTo(this.now);
  }

  /**
   * @param updatable
   * @since 2016. 6. 19.
   */
  protected void assertTimestamp(Updatable updatable) {
    assertThat(this.now).as("now(test start instant) did not set.").isNotNull();

    assertThat(updatable).isNotNull();
    assertThat(updatable.getCreate()).isNotNull()
        .isGreaterThanOrEqualTo(this.now);
    assertThat(updatable.getUpdate()).isNotNull()
        .isGreaterThanOrEqualTo(updatable.getCreate());
  }
}
