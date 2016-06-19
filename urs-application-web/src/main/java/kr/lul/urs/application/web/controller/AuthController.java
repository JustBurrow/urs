/**
 *
 */
package kr.lul.urs.application.web.controller;

import static kr.lul.urs.application.api.AuthApiConfiguration.LOGIN_SPEC;
import static kr.lul.urs.application.api.AuthApiConfiguration.PREFIX;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 운영자의 인증과 관련되 API 묶음.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 21.
 */
@RequestMapping(PREFIX)
public interface AuthController {
  /**
   * 로그인 폼.
   *
   * @param model
   * @return
   */
  @RequestMapping(LOGIN_SPEC)
  public String loginForm(Model model);
}
