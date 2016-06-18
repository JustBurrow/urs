/**
 *
 */
package kr.lul.urs.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.lul.urs.application.configuration.InjectionConstants.Beans;
import kr.lul.urs.core.domain.entity.AgentPlatformEntity;

/**
 * @since 2016. 5. 16.
 * @author Just Burrow just.burrow@lul.kr
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
@Transactional(transactionManager = Beans.NAME_TRANSACTION_MANAGER)
@Rollback(CoreTestConfig.ROLLBACK)
public class AgentPlatformDomainUtilsTest extends AbstractDomainEntityTest {
  @Before
  public void setUp() throws Exception {
    this.setOperatorAsRandom();
  }

  @Test
  public void testCreateWithOwnerAndInternalService() throws Exception {
    // When
    final AgentPlatformEntity platform = AgentPlatformDomainUtils.create(this.operator,
        this.agentPlatformInternalService);

    // Then
    assertThat(platform).isNotNull();
    assertThat(platform.getId()).isGreaterThan(0);
    assertThat(platform.getOwner()).isEqualTo(this.operator);
    assertThat(platform.getCode()).isNotNull().isNotEmpty();
    assertThat(platform.getLabel()).isNotNull().isNotEmpty();
    assertThat(platform.getDescription()).isNotNull();
    if (this.saveAndFlush) {
      assertThat(platform.getCreate()).isNotNull().isGreaterThanOrEqualTo(this.now);
      assertThat(platform.getUpdate()).isEqualTo(platform.getCreate());
    } else {
      assertThat(platform.getCreate()).isNull();
      assertThat(platform.getUpdate()).isNull();
    }
  }
}
