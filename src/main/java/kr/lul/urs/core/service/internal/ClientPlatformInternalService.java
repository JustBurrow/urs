/**
 *
 */
package kr.lul.urs.core.service.internal;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.lul.urs.application.configuration.InjectionConstants.Beans;
import kr.lul.urs.core.command.CreateClientPlatformCmd;
import kr.lul.urs.core.domain.ClientPlatform;

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
}
