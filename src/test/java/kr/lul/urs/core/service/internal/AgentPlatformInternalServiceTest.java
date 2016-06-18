/**
 *
 */
package kr.lul.urs.core.service.internal;

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

import kr.lul.urs.application.configuration.InjectionConstants.Beans;
import kr.lul.urs.core.AbstractDomainEntityTest;
import kr.lul.urs.core.AgentPlatformApiUtils;
import kr.lul.urs.core.AgentPlatformDomainUtils;
import kr.lul.urs.core.CoreTestConfig;
import kr.lul.urs.core.command.CreateAgentPlatformCmd;
import kr.lul.urs.core.command.ReadAgentPlatformCmd;
import kr.lul.urs.core.domain.AgentPlatform;
import kr.lul.urs.util.AssertionException;
import kr.lul.urs.util.Randoms;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
@Transactional(transactionManager = Beans.NAME_TRANSACTION_MANAGER)
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
    final CreateAgentPlatformCmd cmd = AgentPlatformDomainUtils.createCmd(this.operator);

    // When
    AgentPlatform cp = this.agentPlatformInternalService.create(cmd);

    // Then
    assertThat(cp).isNotNull();
    assertThat(cp.getId()).isGreaterThan(0);
    assertThat(cp.getOwner().getId()).isEqualTo(cmd.getOwner());
    this.assertTimestamp(cp);
  }

  @Test
  public void testReadWithId() throws Exception {
    // When
    AgentPlatform actual = this.agentPlatformInternalService.read(this.platform.getId());

    // Then
    assertThat(actual).isNotNull().isEqualTo(this.platform);
  }

  @Test
  public void testReadWithNullCmd() throws Exception {
    assertThatThrownBy(() -> this.agentPlatformInternalService.read(null)).as("command object is null.")
        .isInstanceOf(AssertionException.class);
  }

  @Test
  public void testReadWithIllegalOwnerCmd() throws Exception {
    // Given
    ReadAgentPlatformCmd cmd = AgentPlatformApiUtils.readCmd(this.platform.getId(), this.operator.getId());
    do {
      cmd.setOwner(Randoms.positive());
    } while (this.operator.getId() == cmd.getOwner());

    // When & Then
    assertThatThrownBy(() -> this.agentPlatformInternalService.read(cmd)).as("illegal owner command object.")
        .isInstanceOf(OwnershipException.class);
  }

  @Test
  public void testReadWithCmd() throws Exception {
    // When
    AgentPlatform actual = this.agentPlatformInternalService
        .read(AgentPlatformApiUtils.readCmd(this.platform.getId(), this.operator.getId()));

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
