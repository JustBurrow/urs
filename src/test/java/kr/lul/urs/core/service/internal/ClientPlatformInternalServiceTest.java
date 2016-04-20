/**
 *
 */
package kr.lul.urs.core.service.internal;

import static com.spencerwi.hamcrestJDK8Time.matchers.IsAfter.after;
import static kr.lul.urs.core.test.ClientPlatformUtils.createCmd;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.lul.urs.application.AbstractApplicationTest;
import kr.lul.urs.application.ApplicationTestConfig;
import kr.lul.urs.application.configuration.InjectionConstants.Beans;
import kr.lul.urs.core.command.CreateClientPlatformCmd;
import kr.lul.urs.core.domain.ClientPlatform;
import kr.lul.urs.util.AssertionException;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { ApplicationTestConfig.class })
@Transactional(transactionManager = Beans.NAME_TRANSACTION_MANAGER)
@Rollback(ApplicationTestConfig.ROLLBACK)
public class ClientPlatformInternalServiceTest extends AbstractApplicationTest {
  @Autowired
  private ClientPlatformInternalService clientPlatformInternalService;

  @Before
  public void setUp() throws Exception {
    this.setNow();
    this.setOperatorAsRandom();
  }

  @Test(expected = AssertionException.class)
  public void testCreateWithNull() throws Exception {
    this.clientPlatformInternalService.create(null);
    fail();
  }

  @Test
  public void testCreate() throws Exception {
    // Given
    final CreateClientPlatformCmd cmd = createCmd(this.operator);

    // When
    ClientPlatform cp = this.clientPlatformInternalService.create(cmd);

    // Then
    assertNotNull(cp);
    assertThat(cp.getId(), greaterThan(0));
    assertEquals(cmd.getOwner(), cp.getOwner().getId());
    assertNotNull(cp.getCreate());
    assertThat(cp.getCreate(), allOf(notNullValue(), after(this.now)));
    assertEquals(cp.getCreate(), cp.getUpdate());
  }
}
