/**
 *
 */
package kr.lul.urs.util;

import static kr.lul.urs.util.Randoms.in;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import java.net.URL;

/**
 * {@link URL}
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 28.
 */
public abstract class EMails {
  /**
   * 임의의 E메일 주소를 만든다.
   *
   * @return 임의의 E메일 주소.
   */
  public static String random() {
    String user = randomAlphabetic(in(3, 20));
    String host = randomAlphabetic(in(2, 20)) + "." + randomAlphabetic(in(2, 5));
    return String.format("%s@%s", user, host).toLowerCase();
  }

  protected EMails() {
    throw new UnsupportedOperationException();
  }
}
