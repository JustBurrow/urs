/**
 *
 */
package kr.lul.urs.core.service.internal;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;

import kr.lul.urs.core.domain.ClientPlatform;
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.test.service.internal.ClientPlatformInternalServiceUtils;
import kr.lul.urs.core.test.service.internal.OperatorInternalServiceUtils;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 9.
 */
abstract class AbstractInternalServiceTest {
  @Autowired
  protected OperatorInternalService       operatorInternalService;
  @Autowired
  protected ClientPlatformInternalService clientPlatformInternalService;

  protected Instant                       now;
  protected Operator                      operator;
  protected ClientPlatform                clientPlatform;

  protected void setNow() {
    this.now = Instant.now();
  }

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
