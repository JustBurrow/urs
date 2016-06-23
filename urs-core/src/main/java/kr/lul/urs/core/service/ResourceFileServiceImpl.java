/**
 *
 */
package kr.lul.urs.core.service;

import static kr.lul.urs.util.Asserts.notNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.lul.urs.core.command.CreateResourceFileCmd;
import kr.lul.urs.core.command.UpdateResourceFileCmd;
import kr.lul.urs.core.domain.AgentPlatform;
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.domain.ResourceFile;
import kr.lul.urs.core.domain.ResourceFileUpdateException;
import kr.lul.urs.core.dto.ResourceFileDto;
import kr.lul.urs.core.service.context.CreateResourceFileCtx;
import kr.lul.urs.core.service.converter.ResourceFileReturnFactory;
import kr.lul.urs.core.service.internal.OwnershipException;
import kr.lul.urs.core.service.internal.ResourceFileInternalService;
import kr.lul.urs.spring.tx.Return;

/**
 * @since 2016. 5. 20.
 * @author Just Burrow just.burrow@lul.kr
 */
@Service
class ResourceFileServiceImpl extends AbstractService implements ResourceFileService {
  @Autowired
  private ResourceFileInternalService resourceFileInternalService;
  @Autowired
  private ResourceFileReturnFactory   resourceFileReturnFactory;

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>ResourceFileService
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  /*
   * (non-Javadoc)
   * @see kr.lul.urs.core.service.ResourceFileService#create(kr.lul.urs.core.command.CreateResourceFileCmd)
   * @since 2016. 5. 20.
   */
  @Override
  public Return<ResourceFileDto> create(CreateResourceFileCmd cmd) throws OwnershipException {
    notNull(cmd);

    Operator owner = this.getOperator(cmd);
    if (null == owner) {
      throw new OwnershipException("owner does not exist.", cmd.getOwner(), null);
    }

    AgentPlatform platform = this.agentPlatformInternalService.read(cmd.getPlatform());
    if (null == platform) {
      throw new RuntimeException("agent platform does not exist.");
    } else if (!owner.equals(platform.getOwner())) {
      throw null == platform.getOwner()
          ? new OwnershipException("operator has no permission.", owner.getId(), null)
          : new OwnershipException("operator has no permission.", owner.getId(), platform.getOwner().getId());
    }
    CreateResourceFileCtx ctx = new CreateResourceFileCtx(owner, platform, cmd.getName());
    ResourceFile resourceFile = this.resourceFileInternalService.create(ctx);

    return this.resourceFileReturnFactory.converter(resourceFile);
  }

  /*
   * (non-Javadoc)
   * @see kr.lul.urs.core.service.ResourceFileService#read(int)
   * @since 2016. 5. 20.
   */
  @Override
  public Return<ResourceFileDto> read(int id) {
    if (0 >= id) {
      return null;
    }

    ResourceFile resourceFile = this.resourceFileInternalService.read(id);

    return this.resourceFileReturnFactory.converter(resourceFile);
  }

  /*
   * (non-Javadoc)
   * @see kr.lul.urs.core.service.ResourceFileService#list()
   * @since 2016. 5. 23.
   */
  @Override
  public Return<List<ResourceFileDto>> list() {
    List<ResourceFile> list = this.resourceFileInternalService.list();

    return this.resourceFileReturnFactory.converter(list);
  }

  /*
   * (non-Javadoc)
   * @see kr.lul.urs.core.service.ResourceFileService#update(kr.lul.urs.core.command.UpdateResourceFileCmd)
   * @since 2016. 5. 24.
   */
  @Override
  public Return<ResourceFileDto> update(UpdateResourceFileCmd cmd)
      throws OwnershipException, ResourceFileUpdateException {
    notNull(cmd, "cmd is null.");

    ResourceFile resourceFile = this.resourceFileInternalService.read(cmd.getId());
    if (null == resourceFile) {
      // TODO throw
    } else if (cmd.getOwner() != resourceFile.getOwner().getId()) {
      throw new OwnershipException("no permission or resource file does not exist.", cmd.getOwner(),
          resourceFile.getOwner().getId());
    }

    resourceFile.update(cmd.getInput());

    return this.resourceFileReturnFactory.converter(resourceFile);
  }
}
