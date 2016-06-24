/**
 *
 */
package kr.lul.urs.core.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.lul.urs.core.CoreTestConfig;
import kr.lul.urs.core.command.CreateOperatorCmd;
import kr.lul.urs.core.dto.OperatorDto;
import kr.lul.urs.core.test.AbstractDtoTest;
import kr.lul.urs.util.AssertionException;
import kr.lul.urs.util.EMails;
import kr.lul.urs.util.Strings;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CoreTestConfig.class })
public class OperatorServiceTest extends AbstractDtoTest {
  @Before
  public void setUp() throws Exception {
    this.setOperatorAsRandom();
  }

  @Test
  public void testCreateWithNull() throws Exception {
    assertThatThrownBy(() -> this.operatorService.create(null)).isInstanceOf(AssertionException.class);
  }

  @Test
  public void testCreate() throws Exception {
    // Given
    final String email = EMails.random();
    final CreateOperatorCmd cmd = new CreateOperatorCmd(email, Strings.random(4, 20));

    // When
    final OperatorDto operator = this.operatorService.create(cmd).value();

    // Then
    assertThat(operator).isNotNull();
    assertThat(operator.getId()).isGreaterThan(0);
    assertThat(operator.getEmail()).is(IS_EMAIL);
    this.assertTimestamp(operator);
  }

  @Test
  public void testCreateAndRead() throws Exception {
    // Given
    final CreateOperatorCmd cmd = new CreateOperatorCmd(EMails.random(), Strings.random(4, 20));
    final OperatorDto o1 = this.operatorService.create(cmd).value();

    // When
    final OperatorDto o2 = this.operatorService.read(o1.getId()).value();
    /*
     * JPA/Hibernate의 2nd Lv. Cache 작동 여부를 확인하기 위한 추가 코드.
     * 단위테스트에서 코드로는 확인할 수 없으며, Hibernate와 MySQL 제너럴 로그로 확인할 수 있다.
     */
    final OperatorDto o3 = this.operatorService.read(o2.getId()).value();

    // Then
    assertThat(o2).isNotNull()
        .isNotSameAs(o1)
        .isNotSameAs(o3)
        .isEqualTo(o1)
        .isEqualTo(o3);
    assertThat(o2.getId()).isEqualTo(o1.getId());
    assertThat(o2.getEmail()).is(IS_EMAIL);
    this.assertTimestamp(o2);
  }
}
