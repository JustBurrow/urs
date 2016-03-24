/**
 *
 */
package kr.lul.urs.application.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.lul.urs.application.web.request.CreateOperatorReq;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 21.
 */
@RequestMapping("/operators")
public interface OperatorController {
  /**
   * 계정 등록 폼 출력.
   *
   * @param model
   * @return
   */
  @RequestMapping("/signup")
  public String signupForm(Model model);

  /**
   * 계정 등록 실행.
   *
   * @param req
   *          계정 등록 커맨드 오브젝트.
   * @param bind
   *          리퀘스트의 커맨드 오브젝트 바인딩 결과.
   * @return
   */
  @RequestMapping(method = POST)
  public String signup(CreateOperatorReq req, BindingResult bind, Model model);
}
