/**
 *
 */
package kr.lul.urs.application.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.lul.urs.application.api.IndexApiConstants.C;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 21.
 */
@RequestMapping(C.PREFIX)
public interface IndexController {
  /**
   * 랜딩 페이지.
   *
   * @param model
   *          모델.
   * @return 뷰 템플릿 이름.
   */
  @RequestMapping({ C.ROOT, C.INDEX })
  public String index(Model model);
}
