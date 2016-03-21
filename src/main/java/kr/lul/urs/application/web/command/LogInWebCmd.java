/**
 *
 */
package kr.lul.urs.application.web.command;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 21.
 */
@Data
public class LogInWebCmd {
  @NotNull
  private String email;
  @NotNull
  private String password;
}
