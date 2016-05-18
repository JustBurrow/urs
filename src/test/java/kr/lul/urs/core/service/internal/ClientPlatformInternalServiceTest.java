/**
 *
 */
package kr.lul.urs.core.service.internal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.lul.urs.application.configuration.InjectionConstants.Beans;
import kr.lul.urs.core.AbstractDomainEntityTest;
import kr.lul.urs.core.ClientPlatformDomainUtils;
import kr.lul.urs.core.ClientPlatformApiUtils;
import kr.lul.urs.core.CoreTestConfig;
import kr.lul.urs.core.command.CreateClientPlatformCmd;
import kr.lul.urs.core.command.ReadClientPlatformCmd;
import kr.lul.urs.core.domain.ClientPlatform;
import kr.lul.urs.util.AssertionException;
import kr.lul.urs.util.Randoms;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
@Transactional(transactionManager = Beans.NAME_TRANSACTION_MANAGER)
@Rollback(CoreTestConfig.ROLLBACK)
public class ClientPlatformInternalServiceTest extends AbstractDomainEntityTest {
  @Autowired
  private ClientPlatformInternalService clientPlatformInternalService;

  @Before
  public void setUp() throws Exception {
    this.setClientPlatformAsRandom();
  }

  @Test
  public void testCreateWithNull() throws Exception {
    assertThatThrownBy(() -> this.clientPlatformInternalService.create(null)).isInstanceOf(AssertionException.class);
  }

  @Test
  public void testCreate() throws Exception {
    // Given
    final CreateClientPlatformCmd cmd = ClientPlatformDomainUtils.createCmd(this.operator);

    // When
    ClientPlatform cp = this.clientPlatformInternalService.create(cmd);

    // Then
    assertThat(cp).isNotNull();
    assertThat(cp.getId()).isGreaterThan(0);
    assertThat(cp.getOwner().getId()).isEqualTo(cmd.getOwner());
    if (this.saveAndFlush) {
      assertThat(cp.getCreate()).isGreaterThan(this.now);
      assertThat(cp.getUpdate()).isEqualTo(cp.getCreate());
    } else {
      assertThat(cp.getCreate()).isNull();
      assertThat(cp.getUpdate()).isNull();
    }
  }

  @Test
  public void testReadWithId() throws Exception {
    // When
    ClientPlatform actual = this.clientPlatformInternalService.read(this.clientPlatform.getId());

    // Then
    assertThat(actual).isNotNull().isEqualTo(this.clientPlatform);
  }

  @Test
  public void testReadWithNullCmd() throws Exception {
    assertThatThrownBy(() -> this.clientPlatformInternalService.read(null)).as("command object is null.")
        .isInstanceOf(AssertionException.class);
  }

  @Test
  public void testReadWithIllegalOwnerCmd() throws Exception {
    // Given
    ReadClientPlatformCmd cmd = ClientPlatformApiUtils.readCmd(this.clientPlatform.getId(), this.operator.getId());
    do {
      cmd.setOwner(Randoms.positive());
    } while (this.operator.getId() == cmd.getOwner());

    // When & Then
    assertThatThrownBy(() -> this.clientPlatformInternalService.read(cmd)).as("illegal owner command object.")
        .isInstanceOf(OwnershipException.class);
  }

  @Test
  public void testReadWithCmd() throws Exception {
    // When
    ClientPlatform actual = this.clientPlatformInternalService
        .read(ClientPlatformApiUtils.readCmd(this.clientPlatform.getId(), this.operator.getId()));

    // Then
    assertThat(actual).isNotNull().isEqualTo(this.clientPlatform);
  }

  @Test
  public void testList() throws Exception {
    // Given
    final List<ClientPlatform> l1 = this.clientPlatformInternalService.list();
    ClientPlatform clientPlatform = ClientPlatformDomainUtils.create(this.operator,
        this.clientPlatformInternalService);

    // When
    final List<ClientPlatform> l2 = this.clientPlatformInternalService.list();

    // Then
    assertThat(l1).doesNotContain(clientPlatform);
    assertThat(l2).contains(clientPlatform).hasSize(l1.size() + 1);
  }
}
