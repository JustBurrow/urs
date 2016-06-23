/**
 *
 */
package kr.lul.urs.core.service.internal;

import static kr.lul.urs.core.configuration.InjectionConstants.Properties.KEY_RESOUCE_FILE_STORAGE_DIR;
import static kr.lul.urs.util.Asserts.isTrue;
import static kr.lul.urs.util.Asserts.notNull;

import java.io.File;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.lul.urs.core.dao.ResourceFileDao;
import kr.lul.urs.core.domain.AgentPlatform;
import kr.lul.urs.core.domain.ResourceFile;
import kr.lul.urs.core.domain.entity.ResourceFileEntity;
import kr.lul.urs.core.repository.ResourceFileRepository;
import kr.lul.urs.core.service.context.CreateResourceFileCtx;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 17.
 */
@Service
class ResourceFileInternalServiceImpl extends AbstractInternalService implements ResourceFileInternalService {
  @Value("${" + KEY_RESOUCE_FILE_STORAGE_DIR + "}")
  private File                   storage;

  @Autowired
  private ResourceFileDao        resourceFileDao;
  @Autowired
  private ResourceFileRepository resourceFileRepository;

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
  public ResourceFile create(CreateResourceFileCtx ctx) throws OwnershipException {
    notNull(ctx, "ctx");

    if (null == ctx.getOwner()) {
      throw new OwnershipException("no owner.");
    } else if (!ctx.getOwner().equals(ctx.getPlatform().getOwner())) {
      throw new OwnershipException("owner has no permission of platform", ctx.getOwner().getId(),
          ctx.getPlatform().getOwner().getId());
    }

    ResourceFileEntity rf = new ResourceFileEntity(ctx.getPlatform(), ctx.getName());
    if (this.saveAndFlush) {
      rf = this.resourceFileRepository.saveAndFlush(rf);
    } else {
      rf = this.resourceFileRepository.save(rf);
    }

    return rf;
  }

  @Override
  public ResourceFile read(int id) {
    return this.resourceFileDao.select(id);
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
