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
import kr.lul.urs.core.AbstractDomainEntityTest;
import kr.lul.urs.core.CoreTestConfig;
import kr.lul.urs.core.domain.entity.AgentPlatformEntity;
import kr.lul.urs.util.Randoms;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
@Transactional(transactionManager = Beans.NAME_TRANSACTION_MANAGER)
@Rollback(CoreTestConfig.ROLLBACK)
public class AgentPlatformRepositoryTest extends AbstractDomainEntityTest {
  @Autowired
  private AgentPlatformRepository agentPlatformRepository;

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
    final AgentPlatformEntity p1 = new AgentPlatformEntity(this.operator, code, label);
    p1.setDescription(description);

    // When
    final AgentPlatformEntity p2 = this.agentPlatformRepository.saveAndFlush(p1);

    // Then
    assertThat(p2).isNotNull().isEqualTo(p1);
    assertThat(p2.getId()).isGreaterThan(0);
    assertThat(p2.getOwner()).isEqualTo(this.operator);
    assertThat(p2.getCode()).isEqualTo(code);
    assertThat(p2.getLabel()).isEqualTo(label);
    assertThat(p2.getDescription()).isEqualTo(description);
    assertThat(p2.getCreate()).isGreaterThanOrEqualTo(this.now);
    assertThat(p2.getUpdate()).isEqualTo(p2.getCreate());
  }
}
