/**
 *
 */
package kr.lul.urs.core.test;

import static com.spencerwi.hamcrestJDK8Time.matchers.IsAfter.after;
import static kr.lul.urs.core.test.ResourceFileUtils.create;
import static kr.lul.urs.core.test.ResourceFileUtils.createCmd;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
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
import kr.lul.urs.core.command.CreateResourceFileCmd;
import kr.lul.urs.core.domain.entity.ResourceFileEntity;
import kr.lul.urs.core.service.internal.ResourceFileInternalService;
import kr.lul.urs.util.Conditions;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { ApplicationTestConfig.class })
@Transactional(transactionManager = Beans.NAME_TRANSACTION_MANAGER)
@Rollback(ApplicationTestConfig.ROLLBACK)
public class ResourceFileUtilsTest extends AbstractApplicationTest {
  @Autowired
  private ResourceFileInternalService resourceFileInternalService;

  @Before
  public void setUp() throws Exception {
    this.setNow();
    this.setClientPlatformAsRandom();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testConstructor() {
    new ResourceFileUtils() {
    };
    fail();
  }

  @Test
  public void testCreateCmd() throws Exception {
    // When
    final CreateResourceFileCmd cmd = createCmd(this.clientPlatform);

    // Then
    assertNotNull(cmd);
    assertEquals(this.operator.getId(), cmd.getOwner());
    assertEquals(this.clientPlatform.getId(), cmd.getClientPlatform());
    assertTrue(cmd.getName(), Conditions.matches(cmd.getName(), "(/[a-z][a-zA-Z\\d]*)+"));
  }

  @Test
  public void testCreateWithInternalService() throws Exception {
    // When
    final ResourceFileEntity rf = create(this.clientPlatform, this.resourceFileInternalService);

    // Then
    assertNotNull(rf);
    assertEquals(this.operator, rf.getOwner());
    assertEquals(this.clientPlatform, rf.getClientPlatform());
    assertTrue(rf.getName(), Conditions.matches(rf.getName(), "(/[a-z][a-zA-Z\\d]*)+"));
    assertThat(rf.getCreate(), after(this.now));
    assertEquals(rf.getCreate(), rf.getUpdate());
  }
}
