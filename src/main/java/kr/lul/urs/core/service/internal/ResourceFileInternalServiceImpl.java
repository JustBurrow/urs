/**
 *
 */
package kr.lul.urs.core.service.internal;

import static java.lang.String.format;
import static kr.lul.urs.util.Asserts.isTrue;
import static kr.lul.urs.util.Asserts.notNull;

import java.io.File;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.lul.urs.application.configuration.InjectionConstants.Properties;
import kr.lul.urs.core.command.CreateResourceFileCmd;
import kr.lul.urs.core.command.ReadResourceFileCmd;
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
  @Value("${" + Properties.KEY_RESOUCE_FILE_STORAGE_DIR + "}")
  private File            storage;

  @Autowired
  private ResourceFileDao resourceFileDao;

  @PostConstruct
  private void postConstruct() {
    isTrue(this.storage.exists());
    isTrue(this.storage.isDirectory());
    isTrue(this.storage.canWrite());
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>ResourceFileInternalService
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public ResourceFile create(CreateResourceFileCmd cmd) throws OwnershipException {
    notNull(cmd);

    Operator owner = this.operatorInternalService.read(cmd.getOwner());
    if (null == owner) {
      // TODO throw
    }
    ClientPlatform clientPlatform = this.clientPlatformInternalService.read(cmd.getClientPlatform());
    if (!owner.equals(clientPlatform.getOwner())) {
      throw new OwnershipException(
          format("requested owner[%s] and client platform owner[%s] are not equal.", cmd.getOwner(),
              clientPlatform.getOwner().toSimpleString()),
          cmd.getOwner(), clientPlatform.getOwner().getId());
    }

    ResourceFileEntity rf = new ResourceFileEntity(clientPlatform, cmd.getName());
    rf = (ResourceFileEntity) this.resourceFileDao.insert(rf);

    return rf;
  }

  @Override
  public ResourceFile read(int id) {
    return this.resourceFileDao.select(id);
  }

  @Override
  public ResourceFile read(ReadResourceFileCmd cmd) throws OwnershipException {
    notNull(cmd);

    ResourceFile resourceFile = this.resourceFileDao.select(cmd.getId());
    if (null == resourceFile) {
      return null;
    } else if (cmd.getOwner() == resourceFile.getOwner().getId()) {
      return resourceFile;
    } else {
      throw new OwnershipException("owner does not match.", cmd.getOwner(), resourceFile.getOwner().getId());
    }
  }

  @Override
  public boolean isExists(ClientPlatform clientPlatform, String name) {
    return this.resourceFileDao.isExists(clientPlatform, name);
  }
}
