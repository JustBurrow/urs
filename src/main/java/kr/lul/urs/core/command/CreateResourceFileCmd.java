/**
 *
 */
package kr.lul.urs.core.command;

import lombok.Data;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 17.
 */
@Data
public class CreateResourceFileCmd {
  private int    owner;
  private int    clientPlatform;
  private String name;

  public CreateResourceFileCmd() {
  }

  public CreateResourceFileCmd(int owner, int clientPlatform) {
    this.owner = owner;
    this.clientPlatform = clientPlatform;
  }

  public CreateResourceFileCmd(int owner, int clientPlatform, String name) {
    this(owner, clientPlatform);
    this.name = name;
  }
}
