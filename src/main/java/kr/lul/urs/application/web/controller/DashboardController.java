/**
 *
 */
package kr.lul.urs.application.web.controller;

import static kr.lul.urs.application.api.DashboardApiConfiguration.PREFIX;
import static kr.lul.urs.application.api.DashboardApiConfiguration.SUMMARY;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 대시보드 웹 컨트롤러.
 *
 * @since 2016. 5. 31.
 * @author Just Burrow just.burrow@lul.kr
 */
@RequestMapping(PREFIX)
public interface DashboardController {
  /**
   * 대시보드 요약 정보.
   *
   * @param model
   * @return
   * @since 2016. 5. 31.
   */
  @RequestMapping(SUMMARY)
  public String summary(Model model);
}
