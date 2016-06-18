/**
 *
 */
package kr.lul.urs.core;

import static kr.lul.urs.core.ResourceFileApiUtils.createCmd;
import static kr.lul.urs.util.Asserts.assignable;
import static kr.lul.urs.util.Asserts.notNull;

import kr.lul.urs.core.command.CreateResourceFileCmd;
import kr.lul.urs.core.domain.AgentPlatform;
import kr.lul.urs.core.domain.ResourceFile;
import kr.lul.urs.core.domain.entity.AgentPlatformEntity;
import kr.lul.urs.core.domain.entity.ResourceFileEntity;
import kr.lul.urs.core.service.internal.OwnershipException;
import kr.lul.urs.core.service.internal.ResourceFileInternalService;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 17.
 */
public abstract class ResourceFileDomainUtils {
  /**
   * 에이전트(클라이언트)의 {@link ResourceFile}을 임의로 만들어 반환한다.
   *
   * @param platform
   * @param resourceFileInternalService
   * @return
   * @throws OwnershipException
   */
  public static ResourceFileEntity create(AgentPlatform platform,
      ResourceFileInternalService resourceFileInternalService) throws OwnershipException {
    notNull(platform);
    assignable(platform, AgentPlatformEntity.class);
    notNull(resourceFileInternalService);

    CreateResourceFileCmd cmd;
    do {
      cmd = createCmd(platform.getOwner().getId(), platform.getId());
    } while (resourceFileInternalService.isExists(platform, cmd.getName()));

    ResourceFile resourceFile = resourceFileInternalService.create(cmd);

    return (ResourceFileEntity) resourceFile;
  }

  protected ResourceFileDomainUtils() {
    throw new UnsupportedOperationException();
  }
}
