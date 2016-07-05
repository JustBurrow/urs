/**
 *
 */
package kr.lul.urs.core.test;

import static kr.lul.urs.core.test.AgentPlatformDtoUtils.createCmd;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.lul.urs.core.CoreTestConfig;
import kr.lul.urs.core.command.CreateAgentPlatformCmd;
import kr.lul.urs.core.dto.OperatorDto;
import kr.lul.urs.core.test.AgentPlatformDtoUtils;
import kr.lul.urs.core.test.OperatorDtoUtils;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
public class AgentPlatformDtoUtilsTest extends AbstractDtoTest {
  @Before
  public void setUp() throws Exception {
    this.setNow();
    this.setOperatorAsRandom();
  }

  @Test
  public void testCostructor() {
    assertThatThrownBy(() -> new AgentPlatformDtoUtils() {
    }).isInstanceOf(UnsupportedOperationException.class);
  }

  @Test
  public void testCreateCmdWithOperator() throws Exception {
    // Given
    final OperatorDto owner = OperatorDtoUtils.create(this.operatorService);

    // When
    final CreateAgentPlatformCmd cmd = createCmd(owner);

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
    OperatorDto owner = OperatorDtoUtils.create(this.operatorService);

    // When
    CreateAgentPlatformCmd cmd = createCmd(owner);

    // Then
    assertThat(cmd).isNotNull();
    assertThat(cmd.getOwner()).isEqualTo(owner.getId());
    assertThat(cmd.getCode()).isNotNull().matches("[a-z][a-zA-Z\\d]*");
    assertThat(cmd.getLabel()).isNotNull().isNotEmpty();
    assertThat(cmd.getDescription()).isNotNull();
  }
}
