/**
 *
 */
package kr.lul.urs.core.command;

import lombok.Data;

/**
 * DO의 소유자를 위한 커맨드.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 21.
 */
@Data
public abstract class OwnershipCmd {
  private int owner;

  public OwnershipCmd() {
  }

  public OwnershipCmd(int owner) {
    this.owner = owner;
  }
}
