/**
 *
 */
package kr.lul.urs.core.dto;

import java.time.Instant;

import kr.lul.urs.spring.jpa.timestamp.Updatable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 28.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AgentPlatformDto extends OwnershipDto implements Updatable {
  private int     id;
  private String  code;
  private String  label;
  private String  description;
  private Instant create;
  private Instant update;
}
