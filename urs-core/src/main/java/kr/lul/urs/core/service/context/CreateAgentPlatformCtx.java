/**
 *
 */
package kr.lul.urs.core.service.context;

import kr.lul.urs.core.domain.Operator;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @since 2016. 6. 20.
 * @author Just Burrow just.burrow@lul.kr
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CreateAgentPlatformCtx extends AbstractOwnershipContext {
  private String code;
  private String label;
  private String description;

  public CreateAgentPlatformCtx() {
  }

  public CreateAgentPlatformCtx(Operator owner, String code, String label, String description) {
    super(owner);
    this.code = code;
    this.label = label;
    this.description = description;
  }
}
