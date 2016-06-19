package kr.lul.urs.util;

import static kr.lul.urs.util.Asserts.after;
import static kr.lul.urs.util.Asserts.assignable;
import static kr.lul.urs.util.Asserts.before;
import static kr.lul.urs.util.Asserts.ge;
import static kr.lul.urs.util.Asserts.gt;
import static kr.lul.urs.util.Asserts.hasLength;
import static kr.lul.urs.util.Asserts.in;
import static kr.lul.urs.util.Asserts.isNull;
import static kr.lul.urs.util.Asserts.le;
import static kr.lul.urs.util.Asserts.length;
import static kr.lul.urs.util.Asserts.longer;
import static kr.lul.urs.util.Asserts.lt;
import static kr.lul.urs.util.Asserts.matches;
import static kr.lul.urs.util.Asserts.negative;
import static kr.lul.urs.util.Asserts.notAfter;
import static kr.lul.urs.util.Asserts.notBefore;
import static kr.lul.urs.util.Asserts.notNegative;
import static kr.lul.urs.util.Asserts.notNull;
import static kr.lul.urs.util.Asserts.notPositive;
import static kr.lul.urs.util.Asserts.positive;
import static kr.lul.urs.util.Asserts.shorter;
import static kr.lul.urs.util.Asserts.zero;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.fail;

import java.time.Instant;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

public class AssertsTest extends AbstractTest {
  private String message;

