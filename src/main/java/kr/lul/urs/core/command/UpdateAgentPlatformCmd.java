/**
 *
 */
package kr.lul.urs.core.command;

import kr.lul.urs.core.domain.ClientPlatform;
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
   * {@link ClientPlatform#getId()}
   */
  private int    id;
  /**
   * {@link ClientPlatform#getLabel()}
   */
  private String label;
  /**
   * {@link ClientPlatform#getDescription()}
   */
  private String description;
}
