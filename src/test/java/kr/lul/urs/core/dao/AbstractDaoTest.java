/**
 *
 */
package kr.lul.urs.core.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import kr.lul.urs.AbstractTest;
import kr.lul.urs.application.configuration.InjectionConstants.Properties;
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.domain.entity.OperatorEntity;
import kr.lul.urs.core.service.internal.OperatorInternalService;
import kr.lul.urs.core.service.internal.OperatorInternalServiceUtils;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 5.
 */
abstract class AbstractDaoTest extends AbstractTest {
  @Value("${" + Properties.KEY_DAO_SAVE_AND_FLUSH + "}")
  protected boolean                 saveAndFlush;

  @Autowired
  protected OperatorInternalService operatorInternalService;

  protected Operator                operator;

  /**
   * 호출할 때마다 프로덕트 관리자를 만들어서 {@link #operator}에 저장한다.
   *
   * @since 2016. 5. 5.
   */
  protected void setOperatorAsRandom() {
    this.operator = OperatorInternalServiceUtils.create(this.operatorInternalService);
    assertThat(this.operator).isNotNull().isInstanceOf(OperatorEntity.class);
    assertThat(this.operator.getId()).isGreaterThan(0);
  }
}
