/**
 *
 */
package kr.lul.urs.core;

import static kr.lul.urs.util.Asserts.notNull;

import kr.lul.urs.core.command.CreateResourceFileCmd;
import kr.lul.urs.core.domain.ResourceFile;
import kr.lul.urs.core.dto.ClientPlatformDto;
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

  protected ResourceFileApiUtils() {
    throw new UnsupportedOperationException();
  }
}
