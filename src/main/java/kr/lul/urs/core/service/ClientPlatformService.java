/**
 *
 */
package kr.lul.urs.core.service;

import static kr.lul.urs.application.configuration.InjectionConstants.Beans.NAME_TRANSACTION_MANAGER;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import kr.lul.urs.core.command.CreateClientPlatformCmd;
import kr.lul.urs.core.command.ReadClientPlatformCmd;
import kr.lul.urs.core.dto.ClientPlatformDto;
import kr.lul.urs.core.service.internal.OwnershipException;
import kr.lul.urs.spring.tx.Return;

/**
 * 어떤 프로덕트가 사용가능한 플랫폼 속성을 다루기 위한 서비스.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 28.
 */
@Transactional(transactionManager = NAME_TRANSACTION_MANAGER, propagation = REQUIRES_NEW)
public interface ClientPlatformService {
  /**
   * @param cmd
   * @return
   * @since 2016. 4. 28.
   */
  public Return<ClientPlatformDto> create(CreateClientPlatformCmd cmd);

  /**
   * @param id
   *          ID
   * @return 없으면 <code>null</code>.
   * @since 2016. 5. 3.
   */
  public Return<ClientPlatformDto> read(int id);

  /**
   * @param cmd
   *          ID와 요청자 정보.
   * @return 없으면 <code>null</code>.
   * @throws OwnershipException
   *           커맨드 오브젝트의 소유자가 권한이 없을 때.
   * @since 2016. 4. 28.
   */
  public Return<ClientPlatformDto> read(ReadClientPlatformCmd cmd) throws OwnershipException;

  /**
   * @return 없으면 빈 리스트.
   * @since 2016. 4. 28.
   */
  public Return<List<ClientPlatformDto>> list();
}
