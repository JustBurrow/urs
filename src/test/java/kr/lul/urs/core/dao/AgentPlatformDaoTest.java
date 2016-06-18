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
import kr.lul.urs.core.AbstractDomainEntityTest;
import kr.lul.urs.core.AgentPlatformDomainUtils;
import kr.lul.urs.core.CoreTestConfig;
import kr.lul.urs.core.domain.AgentPlatform;
import kr.lul.urs.core.service.internal.AgentPlatformInternalService;
import kr.lul.urs.util.AssertionException;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
@Transactional(transactionManager = Beans.NAME_TRANSACTION_MANAGER)
@Rollback(CoreTestConfig.ROLLBACK)
public class AgentPlatformDaoTest extends AbstractDomainEntityTest {
  @Autowired
  private AgentPlatformInternalService agentPlatformInternalService;
  @Autowired
  private AgentPlatformDao             agentPlatformDao;

  @Before
  public void setUp() throws Exception {
    this.setOperatorAsRandom();
  }

  @Test
  public void testInsertWithNull() throws Exception {
    assertThatThrownBy(() -> this.agentPlatformDao.insert(null)).isInstanceOf(AssertionException.class);
  }

  @Test
  public void testList() throws Exception {
    // Given
    final List<AgentPlatform> l1 = this.agentPlatformDao.list();
    assertThat(l1).isNotNull().isNotEmpty();
    final AgentPlatform platform = AgentPlatformDomainUtils.create(this.operator,
        this.agentPlatformInternalService);

    // When
    final List<AgentPlatform> l2 = this.agentPlatformDao.list();

    // Then
    assertThat(l1).doesNotContain(platform);
    assertThat(l2).contains(platform).hasSize(l1.size() + 1);
  }
}
