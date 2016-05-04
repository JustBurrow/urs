/**
 *
 */
package kr.lul.urs.core.test.service.internal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.service.internal.OperatorInternalService;
import kr.lul.urs.spring.jpa.timestamp.Timestamper;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
@Transactional(transactionManager = Beans.NAME_TRANSACTION_MANAGER)
@Rollback(CoreTestConfig.ROLLBACK)
public class OperatorInternalServiceUtilsTest extends AbstractInternalTestUtilsTest {
  @Autowired
  private OperatorInternalService operatorInternalService;

  @Before
  public void setUp() throws Exception {
    this.setNow();
    assertThat(Timestamper.getTimeProvider()).isNotNull();
  }

  @Test
  public void testConstructor() throws Exception {
    assertThatThrownBy(() -> new OperatorInternalServiceUtils() {
    }).isInstanceOf(UnsupportedOperationException.class);
  }

  @Test
  public void testCreateWithInternalService() throws Exception {
    // When
    final Operator operator = OperatorInternalServiceUtils.create(this.operatorInternalService);

    // Then
    assertThat(operator).isNotNull();
    assertThat(operator.getId()).isGreaterThan(0);
    assertThat(operator.getEmail()).isNotNull().is(IS_EMAIL);
    assertThat(operator.getPassword()).isNotNull().isNotEmpty().matches("^\\$2[ayb]\\$.{56}$");
    if (this.saveAndFlush) {
      assertThat(operator.getCreate()).isNotNull().isGreaterThanOrEqualTo(this.now);
      assertThat(operator.getUpdate()).isEqualTo(operator.getCreate());
    } else {
      assertThat(operator.getCreate()).isNull();
      assertThat(operator.getUpdate()).isNull();
    }
  }
}
