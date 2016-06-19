/**
 *
 */
package kr.lul.urs.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;

import kr.lul.urs.core.dto.AgentPlatformDto;
import kr.lul.urs.core.dto.OperatorDto;
import kr.lul.urs.core.service.AgentPlatformService;
import kr.lul.urs.core.service.OperatorService;

/**
 * 도메인 엔티티에 직접 접근하지 않고, DTO 혹은 ID를 기준으로 로직을 실행해 내부적으로만 도메인 엔티티에 접근하는 테스트용.
 * 다음의 레이어에서 주로 사용한다.
 * <ul>
 * <li>{@link kr.lul.urs.core.dto}</li>
 * <li>{@link kr.lul.urs.core.service}</li>
 * </ul>
 *
 * @since 2016. 5. 16.
 * @author Just Burrow just.burrow@lul.kr
 */
public abstract class AbstractApiTest extends AbstractCoreTest {
  @Autowired
  protected OperatorService      operatorService;
  @Autowired
  protected AgentPlatformService agentPlatformService;

  protected OperatorDto          operator;
  protected AgentPlatformDto     platform;

  /**
   * @since 2016. 5. 16.
   */
  protected void setOperatorAsRandom() {
    if (null == this.now) {
      this.setNow();
    }

    this.operator = OperatorApiUtils.create(this.operatorService);
  }

  /**
   * @since 2016. 5. 16.
   */
  protected void setAgentPlatformAsRandom() {
    if (null == this.operator) {
      this.setOperatorAsRandom();
    }

    this.platform = AgentPlatformApiUtils.create(this.operator, this.agentPlatformService);

    assertThat(this.platform).isNotNull();
    assertThat(this.platform.getId()).isGreaterThan(0);
    assertThat(this.platform.getOwner()).isEqualTo(this.operator.getId());
    this.assertTimestamp(this.platform);
  }
}
