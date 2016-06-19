/**
 *
 */
package kr.lul.urs.core;

import static kr.lul.urs.util.Asserts.notNull;

import kr.lul.urs.core.command.CreateAgentPlatformCmd;
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.domain.entity.AgentPlatformEntity;
import kr.lul.urs.core.service.internal.AgentPlatformInternalService;

/**
 * @since 2016. 5. 16.
 * @author Just Burrow just.burrow@lul.kr
 */
public abstract class AgentPlatformDomainUtils {
  /**
   * 임의의 플랫폼을 만들 수 있는 커맨드를 만든다.
   *
   * @return
   */
  public static CreateAgentPlatformCmd createCmd(Operator owner) {
    notNull(owner);
    return AgentPlatformApiUtils.createCmd(owner.getId());
  }

  /**
   * 임의의 DO를 만든다.
   *
   * @param owner
   * @param agentPlatformInternalService
   * @return
   */
  public static AgentPlatformEntity create(Operator owner,
      AgentPlatformInternalService agentPlatformInternalService) {
    notNull(owner);
    notNull(agentPlatformInternalService);
    // TODO UQ 중복 처리.
    return (AgentPlatformEntity) agentPlatformInternalService.create(createCmd(owner));
  }

  protected AgentPlatformDomainUtils() {
    throw new UnsupportedOperationException();
  }
}
