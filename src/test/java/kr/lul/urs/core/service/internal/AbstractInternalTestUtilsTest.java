/**
 *
 */
package kr.lul.urs.core.service.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import kr.lul.urs.AbstractTest;
import kr.lul.urs.application.configuration.InjectionConstants.Properties;
import kr.lul.urs.core.domain.ClientPlatform;
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.service.internal.ClientPlatformInternalService;
import kr.lul.urs.core.service.internal.OperatorInternalService;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 3.
 */
abstract class AbstractInternalTestUtilsTest extends AbstractTest {
  @Value("${" + Properties.KEY_DAO_SAVE_AND_FLUSH + "}")
  protected boolean                       saveAndFlush;

  @Autowired
  protected OperatorInternalService       operatorInternalService;
  @Autowired
  protected ClientPlatformInternalService clientPlatformInternalService;

  protected Operator                      operator;
  protected ClientPlatform                clientPlatform;

  protected void setOperatorAsRandom() {
    this.operator = OperatorInternalServiceUtils.create(this.operatorInternalService);
  }

  protected void setClientPlatformAsRandom() {
    if (null == this.operator) {
      this.setOperatorAsRandom();
    }
    this.clientPlatform = ClientPlatformInternalServiceUtils.create(this.operator, this.clientPlatformInternalService);
  }
}
