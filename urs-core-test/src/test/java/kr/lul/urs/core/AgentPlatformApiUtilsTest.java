/**
 *
 */
package kr.lul.urs.core;

import static kr.lul.urs.core.AgentPlatformApiUtils.readCmd;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.lul.urs.core.command.CreateAgentPlatformCmd;
import kr.lul.urs.core.command.ReadAgentPlatformCmd;
import kr.lul.urs.core.domain.AgentPlatform;
import kr.lul.urs.core.domain.entity.AgentPlatformEntity;
import kr.lul.urs.core.dto.OperatorDto;
import kr.lul.urs.core.repository.AgentPlatformRepository;
import kr.lul.urs.core.service.AgentPlatformService;
import kr.lul.urs.util.AssertionException;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
public class AgentPlatformApiUtilsTest extends AbstractApiTest {
  @Autowired
  private AgentPlatformService    agentPlatformService;
  @Autowired
  private AgentPlatformRepository agentPlatformRepository;

  @Before
  public void setUp() throws Exception {
    this.setNow();
    this.setOperatorAsRandom();
  }

  @Test
  public void testCostructor() {
    assertThatThrownBy(() -> new AgentPlatformApiUtils() {
    }).isInstanceOf(UnsupportedOperationException.class);
  }

  @Test
  public void testReadCmdWithNullRepository() throws Exception {
    assertThatThrownBy(() -> readCmd((AgentPlatformRepository) null))
        .isInstanceOf(AssertionException.class);
  }

  @Test
  public void testReadCmdWithRepository() throws Exception {
    // When
    ReadAgentPlatformCmd cmd = readCmd(this.agentPlatformRepository);

    // Then
    assertThat(cmd).isNotNull();
    assertThat(cmd.getId()).isGreaterThan(0);
    assertThat(cmd.getOwner()).isGreaterThan(0);
  }

  @Test
  public void testCreateCmdWithOperator() throws Exception {
    // Given
    final OperatorDto owner = OperatorApiUtils.create(this.operatorService);

    // When
    final CreateAgentPlatformCmd cmd = AgentPlatformApiUtils.createCmd(owner);

    // Then
    assertThat(cmd).isNotNull();
    assertThat(cmd.getOwner()).isEqualTo(owner.getId());
    assertThat(cmd.getCode()).isNotNull().matches("[a-z][a-zA-Z\\d]*");
    assertThat(cmd.getLabel()).isNotNull().isNotEmpty();
    assertThat(cmd.getDescription()).isNotNull();
  }

  @Test
  public void testCreateCmdWithOperatorDto() throws Exception {
    // Given
    OperatorDto owner = OperatorApiUtils.create(this.operatorService);

    // When
    CreateAgentPlatformCmd cmd = AgentPlatformApiUtils.createCmd(owner);

    // Then
    assertThat(cmd).isNotNull();
    assertThat(cmd.getOwner()).isEqualTo(owner.getId());
    assertThat(cmd.getCode()).isNotNull().matches("[a-z][a-zA-Z\\d]*");
    assertThat(cmd.getLabel()).isNotNull().isNotEmpty();
    assertThat(cmd.getDescription()).isNotNull();
  }

  @Test
  public void testReadCmdWithOwnerIdAndAgentPlatformId() throws Exception {
    // Given
    List<AgentPlatformEntity> list = this.agentPlatformRepository.findAll();
    if (list.isEmpty()) {
      AgentPlatformApiUtils.create(this.operator, this.agentPlatformService);
      list = this.agentPlatformRepository.findAll();
    }
    assertThat(list).isNotEmpty();
    Collections.shuffle(list);
    final AgentPlatform expected = list.get(0);

    // When
    final ReadAgentPlatformCmd cmd = AgentPlatformApiUtils.readCmd(expected.getId(), expected.getOwner().getId());

    // Then
    assertThat(cmd).isNotNull();
    assertThat(cmd.getId()).isEqualTo(expected.getId());
    assertThat(cmd.getOwner()).isEqualTo(expected.getOwner().getId());
  }
}
