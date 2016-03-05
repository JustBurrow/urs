package kr.lul.urs.util;

import static java.lang.String.format;

import java.math.BigInteger;
import java.util.Random;

/**
 * 각 데이터 타입의 최대 범위와 확률을 엄밀하게 고려하지는 않지만, 대략적인 임의의 값을 만드는 유틸리티 메서드 모음.
 *
 * @author just.burrow@lul.kr
 */
public abstract class Randoms {
  private static final Random R = new Random();

  /**
   * 임의의 <code>boolean</code>을 고른다.
   *
   * @return 임의의 <code>boolean</code>.
   */
  public static boolean bool() {
    return R.nextBoolean();
  }

  /**
   * 임의의 음수를 반환한다.
   *
   * @return 임의의 음수.
   */
  public static int negative() {
    return -1 - R.nextInt(Integer.MAX_VALUE);
  }

  /**
   * 임의의 음수를 반환한다.
   *
   * @return 임의의 음수.
   */
  public static long negativeLong() {
    long val = R.nextLong();
    if (0L < val) {
      val *= -1;
    }
    return -1L + val;
  }

  /**
   * 임의의 양수를 반환한다.
   *
   * @return 임의의 양수.
   */
  public static int positive() {
    return 1 + R.nextInt(Integer.MAX_VALUE);
  }

  /**
   * 임의의 양수를 반환한다.
   *
   * @return 임의의 양수.
   */
  public static long positiveLong() {
    long val = R.nextLong();
    if (Long.MIN_VALUE == val) {
      return positiveLong();
    } else if (0L > val) {
      val *= -1L;
    }
    return 1 + val;
  }

  /**
   * 0보다 크거나 같은 임의의 수를 반환한다.
   *
   * @return 0보다 크거나 같은 임의의 수.
   */
  public static int notNegative() {
    return R.nextInt(Integer.MAX_VALUE);
  }

  /**
   * 0보다 크거나 같은 임의의 수를 반환한다.
   *
   * @return 0 이상의 임의의 수.
   */
  public static long notNegativeLong() {
    return notNegative(Long.MAX_VALUE);
  }

  /**
   * 0 이상, 최대값 미만의 임의의 값을 반환한다.
   *
   * @param max
   *          최대값(미포함).
   * @return 임의의 0 이상의 수.
   */
  public static int notNegative(int max) {
    Asserts.positive(max);
    return R.nextInt(max);
  }

  /**
   * 0 이상, 최대값 미만의 임의의 값을 반환한다.
   *
   * @param max
   *          최대값(미포함).
   * @return 임의의 0 이상의 수.
   * @see http://stackoverflow.com/a/2546186
   */
  public static long notNegative(long max) {
    Asserts.positive(max);
    long bits, val;
    do {
      bits = R.nextLong() << 1 >>> 1;
      val = bits % max;
    } while (bits - val + max - 1 < 0L);
    return val;
  }

  /**
   * 0이 아닌 임의의 수를 반환한다.
   *
   * @return 0이 아닌 임의의 수.
   */
  public static int notZero() {
    if (R.nextBoolean()) {
      return negative();
    } else {
      return positive();
    }
  }

  /**
   * 0이 아닌 임의의 수를 반환한다.
   *
   * @return 0이 아닌 임의의 수.
   */
  public static long notZeroLong() {
    if (R.nextBoolean()) {
      return negativeLong();
    } else {
      return positiveLong();
    }
  }

  /**
   * 0보다 작거나 같은 임의의 수를 반환한다.
   *
   * @return 0 이하의 임의의 수.
   */
  public static int notPositive() {
    return -R.nextInt(Integer.MAX_VALUE);
  }

  /**
   * 0보다 작거나 같은 임의의 수를 반환한다.
   *
   * @return 0 이하의 임의의 수.
   */
  public static long notPositiveLong() {
    return -notNegative(Long.MAX_VALUE);
  }

