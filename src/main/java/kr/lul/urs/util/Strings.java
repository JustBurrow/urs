package kr.lul.urs.util;

import static kr.lul.urs.util.Asserts.le;
import static kr.lul.urs.util.Asserts.lt;
import static kr.lul.urs.util.Asserts.notNegative;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 임의의 문자열을 생성하는 유틸리티 클래스.
 *
 * @author just.burrow@lul.kr
 */
public abstract class Strings {
  public static final int    DEFAULT_LENGTH = 32;

  public static final String LOWER          = "abcdefghijklmnopqrstuvwxyz";
  public static final String UPPER          = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  public static final String DIGITS         = "0123456789";
  public static final String ETCs           = "`-=~!@#$%^&*()_+[]\\{}|;':\",./<>?";

  /**
   * 임의의 문자열을 반환한다.
   *
   * @return 기본 길이를 가지는 임의의 문자열.
   * @see #DEFAULT_LENGTH
   */
  public static String random() {
    return random(DEFAULT_LENGTH);
  }

  /**
   * 임의의 문자열을 반환한다.
   *
   * @param length
   *          생성할 문자열의 길이.
   * @return 임의의 문자열.
   * @see RandomStringUtils#random(int)
   */
  public static String random(int length) {
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
   * @see #random(int)
   */
  public static String random(int minLength, int maxLength) {
    notNegative(minLength);
    lt(minLength, maxLength);

    return random(Randoms.in(minLength, maxLength));
  }

  /**
   * 지정한 문자 범위에서, 기본 길이를 가진 임의의 문자열을 반환한다.
   *
   * @param min
   *          시작 문자(포함).
   * @param max
   *          끝 문자(포함)
   * @return 임의의 문자열.
   */
  public static String from(char min, char max) {
    return from(DEFAULT_LENGTH, min, max);
  }

  /**
   * 지정한 문자 범위에서, 기본 길이를 가진 임의의 문자열을 반환한다.
   *
   * @param length
   *          생성할 문자열의 길이.
   * @param min
   *          시작 문자(포함).
   * @param max
   *          끝 문자(포함)
   * @return 임의의 문자열.
   */
  public static String from(final int length, final char min, final char max) {
    notNegative(length);
    notNegative(min);
    le(min, max);

    if (0 == length) {
      return "";
    }

    char[] values = new char[length];
    for (int i = 0; i < length; i++) {
      values[i] = (char) Randoms.in(min, max + 1);
    }
    return new String(values);
  }

  /**
   * 지정한 길이 범위와 문자 범위에서, 기본 길이를 가진 임의의 문자열을 반환한다.
   *
   * @param minLength
   *          최소 길이(포함).
   * @param maxLength
   *          최대 길이(미포함).
   * @param min
   *          시작 문자(포함).
   * @param max
   *          끝 문자(포함)
   * @return 임의의 문자열.
   */
  public static String from(final int minLength, final int maxLength, final char min, final char max) {
    notNegative(minLength);
    lt(minLength, maxLength);

    return from(Randoms.in(minLength, maxLength), min, max);
  }

  public Strings() {
    throw new UnsupportedOperationException();
  }
}
