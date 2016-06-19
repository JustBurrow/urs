/**
 *
 */
package kr.lul.urs.application.web.controller;

import static kr.lul.urs.util.Asserts.notNull;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 21.
 */
@Controller
class IndexControllerImpl implements IndexController {
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>IndexController
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public String index(final Model model) {
    notNull(model);

    return "index/index";
  }
}