  /**
   * 최소값과 최대값 사이에서 임의의 수를 반환한다.
   *
   * @param min
   *          최소값(포함).
   * @param max
   *          최대값(미포함).
   * @return 임의의 수.
   * @throws IllegalArgumentException
   *           임의의 수를 만드는 범위가 <code>int</code>형으로 표현 불가능할 때.
   */
  public static int in(int min, int max) throws IllegalArgumentException {
    Asserts.lt(min, max);

    long width = (long) max - (long) min;
    if (Integer.MAX_VALUE < width) {
      return min + (int) notNegative(width);
    } else {
      return min + R.nextInt(max - min);
    }
  }

  /**
   * 최소값과 최대값 사이에서 임의의 수를 반환한다.
   *
   * @param min
   *          최소값(포함).
   * @param max
   *          최대값(미포함).
   * @return 임의의 수.
   */
  public static long in(long min, long max) {
    Asserts.lt(min, max);

    ConditionalExceptions.positive(max - min, () -> {
      throw new IllegalArgumentException(format("width[%s] is wider than long max[%d]",
          BigInteger.valueOf(max).subtract(BigInteger.valueOf(min)), Long.MAX_VALUE));
    });
    return min + notNegative(max - min);
  }

  /**
   * <code>int</code>형으로 표현 가능한 값 중에서, 최소값과 최대값 사이를 제외한 임의의 수를 반환한다.
   *
   * @param min
   *          최소값(제외 영역에 포함).
   * @param max
   *          최대값(제외 영역에 미포함).
   * @return 범위 밖의 임의의 수.
   */
  public static int notIn(int min, int max) {
    Asserts.lt(min, max);
    return R.nextBoolean() ? in(Integer.MIN_VALUE, min + 1) : in(max, Integer.MAX_VALUE);
  }

  /**
   * <code>long</code>형으로 표현 가능한 값 중에서, 최소값과 최대값 사이를 제외한 임의의 수를 반환한다.
   *
   * @param min
   *          최소값(제외 영역에 포함).
   * @param max
   *          최대값(제외 영역에 미포함).
   * @return 범위 밖의 임의의 수.
   */
  public static long notIn(long min, long max) {
    Asserts.lt(min, max);

    long underWidth = (min >> 1) - (Long.MIN_VALUE >> 1);
    long overWidth = (Long.MAX_VALUE >> 1) - (max >> 1);
    long width = underWidth + overWidth;

    long number = notNegative(width);
    if (underWidth >= number) {
      number = Long.MIN_VALUE + (number << 1);
      return 0L == min % 2L && R.nextBoolean() ? number + 1L : number;
    } else {
      return Long.MIN_VALUE + (max - min) + (number << 1) + (1L == (min % 2L + max % 2L) ? 1L : 0L);
    }
  }

  /**
   * 기준값보다 작은 임의의 수를 반환한다.
   *
   * @param boundary
   *          기준값(미포함).
   * @return 기준값보다 작은 임의의 수.
   */
  public static int lt(int boundary) {
    Asserts.gt(boundary, Integer.MIN_VALUE);
    if (Integer.MIN_VALUE + 1 == boundary) {
      return Integer.MIN_VALUE;
    } else if (0 > boundary) {
      return boundary - 1 - R.nextInt(boundary - Integer.MIN_VALUE);
    } else {
      return (int) in((long) Integer.MIN_VALUE, (long) boundary);
    }
  }

  /**
   * 기준값보다 작은 임의의 수를 반환한다.
   *
   * @param boundary
   *          기준값(미포함).
   * @return 기준값보다 작은 임의의 수.
   */
  public static long lt(long boundary) {
    Asserts.gt(boundary, Long.MIN_VALUE);
    if (Long.MIN_VALUE + 1L == boundary) {
      return Long.MIN_VALUE;
    }
    long number = notNegative((boundary >> 1) - (Long.MIN_VALUE >> 1));
    number = Long.MIN_VALUE + (number << 1);
    return 0L == boundary % 2L && R.nextBoolean() ? number : number + 1L;
  }

