/**
 *
 */
package kr.lul.urs.application.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

/**
 * @since 2016. 5. 31.
 * @author Just Burrow just.burrow@lul.kr
 */
@Controller
class DashboardControllerImpl implements DashboardController {
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>DashboardController
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  /*
   * (non-Javadoc)
   * @see kr.lul.urs.application.web.controller.DashboardController#summary(org.springframework.ui.Model)
   * @since 2016. 5. 31.
   */
  @Override
  public String summary(Model model) {
    return "dashboard/summary";
  }
}
