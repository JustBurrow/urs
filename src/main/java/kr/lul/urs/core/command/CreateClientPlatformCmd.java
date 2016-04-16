/**
 *
 */
package kr.lul.urs.core.command;

import kr.lul.urs.core.domain.ClientPlatform;
import kr.lul.urs.core.domain.Operator;
import lombok.Data;

/**
 * {@link ClientPlatform}을 만들기위해 필요한 데이터를 가진 오브젝트.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 9.
 */
@Data
public class CreateClientPlatformCmd {
  /**
   * 소유자 ID.
   *
   * @see Operator#getId()
   */
  private int    owner;
  private String code;
  private String label;
  private String description;

  public CreateClientPlatformCmd() {
  }

  public CreateClientPlatformCmd(int owner, String code, String label, String description) {
    this.owner = owner;
    this.code = code;
    this.label = label;
    this.description = description;
  }
}
