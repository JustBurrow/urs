package kr.lul.urs.util;

import static java.lang.String.format;
import static kr.lul.urs.util.Randoms.bool;
import static kr.lul.urs.util.Randoms.ge;
import static kr.lul.urs.util.Randoms.gt;
import static kr.lul.urs.util.Randoms.in;
import static kr.lul.urs.util.Randoms.le;
import static kr.lul.urs.util.Randoms.lt;
import static kr.lul.urs.util.Randoms.negative;
import static kr.lul.urs.util.Randoms.negativeLong;
import static kr.lul.urs.util.Randoms.notIn;
import static kr.lul.urs.util.Randoms.notNegative;
import static kr.lul.urs.util.Randoms.notNegativeLong;
import static kr.lul.urs.util.Randoms.notPositive;
import static kr.lul.urs.util.Randoms.notPositiveLong;
import static kr.lul.urs.util.Randoms.notZero;
import static kr.lul.urs.util.Randoms.notZeroLong;
import static kr.lul.urs.util.Randoms.positive;
import static kr.lul.urs.util.Randoms.positiveLong;
import static kr.lul.urs.util.Tests.exceptException;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

import java.math.BigInteger;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class RandomsTest {
  private static final int COUNT = Integer.MAX_VALUE / 1024;
  private Random           rand;

  @Before
  public void setUp() throws Exception {
    this.rand = new Random();
  }

  @Test
  public void testBool() throws Exception {
    int t = 0;
    int f = 0;
    for (int i = 0; i < COUNT; i++) {
      if (bool()) {
        t++;
      } else {
        f++;
      }
    }

    assertEquals(format("true=%d, false=%d, diff=%d", t, f, Math.abs(t - f)), 0,
        (int) ((double) Math.abs(t - f) / (COUNT / 2)));
  }

  @Test
  public void testNegative() throws Exception {
    for (int i = 0; i < COUNT; i++) {
      int val = negative();
      assertThat(format("i=%d, val=%d", i, val), val, lessThan(0));
    }
  }

  @Test
  public void testNegativeLong() throws Exception {
    for (int i = 0; i < COUNT; i++) {
      long val = negativeLong();
      assertThat(format("i=%d, val=%d", i, val), val, lessThan(0L));
    }
  }

  @Test
  public void testPositive() throws Exception {
    for (int i = 0; i < COUNT; i++) {
      int val = positive();
      assertThat(format("i=%d, val=%d", i, val), val, greaterThan(0));
    }
  }

  @Test
  public void testPositiveLong() throws Exception {
    for (int i = 0; i < COUNT; i++) {
      long val = positiveLong();
      assertThat(format("i=%d, val=%d", i, val), val, greaterThan(0L));
    }
  }

  @Test
  public void testNotNegative() throws Exception {
    for (int i = 0; i < COUNT; i++) {
      int number = notNegative();
      assertThat(format("i=%d, number=%d", i, number), number, greaterThanOrEqualTo(0));
    }
  }

  @Test
  public void testNotNegativeLong() throws Exception {
    for (int i = 0; i < COUNT; i++) {
      long number = notNegativeLong();
      assertThat(format("i=%d, number=%d", i, number), number, greaterThanOrEqualTo(0L));
    }
  }

  @Test
  public void testNotNegativeWithIntMax() throws Exception {
    for (int i = 0; i < COUNT; i++) {
      int max = 1 + this.rand.nextInt(Integer.MAX_VALUE);
      int number = notNegative(max);

      assertThat(number, allOf(greaterThanOrEqualTo(0), lessThan(max)));
    }
  }

  @Test
  public void testNotNegativeWithLongMax() throws Exception {
    for (int i = 0; i < COUNT; i++) {
      long max;
      do {
        max = this.rand.nextLong();
      } while (0L >= max);
      long number = notNegative(max);

      assertThat(number, allOf(greaterThanOrEqualTo(0L), lessThan(max)));
    }
  }

  @Test
  public void testNotZero() throws Exception {
    for (int i = 0; i < COUNT; i++) {
      assertNotEquals(0, notZero());
    }
  }

  @Test
  public void testNotZeroLong() throws Exception {
    for (int i = 0; i < COUNT; i++) {
      assertNotEquals(0L, notZeroLong());
    }
  }

  @Test
  public void testNotPositive() throws Exception {
    for (int i = 0; i < COUNT; i++) {
      assertThat(notPositive(), lessThanOrEqualTo(0));
    }
  }

  @Test
  public void testNotPositiveLong() throws Exception {
    for (int i = 0; i < COUNT; i++) {
      assertThat(notPositiveLong(), lessThanOrEqualTo(0L));
    }
  }

  @Test
  public void testInWithInt() throws Exception {
    for (int i = 0; i < COUNT; i++) {
      int min = this.rand.nextInt();
      int number = in(min, min + 1);
      assertEquals(min, number);

      int max = min + (int) Math.min(Integer.MAX_VALUE, (long) Integer.MAX_VALUE - min);
      exceptException(AssertionException.class, () -> in(max, min));

      number = in(min, max);
      assertThat(format("min=%d, max=%d, number=%d", min, max, number), number,
          allOf(greaterThanOrEqualTo(min), lessThan(max)));
    }
  }

  @Test
  public void testInWithLong() throws Exception {
    exceptException(IllegalArgumentException.class, () -> in(-1L, Long.MAX_VALUE));

    BigInteger longMax = BigInteger.valueOf(Long.MAX_VALUE);
    for (int i = 0; i < COUNT; i++) {
      long min = this.rand.nextLong();
      long number = in(min, min + 1L);
      assertEquals(min, number);

      long width;
      if (0 > longMax.compareTo(longMax.subtract(BigInteger.valueOf(min)))) {
        width = Long.MAX_VALUE;
      } else {
        width = Long.MAX_VALUE - min;
      }
      long max = min + 1L + notNegative(width);
      exceptException(AssertionException.class, () -> in(max, min));

      number = in(min, max);
      assertThat(format("min=%d, max=%d, number=%d", min, max, number), number,
          allOf(greaterThanOrEqualTo(min), lessThan(max)));
    }
  }

  @Test
  public void testNotIn() throws Exception {
    for (int i = 0; i < COUNT; i++) {
      int min = this.rand.nextInt();
      int max = 0 > min ? min + 1 + this.rand.nextInt(Integer.MAX_VALUE)
          : min + 1 + this.rand.nextInt(Integer.MAX_VALUE - min);
      exceptException(AssertionException.class, () -> notIn(max, min));

      int number = notIn(min, max);
      assertThat(number, anyOf(lessThan(min), greaterThanOrEqualTo(max)));
    }
  }

  @Test
  public void testLt() throws Exception {
    for (int i = 0; i < COUNT; i++) {
      int boundary = this.rand.nextInt();
      int number = lt(boundary);

      assertThat(format("number[%d] is not less than boundary[%d].", number, boundary), number, lessThan(boundary));
    }
  }

  @Test
  public void testLe() throws Exception {
    for (int i = 0; i < COUNT; i++) {
      int boundary = this.rand.nextInt();
      int number = le(boundary);

      assertThat(format("number[%d] is not less than or equal to boundary[%d].", number, boundary), number,
          lessThanOrEqualTo(boundary));
    }
  }

  @Test
  public void testGt() throws Exception {
    for (int i = 0; i < COUNT; i++) {
      int boundary = this.rand.nextInt();
      int number = gt(boundary);

      assertThat(format("number[%d] is not greater than boundary[%d].", number, boundary), number,
          greaterThan(boundary));
    }
  }

  @Test
  public void testGe() throws Exception {
    for (int i = 0; i < COUNT; i++) {
      int boundary = this.rand.nextInt();
      int number = ge(boundary);

      assertThat(format("number[%d] is not greater than or equal to boundary[%d].", number, boundary), number,
          greaterThanOrEqualTo(boundary));
    }
  }
}
