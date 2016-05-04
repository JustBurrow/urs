/**
 *
 */
package kr.lul.urs.application.configuration;

import static kr.lul.urs.application.configuration.InjectionConstants.Beans.NAME_TRANSACTION_MANAGER;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.lul.urs.core.CoreTestConfig;
import kr.lul.urs.core.domain.entity.ParentEntity;
import kr.lul.urs.core.repository.ParentRepository;

/**
 * @author Just Burrow just.burrow@lul.kr
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
@Transactional(transactionManager = NAME_TRANSACTION_MANAGER)
@Rollback(CoreTestConfig.ROLLBACK)
@Ignore
public class JpaConfigurationTest {
  // @Autowired
  private ParentRepository parentRepository;

  @Test
  public void test() {
    // Given
    final String name = RandomStringUtils.random(10);
    final ParentEntity entity = new ParentEntity();
    entity.setName(name);
    assertEquals(0, entity.getId());
    assertEquals(name, entity.getName());

    // When
    final ParentEntity actual = this.parentRepository.saveAndFlush(entity);

    // Than
    assertNotNull(actual);
    assertThat(actual.getId(), greaterThan(0));
    assertEquals(name, actual.getName());
  }
}
