/**
 *
 */
package kr.lul.urs.core.test;

import static com.spencerwi.hamcrestJDK8Time.matchers.IsAfter.after;
import static kr.lul.urs.core.test.OperatorUtils.command;
import static kr.lul.urs.util.Conditions.matches;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.Instant;

import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.lul.urs.application.ApplicationTestConfig;
import kr.lul.urs.application.configuration.InjectionConstants.Beans;
import kr.lul.urs.core.command.CreateOperatorCmd;
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.dto.OperatorDto;
import kr.lul.urs.core.service.OperatorService;
import kr.lul.urs.core.service.internal.OperatorInternalService;
import kr.lul.urs.spring.jpa.timestamp.Timestamper;
import kr.lul.urs.util.AssertionException;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { ApplicationTestConfig.class })
public class OperatorUtilsTest {
  @Autowired
  private OperatorService         operatorService;
  @Autowired
  private OperatorInternalService operatorInternalService;

  private Instant                 now;
  private EmailValidator          emailValidator;

  @Before
  public void setUp() throws Exception {
    this.now = Instant.now();
    this.emailValidator = new EmailValidator();
  }

  /**
   * @see http://tinyurl.com/za9pdbn
   */
  @Test
  public void testCommand() {
    // When
    final CreateOperatorCmd cmd = command();

    // Then
    assertNotNull(cmd);
    assertNotNull(cmd.getEmail());
    assertTrue(cmd.getEmail(), this.emailValidator.isValid(cmd.getEmail(), null));
    assertTrue(cmd.getPassword(), matches(cmd.getPassword(), ".{6,10}"));
  }

  @Test
  public void testCreateWithService() throws Exception {
    // When
    final OperatorDto operator = OperatorUtils.create(this.operatorService).value();

    // Then
    assertNotNull(operator);
    assertThat(operator.getId(), greaterThan(0));
    assertNotNull(operator.getEmail());
    assertTrue(this.emailValidator.isValid(operator.getEmail(), null));
    assertThat(operator.getCreate(), after(this.now));
  }

  @Test
  @Transactional(transactionManager = Beans.NAME_TRANSACTION_MANAGER)
  public void testCreateWithInternalService() throws Exception {
    // When
    final Operator operator = OperatorUtils.create(this.operatorInternalService);

    // Then
    assertNotNull(operator);
    assertThat(operator.getId(), greaterThan(0));
    assertNotNull(operator.getEmail());
    assertTrue(this.emailValidator.isValid(operator.getEmail(), null));
    assertNotNull(operator.getPassword());
    assertTrue(matches(operator.getPassword(), "^\\$2[ayb]\\$.{56}$"));
    assertThat(operator.getCreate(), after(this.now));
    assertThat(operator.getUpdate(), after(this.now));
    assertEquals(operator.getCreate(), operator.getUpdate());
    assertNotNull(Timestamper.getTimeProvider());
  }

  @Test(expected = AssertionException.class)
  public void testRandomRepositoryWithNull() throws Exception {
    OperatorUtils.random(null);
    fail();
  }
}
