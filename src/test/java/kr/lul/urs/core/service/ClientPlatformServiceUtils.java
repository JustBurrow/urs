/**
 *
 */
package kr.lul.urs.core.service;

import static kr.lul.urs.util.Asserts.notNull;
import static kr.lul.urs.util.Asserts.positive;

import kr.lul.urs.core.ClientPlatformUtils;
import kr.lul.urs.core.dto.ClientPlatformDto;
import kr.lul.urs.core.dto.OperatorDto;
import kr.lul.urs.core.service.ClientPlatformService;
import kr.lul.urs.spring.tx.Return;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 3.
 */
public abstract class ClientPlatformServiceUtils {
  public static Return<ClientPlatformDto> create(OperatorDto owner, ClientPlatformService service) {
    notNull(owner);
    return create(owner.getId(), service);
  }

  public static Return<ClientPlatformDto> create(int owner, ClientPlatformService service) {
    positive(owner);
    notNull(service);

    return service.create(ClientPlatformUtils.createCmd(owner));
  }

  protected ClientPlatformServiceUtils() {
    throw new UnsupportedOperationException();
  }
}
