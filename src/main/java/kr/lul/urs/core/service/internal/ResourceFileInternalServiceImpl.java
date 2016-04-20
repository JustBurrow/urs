/**
 *
 */
package kr.lul.urs.core.service.internal;

import static kr.lul.urs.util.Asserts.notNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.lul.urs.core.command.CreateResourceFileCmd;
import kr.lul.urs.core.dao.ResourceFileDao;
import kr.lul.urs.core.domain.ClientPlatform;
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.domain.ResourceFile;
import kr.lul.urs.core.domain.entity.ResourceFileEntity;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 17.
 */
@Service
class ResourceFileInternalServiceImpl extends AbstractPropertyDoInternalService implements ResourceFileInternalService {
  @Autowired
  private ResourceFileDao resourceFileDao;

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>ResourceFileInternalService
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public ResourceFile create(CreateResourceFileCmd cmd) {
    notNull(cmd);

    Operator owner = this.operatorInternalService.read(cmd.getOwner());
    if (null == owner) {
      // TODO throw
    }
    ClientPlatform clientPlatform = this.clientPlatformInternalService.read(cmd.getClientPlatform());

    ResourceFileEntity rf = new ResourceFileEntity(clientPlatform, cmd.getName());
    rf = (ResourceFileEntity) this.resourceFileDao.insert(rf);

    return rf;
  }

  @Override
  public boolean isExists(ClientPlatform clientPlatform, String name) {
    return this.resourceFileDao.isExists(clientPlatform, name);
  }
}
