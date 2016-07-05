/**
 *
 */
package kr.lul.urs.core.test;

import static kr.lul.urs.util.Asserts.notNull;
import static kr.lul.urs.util.Strings.DIGITS;
import static kr.lul.urs.util.Strings.ETCs;
import static kr.lul.urs.util.Strings.LOWER;
import static kr.lul.urs.util.Strings.UPPER;
import static kr.lul.urs.util.Strings.from;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;

import kr.lul.urs.core.command.CreateOperatorCmd;
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.dto.OperatorDto;
import kr.lul.urs.core.service.OperatorService;
import kr.lul.urs.util.Randoms;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 3.
 */
public abstract class OperatorDtoUtils {
  private static final Logger log = LoggerFactory.getLogger(OperatorDtoUtils.class);

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
   * 새로운 {@link Operator}를 생성하고 저장한 후 반환한다.
   *
   * @param operatorService
   * @return
   * @since 2016. 5. 16.
   */
  public static OperatorDto create(OperatorService operatorService) {
    notNull(operatorService);

    OperatorDto operator = null;
    do {
      CreateOperatorCmd cmd = command();
      try {
        operator = operatorService.create(cmd).value();
      } catch (DuplicateKeyException e) {
        operator = null;
        if (log.isDebugEnabled()) {
          log.debug(null == cmd ? "null" : cmd.toString(), e);
        }
      }
    } while (null == operator);

    return operator;
  }

  protected OperatorDtoUtils() {
    throw new UnsupportedOperationException();
  }
}