  /**
   * 기준값보다 작거나 같은 임의의 수를 반환한다.
   *
   * @param boundary
   *          기준값(미포함).
   * @return 기준값보다 작거나 같은 임의의 수.
   */
  public static int le(int boundary) {
    if (Integer.MIN_VALUE == boundary) {
      return Integer.MIN_VALUE;
    } else if (0 > boundary) {
      return boundary - R.nextInt(boundary - Integer.MIN_VALUE);
    } else {
      return (int) in(Integer.MIN_VALUE, 1L + boundary);
    }
  }

  /**
   * 기준값보다 작거나 같은 임의의 수를 반환한다.
   *
   * @param boundary
   *          기준값(미포함).
   * @return 기준값보다 작거나 같은 임의의 수.
   */
  public static long le(long boundary) {
    if (Long.MIN_VALUE == boundary) {
      return Long.MIN_VALUE;
    }
    long number = notNegative(((boundary + 1L) >> 1) - (Long.MIN_VALUE >> 1));
    number = Long.MIN_VALUE + (number << 1);
    return 0L != boundary % 2L && R.nextBoolean() ? number : number + 1L;
  }

  /**
   * 기준값보다 큰 임의의 수를 반환한다.
   *
   * @param boundary
   *          기준값(미포함).
   * @return 기준갑보다 큰 임의의 수.
   */
  public static int gt(int boundary) {
    Asserts.lt(boundary, Integer.MAX_VALUE);
    if (Integer.MAX_VALUE - 1 == boundary) {
      return Integer.MAX_VALUE;
    } else if (0 > boundary) {
      return boundary + 1 + (int) notNegative((long) Integer.MAX_VALUE - boundary);
    } else {
      return boundary + 1 + R.nextInt(Integer.MAX_VALUE - boundary);
    }
  }

  /**
   * 기준값보다 큰 임의의 수를 반환한다.
   *
   * @param boundary
   *          기준값(미포함).
   * @return 기준갑보다 큰 임의의 수.
   */
  public static long gt(long boundary) {
    Asserts.lt(boundary, Long.MAX_VALUE);
    if (Long.MAX_VALUE - 1L == boundary) {
      return Long.MAX_VALUE;
    }

    long number = notNegative((Long.MAX_VALUE >> 1) - (boundary >> 1));
    number = boundary + (number << 1);
    return 0L == boundary % 2L ? number + R.nextInt(1) : number + 1L + R.nextInt(1);
  }

  /**
   * 기준값보다 크거나 같은 임의의 수를 반환한다.
   *
   * @param boundary
   *          기준값(미포함).
   * @return 기준갑보다 크거나 같은 임의의 수.
   */
  public static int ge(int boundary) {
    if (Integer.MAX_VALUE == boundary) {
      return Integer.MAX_VALUE;
    } else if (0 > boundary) {
      return boundary + (int) notNegative((long) Integer.MAX_VALUE - boundary);
    } else {
      return boundary + R.nextInt(Integer.MAX_VALUE - boundary);
    }
  }

  /**
   * 기준값보다 크거나 같은 임의의 수를 반환한다.
   *
   * @param boundary
   *          기준값(미포함).
   * @return 기준갑보다 크거나 같은 임의의 수.
   */
  public static long ge(long boundary) {
    if (Long.MAX_VALUE == boundary) {
      return Long.MAX_VALUE;
    }
    long number = notNegative((Long.MAX_VALUE >> 1) - (boundary >> 1));
    number = boundary + (number << 1);
    return 0L != boundary % 2L ? number + R.nextInt(1) : number + 1L + R.nextInt(1);
  }

  protected Randoms() {
    throw new UnsupportedOperationException();
  }
}
