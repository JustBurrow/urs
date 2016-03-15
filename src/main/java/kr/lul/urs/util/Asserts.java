package kr.lul.urs.util;

import static java.lang.String.format;

import java.time.Instant;

/**
 * <code>assert</code>를 보완하는 단정 유틸리티 모음.
 *
 * @author just.burrow@lul.kr
 * @see Conditions
 */
public abstract class Asserts {
  /**
   * 숫자가 0보다 작음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void negative(byte number) throws AssertionException {
    if ((byte) 0 > number) {
      return;
    }
    throw new AssertionException(format("number[%d] is not negative.", number));
  }

  /**
   * 숫자가 0보다 작음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void negative(byte number, String message) throws AssertionException {
    if ((byte) 0 > number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 작음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void negative(short number) throws AssertionException {
    if ((short) 0 > number) {
      return;
    }
    throw new AssertionException(format("number[%d] is not negative.", number));
  }

  /**
   * 숫자가 0보다 작음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void negative(short number, String message) throws AssertionException {
    if ((short) 0 > number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 작음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void negative(int number) throws AssertionException {
    if (0 > number) {
      return;
    }
    throw new AssertionException(format("number[%d] is not negative.", number));
  }

  /**
   * 숫자가 0보다 작음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void negative(int number, String message) throws AssertionException {
    if (0 > number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 작음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void negative(long number) throws AssertionException {
    if (0L > number) {
      return;
    }
    throw new AssertionException(format("number[%d] is not negative.", number));
  }

  /**
   * 숫자가 0보다 작음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void negative(long number, String message) throws AssertionException {
    if (0L > number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 작음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void negative(float number) throws AssertionException {
    if (0.0F > number) {
      return;
    }
    throw new AssertionException(format("number[%f] is not negative.", number));
  }

  /**
   * 숫자가 0보다 작음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void negative(float number, String message) throws AssertionException {
    if (0.0F > number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 작음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void negative(double number) throws AssertionException {
    if (0.0 > number) {
      return;
    }
    throw new AssertionException(format("number[%f] is not negative.", number));
  }

  /**
   * 숫자가 0보다 작음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void negative(double number, String message) throws AssertionException {
    if (0.0 > number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0임을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void zero(byte number) throws AssertionException {
    if ((byte) 0 == number) {
      return;
    }
    throw new AssertionException(format("number[%d] is not zero.", number));
  }

  /**
   * 숫자가 0임을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void zero(byte number, String message) throws AssertionException {
    if ((byte) 0 == number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0임을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void zero(short number) throws AssertionException {
    if ((short) 0 == number) {
      return;
    }
    throw new AssertionException(format("number[%d] is not zero.", number));
  }

  /**
   * 숫자가 0임을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void zero(short number, String message) throws AssertionException {
    if ((short) 0 == number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0임을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void zero(int number) throws AssertionException {
    if (0 == number) {
      return;
    }
    throw new AssertionException(format("number[%d] is not zero.", number));
  }

  /**
   * 숫자가 0임을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void zero(int number, String message) throws AssertionException {
    if (0 == number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0임을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void zero(long number) throws AssertionException {
    if (0L == number) {
      return;
    }
    throw new AssertionException(format("number[%d] is not zero.", number));
  }

  /**
   * 숫자가 0임을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void zero(long number, String message) throws AssertionException {
    if (0L == number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0임을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void zero(float number) throws AssertionException {
    if (0.0F == number) {
      return;
    }
    throw new AssertionException(format("number[%f] is not zero.", number));
  }

  /**
   * 숫자가 0임을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void zero(float number, String message) throws AssertionException {
    if (0.0F == number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0임을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void zero(double number) throws AssertionException {
    if (0.0 == number) {
      return;
    }
    throw new AssertionException(format("number[%f] is not zero.", number));
  }

  /**
   * 숫자가 0임을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void zero(double number, String message) throws AssertionException {
    if (0.0 == number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 큼을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void positive(byte number) throws AssertionException {
    if ((byte) 0 < number) {
      return;
    }
    throw new AssertionException(format("number[%d] is not zero.", number));
  }

  /**
   * 숫자가 0보다 큼을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void positive(byte number, String message) throws AssertionException {
    if ((byte) 0 < number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 큼을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void positive(short number) throws AssertionException {
    if ((short) 0 < number) {
      return;
    }
    throw new AssertionException(format("number[%d] is not zero.", number));
  }

  /**
   * 숫자가 0보다 큼을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void positive(short number, String message) throws AssertionException {
    if ((short) 0 < number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 큼을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void positive(int number) throws AssertionException {
    if (0 < number) {
      return;
    }
    throw new AssertionException(format("number[%d] is not zero.", number));
  }

  /**
   * 숫자가 0보다 큼을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void positive(int number, String message) throws AssertionException {
    if (0 < number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 큼을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void positive(long number) throws AssertionException {
    if (0L < number) {
      return;
    }
    throw new AssertionException(format("number[%d] is not zero.", number));
  }

  /**
   * 숫자가 0보다 큼을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void positive(long number, String message) throws AssertionException {
    if (0L < number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 큼을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void positive(float number) throws AssertionException {
    if (0.0F < number) {
      return;
    }
    throw new AssertionException(format("number[%f] is not zero.", number));
  }

  /**
   * 숫자가 0보다 큼을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void positive(float number, String message) throws AssertionException {
    if (0.0F < number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 큼을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void positive(double number) throws AssertionException {
    if (0.0 < number) {
      return;
    }
    throw new AssertionException(format("number[%f] is not zero.", number));
  }

  /**
   * 숫자가 0보다 큼을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void positive(double number, String message) throws AssertionException {
    if (0.0 < number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 작지 않음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void notNegative(byte number) throws AssertionException {
    if ((byte) 0 <= number) {
      return;
    }
    throw new AssertionException(format("number[%d] is negative.", number));
  }

  /**
   * 숫자가 0보다 작지 않음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void notNegative(byte number, String message) throws AssertionException {
    if ((byte) 0 <= number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 작지 않음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void notNegative(short number) throws AssertionException {
    if ((short) 0 <= number) {
      return;
    }
    throw new AssertionException(format("number[%d] is negative.", number));
  }

  /**
   * 숫자가 0보다 작지 않음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void notNegative(short number, String message) throws AssertionException {
    if ((short) 0 <= number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 작지 않음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void notNegative(int number) throws AssertionException {
    if (0 <= number) {
      return;
    }
    throw new AssertionException(format("number[%d] is negative.", number));
  }

  /**
   * 숫자가 0보다 작지 않음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void notNegative(int number, String message) throws AssertionException {
    if (0 <= number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 작지 않음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void notNegative(long number) throws AssertionException {
    if (0L <= number) {
      return;
    }
    throw new AssertionException(format("number[%d] is negative.", number));
  }

  /**
   * 숫자가 0보다 작지 않음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void notNegative(long number, String message) throws AssertionException {
    if (0L <= number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 작지 않음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void notNegative(float number) throws AssertionException {
    if (0.0F <= number) {
      return;
    }
    throw new AssertionException(format("number[%f] is negative.", number));
  }

  /**
   * 숫자가 0보다 작지 않음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void notNegative(float number, String message) throws AssertionException {
    if (0.0F <= number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 작지 않음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void notNegative(double number) throws AssertionException {
    if (0.0 <= number) {
      return;
    }
    throw new AssertionException(format("number[%f] is negative.", number));
  }

  /**
   * 숫자가 0보다 작지 않음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void notNegative(double number, String message) throws AssertionException {
    if (0.0 <= number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 크지 않음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void notPositive(byte number) throws AssertionException {
    if ((byte) 0 >= number) {
      return;
    }
    throw new AssertionException(format("number[%d] is positive.", number));
  }

  /**
   * 숫자가 0보다 크지 않음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void notPositive(byte number, String message) throws AssertionException {
    if ((byte) 0 >= number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 크지 않음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void notPositive(short number) throws AssertionException {
    if ((short) 0 >= number) {
      return;
    }
    throw new AssertionException(format("number[%d] is positive.", number));
  }

  /**
   * 숫자가 0보다 크지 않음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void notPositive(short number, String message) throws AssertionException {
    if ((short) 0 >= number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 크지 않음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void notPositive(int number) throws AssertionException {
    if (0 >= number) {
      return;
    }
    throw new AssertionException(format("number[%d] is positive.", number));
  }

  /**
   * 숫자가 0보다 크지 않음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void notPositive(int number, String message) throws AssertionException {
    if (0 >= number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 크지 않음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void notPositive(long number) throws AssertionException {
    if (0L >= number) {
      return;
    }
    throw new AssertionException(format("number[%d] is positive.", number));
  }

  /**
   * 숫자가 0보다 크지 않음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void notPositive(long number, String message) throws AssertionException {
    if (0L >= number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 크지 않음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void notPositive(float number) throws AssertionException {
    if (0.0F >= number) {
      return;
    }
    throw new AssertionException(format("number[%f] is positive.", number));
  }

  /**
   * 숫자가 0보다 크지 않음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void notPositive(float number, String message) throws AssertionException {
    if (0.0F >= number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 크지 않음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void notPositive(double number) throws AssertionException {
    if (0.0 >= number) {
      return;
    }
    throw new AssertionException(format("number[%f] is positive.", number));
  }

  /**
   * 숫자가 0보다 크지 않음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void notPositive(double number, String message) throws AssertionException {
    if (0.0 >= number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 범위 안에 들어감을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param min
   *          최소(포함).
   * @param max
   *          최대(미포함).
   * @throws AssertionException
   *           단정 실패.
   */
  public static void in(byte number, byte min, byte max) throws AssertionException {
    if (Conditions.in(number, min, max)) {
      return;
    }
    throw new AssertionException(format("number[%d] not in range [min[%d], max[%d])", number, min, max));
  }

