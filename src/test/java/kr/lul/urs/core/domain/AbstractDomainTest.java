/**
 *
 */
package kr.lul.urs.core.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;

import kr.lul.urs.AbstractTest;
import kr.lul.urs.core.service.internal.ClientPlatformInternalService;
import kr.lul.urs.core.service.internal.OperatorInternalService;
import kr.lul.urs.core.test.service.internal.ClientPlatformInternalServiceUtils;
import kr.lul.urs.core.test.service.internal.OperatorInternalServiceUtils;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 3.
 */
public class AbstractDomainTest extends AbstractTest {
  @Autowired
  protected OperatorInternalService       operatorInternalService;
  @Autowired
  protected ClientPlatformInternalService clientPlatformInternalService;

  protected Operator                      operator;
  protected ClientPlatform                clientPlatform;

  protected void setOperatorAsRandom() {
    this.operator = OperatorInternalServiceUtils.create(this.operatorInternalService);

    assertThat(this.operator).isNotNull();
    assertThat(this.operator.getId()).isGreaterThan(0);
  }

  protected void setClientPlatformAsRandom() {
    if (null == this.operator) {
      this.setOperatorAsRandom();
    }
    this.clientPlatform = ClientPlatformInternalServiceUtils.create(this.operator, this.clientPlatformInternalService);
    assertThat(this.clientPlatform).isNotNull();
    assertThat(this.clientPlatform.getId()).isGreaterThan(0);
    assertThat(this.clientPlatform.getOwner()).isEqualTo(this.operator);
  }
}
