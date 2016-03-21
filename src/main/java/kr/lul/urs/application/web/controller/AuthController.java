/**
 *
 */
package kr.lul.urs.application.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 21.
 */
@RequestMapping("/auth")
public interface AuthController {
  @RequestMapping("/login")
  public String loginForm(Model model);
}
