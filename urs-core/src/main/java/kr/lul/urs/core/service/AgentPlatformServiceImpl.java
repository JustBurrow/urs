package kr.lul.urs.core.service;

import static kr.lul.urs.util.Asserts.notNull;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.lul.urs.core.command.CreateAgentPlatformCmd;
import kr.lul.urs.core.command.OperatorCmd;
import kr.lul.urs.core.command.ReadAgentPlatformCmd;
import kr.lul.urs.core.command.UpdateAgentPlatformCmd;
import kr.lul.urs.core.domain.AgentPlatform;
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.dto.AgentPlatformDto;
import kr.lul.urs.core.service.context.CreateAgentPlatformCtx;
import kr.lul.urs.core.service.context.UpdateAgentPlatformCtx;
import kr.lul.urs.core.service.converter.AgentPlatformReturnFactory;
import kr.lul.urs.core.service.internal.AgentPlatformInternalService;
import kr.lul.urs.core.service.internal.OwnershipException;
import kr.lul.urs.spring.tx.Return;

@Service
class AgentPlatformServiceImpl extends AbstractService implements AgentPlatformService {
  @Autowired
  private AgentPlatformInternalService agentPlatformInternalService;
  @Autowired
  private AgentPlatformReturnFactory   agentPlatformReturnFactory;

  @PostConstruct
  private void postConstruct() throws Exception {
    this.initializeMapper();
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>AgentPlatformService
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public Return<AgentPlatformDto> create(CreateAgentPlatformCmd cmd) {
    notNull(cmd);

    CreateAgentPlatformCtx ctx = new CreateAgentPlatformCtx(this.getOperator(cmd), cmd.getCode(), cmd.getLabel(),
        cmd.getDescription());
    AgentPlatform platform = this.agentPlatformInternalService.create(ctx);

    return this.agentPlatformReturnFactory.converter(platform);
  }

  @Override
  public Return<AgentPlatformDto> read(ReadAgentPlatformCmd cmd) throws OwnershipException {
    notNull(cmd);

    Operator owner = this.getOperator(cmd);
    if (null == owner) {
      throw new OwnershipException("owner operator does not exist.");
    }

    AgentPlatform platform = this.agentPlatformInternalService.read(cmd.getId());
    if (null == platform) {
      // TODO exception
    } else if (!platform.getOwner().equals(owner)) {
      throw new OwnershipException("no permission.", cmd.getOwner(), platform.getOwner().getId());
    }

    return this.agentPlatformReturnFactory.converter(platform);
  }

  @Override
  public Return<AgentPlatformDto> read(int id) {
    AgentPlatform agentPlatform = this.agentPlatformInternalService.read(id);

    if (null == agentPlatform) {
      return null;
    } else {
      return this.agentPlatformReturnFactory.converter(agentPlatform);
    }
  }

  /*
   * (non-Javadoc)
   * @see kr.lul.urs.core.service.AgentPlatformService#list(kr.lul.urs.core.command.OperatorCmd)
   * @since 2016. 6. 8.
   */
  @Override
  public Return<List<AgentPlatformDto>> list(OperatorCmd cmd) {
    notNull(cmd);

    List<AgentPlatform> list = this.agentPlatformInternalService.list(this.getOperator(cmd));

    return this.agentPlatformReturnFactory.converter(list);
  }

  /*
   * (non-Javadoc)
   * @see kr.lul.urs.core.service.AgentPlatformService#update(kr.lul.urs.core.command.UpdateAgentPlatformCmd)
   * @since 2016. 6. 13.
   */
  @Override
  public Return<AgentPlatformDto> update(UpdateAgentPlatformCmd cmd) throws OwnershipException {
    notNull(cmd, "cmd");

    Operator owner = this.getOperator(cmd);
    if (null == owner) {
      throw new OwnershipException("operator does not exist.", cmd.getOwner(), null);
    }

    AgentPlatform platform = this.agentPlatformInternalService.read(cmd.getId());
    if (null == platform) {
      // TODO platform does not exist.
    } else if (!owner.equals(platform.getOwner())) {
      throw new OwnershipException("no permission.", cmd.getOwner(), platform.getOwner().getId());
    }

    UpdateAgentPlatformCtx ctx = this.mapper.map(cmd, UpdateAgentPlatformCtx.class);

    AgentPlatform updated = this.agentPlatformInternalService.update(platform, ctx);

    return this.agentPlatformReturnFactory.converter(updated);
  }
}