  /**
   * 숫자가 범위 안에 들어감을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param min
   *          최소(포함).
   * @param max
   *          최대(미포함).
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void in(byte number, byte min, byte max, String message) throws AssertionException {
    if (Conditions.in(number, min, max)) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 범위 안에 들어감을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param min
   *          최소(포함).
   * @param max
   *          최대(미포함).
   * @throws AssertionException
   *           단정 실패.
   */
  public static void in(short number, short min, short max) throws AssertionException {
    if (Conditions.in(number, min, max)) {
      return;
    }
    throw new AssertionException(format("number[%d] not in range [min[%d], max[%d])", number, min, max));
  }

  /**
   * 숫자가 범위 안에 들어감을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param min
   *          최소(포함).
   * @param max
   *          최대(미포함).
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void in(short number, short min, short max, String message) throws AssertionException {
    if (Conditions.in(number, min, max)) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 범위 안에 들어감을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param min
   *          최소(포함).
   * @param max
   *          최대(미포함).
   * @throws AssertionException
   *           단정 실패.
   */
  public static void in(int number, int min, int max) throws AssertionException {
    if (Conditions.in(number, min, max)) {
      return;
    }
    throw new AssertionException(format("number[%d] not in range [min[%d], max[%d])", number, min, max));
  }

  /**
   * 숫자가 범위 안에 들어감을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param min
   *          최소(포함).
   * @param max
   *          최대(미포함).
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void in(int number, int min, int max, String message) throws AssertionException {
    if (Conditions.in(number, min, max)) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 범위 안에 들어감을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param min
   *          최소(포함).
   * @param max
   *          최대(미포함).
   * @throws AssertionException
   *           단정 실패.
   */
  public static void in(long number, long min, long max) throws AssertionException {
    if (Conditions.in(number, min, max)) {
      return;
    }
    throw new AssertionException(format("number[%d] not in range [min[%d], max[%d])", number, min, max));
  }

