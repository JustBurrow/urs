/**
 *
 */
package kr.lul.urs.core;

import static kr.lul.urs.util.Asserts.notNull;
import static kr.lul.urs.util.Asserts.positive;

import kr.lul.urs.core.command.CreateResourceFileCmd;
import kr.lul.urs.core.domain.ResourceFile;
import kr.lul.urs.core.dto.ClientPlatformDto;
import kr.lul.urs.core.dto.ResourceFileDto;
import kr.lul.urs.core.service.ResourceFileService;
import kr.lul.urs.core.service.internal.OwnershipException;
import kr.lul.urs.util.Randoms;
import kr.lul.urs.util.Strings;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 3.
 */
public abstract class ResourceFileApiUtils {
  public static CreateResourceFileCmd createCmd(ClientPlatformDto clientPlatform) {
    return createCmd(clientPlatform.getOwner(), clientPlatform.getId());
  }

  /**
   * 클라이언트의 리소스 파일을 만들 수 있는 임의의 명령을 만든다.
   * 기존 {@link ResourceFile}과 중복되는지 여부는 확인하지 않는다.
   *
   * @param clientPlatform
   * @return
   */
  public static CreateResourceFileCmd createCmd(int owner, int clientPlatform) {
    notNull(clientPlatform);

    StringBuilder name = new StringBuilder();
    do {
      name.append('/').append(Strings.from(1, 10, 'a', 'z'));
    } while (1 > Randoms.notNegative(10));

    return new CreateResourceFileCmd(owner, clientPlatform, name.toString());
  }

  /**
   * @param clientPlatform
   *          생성할 리소스 파일의 플랫폼. 소유자 정보도 여기에서 얻는다.
   * @param resourceFileService
   * @return
   * @throws OwnershipException
   * @since 2016. 5. 20.
   */
  public static ResourceFileDto create(ClientPlatformDto clientPlatform, ResourceFileService resourceFileService)
      throws OwnershipException {
    notNull(clientPlatform);
    positive(clientPlatform.getId(), "id");
    positive(clientPlatform.getOwner(), "owner");
    notNull(resourceFileService);

    return resourceFileService.create(createCmd(clientPlatform)).value();
  }

  protected ResourceFileApiUtils() {
    throw new UnsupportedOperationException();
  }
}
