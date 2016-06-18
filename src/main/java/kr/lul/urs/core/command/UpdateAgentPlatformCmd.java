/**
 *
 */
package kr.lul.urs.core.command;

import kr.lul.urs.core.domain.AgentPlatform;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @since 2016. 6. 13.
 * @author Just Burrow just.burrow@lul.kr
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateAgentPlatformCmd extends OwnershipCmd {
  /**
   * {@link AgentPlatform#getId()}
   */
  private int    id;
  /**
   * {@link AgentPlatform#getLabel()}
   */
  private String label;
  /**
   * {@link AgentPlatform#getDescription()}
   */
  private String description;
}
