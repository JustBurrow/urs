/**
 *
 */
package kr.lul.urs.core.service.internal;

import static kr.lul.urs.util.Asserts.notNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.lul.urs.core.command.CreateClientPlatformCmd;
import kr.lul.urs.core.dao.ClientPlatformDao;
import kr.lul.urs.core.domain.ClientPlatform;
import kr.lul.urs.core.domain.entity.ClientPlatformEntity;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 9.
 */
@Service
class ClientPlatformInternalServiceImpl implements ClientPlatformInternalService {
  @Autowired
  private OperatorInternalService operatorInternalService;
  @Autowired
  private ClientPlatformDao       clientPlatformDao;

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
      // TODO throw
    }
    ClientPlatform clientPlatform = this.clientPlatformDao.select(id);
    return clientPlatform;
  }
}
