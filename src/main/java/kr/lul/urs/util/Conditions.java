package kr.lul.urs.util;

import static java.lang.String.format;

import java.time.Instant;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 변수의 상태를 확하기 위한 유틸리티 메서드 모음.
 *
 * @author just.burrow@lul.kr
 */
public abstract class Conditions {
  /**
   * 숫자가 0보다 작은지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @return 숫자가 0보다 작으면 <code>true</code>.
   */
  public static boolean negative(byte number) {
    return (byte) 0 > number;
  }

  /**
   * 숫자가 0보다 작은지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @return 숫자가 0보다 작으면 <code>true</code>.
   */
  public static boolean negative(short number) {
    return (short) 0 > number;
  }

  /**
   * 숫자가 0보다 작은지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @return 숫자가 0보다 작으면 <code>true</code>.
   */
  public static boolean negative(int number) {
    return 0 > number;
  }

  /**
   * 숫자가 0보다 작은지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @return 숫자가 0보다 작으면 <code>true</code>.
   */
  public static boolean negative(long number) {
    return 0L > number;
  }

  /**
   * 숫자가 0보다 작은지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @return 숫자가 0보다 작으면 <code>true</code>.
   */
  public static boolean negative(float number) {
    return 0.0F > number;
  }

  /**
   * 숫자가 0보다 작은지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @return 숫자가 0보다 작으면 <code>true</code>.
   */
  public static boolean negative(double number) {
    return 0.0 > number;
  }

  /**
   * 숫자가 0인지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @return 숫자가 0이면 <code>true</code>.
   */
  public static boolean zero(byte number) {
    return (byte) 0 == number;
  }

  /**
   * 숫자가 0인지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @return 숫자가 0이면 <code>true</code>.
   */
  public static boolean zero(short number) {
    return (short) 0 == number;
  }

  /**
   * 숫자가 0인지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @return 숫자가 0이면 <code>true</code>.
   */
  public static boolean zero(int number) {
    return 0 == number;
  }

  /**
   * 숫자가 0인지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @return 숫자가 0이면 <code>true</code>.
   */
  public static boolean zero(long number) {
    return 0L == number;
  }

  /**
   * 숫자가 0인지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @return 숫자가 0이면 <code>true</code>.
   */
  public static boolean zero(float number) {
    return 0.0F == number;
  }

  /**
   * 숫자가 0인지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @return 숫자가 0이면 <code>true</code>.
   */
  public static boolean zero(double number) {
    return 0.0 == number;
  }

  /**
   * 숫자가 0보다 큰지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @return 숫자가 0보다 크면 <code>true</code>.
   */
  public static boolean positive(byte number) {
    return (byte) 0 < number;
  }

  /**
   * 숫자가 0보다 큰지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @return 숫자가 0보다 크면 <code>true</code>.
   */
  public static boolean positive(short number) {
    return 0 < number;
  }

  /**
   * 숫자가 0보다 큰지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @return 숫자가 0보다 크면 <code>true</code>.
   */
  public static boolean positive(int number) {
    return 0 < number;
  }

  /**
   * 숫자가 0보다 큰지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @return 숫자가 0보다 크면 <code>true</code>.
   */
  public static boolean positive(long number) {
    return 0L < number;
  }

  /**
   * 숫자가 0보다 큰지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @return 숫자가 0보다 크면 <code>true</code>.
   */
  public static boolean positive(float number) {
    return 0.0F < number;
  }

  /**
   * 숫자가 0보다 큰지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @return 숫자가 0보다 크면 <code>true</code>.
   */
  public static boolean positive(double number) {
    return 0.0 < number;
  }

  /**
   * 숫자가 0보다 작지 않은지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @return 숫자가 0보다 크거나 같으면 <code>true</code>.
   */
  public static boolean notNegative(byte number) {
    return (byte) 0 <= number;
  }

  /**
   * 숫자가 0보다 작지 않은지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @return 숫자가 0보다 크거나 같으면 <code>true</code>.
   */
  public static boolean notNegative(short number) {
    return (short) 0 <= number;
  }

