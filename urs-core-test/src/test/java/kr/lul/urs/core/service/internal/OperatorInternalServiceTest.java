/**
 *
 */
package kr.lul.urs.core.service.internal;

import static kr.lul.urs.core.configuration.InjectionConstants.Beans.NAME_TRANSACTION_MANAGER;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.lul.urs.core.CoreTestConfig;
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.service.context.CreateOperatorCtx;
import kr.lul.urs.core.test.AbstractDomainTest;
import kr.lul.urs.core.test.OperatorDtoUti;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
@Transactional(transactionManager = NAME_TRANSACTION_MANAGER)
@Rollback(CoreTestConfig.ROLLBACK)
public class OperatorInternalServiceTest extends AbstractDomainTest {
  @Before
  public void setUp() throws Exception {
    this.setNow();
  }

  @Test
  public void testCreate() throws Exception {
    // Given
    final CreateOperatorCtx ctx = OperatorDtoUti.createContext();
    final String email = ctx.getEmail();
    final String password = ctx.getPassword();

    // When
    final Operator operator = this.operatorInternalService.create(ctx);

    // Then
    assertThat(operator).isNotNull();
    assertThat(operator.getEmail()).isEqualTo(email);
    assertThat(operator.getPassword()).isNotEqualTo(password);
    this.assertTimestamp(operator);
  }
}
