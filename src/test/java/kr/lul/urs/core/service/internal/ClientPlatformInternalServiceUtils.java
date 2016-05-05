/**
 *
 */
package kr.lul.urs.core.service.internal;

import static kr.lul.urs.core.ClientPlatformUtils.createCmd;
import static kr.lul.urs.util.Asserts.notNull;

import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.domain.entity.ClientPlatformEntity;
import kr.lul.urs.core.service.internal.ClientPlatformInternalService;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 8.
 */
public abstract class ClientPlatformInternalServiceUtils {

  /**
   * 임의의 DO를 만든다.
   *
   * @param owner
   * @param clientPlatformInternalService
   * @return
   */
  public static ClientPlatformEntity create(Operator owner,
      ClientPlatformInternalService clientPlatformInternalService) {
    notNull(owner);
    notNull(clientPlatformInternalService);

    return (ClientPlatformEntity) clientPlatformInternalService.create(createCmd(owner));
  }

  protected ClientPlatformInternalServiceUtils() {
    throw new UnsupportedOperationException();
  }
}
