/**
 *
 */
package kr.lul.urs.core.command;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 인증된 운영자 정보를 가지는 단순한 커맨드.
 *
 * @since 2016. 6. 8.
 * @author Just Burrow just.burrow@lul.kr
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OperatorCmd extends OwnershipCmd {
}
