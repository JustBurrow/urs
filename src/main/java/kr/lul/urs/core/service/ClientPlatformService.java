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
import kr.lul.urs.spring.tx.util.Return;

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
   * @param cmd
   * @return
   * @since 2016. 4. 28.
   */
  public Return<ClientPlatformDto> read(ReadClientPlatformCmd cmd);

  /**
   * @return
   * @since 2016. 4. 28.
   */
  public List<Return<ClientPlatformDto>> list();
}
