/**
 *
 */
package kr.lul.urs.core.service;

import static com.spencerwi.hamcrestJDK8Time.matchers.IsAfter.after;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.time.Instant;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.lul.urs.application.ApplicationTestConfig;
import kr.lul.urs.application.configuration.InjectionConstants.Beans;
import kr.lul.urs.core.command.CreateOperatorCmd;
import kr.lul.urs.core.dto.OperatorDto;
import kr.lul.urs.util.EMails;
import kr.lul.urs.util.Strings;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { ApplicationTestConfig.class })
@Transactional(transactionManager = Beans.NAME_TRANSACTION_MANAGER)
public class OperatorServiceTest {
  @Autowired
  private OperatorService          operatorService;
  @Autowired
  private EntityManagerFactoryInfo entityManagerFactoryInfo;

  private Instant                  now;

  @Before
  public void setUp() throws Exception {
    this.now = Instant.now();
  }

  @Test
  public void testCreate() throws Exception {
    // Given
    final String email = EMails.random();
    final CreateOperatorCmd cmd = new CreateOperatorCmd(email, Strings.random(4, 20));

    // When
    final OperatorDto operator = this.operatorService.create(cmd).value();

    // Then
    assertThat(operator.getId(), greaterThan(0));
    assertEquals(email, operator.getEmail());
    assertThat(operator.getCreate(), after(this.now));
  }

  @Test
  public void testCreateAndRead() throws Exception {
    // Given
    final CreateOperatorCmd cmd = new CreateOperatorCmd(EMails.random(), Strings.random(4, 20));
    final OperatorDto o1 = this.operatorService.create(cmd).value();

    // When
    final OperatorDto o2 = this.operatorService.read(o1.getId()).value();
    final OperatorDto o3 = this.operatorService.read(o2.getId()).value();

    // Then
    assertNotNull(o2);
    assertEquals(o1.getId(), o2.getId());
    assertEquals(o1.getEmail(), o2.getEmail());
    assertEquals(o1.getCreate(), o2.getCreate());
    assertEquals(o2, o3);
  }
}
