/**
 *
 */
package kr.lul.urs.core.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import kr.lul.urs.application.configuration.InjectionConstants.Beans;
import kr.lul.urs.core.dto.OperatorDto;
import kr.lul.urs.core.test.OperatorUtils;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 28.
 */
public abstract class AbstractServiceTest {
  @Autowired
  private OperatorService operatorService;

  protected Instant       now;
  protected OperatorDto   operator;

  protected void setNow() {
    this.now = Instant.now();
  }

  @Transactional(transactionManager = Beans.NAME_TRANSACTION_MANAGER)
  protected void setOperator() {
    this.operator = OperatorUtils.create(this.operatorService).value();
  }
}
