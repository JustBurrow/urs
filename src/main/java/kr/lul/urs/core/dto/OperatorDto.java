/**
 *
 */
package kr.lul.urs.core.dto;

import java.time.Instant;

import kr.lul.urs.spring.jpa.timestamp.Updatable;
import lombok.Data;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 21.
 */
@Data
public class OperatorDto implements Updatable {
  private int     id;
  private String  email;
  private Instant create;
  private Instant update;
}
