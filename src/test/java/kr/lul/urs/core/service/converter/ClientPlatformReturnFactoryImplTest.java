/**
 *
 */
package kr.lul.urs.core.service.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
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
import kr.lul.urs.core.CoreTestConfig;
import kr.lul.urs.core.domain.ClientPlatform;
import kr.lul.urs.core.domain.entity.ClientPlatformEntity;
import kr.lul.urs.core.dto.ClientPlatformDto;
import kr.lul.urs.core.service.internal.AbstractInternalServiceTest;
import kr.lul.urs.core.service.internal.ClientPlatformInternalServiceUtils;
import kr.lul.urs.spring.tx.Return;
import kr.lul.urs.util.AssertionException;
import kr.lul.urs.util.Randoms;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
@Transactional(transactionManager = Beans.NAME_TRANSACTION_MANAGER)
@Rollback(CoreTestConfig.ROLLBACK)
public class ClientPlatformReturnFactoryImplTest extends AbstractInternalServiceTest {
  @Autowired
  private ClientPlatformReturnFactory clientPlatformReturnFactory;

  @Before
  public void setUp() {
    this.setOperatorAsRandom();
  }

  @Test
  public void testConverterClientPlatformWithNull() throws Exception {
    assertThatThrownBy(() -> this.clientPlatformReturnFactory.converter((ClientPlatform) null))
        .isInstanceOf(AssertionException.class);
  }

  @Test
  public void testConverterClientPlatform() throws Exception {
    // Given
    final ClientPlatformEntity clientPlatform = ClientPlatformInternalServiceUtils.create(this.operator,
        this.clientPlatformInternalService);

    // When
    final Return<ClientPlatformDto> rv = this.clientPlatformReturnFactory.converter(clientPlatform);

    // Then
    assertThat(rv).isNotNull();

    final ClientPlatformDto dto = rv.value();
    assertThat(dto).isNotNull();
    assertThat(dto.getId()).isEqualTo(clientPlatform.getId());
    assertThat(dto.getOwner()).isEqualTo(this.operator.getId());
    assertThat(dto.getCode()).isEqualTo(clientPlatform.getCode());
    assertThat(dto.getLabel()).isEqualTo(clientPlatform.getLabel());
    assertThat(dto.getDescription()).isEqualTo(clientPlatform.getDescription());
    if (this.saveAndFlush) {
      assertThat(dto.getCreate()).isEqualTo(clientPlatform.getCreate());
      assertThat(dto.getUpdate()).isEqualTo(clientPlatform.getUpdate());
    }
  }

  @Test
  public void testConverterListWithNull() throws Exception {
    assertThatThrownBy(() -> this.clientPlatformReturnFactory.converter((List<ClientPlatform>) null))
        .isInstanceOf(AssertionException.class);
  }

  @Test
  public void testConverterList() throws Exception {
    // Given
    final List<ClientPlatform> l1 = new ArrayList<>();
    for (int i = Randoms.in(10, 50); i > 0; i--) {
      l1.add(ClientPlatformInternalServiceUtils.create(this.operator, this.clientPlatformInternalService));
    }
    assertThat(l1).isNotEmpty();

    // When
    final Return<List<ClientPlatformDto>> rv = this.clientPlatformReturnFactory.converter(l1);

    // Then
    assertThat(rv).isNotNull();

    List<ClientPlatformDto> l2 = rv.value();
    assertThat(l2).isNotNull().hasSize(l1.size());
    for (int i = 0; i < l2.size(); i++) {
      ClientPlatform e = l1.get(i);
      ClientPlatformDto a = l2.get(i);

      assertThat(a).isNotNull();
      assertThat(a.getId()).isEqualTo(e.getId());
      assertThat(a.getOwner()).isEqualTo(this.operator.getId());
      assertThat(a.getCode()).isEqualTo(e.getCode());
      assertThat(a.getLabel()).isEqualTo(e.getLabel());
      assertThat(a.getDescription()).isEqualTo(e.getDescription());
      if (this.saveAndFlush) {
        assertThat(a.getCreate()).isEqualTo(e.getCreate());
        assertThat(a.getUpdate()).isEqualTo(e.getUpdate());
      } else {
        assertThat(a.getCreate()).isNull();
        assertThat(a.getUpdate()).isNull();
      }
    }
  }
}
