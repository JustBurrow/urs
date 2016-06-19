/**
 *
 */
package kr.lul.urs.application.web.controller;

import static kr.lul.urs.util.Asserts.notNull;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import kr.lul.urs.application.web.request.LogInReq;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 21.
 */
@Controller
class AuthControllerImpl implements AuthController {
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>AuthController
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public String loginForm(final Model model) {
    notNull(model);

    model.addAttribute("req", new LogInReq());

    return "auth/login";
  }
}
