/**
 *
 */
package kr.lul.urs.core.repository;

import static org.assertj.core.api.Assertions.assertThat;

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
import kr.lul.urs.core.domain.entity.ClientPlatformEntity;
import kr.lul.urs.core.service.internal.AbstractInternalServiceTest;
import kr.lul.urs.util.Randoms;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
@Transactional(transactionManager = Beans.NAME_TRANSACTION_MANAGER)
@Rollback(CoreTestConfig.ROLLBACK)
public class ClientPlatformRepositoryTest extends AbstractInternalServiceTest {
  @Autowired
  private ClientPlatformRepository clientPlatformRepository;

  @Before
  public void setUp() throws Exception {
    this.setNow();
    this.setOperatorAsRandom();
  }

  @Test
  public void testSaveAndFlush() {
    // Given
    final String code = RandomStringUtils.randomAlphabetic(Randoms.in(3, 10));
    final String label = RandomStringUtils.randomAlphanumeric(Randoms.in(1, 10));
    final String description = RandomStringUtils.randomAlphanumeric(Randoms.in(0, 1000));
    final ClientPlatformEntity cp1 = new ClientPlatformEntity(this.operator, code, label);
    cp1.setDescription(description);

    // When
    final ClientPlatformEntity cp2 = this.clientPlatformRepository.saveAndFlush(cp1);

    // Then
    assertThat(cp2).isNotNull().isEqualTo(cp1);
    assertThat(cp2.getId()).isGreaterThan(0);
    assertThat(cp2.getOwner()).isEqualTo(this.operator);
    assertThat(cp2.getCode()).isEqualTo(code);
    assertThat(cp2.getLabel()).isEqualTo(label);
    assertThat(cp2.getDescription()).isEqualTo(description);
    assertThat(cp2.getCreate()).isGreaterThanOrEqualTo(this.now);
    assertThat(cp2.getUpdate()).isEqualTo(cp2.getCreate());
  }
}
