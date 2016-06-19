/**
 *
 */
package kr.lul.urs.core;

import static kr.lul.urs.util.Asserts.notNull;
import static kr.lul.urs.util.Strings.DIGITS;
import static kr.lul.urs.util.Strings.ETCs;
import static kr.lul.urs.util.Strings.LOWER;
import static kr.lul.urs.util.Strings.UPPER;
import static kr.lul.urs.util.Strings.from;

import org.apache.commons.lang3.RandomStringUtils;

import kr.lul.urs.core.command.CreateOperatorCmd;
import kr.lul.urs.core.dto.OperatorDto;
import kr.lul.urs.core.service.OperatorService;
import kr.lul.urs.util.Randoms;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 3.
 */
public abstract class OperatorApiUtils {
  /**
   * 임의의 관리자 데이터 생성 커맨드.
   *
   * @return
   */
  public static CreateOperatorCmd command() {
    final String atom = LOWER + DIGITS + "!#$%&'*+/=?^_`{|}~-";

    StringBuffer email = new StringBuffer(from(1, 'a', 'z')).append(RandomStringUtils.random(Randoms.in(2, 30), atom));
    for (int i = 0; i < 5 && Randoms.bool() && email.length() < 64; i++) {
      email.append('.').append(RandomStringUtils.random(Randoms.in(2, 8), atom));
    }
    email.append('@');
    for (int i = 0; i < 5 && Randoms.bool() && email.length() < 128; i++) {
      email.append(RandomStringUtils.random(Randoms.in(2, 8), atom));
    }
    email.append(RandomStringUtils.random(Randoms.in(2, 3), atom));

    String password = RandomStringUtils.random(Randoms.in(6, 10), LOWER + UPPER + DIGITS + ETCs);

    return new CreateOperatorCmd(email.toString(), password);
  }

  /**
   * @param operatorService
   * @return
   * @since 2016. 5. 16.
   */
  public static OperatorDto create(OperatorService operatorService) {
    notNull(operatorService);

    OperatorDto operator = operatorService.create(command()).value();

    return operator;
  }

  protected OperatorApiUtils() {
    throw new UnsupportedOperationException();
  }
}
