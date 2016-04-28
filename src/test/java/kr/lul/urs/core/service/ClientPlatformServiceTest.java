/**
 *
 */
package kr.lul.urs.core.service;

import static com.spencerwi.hamcrestJDK8Time.matchers.IsAfter.after;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.lul.urs.application.ApplicationTestConfig;
import kr.lul.urs.core.command.CreateClientPlatformCmd;
import kr.lul.urs.core.dto.ClientPlatformDto;
import kr.lul.urs.core.test.ClientPlatformUtils;
import kr.lul.urs.util.AssertionException;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { ApplicationTestConfig.class })
public class ClientPlatformServiceTest extends AbstractServiceTest {
  @Autowired
  private ClientPlatformService clientPlatformService;

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    this.setNow();
    this.setOperator();
  }

  @Test(expected = AssertionException.class)
  public void testCreateWithNull() {
    this.clientPlatformService.create(null);
    fail();
  }

  @Test
  public void testCreate() throws Exception {
    // Given
    CreateClientPlatformCmd cmd = ClientPlatformUtils.createCmd(this.operator);

    // When
    ClientPlatformDto dto = this.clientPlatformService.create(cmd).value();

    // Then
    assertNotNull(dto);
    assertThat(dto.getId(), greaterThan(0));
    assertEquals(this.operator.getId(), dto.getOwner());
    assertEquals(cmd.getCode(), dto.getCode());
    assertEquals(cmd.getLabel(), dto.getLabel());
    assertEquals(cmd.getDescription(), dto.getDescription());
    assertThat(dto.getCreate(), after(this.now));
    assertEquals(dto.getCreate(), dto.getUpdate());
  }
}
