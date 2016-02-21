package kr.lul.urs.util;

import static java.lang.String.format;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang3.reflect.ConstructorUtils;

/**
 * 간단한 조건에 따라 지정한 예외를 발생하는 유틸리티.
 *
 * @author just.burrow@lul.kr
 */
public abstract class ConditionalExceptions {
  /**
   * 예외 인스턴스 생성 로직이 복잡할 경우 사용할 람다.
   *
   * @author just.burrow@lul.kr
   */
  public static interface Pitcher<E extends Throwable> {
    /**
     * 얘외 인스턴스를 던진다.
     *
     * @throws E
     */
    public void pitch() throws E;
  }

  /**
   * 예외 인스턴스를 만들어 던진다.
   *
   * @param exception
   *          예외 클래스.
   * @param args
   *          생성자 메서드용 인자.
   * @throws E
   *           숫자가 0보다 크거나 같고 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           예외 인스턴스 생성에 실패한 경우.
   */
  private static <E extends Throwable> void pitch(Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (null == exception) {
      throw new IllegalArgumentException("exception type is null.");
    }
    try {
      throw ConstructorUtils.invokeConstructor(exception, args);
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
      throw new IllegalArgumentException(e);
    }
  }

  /**
   * 숫자가 0보다 작지 않으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param exception
   *          예외 클래스.
   * @param args
   *          생성자 메서드용 인자.
   * @throws E
   *           숫자가 0보다 크거나 같고 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           예외 인스턴스 생성에 실패한 경우.
   */
  public static <E extends Throwable> void negative(byte number, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (!Conditions.negative(number)) {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 0보다 작지 않으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param exception
   *          예외 클래스.
   * @param args
   *          생성자 메서드용 인자.
   * @throws E
   *           숫자가 0보다 크거나 같고 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           예외 인스턴스 생성에 실패한 경우.
   */
  public static <E extends Throwable> void negative(short number, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (!Conditions.negative(number)) {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 0보다 작지 않으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param exception
   *          예외 클래스.
   * @param args
   *          생성자 메서드용 인자.
   * @throws E
   *           숫자가 0보다 크거나 같고 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           예외 인스턴스 생성에 실패한 경우.
   */
  public static <E extends Throwable> void negative(int number, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (!Conditions.negative(number)) {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 0보다 작지 않으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param exception
   *          예외 클래스.
   * @param args
   *          생성자 메서드용 인자.
   * @throws E
   *           숫자가 0보다 크거나 같고 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           예외 인스턴스 생성에 실패한 경우.
   */
  public static <E extends Throwable> void negative(long number, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (!Conditions.negative(number)) {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 0보다 작지 않으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param exception
   *          예외 클래스.
   * @param args
   *          생성자 메서드용 인자.
   * @throws E
   *           숫자가 0보다 크거나 같고 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           예외 인스턴스 생성에 실패한 경우.
   */
  public static <E extends Throwable> void negative(float number, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (!Conditions.negative(number)) {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 0보다 작지 않으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param exception
   *          예외 클래스.
   * @param args
   *          생성자 메서드용 인자.
   * @throws E
   *           숫자가 0보다 크거나 같고 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           예외 인스턴스 생성에 실패한 경우.
   */
  public static <E extends Throwable> void negative(double number, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (!Conditions.negative(number)) {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 0보다 작지 않으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 0보다 크거나 같고 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 크거나 같고 투수가 <code>null</code>인 경우.
   */
  public static <E extends Throwable> void negative(byte number, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (Conditions.negative(number)) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException("pitcher is null.");
    }
    pitcher.pitch();
  }

  /**
   * 숫자가 0보다 작지 않으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 0보다 크거나 같고 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 크거나 같고 투수가 <code>null</code>인 경우.
   */
  public static <E extends Throwable> void negative(short number, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (Conditions.negative(number)) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException("pitcher is null.");
    }
    pitcher.pitch();
  }

  /**
   * 숫자가 0보다 작지 않으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 0보다 크거나 같고 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 크거나 같고 투수가 <code>null</code>인 경우.
   */
  public static <E extends Throwable> void negative(int number, Pitcher<E> pitcher) throws E, IllegalArgumentException {
    if (Conditions.negative(number)) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException("pitcher is null.");
    }
    pitcher.pitch();
  }

  /**
   * 숫자가 0보다 작지 않으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 0보다 크거나 같고 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 크거나 같고 투수가 <code>null</code>인 경우.
   */
  public static <E extends Throwable> void negative(long number, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (Conditions.negative(number)) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException("pitcher is null.");
    }
    pitcher.pitch();
  }

  /**
   * 숫자가 0보다 작지 않으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 0보다 크거나 같고 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 크거나 같고 투수가 <code>null</code>인 경우.
   */
  public static <E extends Throwable> void negative(float number, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (Conditions.negative(number)) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException("pitcher is null.");
    }
    pitcher.pitch();
  }

  /**
   * 숫자가 0보다 작지 않으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 0보다 크거나 같고 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 크거나 같고 숫자가 0보다 크거나 같고 투수가 <code>null</code>인 경우.
   */
  public static <E extends Throwable> void negative(double number, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (Conditions.negative(number)) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException("pitcher is null.");
    }
    pitcher.pitch();
  }

  /**
   * 숫자가 0인지 확인해서 0이 아니면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자 메서드의 인자.
   * @throws E
   *           숫자가 0이 아니고 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0이 아니고 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void zero(byte number, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if ((byte) 0 == number) {
      return;
    }
    pitch(exception, args);
  }

  /**
   * 숫자가 0인지 확인해서 0이 아니면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자 메서드의 인자.
   * @throws E
   *           숫자가 0이 아니고 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0이 아니고 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void zero(short number, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if ((short) 0 == number) {
      return;
    }
    pitch(exception, args);
  }

  /**
   * 숫자가 0인지 확인해서 0이 아니면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자 메서드의 인자.
   * @throws E
   *           숫자가 0이 아니고 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0이 아니고 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void zero(int number, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (0 == number) {
      return;
    }
    pitch(exception, args);
  }

  /**
   * 숫자가 0인지 확인해서 0이 아니면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자 메서드의 인자.
   * @throws E
   *           숫자가 0이 아니고 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0이 아니고 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void zero(long number, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (0L == number) {
      return;
    }
    pitch(exception, args);
  }

  /**
   * 숫자가 0인지 확인해서 0이 아니면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자 메서드의 인자.
   * @throws E
   *           숫자가 0이 아니고 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0이 아니고 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void zero(float number, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (0.0F == number) {
      return;
    }
    pitch(exception, args);
  }

  /**
   * 숫자가 0인지 확인해서 0이 아니면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자 메서드의 인자.
   * @throws E
   *           숫자가 0이 아니고 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0이 아니고 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void zero(double number, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (0.0 == number) {
      return;
    }
    pitch(exception, args);
  }

  /**
   * 숫자가 0인지 확인해서 0이 아니면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 0이 아니고 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0이 아니고 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void zero(byte number, Pitcher<E> pitcher) throws E, IllegalArgumentException {
    if ((byte) 0 == number) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException("pitcher is null.");
    }
    pitcher.pitch();
  }

  /**
   * 숫자가 0인지 확인해서 0이 아니면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 0이 아니고 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0이 아니고 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void zero(short number, Pitcher<E> pitcher) throws E, IllegalArgumentException {
    if ((short) 0 == number) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException("pitcher is null.");
    }
    pitcher.pitch();
  }

  /**
   * 숫자가 0인지 확인해서 0이 아니면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 0이 아니고 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0이 아니고 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void zero(int number, Pitcher<E> pitcher) throws E, IllegalArgumentException {
    if (0 == number) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException("pitcher is null.");
    }
    pitcher.pitch();
  }

  /**
   * 숫자가 0인지 확인해서 0이 아니면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 0이 아니고 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0이 아니고 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void zero(long number, Pitcher<E> pitcher) throws E, IllegalArgumentException {
    if (0L == number) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException("pitcher is null.");
    }
    pitcher.pitch();
  }

  /**
   * 숫자가 0인지 확인해서 0이 아니면 예외를 던진다.
   *
   *
   * @param number
   *          확인할 숫자.
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 0이 아니고 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0이 아니고 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void zero(float number, Pitcher<E> pitcher) throws E, IllegalArgumentException {
    if (0.0F == number) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException("pitcher is null.");
    }
    pitcher.pitch();
  }

  /**
   * 숫자가 0인지 확인해서 0이 아니면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 0이 아니고 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0이 아니고 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void zero(double number, Pitcher<E> pitcher) throws E, IllegalArgumentException {
    if (0.0 == number) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException("pitcher is null.");
    }
    pitcher.pitch();
  }

  /**
   * 숫자가 0보다 큰지 확인해서 0보다 작거나 같으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자 메서드의 인자.
   * @throws E
   *           숫자가 0보다 작거나 같고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 작거나 같고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void positive(byte number, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if ((byte) 0 < number) {
      return;
    }
    pitch(exception, args);
  }

  /**
   * 숫자가 0보다 큰지 확인해서 0보다 작거나 같으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자 메서드의 인자.
   * @throws E
   *           숫자가 0보다 작거나 같고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 작거나 같고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void positive(short number, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if ((short) 0 < number) {
      return;
    }
    pitch(exception, args);
  }

  /**
   * 숫자가 0보다 큰지 확인해서 0보다 작거나 같으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자 메서드의 인자.
   * @throws E
   *           숫자가 0보다 작거나 같고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 작거나 같고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void positive(int number, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (0 < number) {
      return;
    }
    pitch(exception, args);
  }

  /**
   * 숫자가 0보다 큰지 확인해서 0보다 작거나 같으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자 메서드의 인자.
   * @throws E
   *           숫자가 0보다 작거나 같고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 작거나 같고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void positive(long number, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (0L < number) {
      return;
    }
    pitch(exception, args);
  }

  /**
   * 숫자가 0보다 큰지 확인해서 0보다 작거나 같으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자 메서드의 인자.
   * @throws E
   *           숫자가 0보다 작거나 같고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 작거나 같고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void positive(float number, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (0.0F < number) {
      return;
    }
    pitch(exception, args);
  }

  /**
   * 숫자가 0보다 큰지 확인해서 0보다 작거나 같으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자 메서드의 인자.
   * @throws E
   *           숫자가 0보다 작거나 같고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 작거나 같고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void positive(double number, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (0.0 < number) {
      return;
    }
    pitch(exception, args);
  }

  /**
   * 숫자가 0보다 큰지 확인해서 0보다 작거나 같으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 0보다 작거나 같고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 작거나 같고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void positive(byte number, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if ((byte) 0 < number) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(format("number[%d] is not positive but pitcher is null.", number));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 0보다 큰지 확인해서 0보다 작거나 같으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 0보다 작거나 같고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 작거나 같고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void positive(short number, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if ((short) 0 < number) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(format("number[%d] is not positive but pitcher is null.", number));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 0보다 큰지 확인해서 0보다 작거나 같으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 0보다 작거나 같고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 작거나 같고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void positive(int number, Pitcher<E> pitcher) throws E, IllegalArgumentException {
    if (0 < number) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(format("number[%d] is not positive but pitcher is null.", number));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 0보다 큰지 확인해서 0보다 작거나 같으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 0보다 작거나 같고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 작거나 같고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void positive(long number, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (0L < number) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(format("number[%d] is not positive but pitcher is null.", number));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 0보다 큰지 확인해서 0보다 작거나 같으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 0보다 작거나 같고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 작거나 같고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void positive(float number, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (0.0F < number) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(format("number[%f] is not positive but pitcher is null.", number));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 0보다 큰지 확인해서 0보다 작거나 같으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 0보다 작거나 같고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 작거나 같고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void positive(double number, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (0.0 < number) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(format("number[%f] is not positive but pitcher is null.", number));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 0보다 크거나 같은지 확인해서 0보다 크거나 같지 같으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자 메서드의 인자.
   * @throws E
   *           숫자가 0보다 작고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 작고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void notNegative(byte number, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if ((byte) 0 <= number) {
      return;
    } else if (null == exception) {
      throw new IllegalArgumentException(format("number[%d] is negative, but exception type is null.", number));
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 0보다 크거나 같은지 확인해서 0보다 크거나 같지 같으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자 메서드의 인자.
   * @throws E
   *           숫자가 0보다 작고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 작고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void notNegative(short number, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if ((short) 0 <= number) {
      return;
    } else if (null == exception) {
      throw new IllegalArgumentException(format("number[%d] is negative, but exception type is null.", number));
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 0보다 크거나 같은지 확인해서 0보다 크거나 같지 같으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자 메서드의 인자.
   * @throws E
   *           숫자가 0보다 작고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 작고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void notNegative(int number, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (0 <= number) {
      return;
    } else if (null == exception) {
      throw new IllegalArgumentException(format("number[%d] is negative, but exception type is null.", number));
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 0보다 크거나 같은지 확인해서 0보다 크거나 같지 같으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자 메서드의 인자.
   * @throws E
   *           숫자가 0보다 작고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 작고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void notNegative(long number, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (0L <= number) {
      return;
    } else if (null == exception) {
      throw new IllegalArgumentException(format("number[%d] is negative, but exception type is null.", number));
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 0보다 크거나 같은지 확인해서 0보다 크거나 같지 같으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자 메서드의 인자.
   * @throws E
   *           숫자가 0보다 작고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 작고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void notNegative(float number, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (0.0F <= number) {
      return;
    } else if (null == exception) {
      throw new IllegalArgumentException(format("number[%f] is negative, but exception type is null.", number));
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 0보다 크거나 같은지 확인해서 0보다 크거나 같지 같으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자 메서드의 인자.
   * @throws E
   *           숫자가 0보다 작고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 작고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void notNegative(double number, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (0.0 <= number) {
      return;
    } else if (null == exception) {
      throw new IllegalArgumentException(format("number[%f] is negative, but exception type is null.", number));
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 0보다 크거나 같은지 확인해서 0보다 크거나 같지 같으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 0보다 작고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 작고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void notNegative(byte number, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if ((byte) 0 <= number) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(format("number[%d] is negative, but pitcheris null.", number));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 0보다 크거나 같은지 확인해서 0보다 크거나 같지 같으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 0보다 작고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 작고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void notNegative(short number, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if ((short) 0 <= number) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(format("number[%d] is negative, but pitcheris null.", number));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 0보다 크거나 같은지 확인해서 0보다 크거나 같지 같으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 0보다 작고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 작고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void notNegative(int number, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (0 <= number) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(format("number[%d] is negative, but pitcheris null.", number));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 0보다 크거나 같은지 확인해서 0보다 크거나 같지 같으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 0보다 작고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 작고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void notNegative(long number, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (0L <= number) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(format("number[%d] is negative, but pitcheris null.", number));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 0보다 크거나 같은지 확인해서 0보다 크거나 같지 같으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 0보다 작고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 작고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void notNegative(float number, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (0.0F <= number) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(format("number[%f] is negative, but pitcheris null.", number));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 0보다 크거나 같은지 확인해서 0보다 크거나 같지 같으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 0보다 작고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 작고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void notNegative(double number, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (0.0 <= number) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(format("number[%f] is negative, but pitcheris null.", number));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 0보다 작거나 같은지 확인해서 0보다 작거나 같지 같으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자 메서드의 인자.
   * @throws E
   *           숫자가 0보다 크고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 크고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void notPositive(byte number, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if ((byte) 0 >= number) {
      return;
    } else if (null == exception) {
      throw new IllegalArgumentException(format("number[%d] is positive, but exception type is null.", number));
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 0보다 작거나 같은지 확인해서 0보다 작거나 같지 같으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자 메서드의 인자.
   * @throws E
   *           숫자가 0보다 크고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 크고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void notPositive(short number, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if ((short) 0 >= number) {
      return;
    } else if (null == exception) {
      throw new IllegalArgumentException(format("number[%d] is positive, but exception type is null.", number));
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 0보다 작거나 같은지 확인해서 0보다 작거나 같지 같으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자 메서드의 인자.
   * @throws E
   *           숫자가 0보다 크고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 크고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void notPositive(int number, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (0 >= number) {
      return;
    } else if (null == exception) {
      throw new IllegalArgumentException(format("number[%d] is positive, but exception type is null.", number));
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 0보다 작거나 같은지 확인해서 0보다 작거나 같지 같으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자 메서드의 인자.
   * @throws E
   *           숫자가 0보다 크고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 크고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void notPositive(long number, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (0L >= number) {
      return;
    } else if (null == exception) {
      throw new IllegalArgumentException(format("number[%d] is positive, but exception type is null.", number));
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 0보다 작거나 같은지 확인해서 0보다 작거나 같지 같으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자 메서드의 인자.
   * @throws E
   *           숫자가 0보다 크고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 크고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void notPositive(float number, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (0.0F >= number) {
      return;
    } else if (null == exception) {
      throw new IllegalArgumentException(format("number[%f] is positive, but exception type is null.", number));
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 0보다 작거나 같은지 확인해서 0보다 작거나 같지 같으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자 메서드의 인자.
   * @throws E
   *           숫자가 0보다 크고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 크고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void notPositive(double number, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (0.0 >= number) {
      return;
    } else if (null == exception) {
      throw new IllegalArgumentException(format("number[%f] is positive, but exception type is null.", number));
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 0보다 작거나 같은지 확인해서 0보다 작거나 같지 같으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 0보다 크고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 크고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void notPositive(byte number, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if ((byte) 0 >= number) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(format("number[%d] is positive, but exception type is null.", number));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 0보다 작거나 같은지 확인해서 0보다 작거나 같지 같으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 0보다 크고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 크고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void notPositive(short number, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if ((short) 0 >= number) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(format("number[%d] is positive, but exception type is null.", number));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 0보다 작거나 같은지 확인해서 0보다 작거나 같지 같으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 0보다 크고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 크고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void notPositive(int number, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (0 >= number) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(format("number[%d] is positive, but exception type is null.", number));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 0보다 작거나 같은지 확인해서 0보다 작거나 같지 같으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 0보다 크고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 크고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void notPositive(long number, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (0L >= number) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(format("number[%d] is positive, but exception type is null.", number));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 0보다 작거나 같은지 확인해서 0보다 작거나 같지 같으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 0보다 크고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 크고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void notPositive(float number, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (0.0F >= number) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(format("number[%f] is positive, but exception type is null.", number));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 0보다 작거나 같은지 확인해서 0보다 작거나 같지 같으면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 0보다 크고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 0보다 크고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void notPositive(double number, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (0.0 >= number) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(format("number[%f] is positive, but exception type is null.", number));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 범위 안인지 확인해서 범위 밖이라면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param min
   *          최소값(포함).
   * @param max
   *          최대값(미포함).
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           숫자가 범위 밖이고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 범위 밖이고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void in(byte number, byte min, byte max, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (Conditions.in(number, min, max)) {
      return;
    } else if (null == exception) {
      throw new IllegalArgumentException(
          format("number[%d] is not in range [min[%d], max[%d]), but exception type is null.", number, min, max));
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 범위 안인지 확인해서 범위 밖이라면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param min
   *          최소값(포함).
   * @param max
   *          최대값(미포함).
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           숫자가 범위 밖이고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 범위 밖이고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void in(short number, short min, short max, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (Conditions.in(number, min, max)) {
      return;
    } else if (null == exception) {
      throw new IllegalArgumentException(
          format("number[%d] is not in range [min[%d], max[%d]), but exception type is null.", number, min, max));
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 범위 안인지 확인해서 범위 밖이라면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param min
   *          최소값(포함).
   * @param max
   *          최대값(미포함).
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           숫자가 범위 밖이고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 범위 밖이고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void in(int number, int min, int max, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (Conditions.in(number, min, max)) {
      return;
    } else if (null == exception) {
      throw new IllegalArgumentException(
          format("number[%d] is not in range [min[%d], max[%d]), but exception type is null.", number, min, max));
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 범위 안인지 확인해서 범위 밖이라면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param min
   *          최소값(포함).
   * @param max
   *          최대값(미포함).
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           숫자가 범위 밖이고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 범위 밖이고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void in(long number, long min, long max, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (Conditions.in(number, min, max)) {
      return;
    } else if (null == exception) {
      throw new IllegalArgumentException(
          format("number[%d] is not in range [min[%d], max[%d]), but exception type is null.", number, min, max));
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 범위 안인지 확인해서 범위 밖이라면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param min
   *          최소값(포함).
   * @param max
   *          최대값(미포함).
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           숫자가 범위 밖이고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 범위 밖이고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void in(float number, float min, float max, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (Conditions.in(number, min, max)) {
      return;
    } else if (null == exception) {
      throw new IllegalArgumentException(
          format("number[%d] is not in range [min[%d], max[%d]), but exception type is null.", number, min, max));
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 범위 안인지 확인해서 범위 밖이라면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param min
   *          최소값(포함).
   * @param max
   *          최대값(미포함).
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           숫자가 범위 밖이고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 범위 밖이고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void in(double number, double min, double max, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (Conditions.in(number, min, max)) {
      return;
    } else if (null == exception) {
      throw new IllegalArgumentException(
          format("number[%d] is not in range [min[%d], max[%d]), but exception type is null.", number, min, max));
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 범위 안인지 확인해서 범위 밖이라면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param min
   *          최소값(포함).
   * @param max
   *          최대값(미포함).
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 범위 밖이고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 범위 밖이고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void in(byte number, byte min, byte max, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (Conditions.in(number, min, max)) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(
          format("number[%d] not in range [min[%d], max[%d]), but pitcher is null.", number, min, max));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 범위 안인지 확인해서 범위 밖이라면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param min
   *          최소값(포함).
   * @param max
   *          최대값(미포함).
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 범위 밖이고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 범위 밖이고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void in(short number, short min, short max, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (Conditions.in(number, min, max)) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(
          format("number[%d] not in range [min[%d], max[%d]), but pitcher is null.", number, min, max));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 범위 안인지 확인해서 범위 밖이라면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param min
   *          최소값(포함).
   * @param max
   *          최대값(미포함).
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 범위 밖이고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 범위 밖이고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void in(int number, int min, int max, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (Conditions.in(number, min, max)) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(
          format("number[%d] not in range [min[%d], max[%d]), but pitcher is null.", number, min, max));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 범위 안인지 확인해서 범위 밖이라면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param min
   *          최소값(포함).
   * @param max
   *          최대값(미포함).
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 범위 밖이고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 범위 밖이고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void in(long number, long min, long max, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (Conditions.in(number, min, max)) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(
          format("number[%d] not in range [min[%d], max[%d]), but pitcher is null.", number, min, max));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 범위 안인지 확인해서 범위 밖이라면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param min
   *          최소값(포함).
   * @param max
   *          최대값(미포함).
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 범위 밖이고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 범위 밖이고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void in(float number, float min, float max, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (Conditions.in(number, min, max)) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(
          format("number[%f] not in range [min[%f], max[%f]), but pitcher is null.", number, min, max));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 범위 안인지 확인해서 범위 밖이라면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param min
   *          최소값(포함).
   * @param max
   *          최대값(미포함).
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 범위 밖이고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 범위 밖이고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void in(double number, double min, double max, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (Conditions.in(number, min, max)) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(
          format("number[%f] not in range [min[%f], max[%f]), but pitcher is null.", number, min, max));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 기준값보다 작은지 확인해서 기준값보다 크거나 같다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           숫자가 기준값보다 크거나 같고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 크거나 같고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void lt(byte number, byte boundary, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (number < boundary) {
      return;
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 기준값보다 작은지 확인해서 기준값보다 크거나 같다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           숫자가 기준값보다 크거나 같고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 크거나 같고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void lt(short number, short boundary, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (number < boundary) {
      return;
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 기준값보다 작은지 확인해서 기준값보다 크거나 같다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           숫자가 기준값보다 크거나 같고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 크거나 같고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void lt(int number, int boundary, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (number < boundary) {
      return;
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 기준값보다 작은지 확인해서 기준값보다 크거나 같다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           숫자가 기준값보다 크거나 같고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 크거나 같고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void lt(long number, long boundary, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (number < boundary) {
      return;
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 기준값보다 작은지 확인해서 기준값보다 크거나 같다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           숫자가 기준값보다 크거나 같고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 크거나 같고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void lt(float number, float boundary, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (number < boundary) {
      return;
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 기준값보다 작은지 확인해서 기준값보다 크거나 같다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           숫자가 기준값보다 크거나 같고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 크거나 같고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void lt(double number, double boundary, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (number < boundary) {
      return;
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 기준값보다 작은지 확인해서 기준값보다 크거나 같다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 기준값보다 크거나 같고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 크거나 같고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void lt(byte number, byte boundary, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (number < boundary) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(
          format("number[%d] is not less than boundary[%d], but exception pitcher is null.", number, boundary));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 기준값보다 작은지 확인해서 기준값보다 크거나 같다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 기준값보다 크거나 같고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 크거나 같고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void lt(short number, short boundary, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (number < boundary) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(
          format("number[%d] is not less than boundary[%d], but exception pitcher is null.", number, boundary));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 기준값보다 작은지 확인해서 기준값보다 크거나 같다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 기준값보다 크거나 같고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 크거나 같고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void lt(int number, int boundary, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (number < boundary) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(
          format("number[%d] is not less than boundary[%d], but exception pitcher is null.", number, boundary));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 기준값보다 작은지 확인해서 기준값보다 크거나 같다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 기준값보다 크거나 같고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 크거나 같고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void lt(long number, long boundary, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (number < boundary) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(
          format("number[%d] is not less than boundary[%d], but exception pitcher is null.", number, boundary));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 기준값보다 작은지 확인해서 기준값보다 크거나 같다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 기준값보다 크거나 같고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 크거나 같고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void lt(float number, float boundary, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (number < boundary) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(
          format("number[%f] is not less than boundary[%f], but exception pitcher is null.", number, boundary));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 기준값보다 작은지 확인해서 기준값보다 크거나 같다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 기준값보다 크거나 같고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 크거나 같고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void lt(double number, double boundary, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (number < boundary) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(
          format("number[%f] is not less than boundary[%f], but exception pitcher is null.", number, boundary));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 기준값보다 작거나 같은지 확인해서 기준값보다 크다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           숫자가 기준값보다 크고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 크고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void le(byte number, byte boundary, Class<E> exception,
      Object... args) throws E, IllegalArgumentException {
    if (number <= boundary) {
      return;
    }
    pitch(exception, args);
  }

  /**
   * 숫자가 기준값보다 작거나 같은지 확인해서 기준값보다 크다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           숫자가 기준값보다 크고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 크고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void le(short number, short boundary, Class<E> exception,
      Object... args) throws E, IllegalArgumentException {
    if (number <= boundary) {
      return;
    }
    pitch(exception, args);
  }

  /**
   * 숫자가 기준값보다 작거나 같은지 확인해서 기준값보다 크다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           숫자가 기준값보다 크고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 크고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void le(int number, int boundary, Class<E> exception,
      Object... args) throws E, IllegalArgumentException {
    if (number <= boundary) {
      return;
    }
    pitch(exception, args);
  }

  /**
   * 숫자가 기준값보다 작거나 같은지 확인해서 기준값보다 크다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           숫자가 기준값보다 크고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 크고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void le(long number, long boundary, Class<E> exception,
      Object... args) throws E, IllegalArgumentException {
    if (number <= boundary) {
      return;
    }
    pitch(exception, args);
  }

  /**
   * 숫자가 기준값보다 작거나 같은지 확인해서 기준값보다 크다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           숫자가 기준값보다 크고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 크고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void le(float number, float boundary, Class<E> exception,
      Object... args) throws E, IllegalArgumentException {
    if (number <= boundary) {
      return;
    }
    pitch(exception, args);
  }

  /**
   * 숫자가 기준값보다 작거나 같은지 확인해서 기준값보다 크다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           숫자가 기준값보다 크고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 크고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void le(double number, double boundary, Class<E> exception,
      Object... args) throws E, IllegalArgumentException {
    if (number <= boundary) {
      return;
    }
    pitch(exception, args);
  }

  /**
   * 숫자가 기준값보다 작거나 같은지 확인해서 기준값보다 크다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 기준값보다 크고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 크고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void le(byte number, byte boundary, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (number <= boundary) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(format(
          "number[%d] is not less than or equal to boundary[%d], but exception pitcher is null.", number, boundary));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 기준값보다 작거나 같은지 확인해서 기준값보다 크다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 기준값보다 크고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 크고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void le(short number, short boundary, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (number <= boundary) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(format(
          "number[%d] is not less than or equal to boundary[%d], but exception pitcher is null.", number, boundary));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 기준값보다 작거나 같은지 확인해서 기준값보다 크다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 기준값보다 크고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 크고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void le(int number, int boundary, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (number <= boundary) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(format(
          "number[%d] is not less than or equal to boundary[%d], but exception pitcher is null.", number, boundary));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 기준값보다 작거나 같은지 확인해서 기준값보다 크다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 기준값보다 크고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 크고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void le(long number, long boundary, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (number <= boundary) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(format(
          "number[%d] is not less than or equal to boundary[%d], but exception pitcher is null.", number, boundary));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 기준값보다 작거나 같은지 확인해서 기준값보다 크다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 기준값보다 크고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 크고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void le(float number, float boundary, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (number <= boundary) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(format(
          "number[%f] is not less than or equal to boundary[%f], but exception pitcher is null.", number, boundary));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 기준값보다 작거나 같은지 확인해서 기준값보다 크다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 기준값보다 크고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 크고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void le(double number, double boundary, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (number <= boundary) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(format(
          "number[%f] is not less than or equal to boundary[%f], but exception pitcher is null.", number, boundary));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 기준값보다 큰지 확인해서 기준값보다 작거나 같다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           숫자가 기준값보다 작거나 같고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 작거나 같고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void gt(byte number, byte boundary, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (number > boundary) {
      return;
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 기준값보다 큰지 확인해서 기준값보다 작거나 같다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           숫자가 기준값보다 작거나 같고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 작거나 같고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void gt(short number, short boundary, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (number > boundary) {
      return;
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 기준값보다 큰지 확인해서 기준값보다 작거나 같다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           숫자가 기준값보다 작거나 같고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 작거나 같고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void gt(int number, int boundary, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (number > boundary) {
      return;
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 기준값보다 큰지 확인해서 기준값보다 작거나 같다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           숫자가 기준값보다 작거나 같고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 작거나 같고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void gt(long number, long boundary, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (number > boundary) {
      return;
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 기준값보다 큰지 확인해서 기준값보다 작거나 같다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           숫자가 기준값보다 작거나 같고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 작거나 같고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void gt(float number, float boundary, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (number > boundary) {
      return;
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 기준값보다 큰지 확인해서 기준값보다 작거나 같다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           숫자가 기준값보다 작거나 같고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 작거나 같고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void gt(double number, double boundary, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (number > boundary) {
      return;
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 기준값보다 큰지 확인해서 기준값보다 작거나 같다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 기준값보다 작거나 같고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 작거나 같고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void gt(byte number, byte boundary, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (number > boundary) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(
          format("number[%d] is not greater than boundary[%d], but exception pitcher is null.", number, boundary));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 기준값보다 큰지 확인해서 기준값보다 작거나 같다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 기준값보다 작거나 같고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 작거나 같고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void gt(short number, short boundary, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (number > boundary) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(
          format("number[%d] is not greater than boundary[%d], but exception pitcher is null.", number, boundary));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 기준값보다 큰지 확인해서 기준값보다 작거나 같다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 기준값보다 작거나 같고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 작거나 같고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void gt(int number, int boundary, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (number > boundary) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(
          format("number[%d] is not greater than boundary[%d], but exception pitcher is null.", number, boundary));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 기준값보다 큰지 확인해서 기준값보다 작거나 같다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 기준값보다 작거나 같고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 작거나 같고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void gt(float number, float boundary, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (number > boundary) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(
          format("number[%f] is not greater than boundary[%f], but exception pitcher is null.", number, boundary));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 기준값보다 큰지 확인해서 기준값보다 작거나 같다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 기준값보다 작거나 같고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 작거나 같고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void gt(double number, double boundary, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (number > boundary) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(
          format("number[%f] is not greater than boundary[%f], but exception pitcher is null.", number, boundary));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 기준값보다 큰거나 같은지 확인해서 기준값보다 작다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           숫자가 기준값보다 작고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 작고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void ge(byte number, byte boundary, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (number >= boundary) {
      return;
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 기준값보다 큰거나 같은지 확인해서 기준값보다 작다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           숫자가 기준값보다 작고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 작고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void ge(short number, short boundary, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (number >= boundary) {
      return;
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 기준값보다 큰거나 같은지 확인해서 기준값보다 작다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           숫자가 기준값보다 작고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 작고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void ge(int number, int boundary, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (number >= boundary) {
      return;
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 기준값보다 큰거나 같은지 확인해서 기준값보다 작다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           숫자가 기준값보다 작고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 작고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void ge(float number, float boundary, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (number >= boundary) {
      return;
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 기준값보다 큰거나 같은지 확인해서 기준값보다 작다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           숫자가 기준값보다 작고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 작고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void ge(double number, double boundary, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (number >= boundary) {
      return;
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 숫자가 기준값보다 큰거나 같은지 확인해서 기준값보다 작다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 기준값보다 작고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 작고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void ge(byte number, byte boundary, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (number >= boundary) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(format(
          "number[%d] is not greater than or equal to boundary[%d], but exception pitcher is null.", number, boundary));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 기준값보다 큰거나 같은지 확인해서 기준값보다 작다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 기준값보다 작고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 작고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void ge(short number, short boundary, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (number >= boundary) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(format(
          "number[%d] is not greater than or equal to boundary[%d], but exception pitcher is null.", number, boundary));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 기준값보다 큰거나 같은지 확인해서 기준값보다 작다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 기준값보다 작고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 작고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void ge(int number, int boundary, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (number >= boundary) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(format(
          "number[%d] is not greater than or equal to boundary[%d], but exception pitcher is null.", number, boundary));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 기준값보다 큰거나 같은지 확인해서 기준값보다 작다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 기준값보다 작고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 작고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void ge(float number, float boundary, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (number >= boundary) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(format(
          "number[%f] is not greater than or equal to boundary[%f], but exception pitcher is null.", number, boundary));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 숫자가 기준값보다 큰거나 같은지 확인해서 기준값보다 작다면 예외를 던진다.
   *
   * @param number
   *          확인할 숫자.
   * @param boundary
   *          기준값(미포함).
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           숫자가 기준값보다 작고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           숫자가 기준값보다 작고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void ge(double number, double boundary, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (number >= boundary) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(format(
          "number[%f] is not greater than or equal to boundary[%f], but exception pitcher is null.", number, boundary));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 인스턴스가 <code>null</code>인지 확인해서 <code>null</code>이 아니면 예외를 던진다.
   *
   * @param instance
   *          확인할 인스턴스.
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           인스턴스가 <code>null</code>이 아니고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           인스턴스가 <code>null</code>이 아니고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void isNull(Object instance, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (null == instance) {
      return;
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 인스턴스가 <code>null</code>인지 확인해서 <code>null</code>이 아니면 예외를 던진다.
   *
   * @param instance
   *          확인할 인스턴스.
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           인스턴스가 <code>null</code>이 아니고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           인스턴스가 <code>null</code>이 아니고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void isNull(Object instance, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (null == instance) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException("instance is null, but pitcher is null.");
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 인스턴스가 <code>null</code>이 아닌지 확인해서 <code>null</code>이면 예외를 던진다.
   *
   * @param instance
   *          확인할 인스턴스.
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           인스턴스가 <code>null</code>이고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           인스턴스가 <code>null</code>이고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void notNull(Object instance, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (null != instance) {
      return;
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 인스턴스가 <code>null</code>이 아닌지 확인해서 <code>null</code>이면 예외를 던진다.
   *
   * @param instance
   *          확인할 인스턴스.
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           인스턴스가 <code>null</code>이고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           인스턴스가 <code>null</code>이고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void notNull(Object instance, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (null != instance) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException("instance is null, but exception pitcher is null.");
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 인스턴스의 타입이 정확하게 예상 타입인지 확인해서, 예상 타입이 아니면 예외를 던진다.
   *
   * @param instance
   *          확인할 인스턴스.
   * @param clz
   *          예상 타입.
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           인스턴스의 타입이 정확히 예상 타입이 아니고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           인스턴스의 타입이 정확히 예상 타입이 아니고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void instance(Object instance, Class<?> clz, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (Conditions.instance(instance, clz)) {
      return;
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 인스턴스의 타입이 정확하게 예상 타입인지 확인해서, 예상 타입이 아니면 예외를 던진다.
   *
   * @param instance
   *          확인할 인스턴스.
   * @param clz
   *          예상 타입.
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           인스턴스의 타입이 정확히 예상 타입이 아니고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           인스턴스의 타입이 정확히 예상 타입이 아니고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void instance(Object instance, Class<?> clz, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (Conditions.instance(instance, clz)) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(
          format("instance[%d] is not object of type[%s], but pitcher is null.", instance, clz));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 인스턴스의 예상 타입에 할당할 수 있는지 확인해서, 할당할 수 없다면 예외를 던진다.
   *
   * @param instance
   *          확인할 인스턴스.
   * @param clz
   *          예상 타입.
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           인스턴스가 예상 타입에 할당할 수 없고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           인스턴스가 예상 타입에 할당할 수 없고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void assignable(Object instance, Class<?> clz, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (Conditions.assignable(instance, clz)) {
      return;
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 인스턴스의 예상 타입에 할당할 수 있는지 확인해서, 할당할 수 없다면 예외를 던진다.
   *
   * @param instance
   *          확인할 인스턴스.
   * @param clz
   *          예상 타입.
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           인스턴스가 예상 타입에 할당할 수 없고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           인스턴스가 예상 타입에 할당할 수 없고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void assignable(Object instance, Class<?> clz, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (Conditions.assignable(instance, clz)) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(
          format("instance[%s] is not assignable to type[%s], but exception pitcher is null.", instance, clz));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 문자열이 길이를 가지고 있는지 확인해서, 가지지 않는다면 예외를 던진다.
   *
   * @param string
   *          확인할 문자열.
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           문자열이 <code>null</code>이거나 빈 문자열이고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           문자열이 <code>null</code>이거나 빈 문자열이고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void hasLength(CharSequence string, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (null != string && 0 < string.length()) {
      return;
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 문자열이 길이를 가지고 있는지 확인해서, 가지지 않는다면 예외를 던진다.
   *
   * @param string
   *          확인할 문자열.
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           문자열이 <code>null</code>이거나 빈 문자열이고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           문자열이 <code>null</code>이거나 빈 문자열이고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void hasLength(CharSequence string, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (null != string && 0 < string.length()) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(format("string[%s] is null or empty, but exception pitcher is null.", string));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 문자열이 예상한 길이인지 확인해서, 길이가 다르다면 예외를 던진다.
   *
   * @param string
   *          확인할 문자열.
   * @param length
   *          예상 길이.
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           문자열의 길이가 예상 길이가 아니고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           문자열의 길이가 예상 길이가 아니고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void length(CharSequence string, int length, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (Conditions.length(string, length)) {
      return;
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 문자열이 예상한 길이인지 확인해서, 길이가 다르다면 예외를 던진다.
   *
   * @param string
   *          확인할 문자열.
   * @param length
   *          예상 길이.
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           문자열의 길이가 예상 길이가 아니고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           문자열의 길이가 예상 길이가 아니고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   */
  public static <E extends Throwable> void length(CharSequence string, int length, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (Conditions.length(string, length)) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(
          format("string[%s] has not excpected length[%d], but exception pitcher is null.", string, length));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 문자열의 길이가 예상 범위 내인지 확인해서, 범위 밖이라면 예외를 던진다.
   *
   * @param string
   *          확인할 문자열.
   * @param min
   *          예상 최소 길이(포함).
   * @param max
   *          예상 최대 길이(포함).
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           문자열의 길이가 예상 범위가 아니고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           문자열의 길이가 예상 범위가 아니고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   * @see Conditions#length(CharSequence, int, int)
   */
  public static <E extends Throwable> void length(CharSequence string, int min, int max, Class<E> exception,
      Object... args) throws E, IllegalArgumentException {
    if (Conditions.length(string, min, max)) {
      return;
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 문자열의 길이가 예상 범위 내인지 확인해서, 범위 밖이라면 예외를 던진다.
   *
   * @param string
   *          확인할 문자열.
   * @param min
   *          예상 최소 길이(포함).
   * @param max
   *          예상 최대 길이(포함).
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           문자열의 길이가 예상 범위가 아니고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           문자열의 길이가 예상 범위가 아니고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   * @see Conditions#length(CharSequence, int, int)
   */
  public static <E extends Throwable> void length(CharSequence string, int min, int max, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (Conditions.length(string, min, max)) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(
          format("string[%s] length is not in range[min[%d], max[%d], but exception piter is null.", string, min, max));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 문자열이 예상 최대 길이보다 짧거나 같은지 확인해서, 예상 길이보다 길다면 예외를 던진다.
   *
   * @param string
   *          확인할 문자열.
   * @param length
   *          예상 최대 길이(포함).
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           문자열의 길이가 예상 최대 길이보다 길고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           문자열의 길이가 예상 최대 길이보다 길고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   * @see Conditions#shorter(CharSequence, int)
   */
  public static <E extends Throwable> void shorter(CharSequence string, int length, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (Conditions.shorter(string, length)) {
      return;
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 문자열이 예상 최대 길이보다 짧거나 같은지 확인해서, 예상 길이보다 길다면 예외를 던진다.
   *
   * @param string
   *          확인할 문자열.
   * @param length
   *          예상 최대 길이(포함).
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           문자열의 길이가 예상 최대 길이보다 길고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           문자열의 길이가 예상 최대 길이보다 길고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   * @see Conditions#shorter(CharSequence, int)
   */
  public static <E extends Throwable> void shorter(CharSequence string, int length, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (Conditions.shorter(string, length)) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(
          format("string[%s] is not shorter than or equal to max length[%d], but exception pitcher is null.", string,
              length));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 문자열이 예상 최대 길이보다 길거나 같은지 확인해서, 예상 길이보다 짧다면 예외를 던진다.
   *
   * @param string
   *          확인할 문자열.
   * @param length
   *          예상 최소 길이(포함).
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           문자열의 길이가 예상 최소 길이보다 짧고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           문자열의 길이가 예상 최소 길이보다 짧고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   * @see Conditions#longer(CharSequence, int)
   */
  public static <E extends Throwable> void longer(CharSequence string, int length, Class<E> exception, Object... args)
      throws E, IllegalArgumentException {
    if (Conditions.longer(string, length)) {
      return;
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 문자열이 예상 최대 길이보다 길거나 같은지 확인해서, 예상 길이보다 짧다면 예외를 던진다.
   *
   * @param string
   *          확인할 문자열.
   * @param length
   *          예상 최소 길이(포함).
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           문자열의 길이가 예상 최소 길이보다 짧고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           문자열의 길이가 예상 최소 길이보다 짧고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   * @see Conditions#longer(CharSequence, int)
   */
  public static <E extends Throwable> void longer(CharSequence string, int length, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (Conditions.longer(string, length)) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(format(
          "string[%s] is not longer than or equal to min length[%d], but exception pitcher is null.", string, length));
    } else {
      pitcher.pitch();
    }
  }

  /**
   * 문자열의 형식이 정규표현식과 일치하는지 확인해서, 일치학지 않는다면 예외를 던진다.
   *
   * @param string
   *          확인할 문자열.
   * @param regex
   *          예상 정규표현식.
   * @param exception
   *          던질 예외 타입.
   * @param args
   *          예외 생성자용 인자.
   * @throws E
   *           문자열이 정규표현식에 일치하지 않고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           문자열이 정규표현식에 일치하지 않고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   * @see Conditions#matches(CharSequence, String)
   */
  public static <E extends Throwable> void matches(CharSequence string, String regex, Class<E> exception,
      Object... args) throws E, IllegalArgumentException {
    if (Conditions.matches(string, regex)) {
      return;
    } else {
      pitch(exception, args);
    }
  }

  /**
   * 문자열의 형식이 정규표현식과 일치하는지 확인해서, 일치학지 않는다면 예외를 던진다.
   *
   * @param string
   *          확인할 문자열.
   * @param regex
   *          예상 정규표현식.
   * @param pitcher
   *          예외 투수.
   * @throws E
   *           문자열이 정규표현식에 일치하지 않고, 정상적으로 예외 인스턴스를 만든 경우.
   * @throws IllegalArgumentException
   *           문자열이 정규표현식에 일치하지 않고, 정상적으로 예외 인스턴스를 만들지 못한 경우.
   * @see Conditions#matches(CharSequence, String)
   */
  public static <E extends Throwable> void matches(CharSequence string, String regex, Pitcher<E> pitcher)
      throws E, IllegalArgumentException {
    if (Conditions.matches(string, regex)) {
      return;
    } else if (null == pitcher) {
      throw new IllegalArgumentException(
          format("string[%s] does not match to regex[%s], but exception pitcher is null.", string, regex));
    } else {
      pitcher.pitch();
    }
  }

  protected ConditionalExceptions() {
    throw new UnsupportedOperationException();
  }
}
