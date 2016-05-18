/**
 *
 */
package kr.lul.urs.core;

import static kr.lul.urs.util.Asserts.notNull;

import kr.lul.urs.core.command.CreateClientPlatformCmd;
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.domain.entity.ClientPlatformEntity;
import kr.lul.urs.core.service.internal.ClientPlatformInternalService;

/**
 * @since 2016. 5. 16.
 * @author Just Burrow just.burrow@lul.kr
 */
public abstract class ClientPlatformDomainUtils {
  /**
   * 임의의 클라이언트 플랫폼을 만들 수 있는 커맨드를 만든다.
   *
   * @return
   */
  public static CreateClientPlatformCmd createCmd(Operator owner) {
    notNull(owner);
    return ClientPlatformApiUtils.createCmd(owner.getId());
  }

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
    // TODO UQ 중복 처리.
    return (ClientPlatformEntity) clientPlatformInternalService.create(createCmd(owner));
  }

  protected ClientPlatformDomainUtils() {
    throw new UnsupportedOperationException();
  }
}
