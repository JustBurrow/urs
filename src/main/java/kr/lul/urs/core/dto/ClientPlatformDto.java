/**
 *
 */
package kr.lul.urs.core.dto;

import java.time.Instant;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 28.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ClientPlatformDto extends OwnershipDto {
  private int     id;
  private String  code;
  private String  label;
  private String  description;
  private Instant create;
  private Instant update;
}
