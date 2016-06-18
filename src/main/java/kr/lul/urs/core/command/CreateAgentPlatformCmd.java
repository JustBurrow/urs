/**
 *
 */
package kr.lul.urs.core.command;

import kr.lul.urs.core.domain.AgentPlatform;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * {@link AgentPlatform}을 만들기위해 필요한 데이터를 가진 오브젝트.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 9.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CreateAgentPlatformCmd extends OwnershipCmd {
  private String code;
  private String label;
  private String description;

  public CreateAgentPlatformCmd() {
  }

  public CreateAgentPlatformCmd(int owner, String code, String label, String description) {
    super(owner);
    this.code = code;
    this.label = label;
    this.description = description;
  }
}
