package kr.lul.urs.core.service;

import static kr.lul.urs.util.Asserts.notNull;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.lul.urs.core.command.CreateClientPlatformCmd;
import kr.lul.urs.core.command.OperatorCmd;
import kr.lul.urs.core.command.ReadClientPlatformCmd;
import kr.lul.urs.core.command.UpdateAgentPlatformCmd;
import kr.lul.urs.core.domain.ClientPlatform;
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.dto.ClientPlatformDto;
import kr.lul.urs.core.service.context.UpdateAgentPlatformCtx;
import kr.lul.urs.core.service.converter.ClientPlatformReturnFactory;
import kr.lul.urs.core.service.internal.ClientPlatformInternalService;
import kr.lul.urs.core.service.internal.OwnershipException;
import kr.lul.urs.spring.tx.Return;

@Service
class ClientPlatformServiceImpl extends AbstractService implements ClientPlatformService {
  @Autowired
  private ClientPlatformInternalService clientPlatformInternalService;
  @Autowired
  private ClientPlatformReturnFactory   clientPlatformReturnFactory;

  @PostConstruct
  private void postConstruct() throws Exception {
    this.initializeMapper();
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>ClientPlatformService
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public Return<ClientPlatformDto> create(CreateClientPlatformCmd cmd) {
    notNull(cmd);

    ClientPlatform clientPlatform = this.clientPlatformInternalService.create(cmd);

    return this.clientPlatformReturnFactory.converter(clientPlatform);
  }

  @Override
  public Return<ClientPlatformDto> read(ReadClientPlatformCmd cmd) throws OwnershipException {
    notNull(cmd);
    ClientPlatform clientPlatform = this.clientPlatformInternalService.read(cmd);
    return this.clientPlatformReturnFactory.converter(clientPlatform);
  }

  @Override
  public Return<ClientPlatformDto> read(int id) {
    ClientPlatform clientPlatform = this.clientPlatformInternalService.read(id);

    if (null == clientPlatform) {
      return null;
    } else {
      return this.clientPlatformReturnFactory.converter(clientPlatform);
    }
  }

  @Override
  public Return<List<ClientPlatformDto>> list() {
    List<ClientPlatform> list = this.clientPlatformInternalService.list();

    return this.clientPlatformReturnFactory.converter(list);
  }

  /*
   * (non-Javadoc)
   * @see kr.lul.urs.core.service.ClientPlatformService#list(kr.lul.urs.core.command.OperatorCmd)
   * @since 2016. 6. 8.
   */
  @Override
  public Return<List<ClientPlatformDto>> list(OperatorCmd cmd) {
    notNull(cmd);

    List<ClientPlatform> list = this.clientPlatformInternalService.list(this.getOperator(cmd));

    return this.clientPlatformReturnFactory.converter(list);
  }

  /*
   * (non-Javadoc)
   * @see kr.lul.urs.core.service.ClientPlatformService#update(kr.lul.urs.core.command.UpdateAgentPlatformCmd)
   * @since 2016. 6. 13.
   */
  @Override
  public Return<ClientPlatformDto> update(UpdateAgentPlatformCmd cmd) throws OwnershipException {
    notNull(cmd, "cmd");

    Operator owner = this.getOperator(cmd);
    if (null == owner) {
      throw new OwnershipException("operator does not exist.", cmd.getOwner(), null);
    }

    ClientPlatform platform = this.clientPlatformInternalService.read(cmd.getId());
    if (null == platform) {
      // TODO platform does not exist.
    } else if (!owner.equals(platform.getOwner())) {
      throw new OwnershipException("no permission.", cmd.getOwner(), platform.getOwner().getId());
    }

    UpdateAgentPlatformCtx ctx = this.mapper.map(cmd, UpdateAgentPlatformCtx.class);

    ClientPlatform updated = this.clientPlatformInternalService.update(platform, ctx);

    return this.clientPlatformReturnFactory.converter(updated);
  }
}