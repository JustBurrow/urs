/**
 *
 */
package kr.lul.urs.core.dao;

import static com.spencerwi.hamcrestJDK8Time.matchers.IsAfter.after;
import static kr.lul.urs.core.test.ClientPlatformUtils.random;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.time.Instant;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.lul.urs.application.ApplicationTestConfig;
import kr.lul.urs.application.configuration.InjectionConstants.Beans;
import kr.lul.urs.core.domain.ClientPlatform;
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.domain.entity.ClientPlatformEntity;
import kr.lul.urs.core.service.internal.OperatorInternalService;
import kr.lul.urs.core.test.OperatorUtils;
import kr.lul.urs.util.AssertionException;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { ApplicationTestConfig.class })
@Transactional(transactionManager = Beans.NAME_TRANSACTION_MANAGER)
@Rollback(ApplicationTestConfig.ROLLBACK)
public class ClientPlatformDaoTest {
  @Autowired
  private ClientPlatformDao       clientPlatformDao;

  @Autowired
  private OperatorInternalService operatorInternalService;

  private Instant                 now;

  @Before
  public void setUp() throws Exception {
    this.now = Instant.now();
  }

  @Test(expected = AssertionException.class)
  public void testInsertWithNull() throws Exception {
    this.clientPlatformDao.insert(null);
    fail();
  }

  @Test
  public void testInsert() throws Exception {
    // Given
    final Operator owner = OperatorUtils.create(this.operatorInternalService);
    final ClientPlatformEntity cp1 = random(owner);

    // When
    final ClientPlatform cp2 = this.clientPlatformDao.insert(cp1);

    // Then
    assertNotNull(cp2);
    assertEquals(cp1, cp2);
    assertEquals(owner, cp2.getOwner());
    assertEquals(cp1.getCode(), cp2.getCode());
    assertEquals(cp1.getLabel(), cp2.getLabel());
    assertEquals(cp1.getDescription(), cp2.getDescription());
    assertThat(cp2.getCreate(), after(this.now));
    assertEquals(cp2.getCreate(), cp2.getUpdate());
  }
}
