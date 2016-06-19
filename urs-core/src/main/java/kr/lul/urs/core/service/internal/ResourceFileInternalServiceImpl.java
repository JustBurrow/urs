/**
 *
 */
package kr.lul.urs.core.service.internal;

import static java.lang.String.format;
import static kr.lul.urs.core.configuration.InjectionConstants.Properties.KEY_RESOUCE_FILE_STORAGE_DIR;
import static kr.lul.urs.util.Asserts.isTrue;
import static kr.lul.urs.util.Asserts.notNull;

import java.io.File;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.lul.urs.core.command.CreateResourceFileCmd;
import kr.lul.urs.core.command.ReadResourceFileCmd;
import kr.lul.urs.core.dao.ResourceFileDao;
import kr.lul.urs.core.domain.AgentPlatform;
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.domain.ResourceFile;
import kr.lul.urs.core.domain.entity.ResourceFileEntity;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 17.
 */
@Service
class ResourceFileInternalServiceImpl extends AbstractInternalService implements ResourceFileInternalService {
  @Value("${" + KEY_RESOUCE_FILE_STORAGE_DIR + "}")
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
      throw new OwnershipException(format("owner[%d] does not exist.", cmd.getOwner()), cmd.getOwner(), 0);
    }
    AgentPlatform platform = this.agentPlatformInternalService.read(cmd.getPlatform());
    if (!owner.equals(platform.getOwner())) {
      throw new OwnershipException(
          format("requested owner[%s] and agent platform owner[%s] are not equal.", cmd.getOwner(),
              platform.getOwner().toSimpleString()),
          cmd.getOwner(), platform.getOwner().getId());
    }

    ResourceFile rf = new ResourceFileEntity(platform, cmd.getName());
    rf = this.resourceFileDao.insert(rf);

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
  public boolean isExists(AgentPlatform platform, String name) {
    return this.resourceFileDao.isExists(platform, name);
  }

  /*
   * (non-Javadoc)
   * @see kr.lul.urs.core.service.internal.ResourceFileInternalService#list()
   * @since 2016. 5. 23.
   */
  @Override
  public List<ResourceFile> list() {
    List<ResourceFile> list = this.resourceFileDao.list();
    return list;
  }
}
