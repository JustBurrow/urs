/**
 *
 */
package kr.lul.urs.core.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.lul.urs.core.ClientPlatformUtils;
import kr.lul.urs.core.CoreTestConfig;
import kr.lul.urs.core.command.CreateClientPlatformCmd;
import kr.lul.urs.core.command.ReadClientPlatformCmd;
import kr.lul.urs.core.dto.ClientPlatformDto;
import kr.lul.urs.core.dto.OperatorDto;
import kr.lul.urs.core.repository.ClientPlatformRepository;
import kr.lul.urs.core.service.internal.OwnershipException;
import kr.lul.urs.util.AssertionException;
import kr.lul.urs.util.Randoms;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
public class ClientPlatformServiceTest extends AbstractServiceTest {
  @Autowired
  private ClientPlatformService    clientPlatformService;
  @Autowired
  private ClientPlatformRepository clientPlatformRepository;

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    this.setNow();
    this.setOperatorAsRandom();
  }

  @Test
  public void testCreateWithNull() {
    assertThatThrownBy(() -> this.clientPlatformService.create(null)).isInstanceOf(AssertionException.class);
  }

  @Test
  public void testCreate() throws Exception {
    // Given
    CreateClientPlatformCmd cmd = ClientPlatformUtils.createCmd(this.operator);

    // When
    ClientPlatformDto dto = this.clientPlatformService.create(cmd).value();

    // Then
    assertThat(dto).isNotNull();
    assertThat(dto.getId()).isGreaterThan(0);
    assertThat(dto.getOwner()).isEqualTo(this.operator.getId());
    assertThat(dto.getCode()).isEqualTo(cmd.getCode());
    assertThat(dto.getLabel()).isEqualTo(cmd.getLabel());
    assertThat(dto.getDescription()).isEqualTo(cmd.getDescription());
    assertThat(dto.getCreate()).isGreaterThanOrEqualTo(this.now);
    assertThat(dto.getUpdate()).isEqualTo(dto.getCreate());
  }

  @Test
  public void testReadWithNegative() throws Exception {
    assertThat(this.clientPlatformService.read(Randoms.negative())).isNull();
  }

  @Test
  public void testReadWith0() throws Exception {
    assertThat(this.clientPlatformService.read(0)).isNull();
  }

  @Test
  public void testReadWithId() throws Exception {
    // Given
    final ClientPlatformDto expected = ClientPlatformServiceUtils.create(this.operator, this.clientPlatformService)
        .value();

    // When
    ClientPlatformDto actual = this.clientPlatformService.read(expected.getId()).value();

    // Then
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testReadWithNull() throws Exception {
    assertThatThrownBy(() -> this.clientPlatformService.read(null)).isInstanceOf(AssertionException.class);
  }

  @Test
  public void testReadWithIllegalOwnership() throws Exception {
    // Given
    OperatorDto op2 = OperatorServiceUtils.create(this.operatorService).value();
    ReadClientPlatformCmd cmd = ClientPlatformUtils.readCmd(this.clientPlatformRepository);
    assertThat(cmd.getOwner()).isNotEqualTo(op2.getId());
    cmd.setOwner(op2.getId());

    // When & Then
    assertThatThrownBy(() -> this.clientPlatformService.read(cmd)).isInstanceOf(OwnershipException.class);
  }

  @Test
  public void testList() throws Exception {
    // Given
    final List<ClientPlatformDto> l1 = this.clientPlatformService.list().value();
    ClientPlatformDto clientPlatform = ClientPlatformServiceUtils.create(this.operator, this.clientPlatformService)
        .value();

    // When
    List<ClientPlatformDto> l2 = this.clientPlatformService.list().value();

    // Then
    assertThat(l1).isNotNull().doesNotContain(clientPlatform);
    assertThat(l2).contains(clientPlatform).hasSize(l1.size() + 1);
  }
}
