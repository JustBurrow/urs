/**
 *
 */
package kr.lul.urs.core.service.internal;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;

import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.test.OperatorUtils;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 9.
 */
abstract class AbstractInternalServiceTest {
  @Autowired
  protected OperatorInternalService operatorInternalService;

  protected Instant                 now;
  protected Operator                operator;

  protected void setNow() {
    this.now = Instant.now();
  }

  protected void generateRandomOperator() {
    this.operator = OperatorUtils.create(this.operatorInternalService);
  }
}
