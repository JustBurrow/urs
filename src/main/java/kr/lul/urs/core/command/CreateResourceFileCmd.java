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
  private int    platform;
  private String name;

  public CreateResourceFileCmd() {
  }

  public CreateResourceFileCmd(int owner, int platform) {
    super(owner);
    this.platform = platform;
  }

  public CreateResourceFileCmd(int owner, int platform, String name) {
    this(owner, platform);
    this.name = name;
  }
}
