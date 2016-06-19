/**
 *
 */
package kr.lul.urs.core.command;

import lombok.Data;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 21.
 */
@Data
public class CreateOperatorCmd {
  private String email;
  private String password;

  public CreateOperatorCmd() {
  }

  public CreateOperatorCmd(String email, String password) {
    this.setEmail(email);
    this.setPassword(password);
  }
}
