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

import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.service.context.CreateOperatorCtx;
import kr.lul.urs.core.service.internal.OperatorInternalService;
import kr.lul.urs.util.Randoms;

/**
 * 프로덕트 관리자와 관련된 데이터를 만드는 유틸리티.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 3.
 */
public abstract class OperatorDomainUtils {
  private static final Logger log = LoggerFactory.getLogger(OperatorDtoUtils.class);

  public static CreateOperatorCtx createContext() {
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

    return new CreateOperatorCtx(email.toString(), password);
  }

  /**
   * 새로운 {@link Operator}를 생성하고 저장한 후 반환한다.
   *
   * @param operatorInternalService
   * @return
   */
  public static Operator create(OperatorInternalService operatorInternalService) {
    notNull(operatorInternalService);
    Operator operator = null;
    do {
      CreateOperatorCtx ctx = OperatorDomainUtils.createContext();
      try {
        operator = operatorInternalService.create(ctx);
      } catch (DuplicateKeyException e) {
        operator = null;
        if (log.isDebugEnabled()) {
          log.debug(ctx.toString(), e);
        }
      }
    } while (null == operator);
    return operator;
  }

  protected OperatorDomainUtils() {
    throw new UnsupportedOperationException();
  }
}
