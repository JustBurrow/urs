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

import kr.lul.urs.core.AbstractApiTest;
import kr.lul.urs.core.ClientPlatformApiUtils;
import kr.lul.urs.core.CoreTestConfig;
import kr.lul.urs.core.OperatorApiUtils;
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
public class ClientPlatformServiceTest extends AbstractApiTest {
  @Autowired
  private ClientPlatformRepository clientPlatformRepository;

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    this.setOperatorAsRandom();
  }

  @Test
  public void testCreateWithNull() {
    assertThatThrownBy(() -> this.clientPlatformService.create(null)).isInstanceOf(AssertionException.class);
  }

  @Test
  public void testCreate() throws Exception {
    // Given
    CreateClientPlatformCmd cmd = ClientPlatformApiUtils.createCmd(this.operator);

    // When
    ClientPlatformDto dto = this.clientPlatformService.create(cmd).value();

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
    assertThat(this.clientPlatformService.read(Randoms.negative())).isNull();
  }

  @Test
  public void testReadWith0() throws Exception {
    assertThat(this.clientPlatformService.read(0)).isNull();
  }

  @Test
  public void testReadWithId() throws Exception {
    // Given
    final ClientPlatformDto expected = ClientPlatformApiUtils.create(this.operator, this.clientPlatformService);

    // When
    ClientPlatformDto actual = this.clientPlatformService.read(expected.getId()).value();

    // Then
    assertThat(actual).isEqualTo(expected)
        .isNotSameAs(expected);
  }

  @Test
  public void testReadWithNull() throws Exception {
    assertThatThrownBy(() -> this.clientPlatformService.read(null)).isInstanceOf(AssertionException.class);
  }

  @Test
  public void testReadWithIllegalOwnership() throws Exception {
    // Given
    OperatorDto op2 = OperatorApiUtils.create(this.operatorService);
    ReadClientPlatformCmd cmd = ClientPlatformApiUtils.readCmd(this.clientPlatformRepository);
    assertThat(cmd.getOwner()).isNotEqualTo(op2.getId());
    cmd.setOwner(op2.getId());

    // When & Then
    assertThatThrownBy(() -> this.clientPlatformService.read(cmd)).isInstanceOf(OwnershipException.class);
  }

  @Test
  public void testList() throws Exception {
    // Given
    final List<ClientPlatformDto> l1 = this.clientPlatformService.list().value();
    ClientPlatformDto clientPlatform = ClientPlatformApiUtils.create(this.operator, this.clientPlatformService);
    assertThat(l1).isNotNull()
        .doesNotContain(clientPlatform);

    // When
    List<ClientPlatformDto> l2 = this.clientPlatformService.list().value();

    // Then
    assertThat(l2).contains(clientPlatform)
        .hasSize(l1.size() + 1);
  }
}