  /**
   * 숫자가 0보다 작지 않은지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @return 숫자가 0보다 크거나 같으면 <code>true</code>.
   */
  public static boolean notNegative(int number) {
    return 0 <= number;
  }

  /**
   * 숫자가 0보다 작지 않은지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @return 숫자가 0보다 크거나 같으면 <code>true</code>.
   */
  public static boolean notNegative(long number) {
    return 0L <= number;
  }

  /**
   * 숫자가 0보다 작지 않은지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @return 숫자가 0보다 크거나 같으면 <code>true</code>.
   */
  public static boolean notNegative(float number) {
    return 0.0F <= number;
  }

  /**
   * 숫자가 0보다 작지 않은지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @return 숫자가 0보다 크거나 같으면 <code>true</code>.
   */
  public static boolean notNegative(double number) {
    return 0.0 <= number;
  }

  /**
   * 숫자가 0보다 크지 않은지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @return 숫자가 0보다 작거나 같으면 <code>true</code>.
   */
  public static boolean notPositive(byte number) {
    return (byte) 0 >= number;
  }

  /**
   * 숫자가 0보다 크지 않은지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @return 숫자가 0보다 작거나 같으면 <code>true</code>.
   */
  public static boolean notPositive(short number) {
    return (short) 0 >= number;
  }

  /**
   * 숫자가 0보다 크지 않은지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @return 숫자가 0보다 작거나 같으면 <code>true</code>.
   */
  public static boolean notPositive(int number) {
    return 0 >= number;
  }

  /**
   * 숫자가 0보다 크지 않은지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @return 숫자가 0보다 작거나 같으면 <code>true</code>.
   */
  public static boolean notPositive(long number) {
    return 0L >= number;
  }

  /**
   * 숫자가 0보다 크지 않은지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @return 숫자가 0보다 작거나 같으면 <code>true</code>.
   */
  public static boolean notPositive(float number) {
    return 0.0F >= number;
  }

  /**
   * 숫자가 0보다 크지 않은지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @return 숫자가 0보다 작거나 같으면 <code>true</code>.
   */
  public static boolean notPositive(double number) {
    return 0.0 >= number;
  }

  /**
   * 숫자가 지정한 범위 안의 숫자인지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @param min
   *          최소값(포함).
   * @param max
   *          최대값(미포함).
   * @return 숫자가 지정한 범위 안이면 <code>true</code>.
   * @throws IllegalArgumentException
   *           최대값이 최소값보다 작거나 같을 때.
   */
  public static boolean in(byte number, byte min, byte max) throws IllegalArgumentException {
    if (max <= min) {
      throw new IllegalArgumentException(format("min[%d] is not less than max[%d].", min, max));
    }
    return min <= number && number < max;
  }

  /**
   * 숫자가 지정한 범위 안의 숫자인지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @param min
   *          최소값(포함).
   * @param max
   *          최대값(미포함).
   * @return 숫자가 지정한 범위 안이면 <code>true</code>.
   * @throws IllegalArgumentException
   *           최대값이 최소값보다 작거나 같을 때.
   */
  public static boolean in(short number, short min, short max) throws IllegalArgumentException {
    if (max <= min) {
      throw new IllegalArgumentException(format("min[%d] is not less than max[%d].", min, max));
    }
    return min <= number && number < max;
  }

  /**
   * 숫자가 지정한 범위 안의 숫자인지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @param min
   *          최소값(포함).
   * @param max
   *          최대값(미포함).
   * @return 숫자가 지정한 범위 안이면 <code>true</code>.
   * @throws IllegalArgumentException
   *           최대값이 최소값보다 작거나 같을 때.
   */
  public static boolean in(int number, int min, int max) throws IllegalArgumentException {
    if (max <= min) {
      throw new IllegalArgumentException(format("min[%d] is not less than max[%d].", min, max));
    }
    return min <= number && number < max;
  }

  /**
   * 숫자가 지정한 범위 안의 숫자인지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @param min
   *          최소값(포함).
   * @param max
   *          최대값(미포함).
   * @return 숫자가 지정한 범위 안이면 <code>true</code>.
   * @throws IllegalArgumentException
   *           최대값이 최소값보다 작거나 같을 때.
   */
  public static boolean in(long number, long min, long max) throws IllegalArgumentException {
    if (max <= min) {
      throw new IllegalArgumentException(format("min[%d] is not less than max[%d].", min, max));
    }
    return min <= number && number < max;
  }

