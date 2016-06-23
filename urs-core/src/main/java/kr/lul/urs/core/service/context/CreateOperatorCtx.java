/**
 *
 */
package kr.lul.urs.core.service.context;

import lombok.Data;

/**
 * @since 2016. 6. 20.
 * @author Just Burrow just.burrow@lul.kr
 */
@Data
public class CreateOperatorCtx {
  private String email;
  private String password;

  public CreateOperatorCtx() {
  }

  public CreateOperatorCtx(String email, String password) {
    this.email = email;
    this.password = password;
  }
}
