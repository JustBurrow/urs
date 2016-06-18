/**
 *
 */
package kr.lul.urs.core.service.internal;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.lul.urs.application.configuration.InjectionConstants.Beans;
import kr.lul.urs.core.command.CreateClientPlatformCmd;
import kr.lul.urs.core.command.ReadClientPlatformCmd;
import kr.lul.urs.core.domain.ClientPlatform;
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.service.context.UpdateAgentPlatformCtx;

/**
 * {@link ClientPlatform}을 다루는 내부 플랫폼.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 9.
 */
@Transactional(transactionManager = Beans.NAME_TRANSACTION_MANAGER, propagation = Propagation.MANDATORY)
public interface ClientPlatformInternalService {
  /**
   * 새로운 {@link ClientPlatform}을 만든다.
   *
   * @param cmd
   * @return
   */
  public ClientPlatform create(CreateClientPlatformCmd cmd);

  /**
   * @param id
   * @return
   */
  public ClientPlatform read(int id);

  /**
   * @param cmd
   * @return
   * @throws OwnershipException
   * @since 2016. 5. 5.
   */
  public ClientPlatform read(ReadClientPlatformCmd cmd) throws OwnershipException;

  /**
   * @return ID로 오름차순 정렬.
   * @since 2016. 5. 5.
   */
  public List<ClientPlatform> list();

  /**
   * 지정한 운영자의 플랫폼을 ID 순서로 반환한다.
   *
   * @param ctx
   *          플랫폼을 조회하려는 운영자.
   * @return ID 오름차순 정렬한 목록. 없으면 <code>null</code>.
   * @since 2016. 6. 9.
   */
  public List<ClientPlatform> list(Operator owner);

  /**
   * 지정한 정보로 플랫폼 정보를 변경한다.
   *
   * @param ctx
   * @return
   * @since 2016. 6. 13.
   */
  public ClientPlatform update(ClientPlatform platform, UpdateAgentPlatformCtx ctx);
}
