/**
 *
 */
package kr.lul.urs.application.web.controller;

import static kr.lul.urs.application.api.AgentPlatformApiConfiguration.PREFIX;
import static kr.lul.urs.application.api.AgentPlatformApiConfiguration.READ;
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

import kr.lul.urs.application.api.AgentPlatformApiConfiguration;
import kr.lul.urs.application.web.request.CreateAgentPlatformReq;
import kr.lul.urs.application.web.request.UpdatAgentPlatformReq;
import kr.lul.urs.application.web.security.OperatorDetails;
import kr.lul.urs.core.command.CreateClientPlatformCmd;
import kr.lul.urs.core.command.OperatorCmd;
import kr.lul.urs.core.command.ReadClientPlatformCmd;
import kr.lul.urs.core.command.UpdateAgentPlatformCmd;
import kr.lul.urs.core.dto.ClientPlatformDto;
import kr.lul.urs.core.service.ClientPlatformService;
import kr.lul.urs.core.service.internal.OwnershipException;

/**
 * @since 2016. 6. 2.
 * @author Just Burrow just.burrow@lul.kr
 */
@Controller
class AgentPlatformControllerImpl extends AbstractController implements AgentPlatformController {
  @Autowired
  private ClientPlatformService agentPlatformService;

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
  public String create(@ModelAttribute final CreateAgentPlatformReq req, final BindingResult binding) {
    notNull(req, "req");
    notNull(binding, "binding");

    OperatorDetails operator = this.currentOperator();

    CreateClientPlatformCmd cmd = new CreateClientPlatformCmd(operator.getId(), req.getCode(), req.getLabel(),
        req.getDescription());
    ClientPlatformDto platform = this.agentPlatformService.create(cmd).value();
    return "redirect:" + fromPath(PREFIX + READ).buildAndExpand(platform.getId()).getPath();
  }

  /*
   * (non-Javadoc)
   * @see kr.lul.urs.application.web.controller.AgentPlatformController#detail(int, org.springframework.ui.Model)
   * @since 2016. 6. 2.
   */
  @Override
  public String detail(@PathVariable("id") final int id, final Model model) {
    positive(id, "id");
    notNull(model, "model");

    ClientPlatformDto platform = this.agentPlatformService.read(id).value();

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

    List<ClientPlatformDto> platforms = this.agentPlatformService.list(cmd).value();
    model.addAttribute("platforms", platforms);

    return TPL_LIST;
  }

  /*
   * (non-Javadoc)
   * @see kr.lul.urs.application.web.controller.AgentPlatformController#updateForm(int, org.springframework.ui.Model)
   * @since 2016. 6. 2.
   */
  @Override
  public String updateForm(@PathVariable("id") final int id, final Model model) {
    positive(id, "id");
    notNull(model, "model");

    ReadClientPlatformCmd cmd = new ReadClientPlatformCmd();
    cmd.setId(id);
    cmd.setOwner(this.currentOperator().getId());

    ClientPlatformDto platform;
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
  public String update(@PathVariable("id") final int id, @ModelAttribute final UpdatAgentPlatformReq req,
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

    ClientPlatformDto platform;
    try {
      platform = this.agentPlatformService.update(cmd).value();
    } catch (OwnershipException e) {
      throw new RuntimeException(e);
    }
    return "redirect:" + AgentPlatformApiConfiguration.PREFIX + "/" + platform.getId();
  }
}
