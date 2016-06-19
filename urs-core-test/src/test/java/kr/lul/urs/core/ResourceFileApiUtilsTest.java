/**
 *
 */
package kr.lul.urs.core;

import static kr.lul.urs.core.ResourceFileApiUtils.createCmd;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.lul.urs.core.command.CreateResourceFileCmd;
import kr.lul.urs.core.dto.AgentPlatformDto;
import kr.lul.urs.core.service.AgentPlatformService;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
public class ResourceFileApiUtilsTest extends AbstractApiTest {
  @Autowired
  private AgentPlatformService agentPlatformService;

  private AgentPlatformDto     agentPlatform;

  @Before
  public void setUp() throws Exception {
    this.setOperatorAsRandom();

    this.agentPlatform = AgentPlatformApiUtils.create(this.operator.getId(), this.agentPlatformService);
    this.assertTimestamp(this.agentPlatform);
  }

  @Test
  public void testConstructor() {
    assertThatThrownBy(() -> new ResourceFileApiUtils() {
    }).isInstanceOf(UnsupportedOperationException.class);
  }

  @Test
  public void testCreateCmd() throws Exception {
    // When
    final CreateResourceFileCmd cmd = createCmd(this.agentPlatform);

    // Then
    assertThat(cmd).isNotNull();
    assertThat(cmd.getOwner()).isEqualTo(this.operator.getId());
    assertThat(cmd.getPlatform()).isEqualTo(this.agentPlatform.getId());
    assertThat(cmd.getName()).matches("(/[a-z][a-zA-Z\\d]*)+");
  }
}
