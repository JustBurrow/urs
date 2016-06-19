/**
 *
 */
package kr.lul.urs.core;

import static kr.lul.urs.core.configuration.InjectionConstants.Properties.KEY_DAO_SAVE_AND_FLUSH;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import kr.lul.urs.core.domain.AgentPlatform;
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.service.internal.AgentPlatformInternalService;
import kr.lul.urs.core.service.internal.OperatorInternalService;
import kr.lul.urs.spring.jpa.timestamp.Creatable;
import kr.lul.urs.spring.jpa.timestamp.Updatable;
import kr.lul.urs.util.TimeProvider;

/**
 * 도메인 엔티티에 직접 접근하는 테스트로 트랜잭션 내부의 로직을 테스트하기 위한 테스트이다.
 * 다음의 레이어가 대상이다.
 * <ul>
 * <li>{@link kr.lul.urs.core.service.internal}</li>
 * <li>DB와 연동한 {@link kr.lul.urs.core.domain.entity}</li>
 * </ul>
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 16.
 */
public abstract class AbstractDomainEntityTest extends AbstractCoreTest {
  public static void assertTimestamp(Creatable creatable, Instant instant) {
    assertThat(creatable).isNotNull();
    assertThat(instant).isNotNull();
    assertThat(creatable.getCreate()).isGreaterThanOrEqualTo(instant);
  }

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
   * @since 2016. 5. 16.
   */
  public static void assertTimestamp(Updatable updatable, Instant create, Instant updata) {
    assertThat(updatable).isNotNull();
    assertThat(create).isNotNull();
    assertThat(updata).isNotNull().isGreaterThanOrEqualTo(create);

    assertThat(updatable.getCreate()).isGreaterThanOrEqualTo(create);
    assertThat(updatable.getUpdate()).isGreaterThanOrEqualTo(updata);
  }

  @Value("${" + KEY_DAO_SAVE_AND_FLUSH + "}")
  protected boolean                      saveAndFlush;

  @Autowired
  protected TimeProvider                 timeProvider;
  @Autowired
  protected OperatorInternalService      operatorInternalService;
  @Autowired
  protected AgentPlatformInternalService agentPlatformInternalService;

  protected Operator                     operator;
  protected AgentPlatform                platform;

  /**
   * 임의의 관리자를 만든다.
   *
   * @since 2016. 5. 16.
   */
  protected void setOperatorAsRandom() {
    if (null == this.now) {
      this.setNow();
    }
    this.operator = OperatorDomainUtils.create(this.operatorInternalService);

    assertThat(this.operator).isNotNull();
    this.assertTimestamp(this.operator);
  }

  /**
   * 임의의 플랫폼을 만든다.
   *
   * @since 2016. 5. 16.
   */
  protected void setAgentPlatformAsRandom() {
    if (null == this.operator) {
      this.setOperatorAsRandom();
    }

    this.platform = AgentPlatformDomainUtils.create(this.operator, this.agentPlatformInternalService);

    assertThat(this.platform).isNotNull();
    assertThat(this.platform.getOwner()).isEqualTo(this.operator);
    this.assertTimestamp(this.platform);
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <A>AbstractTest
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  /*
   * (non-Javadoc)
   * @see kr.lul.urs.AbstractTest#setNow()
   * @since 2016. 5. 16.
   */
  @Override
  protected void setNow() {
    this.now = this.timeProvider.now();
    assertThat(this.now).isNotNull();
  }

  /*
   * (non-Javadoc)
   * @see kr.lul.urs.AbstractTest#assertTimestamp(kr.lul.urs.spring.jpa.timestamp.Creatable)
   * @since 2016. 5. 16.
   */
  @Override
  public void assertTimestamp(Creatable creatable) {
    if (this.saveAndFlush) {
      super.assertTimestamp(creatable);
    }
  }

  /*
   * (non-Javadoc)
   * @see kr.lul.urs.AbstractTest#assertTimestamp(kr.lul.urs.spring.jpa.timestamp.Updatable)
   * @since 2016. 5. 16.
   */
  @Override
  public void assertTimestamp(Updatable updatable) {
    if (this.saveAndFlush) {
      super.assertTimestamp(updatable);
    }
  }
}
