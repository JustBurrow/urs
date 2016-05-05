package kr.lul.urs.core.service;

import static kr.lul.urs.core.service.OperatorServiceUtils.create;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.lul.urs.core.CoreTestConfig;
import kr.lul.urs.core.dto.OperatorDto;
import kr.lul.urs.core.service.OperatorService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
public class OperatorServiceUtilsTest extends AbstractServiceUtilsTest {
  @Autowired
  private OperatorService operatorService;

  @Before
  public void setUp() throws Exception {
    this.setNow();
  }

  @Test()
  public void testConstruct() {
    assertThatThrownBy(() -> new OperatorServiceUtils() {
    }).isInstanceOf(UnsupportedOperationException.class);
  }

  @Test
  public void testCreateWithService() throws Exception {
    // When
    final OperatorDto operator = create(this.operatorService).value();

    // Then
    assertThat(operator).isNotNull();
    assertThat(operator.getId()).isGreaterThan(0);
    assertThat(operator.getEmail()).isNotNull().is(IS_EMAIL);
    assertThat(operator.getCreate()).isNotNull().isGreaterThanOrEqualTo(this.now);
    assertThat(operator.getUpdate()).isEqualTo(operator.getCreate());
  }
}
