/**
 *
 */
package kr.lul.urs.core.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;

import kr.lul.urs.AbstractTest;
import kr.lul.urs.core.dto.OperatorDto;
import kr.lul.urs.core.service.OperatorService;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 3.
 */
abstract class AbstractServiceUtilsTest extends AbstractTest {
  @Autowired
  protected OperatorService operatorService;

  protected OperatorDto     operator;

  protected void setOperatorAsRandom() {
    this.operator = OperatorServiceUtils.create(this.operatorService).value();

    assertThat(this.operator).isNotNull();
    assertThat(this.operator.getId()).isGreaterThan(0);
  }
}
