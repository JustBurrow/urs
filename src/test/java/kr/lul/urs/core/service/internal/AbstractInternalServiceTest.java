/**
 *
 */
package kr.lul.urs.core.service.internal;

import static kr.lul.urs.core.CoreTestConfig.MSG_AUTO_INCREMENT_ID_DOES_NOT_SETTED;
import static kr.lul.urs.core.CoreTestConfig.MSG_TIMESTAMPER_DOES_NOT_WORK;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import kr.lul.urs.AbstractTest;
import kr.lul.urs.application.configuration.InjectionConstants.Properties;
import kr.lul.urs.core.domain.ClientPlatform;
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.domain.mapping.OperatorMapping.Entity;;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 9.
 */
public abstract class AbstractInternalServiceTest extends AbstractTest {
  @Value("${" + Properties.KEY_DAO_SAVE_AND_FLUSH + "}")
  protected boolean                       saveAndFlush;

  @Autowired
  protected OperatorInternalService       operatorInternalService;
  @Autowired
  protected ClientPlatformInternalService clientPlatformInternalService;

  protected Operator                      operator;
  protected ClientPlatform                clientPlatform;

  /**
   * 호출할 때마다 프로덕트 관리자를 만들어서 {@link #operator}에 저장한다.
   *
   * @since 2016. 5. 5.
   */
  protected void setOperatorAsRandom() {
    if (null == this.now) {
      this.setNow();
    }

    this.operator = OperatorInternalServiceUtils.create(this.operatorInternalService);

    assertThat(this.operator).isNotNull();
    if (this.saveAndFlush) {
      assertThat(this.operator.getId()).as(MSG_AUTO_INCREMENT_ID_DOES_NOT_SETTED).isGreaterThan(0);
      assertThat(this.operator.getEmail()).is(IS_EMAIL);
      assertThat(this.operator.getCreate()).as(MSG_TIMESTAMPER_DOES_NOT_WORK, Entity.CREATE).isGreaterThan(this.now);
      assertThat(this.operator.getUpdate()).as(MSG_TIMESTAMPER_DOES_NOT_WORK, Entity.UPDATE)
          .isEqualTo(this.operator.getCreate());
    } else {
      assertThat(this.operator.getId()).isLessThanOrEqualTo(0);
      assertThat(this.operator.getCreate()).isNull();
      assertThat(this.operator.getUpdate()).isNull();
    }
  }

  /**
   * 호출할 때마다 클라이언트 플랫폼을 임의로 만들어 {@link #clientPlatform}에 저장한다.
   *
   * @since 2016. 5. 5.
   */
  protected void setClientPlatformAsRandom() {
    if (null == this.operator) {
      this.setOperatorAsRandom();
    }

    this.clientPlatform = ClientPlatformInternalServiceUtils.create(this.operator, this.clientPlatformInternalService);

    assertThat(this.clientPlatform).isNotNull();
    assertThat(this.clientPlatform.getId()).isGreaterThan(0);
    assertThat(this.clientPlatform.getCode()).isNotNull().isNotEmpty();
    assertThat(this.clientPlatform.getLabel()).isNotNull().isNotEmpty();
    assertThat(this.clientPlatform.getDescription()).isNotNull().isNotEmpty();
    if (this.saveAndFlush) {
      assertThat(this.clientPlatform.getId()).as(MSG_AUTO_INCREMENT_ID_DOES_NOT_SETTED).isGreaterThan(0);
      assertThat(this.clientPlatform.getCreate()).as(MSG_TIMESTAMPER_DOES_NOT_WORK, Entity.CREATE)
          .isGreaterThan(this.now);
      assertThat(this.clientPlatform.getUpdate()).as(MSG_TIMESTAMPER_DOES_NOT_WORK, Entity.UPDATE)
          .isEqualTo(this.clientPlatform.getCreate());
    } else {
      assertThat(this.clientPlatform.getId()).isLessThanOrEqualTo(0);
      assertThat(this.clientPlatform.getCreate()).isNull();
      assertThat(this.clientPlatform.getUpdate()).isNull();
    }
  }
}
