/**
 *
 */
package kr.lul.urs.core.service.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.lul.urs.application.configuration.InjectionConstants.Beans;
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
public class OperatorInternalServiceTest {
  @Autowired
  private OperatorInternalService operatorInternalService;

  @Test
  public void testCreate() throws Exception {
    // Given
    final String email = EMails.random();
    final String password = Strings.random(40, 60);
    final CreateOperatorCmd cmd = new CreateOperatorCmd(email, password);

    assertEquals(email, cmd.getEmail());
    assertEquals(password, cmd.getPassword());

    // When
    final Operator operator = this.operatorInternalService.create(cmd);

    // Then
    assertNotNull(operator);
    assertEquals(email, operator.getEmail());
    assertNotEquals(password, operator.getPassword());
    assertNotNull(operator.getCreate());
    assertEquals(operator.getCreate(), operator.getUpdate());
  }
}
