/**
 *
 */
package kr.lul.urs.core.test;

import static com.spencerwi.hamcrestJDK8Time.matchers.IsAfter.after;
import static kr.lul.urs.core.test.ClientPlatformUtils.create;
import static kr.lul.urs.core.test.ClientPlatformUtils.random;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
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
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.domain.entity.ClientPlatformEntity;
import kr.lul.urs.core.repository.ClientPlatformRepository;
import kr.lul.urs.core.service.internal.OperatorInternalService;
import kr.lul.urs.util.AssertionException;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { ApplicationTestConfig.class })
@Transactional(transactionManager = Beans.NAME_TRANSACTION_MANAGER)
@Rollback(ApplicationTestConfig.ROLLBACK)
public class ClientPlatformUtilsTest {
  @Autowired
  private OperatorInternalService  operatorInternalService;
  @Autowired
  private ClientPlatformRepository clientPlatformRepository;

  private Instant                  now;

  @Before
  public void setUp() throws Exception {
    this.now = Instant.now();
  }

  @Test(expected = AssertionException.class)
  public void testRandomWithNull() throws Exception {
    random(null);
    fail();
  }

  @Test(expected = AssertionException.class)
  public void testCreateWithNullAndNull() throws Exception {
    create(null, null);
    fail();
  }

  @Test
  public void testCreate() throws Exception {
    // Given
    final Operator owner = OperatorUtils.create(this.operatorInternalService);

    // When
    final ClientPlatformEntity clientPlatform = create(owner, this.clientPlatformRepository);

    // Then
    assertNotNull(clientPlatform);
    assertThat(clientPlatform.getId(), greaterThan(0));
    assertEquals(owner, clientPlatform.getOwner());
    assertThat(clientPlatform.getCode(), not(isEmptyOrNullString()));
    assertThat(clientPlatform.getLabel(), not(isEmptyOrNullString()));
    assertThat(clientPlatform.getDescription(), not(isEmptyOrNullString()));
    assertThat(clientPlatform.getCreate(), after(this.now));
    assertEquals(clientPlatform.getCreate(), clientPlatform.getUpdate());
  }
}
