/**
 *
 */
package kr.lul.urs.core;

import static kr.lul.urs.util.Asserts.notNull;
import static kr.lul.urs.util.Randoms.in;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

import kr.lul.urs.core.command.CreateClientPlatformCmd;
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.dto.OperatorDto;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 3.
 */
public abstract class ClientPlatformUtils {
  /**
   * 임의의 클라이언트 플랫폼을 만들 수 있는 커맨드를 만든다.
   *
   * @param owner
   * @return
   * @since 2016. 4. 28.
   */
  public static CreateClientPlatformCmd createCmd(int owner) {
    String code = randomAlphabetic(in(1, 3)).toLowerCase() + randomAlphanumeric(in(0, 10));
    String label = "Test%" + randomAlphanumeric(in(1, 10));
    String description = "Random Client Platform for test.";
    return new CreateClientPlatformCmd(owner, code, label, description);
  }

  /**
   * 임의의 클라이언트 플랫폼을 만들 수 있는 커맨드를 만든다.
   *
   * @return
   */
  public static CreateClientPlatformCmd createCmd(Operator owner) {
    notNull(owner);
    return createCmd(owner.getId());
  }

  /**
   * 임의의 클라이언트 플랫폼을 만들 수 있는 커맨드를 만든다.
   *
   * @param owner
   * @return
   */
  public static CreateClientPlatformCmd createCmd(OperatorDto owner) {
    notNull(owner);

    return createCmd(owner.getId());
  }

  protected ClientPlatformUtils() {
    throw new UnsupportedOperationException();
  }
}