  /**
   * 숫자가 범위 안에 들어감을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param min
   *          최소(포함).
   * @param max
   *          최대(미포함).
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void in(long number, long min, long max, String message) throws AssertionException {
    if (Conditions.in(number, min, max)) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 범위 안에 들어감을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param min
   *          최소(포함).
   * @param max
   *          최대(미포함).
   * @throws AssertionException
   *           단정 실패.
   */
  public static void in(float number, float min, float max) throws AssertionException {
    if (Conditions.in(number, min, max)) {
      return;
    }
    throw new AssertionException(format("number[%f] not in range [min[%f], max[%f])", number, min, max));
  }

  /**
   * 숫자가 범위 안에 들어감을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param min
   *          최소(포함).
   * @param max
   *          최대(미포함).
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void in(float number, float min, float max, String message) throws AssertionException {
    if (Conditions.in(number, min, max)) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 범위 안에 들어감을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param min
   *          최소(포함).
   * @param max
   *          최대(미포함).
   * @throws AssertionException
   *           단정 실패.
   */
  public static void in(double number, double min, double max) throws AssertionException {
    if (Conditions.in(number, min, max)) {
      return;
    }
    throw new AssertionException(format("number[%f] not in range [min[%f], max[%f])", number, min, max));
  }

  /**
   * 숫자가 범위 안에 들어감을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param min
   *          최소(포함).
   * @param max
   *          최대(미포함).
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void in(double number, double min, double max, String message) throws AssertionException {
    if (Conditions.in(number, min, max)) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 작음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void lt(byte number, byte boundary) throws AssertionException {
    if (number < boundary) {
      return;
    }
    throw new AssertionException(format("number[%d] is not less than boundary[%d].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 작음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void lt(byte number, byte boundary, String message) throws AssertionException {
    if (number < boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 작음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void lt(short number, short boundary) throws AssertionException {
    if (number < boundary) {
      return;
    }
    throw new AssertionException(format("number[%d] is not less than boundary[%d].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 작음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void lt(short number, short boundary, String message) throws AssertionException {
    if (number < boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 작음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void lt(int number, int boundary) throws AssertionException {
    if (number < boundary) {
      return;
    }
    throw new AssertionException(format("number[%d] is not less than boundary[%d].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 작음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void lt(int number, int boundary, String message) throws AssertionException {
    if (number < boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 작음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void lt(long number, long boundary) throws AssertionException {
    if (number < boundary) {
      return;
    }
    throw new AssertionException(format("number[%d] is not less than boundary[%d].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 작음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void lt(long number, long boundary, String message) throws AssertionException {
    if (number < boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 작음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void lt(float number, float boundary) throws AssertionException {
    if (number < boundary) {
      return;
    }
    throw new AssertionException(format("number[%f] is not less than boundary[%f].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 작음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void lt(float number, float boundary, String message) throws AssertionException {
    if (number < boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 작음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void lt(double number, double boundary) throws AssertionException {
    if (number < boundary) {
      return;
    }
    throw new AssertionException(format("number[%f] is not less than boundary[%f].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 작음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void lt(double number, double boundary, String message) throws AssertionException {
    if (number < boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 작거나 같음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void le(byte number, byte boundary) throws AssertionException {
    if (number <= boundary) {
      return;
    }
    throw new AssertionException(format("number[%d] is not less than or equal to boundary[%d].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 작거나 같음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void le(byte number, byte boundary, String message) throws AssertionException {
    if (number <= boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 작거나 같음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void le(short number, short boundary) throws AssertionException {
    if (number <= boundary) {
      return;
    }
    throw new AssertionException(format("number[%d] is not less than or equal to boundary[%d].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 작거나 같음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void le(short number, short boundary, String message) throws AssertionException {
    if (number <= boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 작거나 같음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void le(int number, int boundary) throws AssertionException {
    if (number <= boundary) {
      return;
    }
    throw new AssertionException(format("number[%d] is not less than or equal to boundary[%d].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 작거나 같음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void le(int number, int boundary, String message) throws AssertionException {
    if (number <= boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 작거나 같음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void le(long number, long boundary) throws AssertionException {
    if (number <= boundary) {
      return;
    }
    throw new AssertionException(format("number[%d] is not less than or equal to boundary[%d].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 작거나 같음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void le(long number, long boundary, String message) throws AssertionException {
    if (number <= boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 작거나 같음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void le(float number, float boundary) throws AssertionException {
    if (number <= boundary) {
      return;
    }
    throw new AssertionException(format("number[%f] is not less than or equal to boundary[%f].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 작거나 같음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void le(float number, float boundary, String message) throws AssertionException {
    if (number <= boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 작거나 같음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void le(double number, double boundary) throws AssertionException {
    if (number <= boundary) {
      return;
    }
    throw new AssertionException(format("number[%f] is not less than or equal to boundary[%f].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 작거나 같음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void le(double number, double boundary, String message) throws AssertionException {
    if (number <= boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 큼을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void gt(byte number, byte boundary) throws AssertionException {
    if (number > boundary) {
      return;
    }
    throw new AssertionException(format("number[%d] is not greater than boundary[%d].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 큼을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void gt(byte number, byte boundary, String message) throws AssertionException {
    if (number > boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 큼을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void gt(short number, short boundary) throws AssertionException {
    if (number > boundary) {
      return;
    }
    throw new AssertionException(format("number[%d] is not greater than boundary[%d].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 큼을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void gt(short number, short boundary, String message) throws AssertionException {
    if (number > boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 큼을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void gt(int number, int boundary) throws AssertionException {
    if (number > boundary) {
      return;
    }
    throw new AssertionException(format("number[%d] is not greater than boundary[%d].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 큼을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void gt(int number, int boundary, String message) throws AssertionException {
    if (number > boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 큼을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void gt(long number, long boundary) throws AssertionException {
    if (number > boundary) {
      return;
    }
    throw new AssertionException(format("number[%d] is not greater than boundary[%d].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 큼을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void gt(long number, long boundary, String message) throws AssertionException {
    if (number > boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 큼을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void gt(float number, float boundary) throws AssertionException {
    if (number > boundary) {
      return;
    }
    throw new AssertionException(format("number[%f] is not greater than boundary[%f].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 큼을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void gt(float number, float boundary, String message) throws AssertionException {
    if (number > boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 큼을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void gt(double number, double boundary) throws AssertionException {
    if (number > boundary) {
      return;
    }
    throw new AssertionException(format("number[%f] is not greater than boundary[%f].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 큼을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void gt(double number, double boundary, String message) throws AssertionException {
    if (number > boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 크거나 같음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void ge(byte number, byte boundary) throws AssertionException {
    if (number >= boundary) {
      return;
    }
    throw new AssertionException(format("number[%d] is not greater than or equal to boundary[%d].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 크거나 같음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void ge(byte number, byte boundary, String message) throws AssertionException {
    if (number >= boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 크거나 같음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void ge(short number, short boundary) throws AssertionException {
    if (number >= boundary) {
      return;
    }
    throw new AssertionException(format("number[%d] is not greater than or equal to boundary[%d].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 크거나 같음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void ge(short number, short boundary, String message) throws AssertionException {
    if (number >= boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 크거나 같음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void ge(int number, int boundary) throws AssertionException {
    if (number >= boundary) {
      return;
    }
    throw new AssertionException(format("number[%d] is not greater than or equal to boundary[%d].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 크거나 같음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void ge(int number, int boundary, String message) throws AssertionException {
    if (number >= boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 크거나 같음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void ge(long number, long boundary) throws AssertionException {
    if (number >= boundary) {
      return;
    }
    throw new AssertionException(format("number[%d] is not greater than or equal to boundary[%d].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 크거나 같음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void ge(long number, long boundary, String message) throws AssertionException {
    if (number >= boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 크거나 같음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void ge(float number, float boundary) throws AssertionException {
    if (number >= boundary) {
      return;
    }
    throw new AssertionException(format("number[%f] is not greater than or equal to boundary[%f].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 크거나 같음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void ge(float number, float boundary, String message) throws AssertionException {
    if (number >= boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 크거나 같음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void ge(double number, double boundary) throws AssertionException {
    if (number >= boundary) {
      return;
    }
    throw new AssertionException(format("number[%f] is not greater than or equal to boundary[%f].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 크거나 같음을 단정한다.
   *
   * @param number
   *          단정할 숫자.
   * @param boundary
   *          기준값.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void ge(double number, double boundary, String message) throws AssertionException {
    if (number >= boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 인스턴스가 <code>null</code>임을 단정한다.
   *
   * @param instance
   *          단정할 인스턴스.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void isNull(Object instance) throws AssertionException {
    if (null == instance) {
      return;
    }
    throw new AssertionException(format("instance[%s] is not null.", instance.toString()));
  }

  /**
   * 인스턴스가 <code>null</code>임을 단정한다.
   *
   * @param instance
   *          단정할 인스턴스.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void isNull(Object instance, String message) throws AssertionException {
    if (null == instance) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 인스턴스가 <code>null</code>이 아님을 단정한다.
   *
   * @param instance
   *          단정할 인스턴스.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void notNull(Object instance) throws AssertionException {
    if (null != instance) {
      return;
    }
    throw new AssertionException("instance is null.");
  }

  /**
   * 인스턴스가 <code>null</code>이 아님을 단정한다.
   *
   * @param instance
   *          단정할 인스턴스.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void notNull(Object instance, String message) throws AssertionException {
    if (null != instance) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 인스턴스가 특정 타입의 변수에 할당할 수 있는지 단정한다.
   *
   * @param instance
   *          단정할 인스턴스.
   * @param type
   *          할당할 변수의 타입.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void assignable(Object instance, Class<?> type) throws AssertionException {
    if (Conditions.assignable(instance, type)) {
      return;
    }
    throw new AssertionException(
        format("instance[%s] could not assign to variable of type[%s].", instance.toString(), type.getName()));
  }

  /**
   * 인스턴스가 특정 타입의 변수에 할당할 수 있는지 단정한다.
   *
   * @param instance
   *          단정할 인스턴스.
   * @param type
   *          할당할 변수의 타입.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void assignable(Object instance, Class<?> type, String message) throws AssertionException {
    if (Conditions.assignable(instance, type)) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 문자열의 길이가 1 이상임을 단정한다.
   *
   * @param string
   *          단정할 문자열.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void hasLength(CharSequence string) throws AssertionException {
    if (null == string) {
      throw new AssertionException("string is null.");
    } else if (0 == string.length()) {
      throw new AssertionException("string is empty.");
    }
  }

  /**
   * 문자열의 길이가 1 이상임을 단정한다.
   *
   * @param string
   *          단정할 문자열.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void hasLength(CharSequence string, String message) throws AssertionException {
    if (Conditions.hasLength(string)) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 문자열의 길이가 기준 길이와 같음을 단정한다.
   *
   * @param string
   *          단정할 문자열.
   * @param length
   *          기준 길이.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void length(CharSequence string, int length) throws AssertionException {
    if (null == string) {
      throw new IllegalArgumentException("string is null.");
    } else if (0 > length) {
      throw new IllegalArgumentException(format("length[%d] is negative.", length));
    } else if (length > string.length()) {
      throw new AssertionException(
          format("string[%s](%d) is shorter than length[%d]", string, string.length(), length));
    } else if (length < string.length()) {
      throw new AssertionException(
          format("string[%s](%d) is longer than length[%d]", string, string.length(), length));
    }
  }

  /**
   * 문자열의 길이가 기준 길이와 같음을 단정한다.
   *
   * @param string
   *          단정할 문자열.
   * @param length
   *          기준 길이.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void length(CharSequence string, int length, String message) throws AssertionException {
    if (Conditions.length(string, length)) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 문자열의 길이가 기준 범위 내임을 단정한다.
   *
   * @param string
   *          단정할 문자열.
   * @param min
   *          최소 길이(포함).
   * @param max
   *          최대 길이(포함).
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void length(CharSequence string, int min, int max) throws AssertionException {
    if (Conditions.length(string, min, max)) {
      return;
    }
    throw new AssertionException(
        format("length[%d] of string[%s] is not in range [min[%d], max[%d]].", string.length(), string, min, max));
  }

  /**
   * 문자열의 길이가 기준 범위 내임을 단정한다.
   *
   * @param string
   *          단정할 문자열.
   * @param min
   *          최소 길이(포함).
   * @param max
   *          최대 길이(포함).
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void length(CharSequence string, int min, int max, String message) throws AssertionException {
    if (Conditions.length(string, min, max)) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 문자열의 길이가 기준값보다 짧거나 같음을 단정한다.
   *
   * @param string
   *          단정할 문자열.
   * @param length
   *          기준 길이(포함).
   * @throws AssertionException
   *           단정 실패.
   */
  public static void shorter(CharSequence string, int length) throws AssertionException {
    if (Conditions.shorter(string, length)) {
      return;
    }
    throw new AssertionException(
        format("length[%d] of string[%s] is longer than boundary[%d].", string.length(), string, length));
  }

