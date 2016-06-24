/**
 *
 */
package kr.lul.urs.core.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.lul.urs.core.CoreTestConfig;
import kr.lul.urs.core.command.CreateAgentPlatformCmd;
import kr.lul.urs.core.command.ReadAgentPlatformCmd;
import kr.lul.urs.core.dto.AgentPlatformDto;
import kr.lul.urs.core.dto.OperatorDto;
import kr.lul.urs.core.repository.AgentPlatformRepository;
import kr.lul.urs.core.service.internal.OwnershipException;
import kr.lul.urs.core.test.AbstractDtoTest;
import kr.lul.urs.core.test.AgentPlatformDtoUtils;
import kr.lul.urs.core.test.OperatorDtoUti;
import kr.lul.urs.util.AssertionException;
import kr.lul.urs.util.Randoms;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
public class AgentPlatformServiceTest extends AbstractDtoTest {
  @Autowired
  private AgentPlatformRepository agentPlatformRepository;

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    this.setOperatorAsRandom();
  }

  @Test
  public void testCreateWithNull() {
    assertThatThrownBy(() -> this.agentPlatformService.create(null)).isInstanceOf(AssertionException.class);
  }

  @Test
  public void testCreate() throws Exception {
    // Given
    CreateAgentPlatformCmd cmd = AgentPlatformDtoUtils.createCmd(this.operator);

    // When
    AgentPlatformDto dto = this.agentPlatformService.create(cmd).value();

    // Then
    assertThat(dto).isNotNull();
    assertThat(dto.getId()).isGreaterThan(0);
    assertThat(dto.getOwner()).isEqualTo(this.operator.getId());
    assertThat(dto.getCode()).isEqualTo(cmd.getCode());
    assertThat(dto.getLabel()).isEqualTo(cmd.getLabel());
    assertThat(dto.getDescription()).isEqualTo(cmd.getDescription());
    this.assertTimestamp(dto);
  }

  @Test
  public void testReadWithNegative() throws Exception {
    assertThat(this.agentPlatformService.read(Randoms.negative())).isNull();
  }

  @Test
  public void testReadWith0() throws Exception {
    assertThat(this.agentPlatformService.read(0)).isNull();
  }

  @Test
  public void testReadWithId() throws Exception {
    // Given
    final AgentPlatformDto expected = AgentPlatformDtoUtils.create(this.operator, this.agentPlatformService);

    // When
    AgentPlatformDto actual = this.agentPlatformService.read(expected.getId()).value();

    // Then
    assertThat(actual).isEqualTo(expected)
        .isNotSameAs(expected);
  }

  @Test
  public void testReadWithNull() throws Exception {
    assertThatThrownBy(() -> this.agentPlatformService.read(null)).isInstanceOf(AssertionException.class);
  }

  @Test
  public void testReadWithIllegalOwnership() throws Exception {
    // Given
    this.setAgentPlatformAsRandom();
    OperatorDto op2 = OperatorDtoUti.create(this.operatorService);
    ReadAgentPlatformCmd cmd = AgentPlatformDtoUtils.readCmd(this.platform.getId(), op2.getId());

    // When & Then
    assertThatThrownBy(() -> this.agentPlatformService.read(cmd)).isInstanceOf(OwnershipException.class);
  }
}
