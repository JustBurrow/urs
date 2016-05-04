/**
 *
 */
package kr.lul.urs.core.repository;

import static com.spencerwi.hamcrestJDK8Time.matchers.IsAfter.after;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.time.Instant;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.lul.urs.application.configuration.InjectionConstants.Beans;
import kr.lul.urs.core.CoreTestConfig;
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.domain.entity.ClientPlatformEntity;
import kr.lul.urs.core.service.internal.OperatorInternalService;
import kr.lul.urs.core.test.service.internal.OperatorInternalServiceUtils;
import kr.lul.urs.util.Randoms;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
@Transactional(transactionManager = Beans.NAME_TRANSACTION_MANAGER)
@Rollback(CoreTestConfig.ROLLBACK)
public class ClientPlatformRepositoryTest {
  @Autowired
  private ClientPlatformRepository clientPlatformRepository;

  @Autowired
  private OperatorInternalService  operatorInternalService;

  private Instant                  now;

  @Before
  public void setUp() throws Exception {
    this.now = Instant.now();
  }

  @Test
  public void testSaveAndFlush() {
    // Given
    final Operator owner = OperatorInternalServiceUtils.create(this.operatorInternalService);
    final String code = RandomStringUtils.randomAlphabetic(Randoms.in(3, 10));
    final String label = RandomStringUtils.randomAlphanumeric(Randoms.in(1, 10));
    final String description = RandomStringUtils.randomAlphanumeric(Randoms.in(0, 1000));
    final ClientPlatformEntity cp1 = new ClientPlatformEntity(owner, code, label);
    cp1.setDescription(description);

    // When
    final ClientPlatformEntity cp2 = this.clientPlatformRepository.saveAndFlush(cp1);

    // Then
    assertNotNull(cp2);
    assertThat(cp2.getId(), greaterThan(0));
    assertEquals(owner, cp2.getOwner());
    assertEquals(code, cp2.getCode());
    assertEquals(label, cp2.getLabel());
    assertEquals(description, cp2.getDescription());
    assertThat(cp2.getCreate(), after(this.now));
    assertEquals(cp2.getCreate(), cp2.getUpdate());
  }
}
