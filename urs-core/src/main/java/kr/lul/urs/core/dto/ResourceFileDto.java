/**
 *
 */
package kr.lul.urs.core.dto;

import java.time.Instant;

import kr.lul.urs.spring.jpa.timestamp.Updatable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @since 2016. 5. 18.
 * @author Just Burrow just.burrow@lul.kr
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ResourceFileDto extends OwnershipDto implements Updatable {
  private int     id;
  private int     agentPlatform;
  private int     resourceFile;
  private String  name;
  private int     currentRevision;
  private String  currentSha1;
  private Instant create;
  private Instant update;
}
