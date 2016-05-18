/**
 *
 */
package kr.lul.urs.core;

import static kr.lul.urs.core.ClientPlatformApiUtils.readCmd;
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

import kr.lul.urs.core.command.CreateClientPlatformCmd;
import kr.lul.urs.core.command.ReadClientPlatformCmd;
import kr.lul.urs.core.domain.ClientPlatform;
import kr.lul.urs.core.domain.entity.ClientPlatformEntity;
import kr.lul.urs.core.dto.OperatorDto;
import kr.lul.urs.core.repository.ClientPlatformRepository;
import kr.lul.urs.core.service.ClientPlatformService;
import kr.lul.urs.util.AssertionException;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
public class ClientPlatformApiUtilsTest extends AbstractApiTest {
  @Autowired
  private ClientPlatformService    clientPlatformService;
  @Autowired
  private ClientPlatformRepository clientPlatformRepository;

  @Before
  public void setUp() throws Exception {
    this.setNow();
    this.setOperatorAsRandom();
  }

  @Test
  public void testCostructor() {
    assertThatThrownBy(() -> new ClientPlatformApiUtils() {
    }).isInstanceOf(UnsupportedOperationException.class);
  }

  @Test
  public void testReadCmdWithNullService() throws Exception {
    assertThatThrownBy(() -> ClientPlatformApiUtils.readCmd((ClientPlatformService) null))
        .isInstanceOf(AssertionException.class);
  }

  @Test
  public void testReadCmdWithService() throws Exception {
    // When
    ReadClientPlatformCmd cmd = ClientPlatformApiUtils.readCmd(this.clientPlatformService);

    // Then
    assertThat(cmd).isNotNull();
    assertThat(cmd.getId()).isGreaterThan(0);
    assertThat(cmd.getOwner()).isGreaterThan(0);
  }

  @Test
  public void testReadCmdWithNullRepository() throws Exception {
    assertThatThrownBy(() -> readCmd((ClientPlatformRepository) null))
        .isInstanceOf(AssertionException.class);
  }

  @Test
  public void testReadCmdWithRepository() throws Exception {
    // When
    ReadClientPlatformCmd cmd = readCmd(this.clientPlatformRepository);

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
    final CreateClientPlatformCmd cmd = ClientPlatformApiUtils.createCmd(owner);

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
    CreateClientPlatformCmd cmd = ClientPlatformApiUtils.createCmd(owner);

    // Then
    assertThat(cmd).isNotNull();
    assertThat(cmd.getOwner()).isEqualTo(owner.getId());
    assertThat(cmd.getCode()).isNotNull().matches("[a-z][a-zA-Z\\d]*");
    assertThat(cmd.getLabel()).isNotNull().isNotEmpty();
    assertThat(cmd.getDescription()).isNotNull();
  }

  @Test
  public void testReadCmdWithOwnerIdAndClientPlatformId() throws Exception {
    // Given
    List<ClientPlatformEntity> list = this.clientPlatformRepository.findAll();
    if (list.isEmpty()) {
      ClientPlatformApiUtils.create(this.operator, this.clientPlatformService);
      list = this.clientPlatformRepository.findAll();
    }
    assertThat(list).isNotEmpty();
    Collections.shuffle(list);
    final ClientPlatform expected = list.get(0);

    // When
    final ReadClientPlatformCmd cmd = ClientPlatformApiUtils.readCmd(expected.getId(), expected.getOwner().getId());

    // Then
    assertThat(cmd).isNotNull();
    assertThat(cmd.getId()).isEqualTo(expected.getId());
    assertThat(cmd.getOwner()).isEqualTo(expected.getOwner().getId());
  }
}