  /**
   * 숫자가 지정한 범위 안의 숫자인지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @param min
   *          최소값(포함).
   * @param max
   *          최대값(미포함).
   * @return 숫자가 지정한 범위 안이면 <code>true</code>.
   * @throws IllegalArgumentException
   *           최대값이 최소값보다 작거나 같을 때.
   */
  public static boolean in(float number, float min, float max) throws IllegalArgumentException {
    if (max <= min) {
      throw new IllegalArgumentException(format("min[%f] is not less than max[%f].", min, max));
    }
    return min <= number && number < max;
  }

  /**
   * 숫자가 지정한 범위 안의 숫자인지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @param min
   *          최소값(포함).
   * @param max
   *          최대값(미포함).
   * @return 숫자가 지정한 범위 안이면 <code>true</code>.
   * @throws IllegalArgumentException
   *           최대값이 최소값보다 작거나 같을 때.
   */
  public static boolean in(double number, double min, double max) throws IllegalArgumentException {
    if (max <= min) {
      throw new IllegalArgumentException(format("min[%f] is not less than max[%f].", min, max));
    }
    return min <= number && number < max;
  }

  /**
   * 숫자가 기준값보다 작은지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값.
   * @return 숫자가 기준값보다 작으면 <code>true</code>.
   */
  public static boolean lt(byte number, byte boundary) {
    return number < boundary;
  }

  /**
   * 숫자가 기준값보다 작은지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값.
   * @return 숫자가 기준값보다 작으면 <code>true</code>.
   */
  public static boolean lt(short number, short boundary) {
    return number < boundary;
  }

  /**
   * 숫자가 기준값보다 작은지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값.
   * @return 숫자가 기준값보다 작으면 <code>true</code>.
   */
  public static boolean lt(int number, int boundary) {
    return number < boundary;
  }

  /**
   * 숫자가 기준값보다 작은지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값.
   * @return 숫자가 기준값보다 작으면 <code>true</code>.
   */
  public static boolean lt(long number, long boundary) {
    return number < boundary;
  }

  /**
   * 숫자가 기준값보다 작은지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값.
   * @return 숫자가 기준값보다 작으면 <code>true</code>.
   */
  public static boolean lt(float number, float boundary) {
    return number < boundary;
  }

  /**
   * 숫자가 기준값보다 작은지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값.
   * @return 숫자가 기준값보다 작으면 <code>true</code>.
   */
  public static boolean lt(double number, double boundary) {
    return number < boundary;
  }

  /**
   * 숫자가 기준값보다 작거나 같은지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값.
   * @return 숫자가 기준값보다 작거나 같으면 <code>true</code>.
   */
  public static boolean le(byte number, byte boundary) {
    return number <= boundary;
  }

  /**
   * 숫자가 기준값보다 작거나 같은지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값.
   * @return 숫자가 기준값보다 작거나 같으면 <code>true</code>.
   */
  public static boolean le(short number, short boundary) {
    return number <= boundary;
  }

  /**
   * 숫자가 기준값보다 작거나 같은지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값.
   * @return 숫자가 기준값보다 작거나 같으면 <code>true</code>.
   */
  public static boolean le(int number, int boundary) {
    return number <= boundary;
  }

  /**
   * 숫자가 기준값보다 작거나 같은지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값.
   * @return 숫자가 기준값보다 작거나 같으면 <code>true</code>.
   */
  public static boolean le(long number, long boundary) {
    return number <= boundary;
  }

  /**
   * 숫자가 기준값보다 작거나 같은지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값.
   * @return 숫자가 기준값보다 작거나 같으면 <code>true</code>.
   */
  public static boolean le(float number, float boundary) {
    return number <= boundary;
  }

  /**
   * 숫자가 기준값보다 작거나 같은지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값.
   * @return 숫자가 기준값보다 작거나 같으면 <code>true</code>.
   */
  public static boolean le(double number, double boundary) {
    return number <= boundary;
  }

