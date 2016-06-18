/**
 *
 */
package kr.lul.urs.core.service.context;

import kr.lul.urs.core.domain.Operator;
import lombok.Data;

/**
 * 권한을 운영자 정보를 필요로 하는 컨텍트스.
 *
 * @since 2016. 6. 13.
 * @author Just Burrow just.burrow@lul.kr
 */
@Data
public abstract class AbstractOwnershipContext {
  private Operator owner;
}
