/**
 *
 */
package kr.lul.urs.application.web.controller;

import static kr.lul.urs.util.Randoms.in;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

import kr.lul.urs.application.web.request.CreateAgentPlatformReq;

/**
 * @since 2016. 6. 19.
 * @author Just Burrow just.burrow@lul.kr
 */
public abstract class AgentPlatformWebUtils {
  /**
   * @return
   * @since 2016. 6. 7.
   */
  public static CreateAgentPlatformReq createReq() {
    String code = randomAlphabetic(in(1, 3)).toLowerCase() + randomAlphanumeric(in(0, 10));
    String label = "Test%" + randomAlphanumeric(in(1, 10));
    String description = "Random Agent Platform for test.";

    CreateAgentPlatformReq req = new CreateAgentPlatformReq(code, label, description);

    return req;
  }

  protected AgentPlatformWebUtils() {
    throw new UnsupportedOperationException();
  }
}
