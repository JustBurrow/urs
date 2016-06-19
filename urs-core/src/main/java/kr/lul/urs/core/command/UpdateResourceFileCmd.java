/**
 *
 */
package kr.lul.urs.core.command;

import java.io.File;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @since 2016. 5. 22.
 * @author Just Burrow just.burrow@lul.kr
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateResourceFileCmd extends OwnershipCmd {
  private int  id;
  private File input;
}
