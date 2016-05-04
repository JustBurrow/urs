/**
 *
 */
package kr.lul.urs.core.test.service;

import static kr.lul.urs.core.test.OperatorUtils.command;
import static kr.lul.urs.util.Asserts.notNull;

import kr.lul.urs.core.dto.OperatorDto;
import kr.lul.urs.core.service.OperatorService;
import kr.lul.urs.spring.tx.util.Return;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 3.
 */
public abstract class OperatorServiceUtils {

  /**
   * @param operatorService
   * @return
   */
  public static Return<OperatorDto> create(OperatorService operatorService) {
    notNull(operatorService);
    return operatorService.create(command());
  }

  protected OperatorServiceUtils() {
    throw new UnsupportedOperationException();
  }
}
