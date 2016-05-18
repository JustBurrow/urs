/**
 *
 */
package kr.lul.urs.core.service.internal;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.lul.urs.application.configuration.InjectionConstants.Beans;
import kr.lul.urs.core.AbstractDomainEntityTest;
import kr.lul.urs.core.CoreTestConfig;
import kr.lul.urs.core.command.CreateOperatorCmd;
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.util.EMails;
import kr.lul.urs.util.Strings;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
@Transactional(transactionManager = Beans.NAME_TRANSACTION_MANAGER)
@Rollback(CoreTestConfig.ROLLBACK)
public class OperatorInternalServiceTest extends AbstractDomainEntityTest {
  @Before
  public void setUp() throws Exception {
    this.setNow();
  }

  @Test
  public void testCreate() throws Exception {
    // Given
    final String email = EMails.random();
    final String password = Strings.random(40, 60);
    final CreateOperatorCmd cmd = new CreateOperatorCmd(email, password);

    // When
    final Operator operator = this.operatorInternalService.create(cmd);

    // Then
    assertThat(operator).isNotNull();
    assertThat(operator.getEmail()).isEqualTo(email);
    assertThat(operator.getPassword()).isNotEqualTo(password);

    if (this.saveAndFlush) {
      assertThat(operator.getCreate()).isGreaterThanOrEqualTo(this.now);
      assertThat(operator.getUpdate()).isEqualTo(operator.getCreate());
    } else {
      assertThat(operator.getCreate()).isNull();
      assertThat(operator.getUpdate()).isNull();
    }
  }
}
