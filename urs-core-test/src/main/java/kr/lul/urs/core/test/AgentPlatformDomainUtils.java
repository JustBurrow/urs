/**
 *
 */
package kr.lul.urs.core.test;

import static kr.lul.urs.util.Asserts.notNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;

import kr.lul.urs.core.domain.AgentPlatform;
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.domain.entity.AgentPlatformEntity;
import kr.lul.urs.core.service.context.CreateAgentPlatformCtx;
import kr.lul.urs.core.service.internal.AgentPlatformInternalService;
import kr.lul.urs.util.Randoms;
import kr.lul.urs.util.Strings;

/**
 * 도메인 레이어 이하(도메인, 인터널 서비스)의 레이어에서 {@link AgentPlatform}을 사용하기 위한 테스트 유틸리티.
 *
 * @since 2016. 5. 16.
 * @author Just Burrow just.burrow@lul.kr
 */
public abstract class AgentPlatformDomainUtils {
  private static final Logger log = LoggerFactory.getLogger(AgentPlatformDomainUtils.class);

  /**
   * @param owner
   * @return
   * @since 2016. 6. 20.
   */
  public static CreateAgentPlatformCtx createContext(Operator owner) {
    notNull(owner, "owner");

    String code = Strings.from(2, 5, 'a', 'z');
    String label = "Platform #" + Randoms.positive();
    String description = "test description. #" + Randoms.positive();

    return new CreateAgentPlatformCtx(owner, code, label, description);
  }

  /**
   * 임의의 DO를 만든다.
   *
   * @param owner
   * @param agentPlatformInternalService
   * @return
   */
  public static AgentPlatformEntity create(Operator owner, AgentPlatformInternalService agentPlatformInternalService) {
    notNull(owner);
    notNull(agentPlatformInternalService);

    AgentPlatformEntity platform = null;
    do {
      CreateAgentPlatformCtx ctx = createContext(owner);
      try {
        platform = (AgentPlatformEntity) agentPlatformInternalService.create(ctx);
      } catch (DuplicateKeyException e) {
        platform = null;
        if (log.isDebugEnabled()) {
          log.debug(null == ctx ? "null" : ctx.toString(), e);
        }
      }
    } while (null == platform);

    return platform;
  }

  protected AgentPlatformDomainUtils() {
    throw new UnsupportedOperationException();
  }
}
