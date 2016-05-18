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
import kr.lul.urs.core.dto.ClientPlatformDto;
import kr.lul.urs.core.service.ClientPlatformService;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
public class ResourceFileApiUtilsTest extends AbstractApiTest {
  @Autowired
  private ClientPlatformService clientPlatformService;

  private ClientPlatformDto     clientPlatform;

  @Before
  public void setUp() throws Exception {
    this.setOperatorAsRandom();

    this.clientPlatform = ClientPlatformApiUtils.create(this.operator.getId(), this.clientPlatformService);
    this.assertTimestamp(this.clientPlatform);
  }

  @Test
  public void testConstructor() {
    assertThatThrownBy(() -> new ResourceFileApiUtils() {
    }).isInstanceOf(UnsupportedOperationException.class);
  }

  @Test
  public void testCreateCmd() throws Exception {
    // When
    final CreateResourceFileCmd cmd = createCmd(this.clientPlatform);

    // Then
    assertThat(cmd).isNotNull();
    assertThat(cmd.getOwner()).isEqualTo(this.operator.getId());
    assertThat(cmd.getClientPlatform()).isEqualTo(this.clientPlatform.getId());
    assertThat(cmd.getName()).matches("(/[a-z][a-zA-Z\\d]*)+");
  }
}
