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
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.dto.OperatorDto;
import kr.lul.urs.core.service.internal.AbstractInternalServiceTest;
import kr.lul.urs.core.service.internal.OperatorInternalServiceUtils;
import kr.lul.urs.spring.tx.Return;
import kr.lul.urs.util.AssertionException;
import kr.lul.urs.util.Randoms;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
@Transactional(transactionManager = Beans.NAME_TRANSACTION_MANAGER)
@Rollback(CoreTestConfig.ROLLBACK)
public class OperatorReturnFactoryTest extends AbstractInternalServiceTest {
  @Autowired
  private OperatorReturnFactory operatorReturnFactory;

  @Before
  public void setUp() throws Exception {
    this.setNow();
  }

  @Test
  public void testConverterOperatorWithNull() throws Exception {
    assertThatThrownBy(() -> this.operatorReturnFactory.converter((Operator) null))
        .as("generate operator converter with null.").isInstanceOf(AssertionException.class);
  }

  @Test
  public void testConverterOperator() throws Exception {
    // Given
    this.setOperatorAsRandom();

    // When
    Return<OperatorDto> rv = this.operatorReturnFactory.converter(this.operator);

    // Then
    assertThat(rv).isNotNull();
    if (this.saveAndFlush) {
      OperatorDto dto = rv.value();
      assertThat(dto).isNotNull();
      assertThat(dto.getId()).isEqualTo(this.operator.getId());
      assertThat(dto.getEmail()).isEqualTo(this.operator.getEmail());
      assertThat(dto.getCreate()).isEqualTo(this.operator.getCreate());
      assertThat(dto.getUpdate()).isEqualTo(this.operator.getUpdate());
    }
  }

  @Test
  public void testConverterListWithNull() throws Exception {
    assertThatThrownBy(() -> this.operatorReturnFactory.converter((List<Operator>) null))
        .as("generate operator list converter with null.").isInstanceOf(AssertionException.class);
  }

  @Test
  public void testConverterListWithEmpty() throws Exception {
    // When
    Return<List<OperatorDto>> rv = this.operatorReturnFactory.converter(new ArrayList<>());

    // Then
    assertThat(rv).isNotNull();
    assertThat(rv.value()).isNotNull().isEmpty();
  }

  @Test
  public void testConverterList() throws Exception {
    // Given
    final List<Operator> l1 = new ArrayList<>();
    for (int i = Randoms.in(10, 100); i > 0; i--) {
      l1.add(OperatorInternalServiceUtils.create(this.operatorInternalService));
    }

    // When
    Return<List<OperatorDto>> rv = this.operatorReturnFactory.converter(l1);

    // Then
    assertThat(rv).isNotNull();

    List<OperatorDto> l2 = rv.value();
    assertThat(l2).isNotNull().hasSize(l1.size());

    for (int i = 0; i < l1.size(); i++) {
      Operator op = l1.get(i);
      OperatorDto dto = l2.get(i);

      assertThat(op.getId()).isEqualTo(dto.getId());
      assertThat(op.getEmail()).isEqualTo(dto.getEmail());
      if (this.saveAndFlush) {
        assertThat(op.getCreate()).isEqualTo(dto.getCreate());
        assertThat(op.getUpdate()).isEqualTo(dto.getUpdate());
      }
    }
  }
}
