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

import kr.lul.urs.core.command.CreateOperatorCmd;
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.dto.OperatorDto;
import kr.lul.urs.core.service.OperatorService;
import kr.lul.urs.core.service.internal.OperatorInternalService;
import kr.lul.urs.spring.tx.util.Return;
import kr.lul.urs.util.Randoms;

/**
 * 프로덕트 관리자와 관련된 데이터를 만드는 유틸리티.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 3.
 */
public abstract class OperatorUtils {
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
   */
  public static Return<OperatorDto> create(OperatorService operatorService) {
    notNull(operatorService);
    return operatorService.create(command());
  }

  /**
   * @param operatorInternalService
   * @return
   */
  public static Operator create(OperatorInternalService operatorInternalService) {
    notNull(operatorInternalService);
    return operatorInternalService.create(command());
  }

  protected OperatorUtils() {
    throw new UnsupportedOperationException();
  }
}
