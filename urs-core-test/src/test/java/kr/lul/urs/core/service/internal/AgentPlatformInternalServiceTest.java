/**
 *
 */
package kr.lul.urs.core.service.internal;

import static kr.lul.urs.core.configuration.InjectionConstants.Beans.NAME_TRANSACTION_MANAGER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.lul.urs.core.AbstractDomainEntityTest;
import kr.lul.urs.core.AgentPlatformApiUtils;
import kr.lul.urs.core.AgentPlatformDomainUtils;
import kr.lul.urs.core.CoreTestConfig;
import kr.lul.urs.core.domain.AgentPlatform;
import kr.lul.urs.core.service.context.CreateAgentPlatformCtx;
import kr.lul.urs.util.AssertionException;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
@Transactional(transactionManager = NAME_TRANSACTION_MANAGER)
@Rollback(CoreTestConfig.ROLLBACK)
public class AgentPlatformInternalServiceTest extends AbstractDomainEntityTest {
  @Autowired
  private AgentPlatformInternalService agentPlatformInternalService;

  @Before
  public void setUp() throws Exception {
    this.setAgentPlatformAsRandom();
  }

  @Test
  public void testCreateWithNull() throws Exception {
    assertThatThrownBy(() -> this.agentPlatformInternalService.create(null)).isInstanceOf(AssertionException.class);
  }

  @Test
  public void testCreate() throws Exception {
    // Given
    final CreateAgentPlatformCtx ctx = AgentPlatformApiUtils.createContext(this.operator);

    // When
    AgentPlatform platform = this.agentPlatformInternalService.create(ctx);

    // Then
    assertThat(platform).isNotNull();
    assertThat(platform.getId()).isGreaterThan(0);
    assertThat(platform.getOwner()).isEqualTo(this.operator);
    this.assertTimestamp(platform);
  }

  @Test
  public void testReadWithId() throws Exception {
    // When
    AgentPlatform actual = this.agentPlatformInternalService.read(this.platform.getId());

    // Then
    assertThat(actual).isNotNull().isEqualTo(this.platform);
  }

  @Test
  public void testList() throws Exception {
    // Given
    final List<AgentPlatform> l1 = this.agentPlatformInternalService.list();
    AgentPlatform platform = AgentPlatformDomainUtils.create(this.operator,
        this.agentPlatformInternalService);

    // When
    final List<AgentPlatform> l2 = this.agentPlatformInternalService.list();

    // Then
    assertThat(l1).doesNotContain(platform);
    assertThat(l2).contains(platform).hasSize(l1.size() + 1);
  }
}
