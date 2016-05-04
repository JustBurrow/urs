/**
 *
 */
package kr.lul.urs.core.test;

import static kr.lul.urs.core.test.ResourceFileUtils.createCmd;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.lul.urs.core.CoreTestConfig;
import kr.lul.urs.core.command.CreateResourceFileCmd;
import kr.lul.urs.core.dto.ClientPlatformDto;
import kr.lul.urs.core.service.ClientPlatformService;
import kr.lul.urs.core.test.service.ClientPlatformServiceUtils;
import kr.lul.urs.util.Conditions;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
public class ResourceFileUtilsTest extends AbstractUtilsTest {
  @Autowired
  private ClientPlatformService clientPlatformService;

  private ClientPlatformDto     clientPlatform;

  @Before
  public void setUp() throws Exception {
    this.setOperatorAsRandom();
    this.clientPlatform = ClientPlatformServiceUtils.create(this.operator.getId(), this.clientPlatformService).value();
  }

  @Test
  public void testConstructor() {
    assertThatThrownBy(() -> new ResourceFileUtils() {
    }).isInstanceOf(UnsupportedOperationException.class);
  }

  @Test
  public void testCreateCmd() throws Exception {
    // When
    final CreateResourceFileCmd cmd = createCmd(this.clientPlatform);

    // Then
    assertNotNull(cmd);
    assertEquals(this.operator.getId(), cmd.getOwner());
    assertEquals(this.clientPlatform.getId(), cmd.getClientPlatform());
    assertTrue(cmd.getName(), Conditions.matches(cmd.getName(), "(/[a-z][a-zA-Z\\d]*)+"));
  }
}
