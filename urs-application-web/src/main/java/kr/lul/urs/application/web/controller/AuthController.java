/**
 *
 */
package kr.lul.urs.application.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.lul.urs.application.api.AuthApiConstants.C;

/**
 * 운영자의 인증과 관련되 API 묶음.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 21.
 */
@RequestMapping(C.PREFIX)
public interface AuthController {
  /**
   * 로그인 폼.
   *
   * @param model
   * @return
   */
  @RequestMapping(C.LOGIN_FORM)
  public String loginForm(Model model);
}
