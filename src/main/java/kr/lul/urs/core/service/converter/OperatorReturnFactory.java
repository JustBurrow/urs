/**
 *
 */
package kr.lul.urs.core.service.converter;

import java.util.List;

import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.dto.OperatorDto;
import kr.lul.urs.spring.tx.Return;
import kr.lul.urs.spring.tx.ReturnFactory;

/**
 * {@link Operator} -> {@link OperatorDto}
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 6.
 */
public interface OperatorReturnFactory extends ReturnFactory<Operator, OperatorDto> {
  @Override
  public Return<OperatorDto> converter(Operator operator);

  @Override
  public Return<List<OperatorDto>> converter(List<Operator> list);
}
