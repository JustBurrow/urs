/**
 *
 */
package kr.lul.urs.core.command;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 17.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CreateResourceFileCmd extends OwnershipCmd {
  private int    clientPlatform;
  private String name;

  public CreateResourceFileCmd() {
  }

  public CreateResourceFileCmd(int owner, int clientPlatform) {
    super(owner);
    this.clientPlatform = clientPlatform;
  }

  public CreateResourceFileCmd(int owner, int clientPlatform, String name) {
    this(owner, clientPlatform);
    this.name = name;
  }
}
