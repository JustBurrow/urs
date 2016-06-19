/**
 *
 */
package kr.lul.urs.core.command;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 28.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ReadAgentPlatformCmd extends OwnershipCmd {
  private int id;
}