  /**
   * 숫자가 기준값보다 큰지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값.
   * @return 숫자가 기준값보다 크면 <code>true</code>.
   */
  public static boolean gt(byte number, byte boundary) {
    return number > boundary;
  }

  /**
   * 숫자가 기준값보다 큰지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값.
   * @return 숫자가 기준값보다 크면 <code>true</code>.
   */
  public static boolean gt(short number, short boundary) {
    return number > boundary;
  }

  /**
   * 숫자가 기준값보다 큰지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값.
   * @return 숫자가 기준값보다 크면 <code>true</code>.
   */
  public static boolean gt(int number, int boundary) {
    return number > boundary;
  }

  /**
   * 숫자가 기준값보다 큰지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값.
   * @return 숫자가 기준값보다 크면 <code>true</code>.
   */
  public static boolean gt(long number, long boundary) {
    return number > boundary;
  }

  /**
   * 숫자가 기준값보다 큰지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값.
   * @return 숫자가 기준값보다 크면 <code>true</code>.
   */
  public static boolean gt(float number, float boundary) {
    return number > boundary;
  }

  /**
   * 숫자가 기준값보다 큰지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값.
   * @return 숫자가 기준값보다 크면 <code>true</code>.
   */
  public static boolean gt(double number, double boundary) {
    return number > boundary;
  }

  /**
   * 숫자가 기준값보다 크거나 같은지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값.
   * @return 숫자가 기준값보다 크거나 같으면 <code>true</code>.
   */
  public static boolean ge(byte number, byte boundary) {
    return number >= boundary;
  }

  /**
   * 숫자가 기준값보다 크거나 같은지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값.
   * @return 숫자가 기준값보다 크거나 같으면 <code>true</code>.
   */
  public static boolean ge(short number, short boundary) {
    return number >= boundary;
  }

  /**
   * 숫자가 기준값보다 크거나 같은지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값.
   * @return 숫자가 기준값보다 크거나 같으면 <code>true</code>.
   */
  public static boolean ge(int number, int boundary) {
    return number >= boundary;
  }

  /**
   * 숫자가 기준값보다 크거나 같은지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값.
   * @return 숫자가 기준값보다 크거나 같으면 <code>true</code>.
   */
  public static boolean ge(long number, long boundary) {
    return number >= boundary;
  }

  /**
   * 숫자가 기준값보다 크거나 같은지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값.
   * @return 숫자가 기준값보다 크거나 같으면 <code>true</code>.
   */
  public static boolean ge(float number, float boundary) {
    return number >= boundary;
  }

  /**
   * 숫자가 기준값보다 크거나 같은지 확인한다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값.
   * @return 숫자가 기준값보다 크거나 같으면 <code>true</code>.
   */
  public static boolean ge(double number, double boundary) {
    return number >= boundary;
  }

  /**
   * 인스턴스가 <code>null</code>인가?
   *
   * @param object
   *          확인할 인스턴스.
   * @return 인스턴스가 <code>null</code>이면 <code>true</code>.
   */
  public static boolean isNull(Object object) {
    return null == object;
  }

  /**
   * 인스턴스가 <code>null</code>이 아닌가?
   *
   * @param object
   *          확인할 인스턴스.
   * @return 인스턴스가 <code>null</code>이 아니면 <code>true</code>.
   */
  public static boolean notNull(Object object) {
    return null != object;
  }

  /**
   * 인스턴스가 정확히 지정한 클래스의 인스턴스인지 확인한다.
   *
   * @param instance
   *          확인할 인스턴스.
   * @param clz
   *          기준 클래스.
   * @return 인스턴스의 타입이 기준 클래스와 일치하면 <code>true</code>.
   * @throws IllegalArgumentException
   *           <ul>
   *           <li>인스턴스가 <code>null</code>일 때.</li>
   *           <li>기준 클래스가 <code>null</code>일 때.</li>
   *           </ul>
   */
  public static boolean instance(Object instance, Class<?> clz) throws IllegalArgumentException {
    if (null == instance) {
      throw new IllegalArgumentException("instance is null.");
    } else if (null == clz) {
      throw new IllegalArgumentException("class is null.");
    }
    return clz.equals(instance.getClass());
  }

