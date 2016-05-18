/**
 *
 */
package kr.lul.urs.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.lul.urs.application.configuration.InjectionConstants.Beans;
import kr.lul.urs.core.domain.entity.ClientPlatformEntity;

/**
 * @since 2016. 5. 16.
 * @author Just Burrow just.burrow@lul.kr
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
@Transactional(transactionManager = Beans.NAME_TRANSACTION_MANAGER)
@Rollback(CoreTestConfig.ROLLBACK)
public class ClientPlatformDomainUtilsTest extends AbstractDomainEntityTest {
  @Before
  public void setUp() throws Exception {
    this.setOperatorAsRandom();
  }

  @Test
  public void testCreateWithOwnerAndInternalService() throws Exception {
    // When
    final ClientPlatformEntity clientPlatform = ClientPlatformDomainUtils.create(this.operator,
        this.clientPlatformInternalService);

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
