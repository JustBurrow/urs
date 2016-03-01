package kr.lul.urs.util;

import static java.lang.String.format;
import static kr.lul.urs.util.Strings.from;
import static kr.lul.urs.util.Strings.random;
import static kr.lul.urs.util.Tests.exceptException;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

public class StringsTest {
  public static final int COUNT = Integer.MAX_VALUE / 4096;

  @Test(expected = UnsupportedOperationException.class)
  public void testNew() throws Exception {
    new Strings() {
    };
    fail();
  }

  @Test
  public void testRandom() throws Exception {
    for (int i = 0; i < COUNT; i++) {
      String string = random();

      assertNotNull(string);
    }
  }

  @Test
  public void testRandomWithLength() throws Exception {
    exceptException(AssertionException.class, () -> random(-1));
    assertEquals("", random(0));
    for (int i = 0; i < COUNT; i++) {
      int length = Randoms.notNegative(200);
      String string = random(length);

      assertNotNull(string);
      assertEquals(length, string.length());
    }
  }

  @Test
  public void testRandomWithLengthRange() throws Exception {
    exceptException(AssertionException.class, () -> random(0, 0));
    exceptException(AssertionException.class, () -> random(2, 1));
    exceptException(AssertionException.class, () -> random(-2, -1));

    for (int i = 0; i < COUNT; i++) {
      int min = Randoms.in(0, 20);
      int max = Randoms.in(min + 1, 200);
      String string = random(min, max);

      assertNotNull(string);
      assertThat(string.length(), allOf(greaterThanOrEqualTo(min), lessThan(max)));
    }
  }

  @Test
  public void testFromWithCodeRange() throws Exception {
    for (int i = 0; i < COUNT; i++) {
      char min = (char) Randoms.in('\u0000', '\uFFFF');
      char max = (char) Randoms.in(min + 1, '\uFFFF' + 1);
      String string = from(min, max);

      assertNotNull(string);
      string.chars().forEach(c -> assertThat((char) c, allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))));

      from(min, min).chars().forEach(c -> assertEquals(min, (char) c));

      exceptException(format("min=[%c[%d]], max=[%c[%d]]", min, (int) min, max, (int) max), AssertionException.class,
          () -> from(max, min));
    }
  }

  @Test
  public void testFromWithLengthAndCodeRange() throws Exception {
    for (int i = 0; i < COUNT; i++) {
      int length = Randoms.notNegative(200);
      char min = (char) Randoms.in('\u0000', '\uFFFF');
      char max = (char) Randoms.in(min + 1, '\uFFFF' + 1);
      String string = from(length, min, max);

      assertNotNull(string);
      assertEquals(length, string.length());
      string.chars().forEach(c -> assertThat((char) c, allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))));

      string = from(length, min, min);
      assertEquals(length, string.length());
      string.chars().forEach(c -> assertEquals(min, (char) c));

      exceptException(format("length=%d, min=[%c[%d]], max=[%c[%d]]", length, min, (int) min, max, (int) max),
          AssertionException.class,
          () -> from(length, max, min));
    }
  }

  @Test
  public void testFromWithLengthRangeAndCodeRange() throws Exception {
    for (int i = 0; i < COUNT; i++) {
      int minLength = Randoms.notNegative(20);
      int maxLength = Randoms.in(minLength + 1, 200);
      char minCode = (char) Randoms.in('\u0000', '\uFFFF');
      char maxCode = (char) Randoms.in(minCode + 1, '\uFFFF' + 1);
      String string = from(minLength, maxLength, minCode, maxCode);

      assertNotNull(string);
      assertThat(string.length(), allOf(greaterThanOrEqualTo(minLength), lessThan(maxLength)));
      string.chars()
          .forEach(c -> assertThat((char) c, allOf(greaterThanOrEqualTo(minCode), lessThanOrEqualTo(maxCode))));

      string = from(minLength, minLength + 1, minCode, maxCode);
      assertNotNull(string);
      assertEquals(minLength, string.length());
      string.chars()
          .forEach(c -> assertThat((char) c, allOf(greaterThanOrEqualTo(minCode), lessThanOrEqualTo(maxCode))));

      exceptException(format("minLength=%d, maxLength=%d, min=[%c[%d]], max=[%c[%d]]", minLength, maxLength, minCode,
          (int) minCode, maxCode, (int) maxCode), AssertionException.class,
          () -> from(maxLength, minLength, minCode, maxCode));
    }
  }
}