  /**
   * 인스턴스가 지정한 클래스로 캐스팅할 수 있는지 확인한다.
   *
   * @param object
   *          확인할 인스턴스.
   * @param clz
   *          확인할 클래스.
   * @return 인스턴스를 지정한 클래스로 캐스팅할 수 있거나 <code>null</code>이면 <code>true</code>.
   * @throws IllegalArgumentException
   *           <code>clz</code>가 <code>null</code>일 때.
   */
  public static boolean assignable(Object object, Class<?> clz) throws IllegalArgumentException {
    if (null == clz) {
      throw new IllegalArgumentException("clz is null.");
    } else if (null == object) {
      return true;
    }
    return clz.isAssignableFrom(object.getClass());
  }

  /**
   * 인스턴스가 다른 인스턴스와 같은지 확인한다.
   *
   * @param instance
   *          확인할 인스턴스.
   * @param that
   *          비교할 인스턴스.
   * @return 같은 인스턴스이면 <code>true</code>.
   * @throws IllegalArgumentException
   *           확인할 인스턴스가 <code>null</code>인 경우.
   */
  public static boolean equal(Object instance, Object that) throws IllegalArgumentException {
    if (null == instance) {
      throw new IllegalArgumentException("instance is null.");
    }

    if (instance == that) {
      return true;
    } else if (null == that) {
      return false;
    } else {
      return instance.equals(that);
    }
  }

  /**
   * 문자열이 길이를 가지고 있는지 확인한다.
   *
   * @param string
   *          문자열.
   * @return 문자열이 <code>null</code>이 아니고 길이가 1이상이면 <code>true</code>.
   */
  public static boolean hasLength(CharSequence string) {
    return null != string && 0 < string.length();
  }

  /**
   * 문자열이 지정한 길이를 가지고 있는지 확인한다.
   *
   * @param string
   *          확인할 문자열.
   * @param length
   *          길이.
   * @return 문자열의 길이가 지정한 길이이면 <code>true</code>.
   * @throws IllegalArgumentException
   *           문자열이 <code>null</code>이거나 길이가 0보다 작을 때.
   */
  public static boolean length(CharSequence string, int length) throws IllegalArgumentException {
    if (null == string) {
      throw new IllegalArgumentException("string is null.");
    } else if (0 > length) {
      throw new IllegalArgumentException(String.format("length[%d] is negative.", length));
    }
    return length == string.length();
  }

  /**
   * 문자열이 지정한 범위의 길이를 가지고 있는지 확이한다.
   *
   * @param string
   *          확인할 문자열.
   * @param min
   *          최소 길이(포함).
   * @param max
   *          최대 길이(포함).
   * @return 문자열이 최소 길이보다 길거나 같고, 최대 길이보다 짧거나 같으면 <code>true</code>.
   * @throws IllegalArgumentException
   */
  public static boolean length(CharSequence string, int min, int max) throws IllegalArgumentException {
    if (null == string) {
      throw new IllegalArgumentException("string is null.");
    } else if (0 > min) {
      throw new IllegalArgumentException(String.format("min[%d] is negative.", min));
    } else if (0 > max) {
      throw new IllegalArgumentException(String.format("max[%d] is negative.", max));
    } else if (max <= min) {
      throw new IllegalArgumentException(String.format("max[%d] is less than or equal to min[%d].", max, min));
    }
    return min <= string.length() && string.length() <= max;
  }

  /**
   * 문자열이 지정한 길이보다 짧은지 확인한다.
   *
   * @param string
   *          확인할 문자열.
   * @param length
   *          기준 길이(포함).
   * @return 문자열 길이가 기준보다 짧거나 같다면 <code>true</code>.
   * @throws IllegalArgumentException
   *           문자열이 <code>null</code>이거나 길이가 0보다 작을 때.
   */
  public static boolean shorter(CharSequence string, int length) throws IllegalArgumentException {
    if (null == string) {
      throw new IllegalArgumentException("string is null.");
    } else if (0 > length) {
      throw new IllegalArgumentException(String.format("length[%d] is negative.", length));
    }
    return length >= string.length();
  }

