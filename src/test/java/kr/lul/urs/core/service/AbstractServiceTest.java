/**
 *
 */
package kr.lul.urs.core.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;

import kr.lul.urs.AbstractTest;
import kr.lul.urs.core.dto.OperatorDto;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 28.
 */
abstract class AbstractServiceTest extends AbstractTest {
  @Autowired
  protected OperatorService operatorService;

  protected OperatorDto     operator;

  /**
   * 호출할 때마다 프로덕트 관리자를 만들어서 {@link #operator}에 저장한다.
   *
   * @since 2016. 5. 5.
   */
  protected void setOperatorAsRandom() {
    if (null == this.now) {
      this.setNow();
    }

    this.operator = OperatorServiceUtils.create(this.operatorService).value();

    assertThat(this.operator).isNotNull();
    assertThat(this.operator.getId()).isGreaterThan(0);
    assertThat(this.operator.getEmail()).is(IS_EMAIL);
    this.assertTimestamp(this.operator);
  }
}
