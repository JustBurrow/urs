package kr.lul.urs.util;

import static kr.lul.urs.util.RandomStrings.string;
import static kr.lul.urs.util.Tests.exceptException;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

public class RandomStringsTest {
  public static final int COUNT = Integer.MAX_VALUE / 4096;

  @Test(expected = UnsupportedOperationException.class)
  public void testNew() throws Exception {
    new RandomStrings() {
    };
    fail();
  }

  @Test
  public void testString() throws Exception {
    for (int i = 0; i < COUNT; i++) {
      String string = string();

      assertNotNull(string);
    }
  }

  @Test
  public void testStringWithLength() throws Exception {
    exceptException(AssertionException.class, () -> string(-1));
    assertEquals("", string(0));
    for (int i = 0; i < COUNT; i++) {
      int length = Randoms.notNegative(10000);
      String string = string(length);

      assertNotNull(string);
      assertEquals(length, string.length());
    }
  }

  @Test
  public void testStringWithLengthRange() throws Exception {
    exceptException(AssertionException.class, () -> string(0, 0));
    exceptException(AssertionException.class, () -> string(2, 1));
    exceptException(AssertionException.class, () -> string(-2, -1));

    for (int i = 0; i < COUNT; i++) {
      int min = Randoms.in(0, 1000);
      int max = Randoms.in(min + 1, 2000);
      String string = string(min, max);

      assertNotNull(string);
      assertThat(string.length(), allOf(greaterThanOrEqualTo(min), lessThan(max)));
    }
  }
}
