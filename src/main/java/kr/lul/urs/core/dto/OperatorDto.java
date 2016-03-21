/**
 *
 */
package kr.lul.urs.core.dto;

import java.time.Instant;

import lombok.Data;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 21.
 */
@Data
public class OperatorDto {
  private int     id;
  private String  email;
  private Instant create;
}
