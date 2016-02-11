package kr.lul.urs.util;

import static kr.lul.urs.util.Conditions.assignable;
import static kr.lul.urs.util.Conditions.ge;
import static kr.lul.urs.util.Conditions.gt;
import static kr.lul.urs.util.Conditions.hasLength;
import static kr.lul.urs.util.Conditions.in;
import static kr.lul.urs.util.Conditions.isNull;
import static kr.lul.urs.util.Conditions.le;
import static kr.lul.urs.util.Conditions.length;
import static kr.lul.urs.util.Conditions.longer;
import static kr.lul.urs.util.Conditions.lt;
import static kr.lul.urs.util.Conditions.matches;
import static kr.lul.urs.util.Conditions.negative;
import static kr.lul.urs.util.Conditions.notNegative;
import static kr.lul.urs.util.Conditions.notNull;
import static kr.lul.urs.util.Conditions.notPositive;
import static kr.lul.urs.util.Conditions.positive;
import static kr.lul.urs.util.Conditions.shorter;
import static kr.lul.urs.util.Conditions.zero;
import static kr.lul.urs.util.Tests.exceptException;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ConditionsTest {
  @Test(expected = UnsupportedOperationException.class)
  public void test() throws Exception {
    new Conditions() {
    };
  }

  @Test
  public void testNegative() throws Exception {
    assertTrue(negative((byte) -1));
    assertTrue(negative((short) -1));
    assertTrue(negative(-1));
    assertTrue(negative(-1L));
    assertTrue(negative((float) -0.0000000001));
    assertTrue(negative(-0.00000000000000001));

    assertFalse(negative((byte) 0));
    assertFalse(negative((short) 0));
    assertFalse(negative(0));
    assertFalse(negative(0L));
    assertFalse(negative((float) 0.0));
    assertFalse(negative(0.0));

    assertFalse(negative((byte) 1));
    assertFalse(negative((short) 1));
    assertFalse(negative(1));
    assertFalse(negative(1L));
    assertFalse(negative((float) 0.00000000001));
    assertFalse(negative(0.000000000000000001));
  }

  @Test
  public void testZero() throws Exception {
    assertFalse(zero((byte) -1));
    assertFalse(zero((short) -1));
    assertFalse(zero(-1));
    assertFalse(zero(-1L));
    assertFalse(zero((float) -0.0000000000000001));
    assertFalse(zero(-0.00000000000000000000001));

    assertTrue(zero((byte) 0));
    assertTrue(zero((short) 0));
    assertTrue(zero(0));
    assertTrue(zero(0L));
    assertTrue(zero((float) 0.0));
    assertTrue(zero(0.0));

    assertFalse(zero((byte) 1));
    assertFalse(zero((short) 1));
    assertFalse(zero(1));
    assertFalse(zero(1L));
    assertFalse(zero((float) 0.00000000000001));
    assertFalse(zero(0.000000000000000000001));
  }

  @Test
  public void testPositive() throws Exception {
    assertFalse(positive((byte) -1));
    assertFalse(positive((short) -1));
    assertFalse(positive(-1));
    assertFalse(positive(-1L));
    assertFalse(positive((float) -0.0000000000000001));
    assertFalse(positive(-0.00000000000000000000001));

    assertFalse(positive((byte) 0));
    assertFalse(positive((short) 0));
    assertFalse(positive(0));
    assertFalse(positive(0L));
    assertFalse(positive((float) 0.0));
    assertFalse(positive(0.0));

    assertTrue(positive((byte) 1));
    assertTrue(positive((short) 1));
    assertTrue(positive(1));
    assertTrue(positive(1L));
    assertTrue(positive((float) 0.00000000000001));
    assertTrue(positive(0.000000000000000000001));
  }

  @Test
  public void testNotNegative() throws Exception {
    assertFalse(notNegative((byte) -1));
    assertFalse(notNegative((short) -1));
    assertFalse(notNegative(-1));
    assertFalse(notNegative(-1L));
    assertFalse(notNegative((float) -0.0000000000000001));
    assertFalse(notNegative(-0.00000000000000000000001));

    assertTrue(notNegative((byte) 0));
    assertTrue(notNegative((short) 0));
    assertTrue(notNegative(0));
    assertTrue(notNegative(0L));
    assertTrue(notNegative((float) 0.0));
    assertTrue(notNegative(0.0));

    assertTrue(notNegative((byte) 1));
    assertTrue(notNegative((short) 1));
    assertTrue(notNegative(1));
    assertTrue(notNegative(1L));
    assertTrue(notNegative((float) 0.00000000000001));
    assertTrue(notNegative(0.000000000000000000001));
  }

  @Test
  public void testNotPositive() throws Exception {
    assertTrue(notPositive((byte) -1));
    assertTrue(notPositive((short) -1));
    assertTrue(notPositive(-1));
    assertTrue(notPositive(-1L));
    assertTrue(notPositive((float) -0.0000000000000001));
    assertTrue(notPositive(-0.00000000000000000000001));

    assertTrue(notPositive((byte) 0));
    assertTrue(notPositive((short) 0));
    assertTrue(notPositive(0));
    assertTrue(notPositive(0L));
    assertTrue(notPositive((float) 0.0));
    assertTrue(notPositive(0.0));

    assertFalse(notPositive((byte) 1));
    assertFalse(notPositive((short) 1));
    assertFalse(notPositive(1));
    assertFalse(notPositive(1L));
    assertFalse(notPositive((float) 0.00000000000001));
    assertFalse(notPositive(0.000000000000000000001));
  }

  @Test
  public void testInWithIlleagalRange() throws Exception {
    exceptException(() -> in((byte) -1, (byte) 0, (byte) 0), IllegalArgumentException.class);
    exceptException(() -> in((short) -1, (short) 0, (short) 0), IllegalArgumentException.class);
    exceptException(() -> in(-1, 0, 0), IllegalArgumentException.class);
    exceptException(() -> in(-1L, 0L, 0L), IllegalArgumentException.class);
    exceptException(() -> in(-0.1F, 0.0F, 0.0F), IllegalArgumentException.class);
    exceptException(() -> in(-0.1, 0.0, 0.0), IllegalArgumentException.class);

    exceptException(() -> in((byte) -1, (byte) 0, (byte) -1), IllegalArgumentException.class);
    exceptException(() -> in((short) -1, (short) 0, (short) -1), IllegalArgumentException.class);
    exceptException(() -> in(-1, 0, -1), IllegalArgumentException.class);
    exceptException(() -> in(-1L, 0L, -1L), IllegalArgumentException.class);
    exceptException(() -> in(-0.1F, 0.0F, -0.1F), IllegalArgumentException.class);
    exceptException(() -> in(-0.1, 0.0, -0.1), IllegalArgumentException.class);

    exceptException(() -> in((byte) 0, (byte) 0, (byte) 0), IllegalArgumentException.class);
    exceptException(() -> in((short) 0, (short) 0, (short) 0), IllegalArgumentException.class);
    exceptException(() -> in(0, 0, 0), IllegalArgumentException.class);
    exceptException(() -> in(0L, 0L, 0L), IllegalArgumentException.class);
    exceptException(() -> in(0.0F, 0.0F, 0.0F), IllegalArgumentException.class);
    exceptException(() -> in(0.0, 0.0, 0.0), IllegalArgumentException.class);

    exceptException(() -> in((byte) 0, (byte) 0, (byte) -1), IllegalArgumentException.class);
    exceptException(() -> in((short) 0, (short) 0, (short) -1), IllegalArgumentException.class);
    exceptException(() -> in(0, 0, -1), IllegalArgumentException.class);
    exceptException(() -> in(0L, 0L, -1L), IllegalArgumentException.class);
    exceptException(() -> in(0.0F, 0.0F, -0.1F), IllegalArgumentException.class);
    exceptException(() -> in(0.0, 0.0, -0.1), IllegalArgumentException.class);

    exceptException(() -> in((byte) 1, (byte) 0, (byte) 0), IllegalArgumentException.class);
    exceptException(() -> in((short) 1, (short) 0, (short) 0), IllegalArgumentException.class);
    exceptException(() -> in(1, 0, 0), IllegalArgumentException.class);
    exceptException(() -> in(1L, 0L, 0L), IllegalArgumentException.class);
    exceptException(() -> in(0.1F, 0.0F, 0.0F), IllegalArgumentException.class);
    exceptException(() -> in(0.1, 0.0, 0.0), IllegalArgumentException.class);

    exceptException(() -> in((byte) 1, (byte) 0, (byte) -1), IllegalArgumentException.class);
    exceptException(() -> in((short) 1, (short) 0, (short) -1), IllegalArgumentException.class);
    exceptException(() -> in(1, 0, -1), IllegalArgumentException.class);
    exceptException(() -> in(1L, 0L, -1L), IllegalArgumentException.class);
    exceptException(() -> in(0.1F, 0.0F, -0.1F), IllegalArgumentException.class);
    exceptException(() -> in(0.1, 0.0, -0.1), IllegalArgumentException.class);
  }

  @Test
  public void testIn() throws Exception {
    assertFalse(in((byte) -1, (byte) 0, (byte) 1));
    assertFalse(in((short) -1, (short) 0, (short) 1));
    assertFalse(in(-1, 0, 1));
    assertFalse(in(-1L, 0L, 1L));
    assertFalse(in(-0.00000001F, 0.0F, 0.000001F));
    assertFalse(in(-0.000000001, 0.0, 0.0000001));

    assertTrue(in((byte) 0, (byte) 0, (byte) 1));
    assertTrue(in((short) 0, (short) 0, (short) 1));
    assertTrue(in(0, 0, 1));
    assertTrue(in(0L, 0L, 1L));
    assertTrue(in(0.0F, 0.0F, 0.0000000001F));
    assertTrue(in(0.0, 0.0, 0.000000000001));

    assertTrue(in((byte) 1, (byte) 0, (byte) 2));
    assertTrue(in((short) 1, (short) 0, (short) 2));
    assertTrue(in(1, 0, 2));
    assertTrue(in(1L, 0, 2L));
    assertTrue(in(0.1F, 0.0F, 0.2F));
    assertTrue(in(0.1, 0.0, 0.2));

    assertFalse(in((byte) 1, (byte) 0, (byte) 1));
    assertFalse(in((short) 1, (short) 0, (short) 1));
    assertFalse(in(1, 0, 1));
    assertFalse(in(1L, 0L, 1L));
    assertFalse(in(0.1F, 0.0F, 0.1F));
    assertFalse(in(0.1, 0.0, 0.1));
  }

  @Test
  public void testLt() throws Exception {
    assertTrue(lt((byte) -1, (byte) 0));
    assertTrue(lt((short) -1, (short) 0));
    assertTrue(lt(-1, 0));
    assertTrue(lt(-1L, 0));
    assertTrue(lt(-0.1F, 0.0F));
    assertTrue(lt(-0.1, 0.0));

    assertFalse(lt((byte) 0, (byte) 0));
    assertFalse(lt((short) 0, (short) 0));
    assertFalse(lt(0, 0));
    assertFalse(lt(0L, 0));
    assertFalse(lt(0.0F, 0.0F));
    assertFalse(lt(0.0, 0.0));

    assertFalse(lt((byte) 1, (byte) 0));
    assertFalse(lt((short) 1, (short) 0));
    assertFalse(lt(1, 0));
    assertFalse(lt(1L, 0));
    assertFalse(lt(0.1F, 0.0F));
    assertFalse(lt(0.1, 0.0));
  }

  @Test
  public void testLe() throws Exception {
    assertTrue(le((byte) -1, (byte) 0));
    assertTrue(le((short) -1, (short) 0));
    assertTrue(le(-1, 0));
    assertTrue(le(-1L, 0));
    assertTrue(le(-0.1F, 0.0F));
    assertTrue(le(-0.1, 0.0));

    assertTrue(le((byte) 0, (byte) 0));
    assertTrue(le((short) 0, (short) 0));
    assertTrue(le(0, 0));
    assertTrue(le(0L, 0));
    assertTrue(le(0.0F, 0.0F));
    assertTrue(le(0.0, 0.0));

    assertFalse(le((byte) 1, (byte) 0));
    assertFalse(le((short) 1, (short) 0));
    assertFalse(le(1, 0));
    assertFalse(le(1L, 0));
    assertFalse(le(0.1F, 0.0F));
    assertFalse(le(0.1, 0.0));
  }

  @Test
  public void testGt() throws Exception {
    assertFalse(gt((byte) -1, (byte) 0));
    assertFalse(gt((short) -1, (short) 0));
    assertFalse(gt(-1, 0));
    assertFalse(gt(-1L, 0L));
    assertFalse(gt(-0.1F, 0.0F));
    assertFalse(gt(-0.1, 0.0));

    assertFalse(gt((byte) 0, (byte) 0));
    assertFalse(gt((short) 0, (short) 0));
    assertFalse(gt(0, 0));
    assertFalse(gt(0L, 0L));
    assertFalse(gt(0.0F, 0.0F));
    assertFalse(gt(0.0, 0.0));

    assertTrue(gt((byte) 1, (byte) 0));
    assertTrue(gt((short) 1, (short) 0));
    assertTrue(gt(1, 0));
    assertTrue(gt(1L, 0L));
    assertTrue(gt(0.1F, 0.0F));
    assertTrue(gt(0.1, 0.0));
  }

  @Test
  public void testGe() throws Exception {
    assertFalse(ge((byte) -1, (byte) 0));
    assertFalse(ge((short) -1, (short) 0));
    assertFalse(ge(-1, 0));
    assertFalse(ge(-1L, 0L));
    assertFalse(ge(-0.1F, 0.0F));
    assertFalse(ge(-0.1, 0.0));

    assertTrue(ge((byte) 0, (byte) 0));
    assertTrue(ge((short) 0, (short) 0));
    assertTrue(ge(0, 0));
    assertTrue(ge(0L, 0L));
    assertTrue(ge(0.0F, 0.0F));
    assertTrue(ge(0.0, 0.0));

    assertTrue(ge((byte) 1, (byte) 0));
    assertTrue(ge((short) 1, (short) 0));
    assertTrue(ge(1, 0));
    assertTrue(ge(1L, 0L));
    assertTrue(ge(0.1F, 0.0F));
    assertTrue(ge(0.1, 0.0));
  }

  @Test
  public void testIsNull() throws Exception {
    assertTrue(isNull(null));
    assertFalse(isNull(new Object()));
  }

  @Test
  public void testNotNull() throws Exception {
    assertFalse(notNull(null));
    assertTrue(notNull(new Object()));
  }

  @Test
  public void testAssignable() throws Exception {
    class A {
    }
    assertTrue(assignable(new Object(), Object.class));
    assertTrue(assignable(new A(), Object.class));
    assertFalse(assignable(new Object(), A.class));
  }

  @Test
  public void testHasLengthWithNull() throws Exception {
    assertFalse(hasLength(null));
    assertFalse(hasLength(""));
    assertTrue(hasLength("1"));
  }

  @Test
  public void testLengthForException() throws Exception {
    exceptException(() -> length(null, 0), IllegalArgumentException.class);
    exceptException(() -> length("", -1), IllegalArgumentException.class);
  }

  @Test
  public void testLength() throws Exception {
    assertTrue(length("", 0));
    assertTrue(length(".", 1));
    assertFalse(length("", 1));
    assertFalse(length(".", 0));
  }

  @Test
  public void testShorterForException() throws Exception {
    exceptException(() -> shorter(null, -1), IllegalArgumentException.class);
    exceptException(() -> shorter(null, 0), IllegalArgumentException.class);
  }

  @Test
  public void testShorter() throws Exception {
    assertTrue(shorter("", 0));
    assertTrue(shorter("", 1));
    assertFalse(shorter("1", 0));
    assertTrue(shorter("1", 1));
    assertTrue(shorter("1", 2));
  }

  @Test
  public void testLongerForException() throws Exception {
    exceptException(() -> longer(null, -1), IllegalArgumentException.class);
    exceptException(() -> longer(null, 0), IllegalArgumentException.class);
  }

  @Test
  public void testLonger() throws Exception {
    assertTrue(longer("", 0));
    assertFalse(longer("", 1));
    assertTrue(longer("1", 0));
    assertTrue(longer("1", 1));
    assertFalse(longer("1", 2));
  }

  @Test
  public void testLengthWithRangeForException() throws Exception {
    exceptException(() -> length(null, 0, 0), IllegalArgumentException.class);
    exceptException(() -> length(null, 0, -1), IllegalArgumentException.class);
    exceptException(() -> length(null, -1, -1), IllegalArgumentException.class);
    exceptException(() -> length(null, 0, 1), IllegalArgumentException.class);
    exceptException(() -> length(null, 1, 1), IllegalArgumentException.class);

    exceptException(() -> length("", 0, 0), IllegalArgumentException.class);
    exceptException(() -> length("", 0, -1), IllegalArgumentException.class);
    exceptException(() -> length("", -1, -1), IllegalArgumentException.class);
    exceptException(() -> length("", 1, 1), IllegalArgumentException.class);

    exceptException(() -> length("1", 0, 0), IllegalArgumentException.class);
    exceptException(() -> length("1", 0, -1), IllegalArgumentException.class);
    exceptException(() -> length("1", -1, -1), IllegalArgumentException.class);
    exceptException(() -> length("1", 1, 1), IllegalArgumentException.class);
  }

  @Test
  public void testLengthWithRange() throws Exception {
    assertTrue(length("", 0, 1));
    assertFalse(length("", 1, 2));
    assertTrue(length("a", 1, 2));
    assertTrue(length("aa", 1, 2));
    assertFalse(length("aaa", 1, 2));
  }

  @Test
  public void testMatchesWithIllegals() throws Exception {
    exceptException(() -> matches(null, null), IllegalArgumentException.class);
    exceptException(() -> matches(null, ".*"), IllegalArgumentException.class);
    exceptException(() -> matches("", "{"), IllegalArgumentException.class);
  }

  @Test
  public void testMatches() throws Exception {
    assertTrue(matches("", ".*"));
    assertTrue(matches("a", ".*"));
    assertFalse(matches("a", "\\d*"));
  }
}
