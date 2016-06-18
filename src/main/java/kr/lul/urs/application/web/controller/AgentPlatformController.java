/**
 *
 */
package kr.lul.urs.application.web.controller;

import static kr.lul.urs.application.api.AgentPlatformApiConfiguration.CREATE;
import static kr.lul.urs.application.api.AgentPlatformApiConfiguration.CREATE_FORM;
import static kr.lul.urs.application.api.AgentPlatformApiConfiguration.LIST;
import static kr.lul.urs.application.api.AgentPlatformApiConfiguration.PREFIX;
import static kr.lul.urs.application.api.AgentPlatformApiConfiguration.READ;
import static kr.lul.urs.application.api.AgentPlatformApiConfiguration.UPDATE;
import static kr.lul.urs.application.api.AgentPlatformApiConfiguration.UPDATE_FORM;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.lul.urs.application.web.request.CreateAgentPlatformReq;
import kr.lul.urs.application.web.request.UpdatAgentPlatformReq;

/**
 * 플렛폼 정보 관리용 콘트롤러.
 *
 * @since 2016. 6. 2.
 * @author Just Burrow just.burrow@lul.kr
 */
@RequestMapping(PREFIX)
public interface AgentPlatformController {
  /**
   * 신규 작성 폼 출력.
   *
   * @param model
   * @return 폼 템플릿 이름.
   * @since 2016. 6. 2.
   */
  @RequestMapping(CREATE_FORM)
  public String createForm(final Model model);

  /**
   * 신규 작성.
   *
   * @param req
   * @param binding
   * @return 새로 생성한 플랫폼 페이지로 리다이렉트.
   * @since 2016. 6. 2.
   */
  @RequestMapping(value = CREATE, method = RequestMethod.POST)
  public String create(@ModelAttribute final CreateAgentPlatformReq req, final BindingResult binding);

  /**
   * 플랫폼 상세 정보 보기.
   *
   * @param id
   *          플랫폼 ID.
   * @param model
   * @return 플랫폼 상세 정보 템플릿 이름.
   * @since 2016. 6. 2.
   */
  @RequestMapping(READ)
  public String detail(@PathVariable("id") final int id, final Model model);

  /**
   * 자신의 전체 플랫폼 보기.
   *
   * @param model
   * @return 플랫폼 목록의 템플릿 이름.
   * @since 2016. 6. 2.
   */
  @RequestMapping(LIST)
  public String list(final Model model);

  /**
   * 플랫폼 수정에 필요한 폼 출력.
   *
   * @param id
   *          플랫폼 ID.
   * @param model
   * @return 플랫폼 수정 폼의 템플릿 이름.
   * @since 2016. 6. 2.
   */
  @RequestMapping(UPDATE_FORM)
  public String updateForm(@PathVariable("id") final int id, final Model model);

  /**
   * 플랫폼 수정.
   *
   * @param id
   *          수정할 플랫폼 ID.
   * @param model
   * @return 수정된 플랫폼 상세 정보페이지로 리다이렉트.
   * @since 2016. 6. 10.
   */
  @RequestMapping(value = UPDATE, method = RequestMethod.PATCH)
  public String update(@PathVariable("id") final int id, @ModelAttribute final UpdatAgentPlatformReq req,
      final BindingResult binding, final Model model);
}
