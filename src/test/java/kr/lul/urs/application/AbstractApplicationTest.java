/**
 *
 */
package kr.lul.urs.application;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;

import kr.lul.urs.core.domain.ClientPlatform;
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.repository.OperatorRepository;
import kr.lul.urs.core.service.internal.ClientPlatformInternalService;
import kr.lul.urs.core.service.internal.OperatorInternalService;
import kr.lul.urs.core.test.ClientPlatformUtils;
import kr.lul.urs.core.test.OperatorUtils;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 9.
 */
public abstract class AbstractApplicationTest {
  @Autowired
  protected OperatorInternalService       operatorInternalService;
  @Autowired
  protected ClientPlatformInternalService clientPlatformInternalService;

  @Autowired
  protected OperatorRepository            operatorRepository;

  protected Instant                       now;
  protected Operator                      operator;
  protected ClientPlatform                clientPlatform;

  protected void setNow() {
    this.now = Instant.now();
  }

  protected void setOperatorAsRandom() {
    this.operator = OperatorUtils.create(this.operatorInternalService);
  }

  protected void setClientPlatformAsRandom() {
    if (null == this.operator) {
      this.setOperatorAsRandom();
    }
    this.clientPlatform = ClientPlatformUtils.create(this.operator, this.clientPlatformInternalService);
  }
}
