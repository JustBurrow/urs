/**
 *
 */
package kr.lul.urs;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.time.Instant;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ClassUtils;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;

import kr.lul.urs.core.dto.OperatorDto;
import kr.lul.urs.core.service.OperatorService;
import kr.lul.urs.core.test.OperatorDtoUtils;
import kr.lul.urs.spring.jpa.timestamp.Creatable;
import kr.lul.urs.spring.jpa.timestamp.Updatable;
import kr.lul.urs.util.Asserts;

/**
 * 애플리케이션에 독립적은 테스트 도구를 제공한다.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 3.
 */
public abstract class AbstractTest {
  private final static EmailValidator    EMAIL_VALIDATOR = new EmailValidator();

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

  @Autowired
  protected OperatorService operatorService;

  protected Instant         now;
  protected OperatorDto     operator;

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
   * @since 2016. 6. 19.
   */
  protected void setOperatorAsRandom() {
    assertThat(this.operatorService).isNotNull();
    this.operator = OperatorDtoUtils.create(this.operatorService);
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

  /**
   * 테스트용 파일을 반환한다. 각 테스트 클래스마다 전용 디렉토리를 가지며, 그 안에서 파일을 찾는다.
   *
   * @param filename
   * @return
   * @since 2016. 5. 24.
   */
  public static File getTestFile(String filename) {
    Asserts.hasLength(filename, "filename is null or empty.");

    return FileUtils.getFile(TestConfig.TEST_RESOURCE_BASE_PATH,
        ClassUtils.getShortClassName(new Exception().getStackTrace()[1].getClassName()), filename);
  }
}
