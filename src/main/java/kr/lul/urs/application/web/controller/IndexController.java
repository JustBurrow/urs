/**
 *
 */
package kr.lul.urs.application.web.controller;

import static kr.lul.urs.application.api.IndexApiConfiguration.DOMAIN;
import static kr.lul.urs.application.api.IndexApiConfiguration.INDEX;
import static kr.lul.urs.application.api.IndexApiConfiguration.PREFIX;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 21.
 */
@RequestMapping(PREFIX)
public interface IndexController {
  /**
   * 랜딩 페이지.
   *
   * @param model
   *          모델.
   * @return 뷰 템플릿 이름.
   */
  @RequestMapping({ DOMAIN, INDEX })
  public String index(Model model);
}
