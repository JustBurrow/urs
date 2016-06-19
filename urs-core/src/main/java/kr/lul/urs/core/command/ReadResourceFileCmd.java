/**
 *
 */
package kr.lul.urs.core.command;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 21.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ReadResourceFileCmd extends OwnershipCmd {
  private int id;

  public ReadResourceFileCmd() {
  }

  public ReadResourceFileCmd(int owner, int id) {
    super(owner);
    this.id = id;
  }
}