  /**
   * 문자열이 지정한 길이보다 긴지 확인한다.
   *
   * @param string
   *          확인할 문자열.
   * @param length
   *          기준 길이(포함).
   * @return 문자열 길이 기준보다 길거나 같다면 <code>true</code>.
   * @throws IllegalArgumentException
   *           문자열이 <code>null</code>이거나 길이가 0보다 작을 때.
   */
  public static boolean longer(CharSequence string, int length) throws IllegalArgumentException {
    if (null == string) {
      throw new IllegalArgumentException("string is null.");
    } else if (0 > length) {
      throw new IllegalArgumentException(String.format("length[%d] is negative.", length));
    }
    return length <= string.length();
  }

  /**
   * 문자열이 지정한 포맷을 가지고 있는지 확인한다.
   *
   * @param string
   * @param regex
   * @return
   * @throws IllegalArgumentException
   */
  public static boolean matches(CharSequence string, String regex) throws IllegalArgumentException {
    if (null == string) {
      throw new IllegalArgumentException("string is null.");
    } else if (null == regex) {
      throw new IllegalArgumentException("regex is null.");
    }
    try {
      return Pattern.compile(regex).matcher(string).matches();
    } catch (PatternSyntaxException e) {
      throw new IllegalArgumentException(e);
    }
  }

  /**
   * 확인할 시각이 기준 시각보다 이전인지 여부를 반환한다.
   *
   * @param instant
   *          확인할 시각.
   * @param boundary
   *          기준 시각.
   * @return 확인할 시각이 기준 시각보다 빠르면 <code>true</code>.
   * @throws IllegalArgumentException
   *           인자가 <code>null</code>일 때.
   */
  public static boolean before(Instant instant, Instant boundary) {
    if (null == instant) {
      throw new IllegalArgumentException("instant is null.");
    } else if (null == boundary) {
      throw new IllegalArgumentException("boundary is null.");
    } else {
      return 0 > instant.compareTo(boundary);
    }
  }

  /**
   * 확인할 시각이 기준 시각보다 이후지 여부를 반환한다.
   *
   * @param instant
   *          확인할 시각.
   * @param boundary
   *          기준 시각.
   * @return 확인할 시각이 기준 시각보다 느리면 <code>true</code>.
   * @throws IllegalArgumentException
   *           인자가 <code>null</code>일 때.
   */
  public static boolean after(Instant instant, Instant boundary) throws IllegalArgumentException {
    if (null == instant) {
      throw new IllegalArgumentException("instant is null.");
    } else if (null == boundary) {
      throw new IllegalArgumentException("boundary is null.");
    } else {
      return 0 < instant.compareTo(boundary);
    }
  }

  /**
   * 확인할 시각이 기준 시각보다 같거나 이후인지 확인한다.
   *
   * @param instant
   *          확인할 시각.
   * @param boundary
   *          기준시각.
   * @return 확인할 시각이 기준시각보다 같거나 이후이면 <code>true</code>.
   * @throws IllegalArgumentException
   *           인자가 <code>null</code>일 때.
   */
  public static boolean notBefore(Instant instant, Instant boundary) throws IllegalArgumentException {
    if (null == instant) {
      throw new IllegalArgumentException("instant is null.");
    } else if (null == boundary) {
      throw new IllegalArgumentException("boundary is null.");
    } else {
      return 0 <= instant.compareTo(boundary);
    }
  }

  /**
   * 확인할 시각이 기준 시각보다 같거나 이전인지 확인한다.
   *
   * @param instant
   *          확인할 시각.
   * @param boundary
   *          기준시각.
   * @return 확인할 시각이 기준시각보다 같거나 이전이면 <code>true</code>.
   * @throws IllegalArgumentException
   *           인자가 <code>null</code>일 때.
   */
  public static boolean notAfter(Instant instant, Instant boundary) throws IllegalArgumentException {
    if (null == instant) {
      throw new IllegalArgumentException("instant is null.");
    } else if (null == boundary) {
      throw new IllegalArgumentException("boundary is null.");
    } else {
      return 0 >= instant.compareTo(boundary);
    }
  }

  protected Conditions() {
    throw new UnsupportedOperationException();
  }
}
