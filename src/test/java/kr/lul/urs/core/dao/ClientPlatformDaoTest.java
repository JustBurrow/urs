/**
 *
 */
package kr.lul.urs.core.dao;

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
import kr.lul.urs.core.CoreTestConfig;
import kr.lul.urs.core.domain.ClientPlatform;
import kr.lul.urs.core.service.internal.ClientPlatformInternalService;
import kr.lul.urs.core.service.internal.ClientPlatformInternalServiceUtils;
import kr.lul.urs.util.AssertionException;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
@Transactional(transactionManager = Beans.NAME_TRANSACTION_MANAGER)
@Rollback(CoreTestConfig.ROLLBACK)
public class ClientPlatformDaoTest extends AbstractDaoTest {
  @Autowired
  private ClientPlatformInternalService clientPlatformInternalService;
  @Autowired
  private ClientPlatformDao             clientPlatformDao;

  @Before
  public void setUp() throws Exception {
    this.setOperatorAsRandom();
  }

  @Test
  public void testInsertWithNull() throws Exception {
    assertThatThrownBy(() -> this.clientPlatformDao.insert(null)).isInstanceOf(AssertionException.class);
  }

  @Test
  public void testList() throws Exception {
    // Given
    final List<ClientPlatform> l1 = this.clientPlatformDao.list();
    assertThat(l1).isNotNull().isNotEmpty();
    final ClientPlatform clientPlatform = ClientPlatformInternalServiceUtils.create(this.operator,
        this.clientPlatformInternalService);

    // When
    final List<ClientPlatform> l2 = this.clientPlatformDao.list();

    // Then
    assertThat(l1).doesNotContain(clientPlatform);
    assertThat(l2).contains(clientPlatform).hasSize(l1.size() + 1);
  }
}
