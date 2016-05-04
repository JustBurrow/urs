/**
 *
 */
package kr.lul.urs.core.test.service.internal;

import static kr.lul.urs.core.test.service.internal.ClientPlatformInternalServiceUtils.create;
import static org.assertj.core.api.Assertions.assertThat;

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
import kr.lul.urs.core.domain.entity.ClientPlatformEntity;
import kr.lul.urs.core.service.internal.ClientPlatformInternalService;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
@Transactional(transactionManager = Beans.NAME_TRANSACTION_MANAGER)
@Rollback(CoreTestConfig.ROLLBACK)
public class ClientPlatformInternalServiceUtilsTest extends AbstractInternalTestUtilsTest {
  @Autowired
  private ClientPlatformInternalService clientPlatformInternalService;

  @Before
  public void setUp() throws Exception {
    this.setNow();
    this.setOperatorAsRandom();
    assertThat(this.now).isNotNull();
    assertThat(this.operator).isNotNull();
  }

  @Test
  public void testCreateWithOwnerAndInternalService() throws Exception {
    // When
    final ClientPlatformEntity clientPlatform = create(this.operator, this.clientPlatformInternalService);

    // Then
    assertThat(clientPlatform).isNotNull();
    assertThat(clientPlatform.getId()).isGreaterThan(0);
    assertThat(clientPlatform.getOwner()).isEqualTo(this.operator);
    assertThat(clientPlatform.getCode()).isNotNull().isNotEmpty();
    assertThat(clientPlatform.getLabel()).isNotNull().isNotEmpty();
    assertThat(clientPlatform.getDescription()).isNotNull();
    if (this.saveAndFlush) {
      assertThat(clientPlatform.getCreate()).isNotNull().isGreaterThanOrEqualTo(this.now);
      assertThat(clientPlatform.getUpdate()).isEqualTo(clientPlatform.getCreate());
    } else {
      assertThat(clientPlatform.getCreate()).isNull();
      assertThat(clientPlatform.getUpdate()).isNull();
    }
  }
}
