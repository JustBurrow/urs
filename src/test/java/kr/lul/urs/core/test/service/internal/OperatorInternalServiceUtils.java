/**
 *
 */
package kr.lul.urs.core.test.service.internal;

import static kr.lul.urs.core.test.OperatorUtils.command;
import static kr.lul.urs.util.Asserts.notNull;

import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.service.internal.OperatorInternalService;

/**
 * 프로덕트 관리자와 관련된 데이터를 만드는 유틸리티.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 3.
 */
public abstract class OperatorInternalServiceUtils {
  /**
   * @param operatorInternalService
   * @return
   */
  public static Operator create(OperatorInternalService operatorInternalService) {
    notNull(operatorInternalService);
    return operatorInternalService.create(command());
  }

  protected OperatorInternalServiceUtils() {
    throw new UnsupportedOperationException();
  }
}
