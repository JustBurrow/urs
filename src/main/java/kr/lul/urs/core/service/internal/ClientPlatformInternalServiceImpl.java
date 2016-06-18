/**
 *
 */
package kr.lul.urs.core.service.internal;

import static kr.lul.urs.util.Asserts.notNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.lul.urs.application.configuration.InjectionConstants.Properties;
import kr.lul.urs.core.command.CreateClientPlatformCmd;
import kr.lul.urs.core.command.ReadClientPlatformCmd;
import kr.lul.urs.core.dao.ClientPlatformDao;
import kr.lul.urs.core.domain.ClientPlatform;
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.domain.entity.ClientPlatformEntity;
import kr.lul.urs.core.repository.ClientPlatformRepository;
import kr.lul.urs.core.service.context.UpdateAgentPlatformCtx;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 9.
 */
@Service
class ClientPlatformInternalServiceImpl implements ClientPlatformInternalService {
  @Value("${" + Properties.KEY_DAO_SAVE_AND_FLUSH + "}")
  private boolean                  saveAndFlush;

  @Autowired
  private OperatorInternalService  operatorInternalService;
  @Autowired
  private ClientPlatformDao        clientPlatformDao;
  @Autowired
  private ClientPlatformRepository clientPlatformRepository;

  private ClientPlatform save(ClientPlatformEntity platform) {
    if (this.saveAndFlush) {
      return this.clientPlatformRepository.saveAndFlush(platform);
    } else {
      return this.clientPlatformRepository.save(platform);
    }
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>ClientPlatformInternalService
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public ClientPlatform create(CreateClientPlatformCmd cmd) {
    notNull(cmd);

    ClientPlatform clientPlatform = new ClientPlatformEntity(this.operatorInternalService.read(cmd.getOwner()),
        cmd.getCode(), cmd.getLabel(), cmd.getDescription());
    clientPlatform = this.clientPlatformDao.insert(clientPlatform);

    return clientPlatform;
  }

  @Override
  public ClientPlatform read(int id) {
    if (0 >= id) {
      return null;
    }
    ClientPlatform clientPlatform = this.clientPlatformDao.select(id);
    return clientPlatform;
  }

  @Override
  public ClientPlatform read(ReadClientPlatformCmd cmd) throws OwnershipException {
    notNull(cmd);

    ClientPlatform clientPlatform = this.clientPlatformDao.select(cmd.getId());
    if (null == clientPlatform) {
      return null;
    } else if (cmd.getOwner() != clientPlatform.getOwner().getId()) {
      throw new OwnershipException("no ownership.", cmd.getOwner(), clientPlatform.getOwner().getId());
    } else {
      return clientPlatform;
    }
  }

  @Override
  public List<ClientPlatform> list() {
    return this.clientPlatformDao.list();
  }

  /*
   * (non-Javadoc)
   * @see kr.lul.urs.core.service.internal.ClientPlatformInternalService#list(kr.lul.urs.core.domain.Operator)
   * @since 2016. 6. 9.
   */
  @Override
  public List<ClientPlatform> list(Operator owner) {
    notNull(owner, "owner");

    return this.clientPlatformDao.list(owner);
  }

  @Override
  public ClientPlatform update(ClientPlatform platform, UpdateAgentPlatformCtx ctx) {
    notNull(ctx, "context");
    notNull(platform, "platform");

    platform.setLabel(ctx.getLabel());
    platform.setDescription(ctx.getDescription());

    ClientPlatform updated = this.save((ClientPlatformEntity) platform);
    return updated;
  }
}
