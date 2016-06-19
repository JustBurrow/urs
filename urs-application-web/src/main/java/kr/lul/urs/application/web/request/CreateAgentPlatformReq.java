/**
 *
 */
package kr.lul.urs.application.web.request;

import lombok.Data;

/**
 * @since 2016. 6. 2.
 * @author Just Burrow just.burrow@lul.kr
 */
@Data
public class CreateAgentPlatformReq {
  private String code;
  private String label;
  private String description;

  public CreateAgentPlatformReq() {
  }

  public CreateAgentPlatformReq(String code, String label, String description) {
    this.code = code;
    this.label = label;
    this.description = description;
  }
}
