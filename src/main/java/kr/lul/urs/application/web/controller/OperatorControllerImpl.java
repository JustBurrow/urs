/**
 *
 */
package kr.lul.urs.application.web.controller;

import static kr.lul.urs.util.Asserts.notNull;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import kr.lul.urs.application.web.command.CreateOperatorWebCmd;
import kr.lul.urs.core.command.CreateOperatorCmd;
import kr.lul.urs.core.dto.OperatorDto;
import kr.lul.urs.core.service.OperatorService;
import kr.lul.urs.spring.tx.util.Return;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 21.
 */
@Controller
class OperatorControllerImpl implements OperatorController {
  private static Logger   log = LoggerFactory.getLogger(OperatorController.class);

  @Autowired
  private OperatorService operatorService;

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>OperatorController
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public String signupForm(final Model model) {
    notNull(model);

    model.addAttribute("cmd", new CreateOperatorWebCmd());
    return "operators/signup";
  }

  @Override
  public String signup(final @Valid CreateOperatorWebCmd cmd, final BindingResult bind, final Model model) {
    notNull(cmd);
    notNull(bind);
    notNull(model);

    if (!cmd.getPassword().equals(cmd.getPassword2())) {
      // TODO 에러 추가.
      return "operators/signup";
    }

    CreateOperatorCmd command = new CreateOperatorCmd();
    command.setEmail(cmd.getEmail());
    command.setPassword(cmd.getPassword());
    Return<OperatorDto> operator = this.operatorService.create(command);

    if (log.isDebugEnabled()) {
      log.debug(operator.value().toString());
    }
    return "redirect:/sessions/new";
  }
}
