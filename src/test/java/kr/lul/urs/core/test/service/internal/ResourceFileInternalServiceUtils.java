/**
 *
 */
package kr.lul.urs.core.test.service.internal;

import static kr.lul.urs.core.test.ResourceFileUtils.createCmd;
import static kr.lul.urs.util.Asserts.assignable;
import static kr.lul.urs.util.Asserts.notNull;

import kr.lul.urs.core.command.CreateResourceFileCmd;
import kr.lul.urs.core.domain.ClientPlatform;
import kr.lul.urs.core.domain.ResourceFile;
import kr.lul.urs.core.domain.entity.ClientPlatformEntity;
import kr.lul.urs.core.domain.entity.ResourceFileEntity;
import kr.lul.urs.core.service.internal.OwnershipException;
import kr.lul.urs.core.service.internal.ResourceFileInternalService;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 17.
 */
public abstract class ResourceFileInternalServiceUtils {
  /**
   * 클라이언트의 {@link ResourceFile}을 임의로 만들어 반환한다.
   *
   * @param clientPlatform
   * @param resourceFileInternalService
   * @return
   * @throws OwnershipException
   */
  public static ResourceFileEntity create(ClientPlatform clientPlatform,
      ResourceFileInternalService resourceFileInternalService) throws OwnershipException {
    notNull(clientPlatform);
    assignable(clientPlatform, ClientPlatformEntity.class);
    notNull(resourceFileInternalService);

    CreateResourceFileCmd cmd;
    do {
      cmd = createCmd(clientPlatform.getOwner().getId(), clientPlatform.getId());
    } while (resourceFileInternalService.isExists(clientPlatform, cmd.getName()));

    ResourceFile resourceFile = resourceFileInternalService.create(cmd);

    return (ResourceFileEntity) resourceFile;
  }

  protected ResourceFileInternalServiceUtils() {
    throw new UnsupportedOperationException();
  }
}
