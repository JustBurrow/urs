/**
 *
 */
package kr.lul.urs.core.dto;

import kr.lul.urs.core.domain.Operator;
import lombok.Data;

/**
 * 어떤 {@link Operator}의 소유인 데이터의 DTO를 위한 공통 인터페이스.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 28.
 */
@Data
public abstract class OwnershipDto {
  private int owner;
}
