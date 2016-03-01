package kr.lul.urs.util;

import static kr.lul.urs.util.Asserts.lt;
import static kr.lul.urs.util.Asserts.notNegative;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 임의의 문자열을 생성하는 유틸리티 클래스.
 *
 * @author just.burrow@lul.kr
 */
public abstract class RandomStrings {
  public static final int DEFAULT_LENGTH = 32;

  /**
   * 임의의 문자열을 반환한다.
   *
   * @return 기본 길이를 가지는 임의의 문자열.
   *
   * @see #DEFAULT_LENGTH
   */
  public static String string() {
    return string(DEFAULT_LENGTH);
  }

  /**
   * 임의의 문자열을 반환한다.
   *
   * @param length
   *          생성할 문자열의 길이.
   * @return 임의의 문자열.
   * @see RandomStringUtils#random(int)
   */
  public static String string(int length) {
    notNegative(length);
    if (0 == length) {
      return "";
    } else {
      return RandomStringUtils.random(length);
    }
  }

  /**
   * 범위 내에서 임의의 문자열을 반환한다.
   *
   * @param minLength
   *          최소 길이(포함).
   * @param maxLength
   *          최대 길이(미포함).
   * @return 임의의 문자열.
   * @see Randoms#in(int, int)
   * @see #string(int)
   */
  public static String string(int minLength, int maxLength) {
    notNegative(minLength);
    lt(minLength, maxLength);

    return string(Randoms.in(minLength, maxLength));
  }

  public RandomStrings() {
    throw new UnsupportedOperationException();
  }
}
