/**
 *
 */
package kr.lul.urs.core.service;

import static kr.lul.urs.core.configuration.InjectionConstants.Beans.NAME_TRANSACTION_MANAGER;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import kr.lul.urs.core.command.CreateAgentPlatformCmd;
import kr.lul.urs.core.command.OperatorCmd;
import kr.lul.urs.core.command.ReadAgentPlatformCmd;
import kr.lul.urs.core.command.UpdateAgentPlatformCmd;
import kr.lul.urs.core.dto.AgentPlatformDto;
import kr.lul.urs.core.service.internal.OwnershipException;
import kr.lul.urs.spring.tx.Return;

/**
 * 어떤 프로덕트가 사용가능한 플랫폼 속성을 다루기 위한 서비스.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 28.
 */
@Transactional(transactionManager = NAME_TRANSACTION_MANAGER, propagation = REQUIRES_NEW)
public interface AgentPlatformService {
  /**
   * @param cmd
   * @return
   * @since 2016. 4. 28.
   */
  public Return<AgentPlatformDto> create(CreateAgentPlatformCmd cmd);

  /**
   * @param id
   *          ID
   * @return 없으면 <code>null</code>.
   * @since 2016. 5. 3.
   */
  public Return<AgentPlatformDto> read(int id);

  /**
   * 특정 관리자의 요청으로 플랫폼 정보를 읽는다. 만약, 요청한 관리자가 해당 플랫폼의 소유자가 아닐 경우, 에러가 발생한다.
   *
   * @param cmd
   *          ID와 요청자 정보.
   * @return 없으면 <code>null</code>.
   * @throws OwnershipException
   *           커맨드 오브젝트의 소유자가 권한이 없을 때.
   * @since 2016. 4. 28.
   */
  public Return<AgentPlatformDto> read(ReadAgentPlatformCmd cmd) throws OwnershipException;

  /**
   * 관리자의 플랫폼 목록.
   *
   * @param cmd
   * @return
   * @since 2016. 6. 8.
   */
  public Return<List<AgentPlatformDto>> list(OperatorCmd cmd);

  /**
   * 에이전트 플랫폼의 정보를 수정한다.
   *
   * @param cmd
   * @return
   * @throws OwnershipException
   * @since 2016. 6. 13.
   */
  public Return<AgentPlatformDto> update(UpdateAgentPlatformCmd cmd) throws OwnershipException;
}
