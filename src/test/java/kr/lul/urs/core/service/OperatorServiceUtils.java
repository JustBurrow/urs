/**
 *
 */
package kr.lul.urs.core.service;

import static kr.lul.urs.core.OperatorUtils.command;
import static kr.lul.urs.util.Asserts.notNull;

import kr.lul.urs.core.dto.OperatorDto;
import kr.lul.urs.core.service.OperatorService;
import kr.lul.urs.spring.tx.Return;

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
