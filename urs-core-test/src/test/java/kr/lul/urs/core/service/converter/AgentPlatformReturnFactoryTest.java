/**
 *
 */
package kr.lul.urs.core.service.converter;

import static kr.lul.urs.core.configuration.InjectionConstants.Beans.NAME_TRANSACTION_MANAGER;
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

import kr.lul.urs.core.AbstractDomainEntityTest;
import kr.lul.urs.core.AgentPlatformDomainUtils;
import kr.lul.urs.core.CoreTestConfig;
import kr.lul.urs.core.domain.AgentPlatform;
import kr.lul.urs.core.domain.entity.AgentPlatformEntity;
import kr.lul.urs.core.dto.AgentPlatformDto;
import kr.lul.urs.spring.tx.Return;
import kr.lul.urs.util.AssertionException;
import kr.lul.urs.util.Randoms;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
@Transactional(transactionManager = NAME_TRANSACTION_MANAGER)
@Rollback(CoreTestConfig.ROLLBACK)
public class AgentPlatformReturnFactoryTest extends AbstractDomainEntityTest {
  @Autowired
  private AgentPlatformReturnFactory agentPlatformReturnFactory;

  @Before
  public void setUp() {
    this.setOperatorAsRandom();
  }

  @Test
  public void testConverterAgentPlatformWithNull() throws Exception {
    assertThatThrownBy(() -> this.agentPlatformReturnFactory.converter((AgentPlatform) null))
        .isInstanceOf(AssertionException.class);
  }

  @Test
  public void testConverterAgentPlatform() throws Exception {
    // Given
    final AgentPlatformEntity platform = AgentPlatformDomainUtils.create(this.operator,
        this.agentPlatformInternalService);

    // When
    final Return<AgentPlatformDto> rv = this.agentPlatformReturnFactory.converter(platform);

    // Then
    assertThat(rv).isNotNull();

    final AgentPlatformDto dto = rv.value();
    assertThat(dto).isNotNull();
    assertThat(dto.getId()).isEqualTo(platform.getId());
    assertThat(dto.getOwner()).isEqualTo(this.operator.getId());
    assertThat(dto.getCode()).isEqualTo(platform.getCode());
    assertThat(dto.getLabel()).isEqualTo(platform.getLabel());
    assertThat(dto.getDescription()).isEqualTo(platform.getDescription());
    if (this.saveAndFlush) {
      this.assertTimestamp(platform);
      this.assertTimestamp(dto);
      assertThat(dto.getCreate()).isEqualTo(platform.getCreate());
      assertThat(dto.getUpdate()).isEqualTo(platform.getUpdate());
    }
  }

  @Test
  public void testConverterListWithNull() throws Exception {
    assertThatThrownBy(() -> this.agentPlatformReturnFactory.converter((List<AgentPlatform>) null))
        .isInstanceOf(AssertionException.class);
  }

  @Test
  public void testConverterList() throws Exception {
    // Given
    final List<AgentPlatform> l1 = new ArrayList<>();
    for (int i = Randoms.in(10, 50); i > 0; i--) {
      l1.add(AgentPlatformDomainUtils.create(this.operator, this.agentPlatformInternalService));
    }
    assertThat(l1).isNotEmpty();

    // When
    final Return<List<AgentPlatformDto>> rv = this.agentPlatformReturnFactory.converter(l1);

    // Then
    assertThat(rv).isNotNull();

    List<AgentPlatformDto> l2 = rv.value();
    assertThat(l2).isNotNull().hasSize(l1.size());
    for (int i = 0; i < l2.size(); i++) {
      AgentPlatform expected = l1.get(i);
      AgentPlatformDto actual = l2.get(i);

      assertThat(actual).isNotNull();
      assertThat(actual.getId()).isEqualTo(expected.getId());
      assertThat(actual.getOwner()).isEqualTo(this.operator.getId());
      assertThat(actual.getCode()).isEqualTo(expected.getCode());
      assertThat(actual.getLabel()).isEqualTo(expected.getLabel());
      assertThat(actual.getDescription()).isEqualTo(expected.getDescription());
      if (this.saveAndFlush) {
        this.assertTimestamp(actual);
      } else {
        assertThat(actual.getCreate()).isNull();
        assertThat(actual.getUpdate()).isNull();
      }
    }
  }
}
