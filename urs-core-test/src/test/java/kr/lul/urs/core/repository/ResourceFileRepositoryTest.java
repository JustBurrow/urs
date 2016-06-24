/**
 *
 */
package kr.lul.urs.core.repository;

import static kr.lul.urs.core.configuration.InjectionConstants.Beans.NAME_TRANSACTION_MANAGER;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.lul.urs.core.CoreTestConfig;
import kr.lul.urs.core.test.AbstractDomainTest;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
@Transactional(transactionManager = NAME_TRANSACTION_MANAGER)
@Rollback(CoreTestConfig.ROLLBACK)
public class ResourceFileRepositoryTest extends AbstractDomainTest {
  @Test
  public void testSaveAndFlush() throws Exception {
  }
}
