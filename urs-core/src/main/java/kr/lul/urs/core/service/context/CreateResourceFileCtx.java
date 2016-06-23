/**
 *
 */
package kr.lul.urs.core.service.context;

import kr.lul.urs.core.domain.AgentPlatform;
import kr.lul.urs.core.domain.Operator;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @since 2016. 6. 23.
 * @author Just Burrow just.burrow@lul.kr
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CreateResourceFileCtx extends AbstractOwnershipContext {
  private AgentPlatform platform;
  private String        name;

  public CreateResourceFileCtx() {
  }

  public CreateResourceFileCtx(Operator owner, AgentPlatform platform, String name) {
    super(owner);
    this.platform = platform;
    this.name = name;
  }
}