  @Before
  public void setUp() throws Exception {
    this.message = RandomStringUtils.randomAlphanumeric(10);
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  @Test(expected = UnsupportedOperationException.class)
  public void test() {
    new Asserts() {
    };
    fail();
  }

  @Test
  public void testNegative() throws Exception {
    final String message = "expected message@" + Instant.now();

    negative((byte) -1);
    negative((short) -1);
    negative(-1);
    negative(-1L);
    negative(-0.1F);
    negative(-0.1);

    negative((byte) -1, null);
    negative((short) -1, null);
    negative(-1, null);
    negative(-1L, null);
    negative(-0.1F, null);
    negative(-0.1, null);

    negative((byte) -1, message);
    negative((short) -1, message);
    negative(-1, message);
    negative(-1L, message);
    negative(-0.1F, message);
    negative(-0.1, message);

    assertThatThrownBy(() -> negative((byte) 0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> negative((byte) 0, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> negative((short) 0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> negative((short) 0, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> negative(0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> negative(0, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> negative(0L)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> negative(0L, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> negative(0.0F)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> negative(0.0F, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> negative(0.0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> negative(0.0, message)).isInstanceOf(AssertionException.class).hasMessage(message);

    assertThatThrownBy(() -> negative((byte) 1)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> negative((byte) 1, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> negative((short) 1)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> negative((short) 1, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> negative(1)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> negative(1, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> negative(1L)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> negative(1L, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> negative(1.0F)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> negative(1.0F, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> negative(1.0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> negative(1.0, message)).isInstanceOf(AssertionException.class).hasMessage(message);
  }

  @Test
  public void testZero() throws Exception {
    final String message = "expected message@" + Instant.now();

    assertThatThrownBy(() -> zero((byte) -1)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> zero((byte) -1, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> zero((short) -1)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> zero((short) -1, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> zero(-1)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> zero(-1, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> zero(-1L)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> zero(-1L, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> zero(-1.0F)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> zero(-1.0F, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> zero(-1.0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> zero(-1.0, message)).isInstanceOf(AssertionException.class).hasMessage(message);

    zero((byte) 0);
    zero((short) 0);
    zero(0);
    zero(0L);
    zero(0.0F);
    zero(0.0);

    zero((byte) 0, null);
    zero((short) 0, null);
    zero(0, null);
    zero(0L, null);
    zero(0.0F, null);
    zero(0.0, null);

    zero((byte) 0, message);
    zero((short) 0, message);
    zero(0, message);
    zero(0L, message);
    zero(0.0F, message);
    zero(0.0, message);

    assertThatThrownBy(() -> zero((byte) 1)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> zero((byte) 1, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> zero((short) 1)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> zero((short) 1, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> zero(1)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> zero(1, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> zero(1L)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> zero(1L, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> zero(1.0F)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> zero(1.0F, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> zero(1.0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> zero(1.0, message)).isInstanceOf(AssertionException.class).hasMessage(message);
  }

  @Test
  public void testPositive() throws Exception {
    final String message = "expected message@" + Instant.now();

    assertThatThrownBy(() -> positive((byte) -1)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> positive((byte) -1, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> positive((short) -1)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> positive((short) -1, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> positive(-1)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> positive(-1, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> positive(-1L)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> positive(-1L, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> positive(-1.0F)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> positive(-1.0F, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> positive(-1.0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> positive(-1.0, message)).isInstanceOf(AssertionException.class).hasMessage(message);

    assertThatThrownBy(() -> positive((byte) 0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> positive((byte) 0, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> positive((short) 0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> positive((short) 0, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> positive(0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> positive(0, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> positive(0L)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> positive(0L, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> positive(0.0F)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> positive(0.0F, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> positive(0.0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> positive(0.0, message)).isInstanceOf(AssertionException.class).hasMessage(message);

    positive((byte) 1);
    positive((short) 1);
    positive(1);
    positive(1L);
    positive(0.1F);
    positive(0.1);

    positive((byte) 1, null);
    positive((short) 1, null);
    positive(1, null);
    positive(1L, null);
    positive(0.1F, null);
    positive(0.1, null);

    positive((byte) 1, message);
    positive((short) 1, message);
    positive(1, message);
    positive(1L, message);
    positive(0.1F, message);
    positive(0.1, message);
  }

  @Test
  public void testNotNegative() throws Exception {
    final String message = "expected message@" + Instant.now();

    assertThatThrownBy(() -> notNegative((byte) -1)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> notNegative((byte) -1, message)).isInstanceOf(AssertionException.class)
        .hasMessage(message);
    assertThatThrownBy(() -> notNegative((short) -1)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> notNegative((short) -1, message)).isInstanceOf(AssertionException.class)
        .hasMessage(message);
    assertThatThrownBy(() -> notNegative(-1)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> notNegative(-1, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> notNegative(-1L)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> notNegative(-1L, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> notNegative(-1.0F)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> notNegative(-1.0F, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> notNegative(-1.0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> notNegative(-1.0, message)).isInstanceOf(AssertionException.class).hasMessage(message);

    notNegative((byte) 0);
    notNegative((short) 0);
    notNegative(0);
    notNegative(0L);
    notNegative(0.0F);
    notNegative(0.0);

    notNegative((byte) 0, null);
    notNegative((short) 0, null);
    notNegative(0, null);
    notNegative(0L, null);
    notNegative(0.0F, null);
    notNegative(0.0, null);

    notNegative((byte) 0, message);
    notNegative((short) 0, message);
    notNegative(0, message);
    notNegative(0L, message);
    notNegative(0.0F, message);
    notNegative(0.0, message);

    notNegative((byte) 1);
    notNegative((short) 1);
    notNegative(1);
    notNegative(1L);
    notNegative(0.1F);
    notNegative(0.1);

    notNegative((byte) 1, null);
    notNegative((short) 1, null);
    notNegative(1, null);
    notNegative(1L, null);
    notNegative(0.1F, null);
    notNegative(0.1, null);

    notNegative((byte) 1, message);
    notNegative((short) 1, message);
    notNegative(1, message);
    notNegative(1L, message);
    notNegative(0.1F, message);
    notNegative(0.1, message);
  }

  @Test
  public void testNotPositive() throws Exception {
    final String message = "expected message@" + Instant.now();

    notPositive((byte) -1);
    notPositive((short) -1);
    notPositive(-1);
    notPositive(-1L);
    notPositive(-0.1F);
    notPositive(-0.1);

    notPositive((byte) -1, null);
    notPositive((short) -1, null);
    notPositive(-1, null);
    notPositive(-1L, null);
    notPositive(-0.1F, null);
    notPositive(-0.1, null);

    notPositive((byte) -1, message);
    notPositive((short) -1, message);
    notPositive(-1, message);
    notPositive(-1L, message);
    notPositive(-0.1F, message);
    notPositive(-0.1, message);

    notPositive((byte) 0);
    notPositive((short) 0);
    notPositive(0);
    notPositive(0L);
    notPositive(0.0F);
    notPositive(0.0);

    notPositive((byte) 0, null);
    notPositive((short) 0, null);
    notPositive(0, null);
    notPositive(0L, null);
    notPositive(0.0F, null);
    notPositive(0.0, null);

    notPositive((byte) 0, message);
    notPositive((short) 0, message);
    notPositive(0, message);
    notPositive(0L, message);
    notPositive(0.0F, message);
    notPositive(0.0, message);

    assertThatThrownBy(() -> notPositive((byte) 1)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> notPositive((byte) 1, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> notPositive((short) 1)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> notPositive((short) 1, message)).isInstanceOf(AssertionException.class)
        .hasMessage(message);
    assertThatThrownBy(() -> notPositive(1)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> notPositive(1, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> notPositive(1L)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> notPositive(1L, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> notPositive(1.0F)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> notPositive(1.0F, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> notPositive(1.0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> notPositive(1.0, message)).isInstanceOf(AssertionException.class).hasMessage(message);
  }

  @Test
  public void testInForException() throws Exception {
    assertThatThrownBy(() -> in((byte) 0, (byte) 0, (byte) 0)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> in((short) 0, (short) 0, (short) 0)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> in(0, 0, 0)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> in(0L, 0L, 0L)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> in(0.0F, 0.0F, 0.0F)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> in(0.0, 0.0, 0.0)).isInstanceOf(IllegalArgumentException.class);

    assertThatThrownBy(() -> in((byte) -1, (byte) 0, (byte) -1)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> in((short) -1, (short) 0, (short) -1)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> in(-1, 0, -1)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> in(-1L, 0L, -1L)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> in(-1.0F, 0.0F, -1.0F)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> in(-1.0, 0.0, -1.0)).isInstanceOf(IllegalArgumentException.class);

    assertThatThrownBy(() -> in((byte) 0, (byte) 0, (byte) -1)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> in((short) 0, (short) 0, (short) -1)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> in(0, 0, -1)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> in(0L, 0L, -1L)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> in(0.0F, 0.0F, -1.0F)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> in(0.0, 0.0, -1.0)).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void testIn() throws Exception {
    final String message = "expected message@" + Instant.now();

    assertThatThrownBy(() -> in((byte) -1, (byte) 0, (byte) 1)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> in((byte) -1, (byte) 0, (byte) 1, message)).isInstanceOf(AssertionException.class)
        .hasMessage(message);
    assertThatThrownBy(() -> in((short) -1, (short) 0, (short) 1)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> in((short) -1, (short) 0, (short) 1, message)).isInstanceOf(AssertionException.class)
        .hasMessage(message);
    assertThatThrownBy(() -> in(-1, 0, 1)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> in(-1, 0, 1, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> in(-1L, 0L, 1L)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> in(-1L, 0L, 1L, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> in(-1.0F, 0.0F, 1.0F)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> in(-1.0F, 0.0F, 1.0F, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> in(-1.0, 0.0, 1.0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> in(-1.0, 0.0, 1.0, message)).isInstanceOf(AssertionException.class).hasMessage(message);

    in((byte) 0, (byte) 0, (byte) 2);
    in((short) 0, (short) 0, (short) 2);
    in(0, 0, 2);
    in(0L, 0L, 2L);
    in(0.0F, 0.0F, 2.0F);
    in(0.0, 0.0, 2.0);

    in((byte) 0, (byte) 0, (byte) 2, null);
    in((short) 0, (short) 0, (short) 2, null);
    in(0, 0, 2, null);
    in(0L, 0L, 2L, null);
    in(0.0F, 0.0F, 2.0F, null);
    in(0.0, 0.0, 2.0, null);

    in((byte) 0, (byte) 0, (byte) 2, message);
    in((short) 0, (short) 0, (short) 2, message);
    in(0, 0, 2, message);
    in(0L, 0L, 2L, message);
    in(0.0F, 0.0F, 2.0F, message);
    in(0.0, 0.0, 2.0, message);

    in((byte) 1, (byte) 0, (byte) 2);
    in((short) 1, (short) 0, (short) 2);
    in(1, 0, 2);
    in(1L, 0L, 2L);
    in(1.0F, 0.0F, 2.0F);
    in(1.0, 0.0, 2.0);

    in((byte) 1, (byte) 0, (byte) 2, null);
    in((short) 1, (short) 0, (short) 2, null);
    in(1, 0, 2, null);
    in(1L, 0L, 2L, null);
    in(1.0F, 0.0F, 2.0F, null);
    in(1.0, 0.0, 2.0, null);

    in((byte) 1, (byte) 0, (byte) 2, message);
    in((short) 1, (short) 0, (short) 2, message);
    in(1, 0, 2, message);
    in(1L, 0L, 2L, message);
    in(1.0F, 0.0F, 2.0F, message);
    in(1.0, 0.0, 2.0, message);

    assertThatThrownBy(() -> in((byte) 2, (byte) 0, (byte) 2)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> in((byte) 2, (byte) 0, (byte) 2, message)).isInstanceOf(AssertionException.class)
        .hasMessage(message);
    assertThatThrownBy(() -> in((short) 2, (short) 0, (short) 2)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> in((short) 2, (short) 0, (short) 2, message)).isInstanceOf(AssertionException.class)
        .hasMessage(message);
    assertThatThrownBy(() -> in(2, 0, 2)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> in(2, 0, 2, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> in(2L, 0L, 2L)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> in(2L, 0L, 2L, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> in(2.0F, 0.0F, 2.0F)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> in(2.0F, 0.0F, 2.0F, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> in(2.0, 0.0, 2.0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> in(2.0, 0.0, 2.0, message)).isInstanceOf(AssertionException.class).hasMessage(message);
  }

  @Test
  public void testLt() throws Exception {
    final String message = "expected message@" + Instant.now();

    lt((byte) -1, (byte) 0);
    lt((short) -1, (short) 0);
    lt(-1, 0);
    lt(-1L, 0L);
    lt(-1.0F, 0.0F);
    lt(-1.0, 0.0);

    lt((byte) -1, (byte) 0, null);
    lt((short) -1, (short) 0, null);
    lt(-1, 0, null);
    lt(-1L, 0L, null);
    lt(-1.0F, 0.0F, null);
    lt(-1.0, 0.0, null);

    lt((byte) -1, (byte) 0, message);
    lt((short) -1, (short) 0, message);
    lt(-1, 0, message);
    lt(-1L, 0L, message);
    lt(-1.0F, 0.0F, message);
    lt(-1.0, 0.0, message);

    assertThatThrownBy(() -> lt((byte) 0, (byte) 0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> lt((byte) 0, (byte) 0, message)).isInstanceOf(AssertionException.class)
        .hasMessage(message);
    assertThatThrownBy(() -> lt((short) 0, (short) 0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> lt((short) 0, (short) 0, message)).isInstanceOf(AssertionException.class)
        .hasMessage(message);
    assertThatThrownBy(() -> lt(0, 0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> lt(0, 0, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> lt(0L, 0L)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> lt(0L, 0L, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> lt(0.0F, 0.0F)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> lt(0.0F, 0.0F, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> lt(0.0, 0.0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> lt(0.0, 0.0, message)).isInstanceOf(AssertionException.class).hasMessage(message);

    assertThatThrownBy(() -> lt((byte) 1, (byte) 0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> lt((byte) 1, (byte) 0, message)).isInstanceOf(AssertionException.class)
        .hasMessage(message);
    assertThatThrownBy(() -> lt((short) 1, (short) 0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> lt((short) 1, (short) 0, message)).isInstanceOf(AssertionException.class)
        .hasMessage(message);
    assertThatThrownBy(() -> lt(1, 0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> lt(1, 0, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> lt(1L, 0L)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> lt(1L, 0L, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> lt(1.0F, 0.0F)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> lt(1.0F, 0.0F, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> lt(1.0, 0.0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> lt(1.0, 0.0, message)).isInstanceOf(AssertionException.class).hasMessage(message);
  }

  @Test
  public void testLe() throws Exception {
    final String message = "expected message@" + Instant.now();

    le((byte) -1, (byte) 0);
    le((short) -1, (short) 0);
    le(-1, 0);
    le(-1L, 0L);
    le(-1.0F, 0.0F);
    le(-1.0, 0.0);

    le((byte) -1, (byte) 0, null);
    le((short) -1, (short) 0, null);
    le(-1, 0, null);
    le(-1L, 0L, null);
    le(-1.0F, 0.0F, null);
    le(-1.0, 0.0, null);

    le((byte) -1, (byte) 0, message);
    le((short) -1, (short) 0, message);
    le(-1, 0, message);
    le(-1L, 0L, message);
    le(-1.0F, 0.0F, message);
    le(-1.0, 0.0, message);

    le((byte) 0, (byte) 0);
    le((short) 0, (short) 0);
    le(0, 0);
    le(0L, 0L);
    le(0.0F, 0.0F);
    le(0.0, 0.0);

    le((byte) 0, (byte) 0, null);
    le((short) 0, (short) 0, null);
    le(0, 0, null);
    le(0L, 0L, null);
    le(0.0F, 0.0F, null);
    le(0.0, 0.0, null);

    le((byte) 0, (byte) 0, message);
    le((short) 0, (short) 0, message);
    le(0, 0, message);
    le(0L, 0L, message);
    le(0.0F, 0.0F, message);
    le(0.0, 0.0, message);

    assertThatThrownBy(() -> le((short) 1, (short) 0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> le((short) 1, (short) 0, message)).isInstanceOf(AssertionException.class)
        .hasMessage(message);
    assertThatThrownBy(() -> le((byte) 1, (byte) 0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> le((byte) 1, (byte) 0, message)).isInstanceOf(AssertionException.class)
        .hasMessage(message);
    assertThatThrownBy(() -> le(1, 0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> le(1, 0, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> le(1L, 0L)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> le(1L, 0L, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> le(1.0F, 0.0F)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> le(1.0F, 0.0F, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> le(1.0, 0.0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> le(1.0, 0.0, message)).isInstanceOf(AssertionException.class).hasMessage(message);
  }

  @Test
  public void testGt() throws Exception {
    final String message = "expected message@" + Instant.now();

    assertThatThrownBy(() -> gt((short) -1, (short) 0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> gt((short) -1, (short) 0, message)).isInstanceOf(AssertionException.class)
        .hasMessage(message);
    assertThatThrownBy(() -> gt((byte) -1, (byte) 0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> gt((byte) -1, (byte) 0, message)).isInstanceOf(AssertionException.class)
        .hasMessage(message);
    assertThatThrownBy(() -> gt(-1, 0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> gt(-1, 0, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> gt(-1L, 0L)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> gt(-1L, 0L, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> gt(-1.0F, 0.0F)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> gt(-1.0F, 0.0F, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> gt(-1.0, 0.0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> gt(-1.0, 0.0, message)).isInstanceOf(AssertionException.class).hasMessage(message);

    assertThatThrownBy(() -> gt((short) 0, (short) 0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> gt((short) 0, (short) 0, message)).isInstanceOf(AssertionException.class)
        .hasMessage(message);
    assertThatThrownBy(() -> gt((byte) 0, (byte) 0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> gt((byte) 0, (byte) 0, message)).isInstanceOf(AssertionException.class)
        .hasMessage(message);
    assertThatThrownBy(() -> gt(0, 0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> gt(0, 0, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> gt(0L, 0L)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> gt(0L, 0L, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> gt(0.0F, 0.0F)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> gt(0.0F, 0.0F, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> gt(0.0, 0.0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> gt(0.0, 0.0, message)).isInstanceOf(AssertionException.class).hasMessage(message);

    gt((byte) 1, (byte) 0);
    gt((short) 1, (short) 0);
    gt(1, 0);
    gt(1L, 0L);
    gt(1.0F, 0.0F);
    gt(1.0, 0.0);

    gt((byte) 1, (byte) 0, null);
    gt((short) 1, (short) 0, null);
    gt(1, 0, null);
    gt(1L, 0L, null);
    gt(1.0F, 0.0F, null);
    gt(1.0, 0.0, null);

    gt((byte) 1, (byte) 0, message);
    gt((short) 1, (short) 0, message);
    gt(1, 0, message);
    gt(1L, 0L, message);
    gt(1.0F, 0.0F, message);
    gt(1.0, 0.0, message);
  }

  @Test
  public void testGe() throws Exception {
    final String message = "expected message@" + Instant.now();

    assertThatThrownBy(() -> ge((short) -1, (short) 0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> ge((short) -1, (short) 0, message)).isInstanceOf(AssertionException.class)
        .hasMessage(message);
    assertThatThrownBy(() -> ge((byte) -1, (byte) 0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> ge((byte) -1, (byte) 0, message)).isInstanceOf(AssertionException.class)
        .hasMessage(message);
    assertThatThrownBy(() -> ge(-1, 0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> ge(-1, 0, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> ge(-1L, 0L)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> ge(-1L, 0L, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> ge(-1.0F, 0.0F)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> ge(-1.0F, 0.0F, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> ge(-1.0, 0.0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> ge(-1.0, 0.0, message)).isInstanceOf(AssertionException.class).hasMessage(message);

    ge((byte) 0, (byte) 0);
    ge((short) 0, (short) 0);
    ge(0, 0);
    ge(0L, 0L);
    ge(0.0F, 0.0F);
    ge(0.0, 0.0);

    ge((byte) 0, (byte) 0, null);
    ge((short) 0, (short) 0, null);
    ge(0, 0, null);
    ge(0L, 0L, null);
    ge(0.0F, 0.0F, null);
    ge(0.0, 0.0, null);

    ge((byte) 0, (byte) 0, message);
    ge((short) 0, (short) 0, message);
    ge(0, 0, message);
    ge(0L, 0L, message);
    ge(0.0F, 0.0F, message);
    ge(0.0, 0.0, message);

    ge((byte) 1, (byte) 0);
    ge((short) 1, (short) 0);
    ge(1, 0);
    ge(1L, 0L);
    ge(1.0F, 0.0F);
    ge(1.0, 0.0);

    ge((byte) 1, (byte) 0, null);
    ge((short) 1, (short) 0, null);
    ge(1, 0, null);
    ge(1L, 0L, null);
    ge(1.0F, 0.0F, null);
    ge(1.0, 0.0, null);

    ge((byte) 1, (byte) 0, message);
    ge((short) 1, (short) 0, message);
    ge(1, 0, message);
    ge(1L, 0L, message);
    ge(1.0F, 0.0F, message);
    ge(1.0, 0.0, message);
  }

  @Test
  public void testIsNull() throws Exception {
    final String message = "expected message@" + Instant.now();

    isNull(null);
    isNull(null, null);
    isNull(null, message);

    assertThatThrownBy(() -> isNull(new Object())).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> isNull(new Object(), message)).isInstanceOf(AssertionException.class).hasMessage(message);
  }

  @Test
  public void testNotNull() throws Exception {
    final String message = "expected message@" + Instant.now();

    notNull(new Object());
    notNull(new Object(), null);
    notNull(new Object(), message);

    assertThatThrownBy(() -> notNull(null)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> notNull(null, message)).isInstanceOf(AssertionException.class).hasMessage(message);
  }

  @Test
  public void testAssignableForException() throws Exception {
    assertThatThrownBy(() -> assignable(null, null)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> assignable(new Object(), null)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> assignable("", null)).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void testAssignable() throws Exception {
    class A {
    }
    class B {
    }
    final String message = "expected message@" + Instant.now();

    assignable(new A(), Object.class);
    assignable(null, Object.class);
    assignable(null, A.class);

    assignable(new A(), Object.class, null);
    assignable(null, Object.class, null);
    assignable(null, A.class, null);

    assignable(new A(), Object.class, message);
    assignable(null, Object.class, message);
    assignable(null, A.class, message);

    assertThatThrownBy(() -> assignable(new Object(), A.class)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> assignable(new Object(), A.class, message)).isInstanceOf(AssertionException.class)
        .hasMessage(message);
    assertThatThrownBy(() -> assignable(new B(), A.class)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> assignable(new B(), A.class, message)).isInstanceOf(AssertionException.class)
        .hasMessage(message);
  }

  @Test
  public void testHasLength() throws Exception {
    final String message = "expected message@" + Instant.now();

    hasLength(".");
    hasLength(".", null);
    hasLength(".", message);

    assertThatThrownBy(() -> hasLength(null)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> hasLength(null, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> hasLength("")).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> hasLength("", message)).isInstanceOf(AssertionException.class).hasMessage(message);
  }

  @Test
  public void testLengthForException() throws Exception {
    final String message = "expected message@" + Instant.now();

    assertThatThrownBy(() -> length(null, -1)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> length(null, 0)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> length(null, 1)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> length("", -1)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> length(".", -1)).isInstanceOf(IllegalArgumentException.class);

    assertThatThrownBy(() -> length(null, -1, message)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> length(null, 0, message)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> length(null, 1, message)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> length("", -1, message)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> length(".", -1, message)).isInstanceOf(IllegalArgumentException.class);

    assertThatThrownBy(() -> length(null, 0, -1)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> length(null, 0, 0)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> length("", 0, -1)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> length("", 0, 0)).isInstanceOf(IllegalArgumentException.class);

    assertThatThrownBy(() -> length(null, 0, -1, message)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> length(null, 0, 0, message)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> length("", 0, -1, message)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> length("", 0, 0, message)).isInstanceOf(IllegalArgumentException.class);

    assertThatThrownBy(() -> length(null, 1, -1)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> length(null, 1, 0)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> length("", 1, -1)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> length("", 1, 0)).isInstanceOf(IllegalArgumentException.class);

    assertThatThrownBy(() -> length(null, 1, -1, message)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> length(null, 1, 0, message)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> length("", 1, -1, message)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> length("", 1, 0, message)).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void testLengthWithStringAndLength() throws Exception {
    final String message = "expected message@" + Instant.now();

    length("", 0);
    length(".", 1);

    length("", 0, null);
    length(".", 1, null);

    length("", 0, message);
    length(".", 1, message);

    assertThatThrownBy(() -> length(".", 0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> length(".", 0, message)).isInstanceOf(AssertionException.class).hasMessage(message);

    assertThatThrownBy(() -> length("", 1)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> length("", 1, message)).isInstanceOf(AssertionException.class).hasMessage(message);
  }

  @Test
  public void testLengthWithStringAndMinAndMax() throws Exception {
    final String message = "expected message@" + Instant.now();

    length("", 0, 1);
    length(".", 0, 1);

    length("", 0, 1, null);
    length(".", 0, 1, null);

    length("", 0, 1, message);
    length(".", 0, 1, message);

    assertThatThrownBy(() -> length("", 1, 2)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> length("", 1, 2, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> length("111", 1, 2)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> length("111", 1, 2, message)).isInstanceOf(AssertionException.class).hasMessage(message);
  }

  @Test
  public void testShorterForException() throws Exception {
    final String message = "expected message@" + Instant.now();

    assertThatThrownBy(() -> shorter(null, -1)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> shorter(null, 0)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> shorter(null, 1)).isInstanceOf(IllegalArgumentException.class);

    assertThatThrownBy(() -> shorter(null, -1, message)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> shorter(null, 0, message)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> shorter(null, 1, message)).isInstanceOf(IllegalArgumentException.class);

    assertThatThrownBy(() -> shorter("", -1)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> shorter("1", -1)).isInstanceOf(IllegalArgumentException.class);

    assertThatThrownBy(() -> shorter("", -1, message)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> shorter("1", -1, message)).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void testShorter() throws Exception {
    final String message = "expected message@" + Instant.now();

    shorter("", 0);
    shorter("", 1);
    shorter("1", 1);
    shorter("1", 2);

    shorter("", 0, null);
    shorter("", 1, null);
    shorter("1", 1, null);
    shorter("1", 2, null);

    shorter("", 0, message);
    shorter("", 1, message);
    shorter("1", 1, message);
    shorter("1", 2, message);

    assertThatThrownBy(() -> shorter("1", 0)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> shorter("1", 0, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> shorter("11", 1)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> shorter("11", 1, message)).isInstanceOf(AssertionException.class).hasMessage(message);
  }

  @Test
  public void testLongerForException() throws Exception {
    final String message = "expected message@" + Instant.now();

    assertThatThrownBy(() -> longer(null, -1)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> longer(null, 0)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> longer(null, 1)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> longer("", -1)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> longer(".", -1)).isInstanceOf(IllegalArgumentException.class);

    assertThatThrownBy(() -> longer(null, -1, message)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> longer(null, 0, message)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> longer(null, 1, message)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> longer("", -1, message)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> longer(".", -1, message)).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void testLonger() throws Exception {
    final String message = "expected message@" + Instant.now();

    longer("", 0);
    longer("1", 0);
    longer("1", 1);

    longer("", 0, null);
    longer("1", 0, null);
    longer("1", 1, null);

    longer("", 0, message);
    longer("1", 0, message);
    longer("1", 1, message);

    assertThatThrownBy(() -> longer("", 1)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> longer("1", 2)).isInstanceOf(AssertionException.class);

    assertThatThrownBy(() -> longer("", 1, message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> longer("1", 2, message)).isInstanceOf(AssertionException.class).hasMessage(message);
  }

  @Test
  public void testMatchesForException() throws Exception {
    final String message = "expected message@" + Instant.now();

    assertThatThrownBy(() -> matches(null, null)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> matches(null, "[")).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> matches("", null)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> matches("", "[")).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> matches(null, ".*")).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> matches(null, ".*")).isInstanceOf(IllegalArgumentException.class);

    assertThatThrownBy(() -> matches(null, null, message)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> matches(null, "[", message)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> matches("", null, message)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> matches("", "[", message)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> matches(null, ".*", message)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> matches(null, ".*", message)).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void testMatches() throws Exception {
    final String message = "expected message@" + Instant.now();

    matches("", ".*");
    matches("0", "\\d*");

    matches("", ".*", null);
    matches("0", "\\d*", null);

    matches("", ".*", message);
    matches("0", "\\d*", message);

    assertThatThrownBy(() -> matches("", ".+")).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> matches("0", "\\D+")).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> matches("a", "\\d+")).isInstanceOf(AssertionException.class);

    assertThatThrownBy(() -> matches("", ".+", message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> matches("0", "\\D+", message)).isInstanceOf(AssertionException.class).hasMessage(message);
    assertThatThrownBy(() -> matches("a", "\\d+", message)).isInstanceOf(AssertionException.class).hasMessage(message);
  }

  @Test
  public void testBefore() throws Exception {
    final Instant now = Instant.now();
    final Instant before = now.minusMillis(1L);

    assertThatThrownBy(() -> before(null, null)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> before(null, now)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> before(now, null)).isInstanceOf(IllegalArgumentException.class);

    assertThatThrownBy(() -> before(null, null, this.message)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> before(null, now, this.message)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> before(now, null, this.message)).isInstanceOf(IllegalArgumentException.class);

    before(before, now);
    before(before, now, null);
    before(before, now, this.message);

    assertThatThrownBy(() -> before(now, before)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> before(now, now)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> before(now, Instant.ofEpochMilli(now.toEpochMilli())))
        .isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> before(now, before, null)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> before(now, now, null)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> before(now, Instant.ofEpochMilli(now.toEpochMilli()), null))
        .isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> before(now, before, this.message)).isInstanceOf(AssertionException.class)
        .hasMessage(this.message);
    assertThatThrownBy(() -> before(now, now, this.message)).isInstanceOf(AssertionException.class)
        .hasMessage(this.message);
    assertThatThrownBy(() -> before(now, Instant.ofEpochMilli(now.toEpochMilli()), this.message))
        .isInstanceOf(AssertionException.class).hasMessage(this.message);
  }

  @Test
  public void testAfter() throws Exception {
    final Instant now = Instant.now();
    final Instant before = now.minusMillis(1L);

    assertThatThrownBy(() -> after(null, null)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> after(null, now)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> after(now, null)).isInstanceOf(IllegalArgumentException.class);

    assertThatThrownBy(() -> after(null, null, null)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> after(null, now, null)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> after(now, null, null)).isInstanceOf(IllegalArgumentException.class);

    assertThatThrownBy(() -> after(null, null, this.message)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> after(null, now, this.message)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> after(now, null, this.message)).isInstanceOf(IllegalArgumentException.class);

    after(now, before);
    after(now, before, null);
    after(now, before, this.message);

    assertThatThrownBy(() -> after(now, now)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> after(now, now, null)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> after(now, now, this.message)).isInstanceOf(AssertionException.class)
        .hasMessage(this.message);

    assertThatThrownBy(() -> after(before, now)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> after(before, Instant.ofEpochMilli(now.toEpochMilli())))
        .isInstanceOf(AssertionException.class);

    assertThatThrownBy(() -> after(before, now, null)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> after(before, Instant.ofEpochMilli(now.toEpochMilli()), null))
        .isInstanceOf(AssertionException.class);

    assertThatThrownBy(() -> after(before, now, this.message)).isInstanceOf(AssertionException.class)
        .hasMessage(this.message);
    assertThatThrownBy(() -> after(before, Instant.ofEpochMilli(now.toEpochMilli()), this.message))
        .isInstanceOf(AssertionException.class).hasMessage(this.message);
  }

  @Test
  public void testNotBefore() throws Exception {
    final Instant now = Instant.now();
    final Instant before = now.minusMillis(1L);

    assertThatThrownBy(() -> notBefore(null, null)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> notBefore(null, now)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> notBefore(now, null)).isInstanceOf(IllegalArgumentException.class);

    assertThatThrownBy(() -> notBefore(null, null, this.message)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> notBefore(null, now, this.message)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> notBefore(now, null, this.message)).isInstanceOf(IllegalArgumentException.class);

    notBefore(now, before);
    notBefore(now, before, null);
    notBefore(now, before, this.message);

    notBefore(now, now);
    notBefore(now, now, null);
    notBefore(now, now, this.message);

    notBefore(now, Instant.ofEpochMilli(now.toEpochMilli()));
    notBefore(now, Instant.ofEpochMilli(now.toEpochMilli()), null);
    notBefore(now, Instant.ofEpochMilli(now.toEpochMilli()), this.message);

    assertThatThrownBy(() -> notBefore(before, now)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> notBefore(before, now, this.message)).isInstanceOf(AssertionException.class)
        .hasMessage(this.message);
  }

  @Test
  public void testNotAfter() throws Exception {
    final Instant now = Instant.now();
    final Instant before = now.minusMillis(1L);

    assertThatThrownBy(() -> notAfter(null, null)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> notAfter(null, now)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> notAfter(now, null)).isInstanceOf(IllegalArgumentException.class);

    assertThatThrownBy(() -> notAfter(null, null, null)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> notAfter(null, now, null)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> notAfter(now, null, null)).isInstanceOf(IllegalArgumentException.class);

    assertThatThrownBy(() -> notAfter(null, null, this.message)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> notAfter(null, now, this.message)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> notAfter(now, null, this.message)).isInstanceOf(IllegalArgumentException.class);

    notAfter(before, now);
    notAfter(before, now, null);
    notAfter(before, now, this.message);

    notAfter(now, now);
    notAfter(now, now, null);
    notAfter(now, now, this.message);

    notAfter(now, Instant.ofEpochMilli(now.toEpochMilli()));
    notAfter(now, Instant.ofEpochMilli(now.toEpochMilli()), null);
    notAfter(now, Instant.ofEpochMilli(now.toEpochMilli()), this.message);

    assertThatThrownBy(() -> notAfter(now, before)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> notAfter(now, before, null)).isInstanceOf(AssertionException.class);
    assertThatThrownBy(() -> notAfter(now, before, this.message)).isInstanceOf(AssertionException.class)
        .hasMessage(this.message);
  }
}
