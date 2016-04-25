/**
 *
 */
package kr.lul.urs.core.test;

import static kr.lul.urs.util.Asserts.notNull;

import kr.lul.urs.core.command.CreateResourceFileCmd;
import kr.lul.urs.core.domain.ClientPlatform;
import kr.lul.urs.core.domain.ResourceFile;
import kr.lul.urs.core.domain.entity.ResourceFileEntity;
import kr.lul.urs.core.service.internal.OwnershipException;
import kr.lul.urs.core.service.internal.ResourceFileInternalService;
import kr.lul.urs.util.Randoms;
import kr.lul.urs.util.Strings;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 17.
 */
public abstract class ResourceFileUtils {
  /**
   * 클라이언트의 리소스 파일을 만들 수 있는 임의의 명령을 만든다.
   * 기존 {@link ResourceFile}과 중복되는지 여부는 확인하지 않는다.
   *
   * @param clientPlatform
   * @return
   */
  public static CreateResourceFileCmd createCmd(ClientPlatform clientPlatform) {
    notNull(clientPlatform);

    StringBuilder name = new StringBuilder();
    do {
      name.append('/').append(Strings.from(1, 10, 'a', 'z'));
    } while (1 > Randoms.notNegative(10));

    return new CreateResourceFileCmd(clientPlatform.getOwner().getId(), clientPlatform.getId(), name.toString());
  }

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
    notNull(resourceFileInternalService);

    CreateResourceFileCmd cmd;
    do {
      cmd = createCmd(clientPlatform);
    } while (resourceFileInternalService.isExists(clientPlatform, cmd.getName()));

    ResourceFile resourceFile = resourceFileInternalService.create(cmd);

    return (ResourceFileEntity) resourceFile;
  }

  protected ResourceFileUtils() {
    throw new UnsupportedOperationException();
  }
}
