/**
 *
 */
package kr.lul.urs.application.web.controller;

import static kr.lul.urs.application.api.OperatorApiConfiguration.PREFIX;
import static kr.lul.urs.application.api.OperatorApiConfiguration.SIGN_UP;
import static kr.lul.urs.application.api.OperatorApiConfiguration.SIGN_UP_SPEC;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.lul.urs.application.web.request.CreateOperatorReq;

/**
 * 프로덕트 운영자 계정과 관련된 API 묶음.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 21.
 */
@RequestMapping(PREFIX)
public interface OperatorController {
  /**
   * 계정 등록 폼 출력.
   *
   * @param model
   * @return
   */
  @RequestMapping(SIGN_UP_SPEC)
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
  @RequestMapping(value = SIGN_UP, method = POST)
  public String signup(CreateOperatorReq req, BindingResult bind, Model model);
}
