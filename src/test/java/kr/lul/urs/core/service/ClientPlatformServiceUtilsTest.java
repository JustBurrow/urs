/**
 *
 */
package kr.lul.urs.core.service;

import static kr.lul.urs.core.service.ClientPlatformServiceUtils.create;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.lul.urs.core.CoreTestConfig;
import kr.lul.urs.core.dto.ClientPlatformDto;
import kr.lul.urs.core.service.ClientPlatformService;
import kr.lul.urs.util.AssertionException;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
public class ClientPlatformServiceUtilsTest extends AbstractServiceUtilsTest {
  @Autowired
  private ClientPlatformService clientPlatformService;

  @Before
  public void setUp() throws Exception {
    this.setNow();
    this.setOperatorAsRandom();
  }

  @Test
  public void testConstructor() {
    assertThatThrownBy(() -> new ClientPlatformServiceUtils() {
    }).isInstanceOf(UnsupportedOperationException.class);
  }

  @Test
  public void testCreateWithNullAndNull() throws Exception {
    assertThatThrownBy(() -> create(null, null)).isInstanceOf(AssertionException.class);
  }

  @Test
  public void testCreateWith0AndNull() throws Exception {
    assertThatThrownBy(() -> create(0, null)).isInstanceOf(AssertionException.class);
  }

  @Test
  public void testCreateWithOwnerAndService() throws Exception {
    // When
    ClientPlatformDto clientPlatform = create(this.operator, this.clientPlatformService).value();

    // Then
    assertThat(clientPlatform).isNotNull();
    assertThat(clientPlatform.getId()).isGreaterThan(0);
    assertThat(clientPlatform.getOwner()).isEqualTo(this.operator.getId());
    assertThat(clientPlatform.getCode()).isNotNull().isNotEmpty();
    assertThat(clientPlatform.getLabel()).isNotNull().isNotEmpty();
    assertThat(clientPlatform.getDescription()).isNotNull();
    assertThat(clientPlatform.getCreate()).isNotNull().isGreaterThanOrEqualTo(this.now);
    assertThat(clientPlatform.getUpdate()).isEqualTo(clientPlatform.getCreate());
  }
}
