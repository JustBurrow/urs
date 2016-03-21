/**
 *
 */
package kr.lul.urs.application.web.command;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * 계정 생성 폼 커맨드.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 21.
 */
@Data
public class CreateOperatorWebCmd {
  @NotNull
  private String email;
  @NotNull
  private String password;
  @NotNull
  private String password2;
}