  /**
   * 문자열의 길이가 기준값보다 짧거나 같음을 단정한다.
   *
   * @param string
   *          단정할 문자열.
   * @param length
   *          기준 길이(포함).
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void shorter(CharSequence string, int length, String message) throws AssertionException {
    if (Conditions.shorter(string, length)) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 문자열의 길이가 기준값보다 길거나 같음을 단정한다.
   *
   * @param string
   *          단정할 문자열.
   * @param length
   *          기준 길이(포함).
   * @throws AssertionException
   *           단정 실패.
   */
  public static void longer(CharSequence string, int length) throws AssertionException {
    if (Conditions.longer(string, length)) {
      return;
    }
    throw new AssertionException(
        format("length[%d] of string[%s] is shorter than boundary[%d].", string.length(), string, length));
  }

  /**
   * 문자열의 길이가 기준값보다 길거나 같음을 단정한다.
   *
   * @param string
   *          단정할 문자열.
   * @param length
   *          기준 길이(포함).
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void longer(CharSequence string, int length, String message) throws AssertionException {
    if (Conditions.longer(string, length)) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 문자열이 정규표현식에 맞는지 단정한다.
   *
   * @param string
   *          단정할 문자열.
   * @param regex
   *          기준 정규표현식.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void matches(CharSequence string, String regex) throws AssertionException {
    if (Conditions.matches(string, regex)) {
      return;
    }
    throw new AssertionException(format("string[%s] does not match to regex[%s].", string, regex));
  }

  /**
   * 문자열이 정규표현식에 맞는지 단정한다.
   *
   * @param string
   *          단정할 문자열.
   * @param regex
   *          기준 정규표현식.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void matches(CharSequence string, String regex, String message) throws AssertionException {
    if (Conditions.matches(string, regex)) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 시각이 기준시각보다 이전임을 단정한다.
   *
   * @param instant
   *          단정할 시각.
   * @param boundary
   *          기준 시각.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void before(Instant instant, Instant boundary) throws AssertionException {
    if (Conditions.before(instant, boundary)) {
      return;
    }
    throw new AssertionException(format("instant[%s] is not before than boundary[%s].", instant, boundary));
  }

  /**
   * 시각이 기준시각보다 이전임을 단정한다.
   *
   * @param instant
   *          단정할 시각.
   * @param boundary
   *          기준 시각.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void before(Instant instant, Instant boundary, String message) throws AssertionException {
    if (Conditions.before(instant, boundary)) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 시각이 기죽시각보다 이후임을 단정한다.
   *
   * @param instant
   *          단정할 시각.
   * @param boundary
   *          기준 시각.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void after(Instant instant, Instant boundary) throws AssertionException {
    if (Conditions.after(instant, boundary)) {
      return;
    }
    throw new AssertionException(format("instant[%s] is not after than boundary[%s].", instant, boundary));
  }

  /**
   * 시각이 기죽시각보다 이후임을 단정한다.
   *
   * @param instant
   *          단정할 시각.
   * @param boundary
   *          기준 시각.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void after(Instant instant, Instant boundary, String message) throws AssertionException {
    if (Conditions.after(instant, boundary)) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 시각이 기준시각보다 이전이 아님을 단정한다.
   *
   * @param instant
   *          단정할 시각.
   * @param boundary
   *          기준 시각.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void notBefore(Instant instant, Instant boundary) throws AssertionException {
    if (Conditions.notBefore(instant, boundary)) {
      return;
    }
    throw new AssertionException(format("instant[%s] is before than boundary[%s].", instant, boundary));
  }

  /**
   * 시각이 기준시각보다 이전이 아님을 단정한다.
   *
   * @param instant
   *          단정할 시각.
   * @param boundary
   *          기준 시각.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void notBefore(Instant instant, Instant boundary, String message) throws AssertionException {
    if (Conditions.notBefore(instant, boundary)) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 시각이 기준시각보다 이후가 아님을 단정한다.
   *
   * @param instant
   *          단정할 시각.
   * @param boundary
   *          기준 시각.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void notAfter(Instant instant, Instant boundary) throws AssertionException {
    if (Conditions.notAfter(instant, boundary)) {
      return;
    }
    throw new AssertionException(format("instant[%s] is after than boundary[%s].", instant, boundary));
  }

  /**
   * 시각이 기준시각보다 이후가 아님을 단정한다.
   *
   * @param instant
   *          단정할 시각.
   * @param boundary
   *          기준 시각.
   * @param message
   *          단정 실패시의 예외 메시지.
   * @throws AssertionException
   *           단정 실패.
   */
  public static void notAfter(Instant instant, Instant boundary, String message) throws AssertionException {
    if (Conditions.notAfter(instant, boundary)) {
      return;
    }
    throw new AssertionException(message);
  }

  protected Asserts() {
    throw new UnsupportedOperationException();
  }
}
