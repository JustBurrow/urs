/**
 *
 */
package kr.lul.urs.application.web.controller;

import static kr.lul.urs.application.api.AgentPlatformApiConstants.C.PREFIX;
import static kr.lul.urs.application.api.AgentPlatformApiConstants.C.READ;
import static kr.lul.urs.application.web.view.AgentPlatformView.TPL_CREATE;
import static kr.lul.urs.application.web.view.AgentPlatformView.TPL_DETAIL;
import static kr.lul.urs.application.web.view.AgentPlatformView.TPL_LIST;
import static kr.lul.urs.application.web.view.AgentPlatformView.TPL_UPDATE;
import static kr.lul.urs.util.Asserts.notNull;
import static kr.lul.urs.util.Asserts.positive;
import static org.springframework.web.util.UriComponentsBuilder.fromPath;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import kr.lul.urs.application.api.AgentPlatformApiConstants.C;
import kr.lul.urs.application.api.AgentPlatformApiConstants.M;
import kr.lul.urs.application.web.request.CreateAgentPlatformReq;
import kr.lul.urs.application.web.request.UpdatAgentPlatformReq;
import kr.lul.urs.application.web.security.OperatorDetails;
import kr.lul.urs.core.command.CreateAgentPlatformCmd;
import kr.lul.urs.core.command.OperatorCmd;
import kr.lul.urs.core.command.ReadAgentPlatformCmd;
import kr.lul.urs.core.command.UpdateAgentPlatformCmd;
import kr.lul.urs.core.dto.AgentPlatformDto;
import kr.lul.urs.core.service.AgentPlatformService;
import kr.lul.urs.core.service.internal.OwnershipException;

/**
 * @since 2016. 6. 2.
 * @author Just Burrow just.burrow@lul.kr
 */
@Controller
class AgentPlatformControllerImpl extends AbstractController implements AgentPlatformController {
  @Autowired
  private AgentPlatformService agentPlatformService;

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>AgentPlatformController
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  /*
   * (non-Javadoc)
   * @see kr.lul.urs.application.web.controller.AgentPlatformController#createForm(org.springframework.ui.Model)
   * @since 2016. 6. 2.
   */
  @Override
  public String createForm(final Model model) {
    notNull(model, "model");

    model.addAttribute("createReq", new CreateAgentPlatformReq());

    return TPL_CREATE;
  }

  /*
   * (non-Javadoc)
   * @see
   * kr.lul.urs.application.web.controller.AgentPlatformController#create(kr.lul.urs.application.web.controller.request.
   * CreateAgentPlatformReq, org.springframework.validation.BindingResult)
   * @since 2016. 6. 2.
   */
  @Override
  public String create(@ModelAttribute(M.CREATE_REQ) final CreateAgentPlatformReq req, final BindingResult binding) {
    notNull(req, "req");
    notNull(binding, "binding");

    OperatorDetails operator = this.currentOperator();

    CreateAgentPlatformCmd cmd = new CreateAgentPlatformCmd(operator.getId(), req.getCode(), req.getLabel(),
        req.getDescription());
    AgentPlatformDto platform = this.agentPlatformService.create(cmd).value();
    return "redirect:" + fromPath(PREFIX + READ).buildAndExpand(platform.getId()).getPath();
  }

  /*
   * (non-Javadoc)
   * @see kr.lul.urs.application.web.controller.AgentPlatformController#detail(int, org.springframework.ui.Model)
   * @since 2016. 6. 2.
   */
  @Override
  public String detail(@PathVariable(M.ID) final int id, final Model model) {
    positive(id, "id");
    notNull(model, "model");

    AgentPlatformDto platform = this.agentPlatformService.read(id).value();

    model.addAttribute("platform", platform);

    return TPL_DETAIL;
  }

  /*
   * (non-Javadoc)
   * @see kr.lul.urs.application.web.controller.AgentPlatformController#list(org.springframework.ui.Model)
   * @since 2016. 6. 2.
   */
  @Override
  public String list(Model model) {
    notNull(model, "model");

    OperatorCmd cmd = new OperatorCmd();
    cmd.setOwner(this.currentOperator().getId());

    List<AgentPlatformDto> platforms = this.agentPlatformService.list(cmd).value();
    model.addAttribute("platforms", platforms);

    return TPL_LIST;
  }

  /*
   * (non-Javadoc)
   * @see kr.lul.urs.application.web.controller.AgentPlatformController#updateForm(int, org.springframework.ui.Model)
   * @since 2016. 6. 2.
   */
  @Override
  public String updateForm(@PathVariable(M.ID) final int id, final Model model) {
    positive(id, "id");
    notNull(model, "model");

    ReadAgentPlatformCmd cmd = new ReadAgentPlatformCmd();
    cmd.setId(id);
    cmd.setOwner(this.currentOperator().getId());

    AgentPlatformDto platform;
    try {
      platform = this.agentPlatformService.read(cmd).value();
    } catch (OwnershipException e) {
      e.printStackTrace();
      throw new RuntimeException(e); // TODO 에러처리, 로그 처리 공통화.
    }

    model.addAttribute("platform", platform);

    UpdatAgentPlatformReq req = new UpdatAgentPlatformReq();
    req.setLabel(platform.getLabel());
    req.setDescription(platform.getDescription());
    model.addAttribute("req", req);

    return TPL_UPDATE;
  }

  /*
   * (non-Javadoc)
   * @see kr.lul.urs.application.web.controller.AgentPlatformController#update(int,
   * kr.lul.urs.application.web.request.UpdatAgentPlatformReq, org.springframework.validation.BindingResult,
   * org.springframework.ui.Model)
   * @since 2016. 6. 10.
   */
  @Override
  public String update(@PathVariable(M.ID) final int id, @ModelAttribute(M.UPDATE_REQ) final UpdatAgentPlatformReq req,
      final BindingResult binding, final Model model) {
    positive(id, "id");
    notNull(req, "req");
    notNull(binding, "binding");
    notNull(model, "model");

    UpdateAgentPlatformCmd cmd = new UpdateAgentPlatformCmd();
    cmd.setId(id);
    cmd.setOwner(this.currentOperator().getId());
    cmd.setLabel(req.getLabel());
    cmd.setDescription(req.getDescription());

    AgentPlatformDto platform;
    try {
      platform = this.agentPlatformService.update(cmd).value();
    } catch (OwnershipException e) {
      throw new RuntimeException(e);
    }
    return "redirect:" + C.PREFIX + "/" + platform.getId();
  }
}
