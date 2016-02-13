package kr.lul.urs.util;

import static java.lang.String.format;
import static kr.lul.urs.util.Asserts.assignable;
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
import static kr.lul.urs.util.Asserts.notNegative;
import static kr.lul.urs.util.Asserts.notNull;
import static kr.lul.urs.util.Asserts.notPositive;
import static kr.lul.urs.util.Asserts.positive;
import static kr.lul.urs.util.Asserts.shorter;
import static kr.lul.urs.util.Asserts.zero;
import static kr.lul.urs.util.Tests.exceptException;
import static org.junit.Assert.fail;

import java.time.Instant;

import org.junit.Test;

public class AssertsTest {
  @Test(expected = UnsupportedOperationException.class)
  public void test() {
    new Asserts() {
    };
    fail();
  }

  @Test
  public void testNegative() throws Exception {
    negative((byte) -1);
    negative((short) -1);
    negative(-1);
    negative(-1L);
    negative(-0.1F);
    negative(-0.1);

    final String message = "expected message@" + Instant.now();

    exceptException(AssertionException.class, () -> negative((byte) 0));
    exceptException(AssertionException.class, () -> negative((byte) 0, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> negative((short) 0));
    exceptException(AssertionException.class, () -> negative((short) 0, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> negative(0));
    exceptException(AssertionException.class, () -> negative(0, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> negative(0L));
    exceptException(AssertionException.class, () -> negative(0L, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> negative(0.0F));
    exceptException(AssertionException.class, () -> negative(0.0F, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> negative(0.0));
    exceptException(AssertionException.class, () -> negative(0.0, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));

    exceptException(AssertionException.class, () -> negative((byte) 1));
    exceptException(AssertionException.class, () -> negative((byte) 1, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> negative((short) 1));
    exceptException(AssertionException.class, () -> negative((short) 1, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> negative(1));
    exceptException(AssertionException.class, () -> negative(1, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> negative(1L));
    exceptException(AssertionException.class, () -> negative(1L, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> negative(0.1F));
    exceptException(AssertionException.class, () -> negative(0.1F, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> negative(0.1));
    exceptException(AssertionException.class, () -> negative(0.1, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
  }

  @Test
  public void testZero() throws Exception {
    final String message = "expected message@" + Instant.now();

    exceptException(AssertionException.class, () -> zero((byte) -1));
    exceptException(AssertionException.class, () -> zero((byte) -1, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> zero((short) -1));
    exceptException(AssertionException.class, () -> zero((short) -1, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> zero(-1));
    exceptException(AssertionException.class, () -> zero(-1, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> zero(-1L));
    exceptException(AssertionException.class, () -> zero(-1L, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> zero(-0.1F));
    exceptException(AssertionException.class, () -> zero(-0.1F, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> zero(-0.1));
    exceptException(AssertionException.class, () -> zero(-0.1, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));

    zero((byte) 0);
    zero((short) 0);
    zero(0);
    zero(0L);
    zero(0.0F);
    zero(0.0);

    exceptException(AssertionException.class, () -> zero((byte) 1));
    exceptException(AssertionException.class, () -> zero((byte) 1, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> zero((short) 1));
    exceptException(AssertionException.class, () -> zero((short) 1, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> zero(1));
    exceptException(AssertionException.class, () -> zero(1, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> zero(1L));
    exceptException(AssertionException.class, () -> zero(1L, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> zero(0.1F));
    exceptException(AssertionException.class, () -> zero(0.1F, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> zero(0.1));
    exceptException(AssertionException.class, () -> zero(0.1, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
  }

  @Test
  public void testPositive() throws Exception {
    final String message = "expected message@" + Instant.now();

    exceptException(AssertionException.class, () -> positive((byte) -1));
    exceptException(AssertionException.class, () -> positive((byte) -1, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> positive((short) -1));
    exceptException(AssertionException.class, () -> positive((short) -1, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> positive(-1));
    exceptException(AssertionException.class, () -> positive(-1, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> positive(-1L));
    exceptException(AssertionException.class, () -> positive(-1L, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> positive(-0.1F));
    exceptException(AssertionException.class, () -> positive(-0.1F, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> positive(-0.1));
    exceptException(AssertionException.class, () -> positive(-0.1, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));

    exceptException(AssertionException.class, () -> positive((byte) 0));
    exceptException(AssertionException.class, () -> positive((byte) 0, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> positive((short) 0));
    exceptException(AssertionException.class, () -> positive((short) 0, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> positive(0));
    exceptException(AssertionException.class, () -> positive(0, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> positive(0L));
    exceptException(AssertionException.class, () -> positive(0L, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> positive(0.0F));
    exceptException(AssertionException.class, () -> positive(0.0F, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> positive(0.0));
    exceptException(AssertionException.class, () -> positive(0.0, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));

    positive((byte) 1);
    positive((short) 1);
    positive(1);
    positive(1L);
    positive(0.1F);
    positive(0.1);
  }

  @Test
  public void testNotNegative() throws Exception {
    final String message = "expected message@" + Instant.now();

    exceptException(AssertionException.class, () -> notNegative((byte) -1));
    exceptException(AssertionException.class, () -> notNegative((byte) -1, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> notNegative((short) -1));
    exceptException(AssertionException.class, () -> notNegative((short) -1, message),
        e -> e.getMessage().equals(message)
            ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> notNegative(-1));
    exceptException(AssertionException.class, () -> notNegative(-1, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> notNegative(-1L));
    exceptException(AssertionException.class, () -> notNegative(-1L, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> notNegative(-0.1F));
    exceptException(AssertionException.class, () -> notNegative(-0.1F, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> notNegative(-0.1));
    exceptException(AssertionException.class, () -> notNegative(-0.1, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));

    notNegative((byte) 0);
    notNegative((short) 0);
    notNegative(0);
    notNegative(0L);
    notNegative(0.0F);
    notNegative(0.0);

    notNegative((byte) 1);
    notNegative((short) 1);
    notNegative(1);
    notNegative(1L);
    notNegative(0.1F);
    notNegative(0.1);
  }

  @Test
  public void testNotPositive() throws Exception {
    notPositive((byte) -1);
    notPositive((short) -1);
    notPositive(-1);
    notPositive(-1L);
    notPositive(-0.1F);
    notPositive(-0.1);

    notPositive((byte) 0);
    notPositive((short) 0);
    notPositive(0);
    notPositive(0L);
    notPositive(0.0F);
    notPositive(0.0);

    final String message = "expected message@" + Instant.now();

    exceptException(AssertionException.class, () -> notPositive((byte) 1));
    exceptException(AssertionException.class, () -> notPositive((byte) 1, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> notPositive((short) 1));
    exceptException(AssertionException.class, () -> notPositive((short) 1, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> notPositive(1));
    exceptException(AssertionException.class, () -> notPositive(1, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> notPositive(1L));
    exceptException(AssertionException.class, () -> notPositive(1L, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> notPositive(0.1F));
    exceptException(AssertionException.class, () -> notPositive(0.1F, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
    exceptException(AssertionException.class, () -> notPositive(0.1));
    exceptException(AssertionException.class, () -> notPositive(0.1, message), e -> e.getMessage().equals(message)
        ? null : format("expected message is [%s] but actual message is [%s].", message, e.getMessage()));
  }

  @Test
  public void testInForException() throws Exception {
    exceptException(IllegalArgumentException.class, () -> in((byte) -1, (byte) 0, (byte) 0));
    exceptException(IllegalArgumentException.class, () -> in((short) -1, (short) 0, (short) 0));
    exceptException(IllegalArgumentException.class, () -> in(-1, 0, 0));
    exceptException(IllegalArgumentException.class, () -> in(-1L, 0L, 0L));
    exceptException(IllegalArgumentException.class, () -> in(-1.0F, 0.0F, 0.0F));
    exceptException(IllegalArgumentException.class, () -> in(-1.0, 0.0, 0.0));

    exceptException(IllegalArgumentException.class, () -> in((byte) -1, (byte) 0, (byte) -1));
    exceptException(IllegalArgumentException.class, () -> in((short) -1, (short) 0, (short) -1));
    exceptException(IllegalArgumentException.class, () -> in(-1, 0, -1));
    exceptException(IllegalArgumentException.class, () -> in(-1L, 0L, -1L));
    exceptException(IllegalArgumentException.class, () -> in(-1.0F, 0.0F, -1.0F));
    exceptException(IllegalArgumentException.class, () -> in(-1.0, 0.0, -1.0));

    exceptException(IllegalArgumentException.class, () -> in((byte) 0, (byte) 0, (byte) 0));
    exceptException(IllegalArgumentException.class, () -> in((short) 0, (short) 0, (short) 0));
    exceptException(IllegalArgumentException.class, () -> in(0, 0, 0));
    exceptException(IllegalArgumentException.class, () -> in(0L, 0L, 0L));
    exceptException(IllegalArgumentException.class, () -> in(0.0F, 0.0F, 0.0F));
    exceptException(IllegalArgumentException.class, () -> in(0.0, 0.0, 0.0));

    exceptException(IllegalArgumentException.class, () -> in((byte) 0, (byte) 0, (byte) -1));
    exceptException(IllegalArgumentException.class, () -> in((short) 0, (short) 0, (short) -1));
    exceptException(IllegalArgumentException.class, () -> in(0, 0, -1));
    exceptException(IllegalArgumentException.class, () -> in(0L, 0L, -1L));
    exceptException(IllegalArgumentException.class, () -> in(0.0F, 0.0F, -1.0F));
    exceptException(IllegalArgumentException.class, () -> in(0.0, 0.0, -1.0));
  }

  @Test
  public void testIn() throws Exception {
    final String message = "expected message@" + Instant.now();

    exceptException(AssertionException.class, () -> in((byte) -1, (byte) 0, (byte) 1));
    exceptException(AssertionException.class, () -> in((byte) -1, (byte) 0, (byte) 1, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> in((short) -1, (short) 0, (short) 1));
    exceptException(AssertionException.class, () -> in((short) -1, (short) 0, (short) 1, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> in(-1, 0, 1));
    exceptException(AssertionException.class, () -> in(-1, 0, 1, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> in(-1L, 0L, 1L));
    exceptException(AssertionException.class, () -> in(-1L, 0L, 1L, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> in(-0.1F, 0.0F, 1.0F));
    exceptException(AssertionException.class, () -> in(-0.1F, 0.0F, 1.0F, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> in(-0.1, 0.0, 1.0));
    exceptException(AssertionException.class, () -> in(-0.1, 0.0, 1.0, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());

    in((byte) 0, (byte) 0, (byte) 2);
    in((byte) 0, (byte) 0, (byte) 2, message);
    in((short) 0, (short) 0, (short) 2);
    in((short) 0, (short) 0, (short) 2, message);
    in(0, 0, 2);
    in(0, 0, 2, message);
    in(0L, 0L, 2L);
    in(0L, 0L, 2L, message);
    in(0.0F, 0.0F, 2.0F);
    in(0.0F, 0.0F, 2.0F, message);
    in(0.0, 0.0, 2.0);
    in(0.0, 0.0, 2.0, message);

    in((byte) 1, (byte) 0, (byte) 2);
    in((byte) 1, (byte) 0, (byte) 2, message);
    in((short) 1, (short) 0, (short) 2);
    in((short) 1, (short) 0, (short) 2, message);
    in(1, 0, 2);
    in(1, 0, 2, message);
    in(1L, 0L, 2L);
    in(1L, 0L, 2L, message);
    in(1.0F, 0.0F, 2.0F);
    in(1.0F, 0.0F, 2.0F, message);
    in(1.0, 0.0, 2.0);
    in(1.0, 0.0, 2.0, message);

    exceptException(AssertionException.class, () -> in((byte) 2, (byte) 0, (byte) 2));
    exceptException(AssertionException.class, () -> in((byte) 2, (byte) 0, (byte) 2, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> in((short) 2, (short) 0, (short) 2));
    exceptException(AssertionException.class, () -> in((short) 2, (short) 0, (short) 2, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> in(2, 0, 2));
    exceptException(AssertionException.class, () -> in(2, 0, 2, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> in(2L, 0L, 2L));
    exceptException(AssertionException.class, () -> in(2L, 0L, 2L, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> in(2.0F, 0.0F, 2.0F));
    exceptException(AssertionException.class, () -> in(2.0F, 0.0F, 2.0F, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> in(2.0, 0.0, 2.0));
    exceptException(AssertionException.class, () -> in(2.0, 0.0, 2.0, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
  }

  @Test
  public void testLt() throws Exception {
    final String message = "expected message@" + Instant.now();

    lt((byte) -1, (byte) 0);
    lt((byte) -1, (byte) 0, message);
    lt((short) -1, (short) 0);
    lt((short) -1, (short) 0, message);
    lt(-1, 0);
    lt(-1, 0, message);
    lt(-1L, 0L);
    lt(-1L, 0L, message);
    lt(-1.0F, 0.0F);
    lt(-1.0F, 0.0F, message);
    lt(-1.0, 0.0);
    lt(-1.0, 0.0, message);

    exceptException(AssertionException.class, () -> lt((byte) 0, (byte) 0));
    exceptException(AssertionException.class, () -> lt((byte) 0, (byte) 0, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> lt((short) 0, (short) 0));
    exceptException(AssertionException.class, () -> lt((short) 0, (short) 0, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> lt(0, 0));
    exceptException(AssertionException.class, () -> lt(0, 0, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> lt(0L, 0L));
    exceptException(AssertionException.class, () -> lt(0L, 0L, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> lt(0.0F, 0.0F));
    exceptException(AssertionException.class, () -> lt(0.0F, 0.0F, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> lt(0.0, 0.0));
    exceptException(AssertionException.class, () -> lt(0.0, 0.0, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());

    exceptException(AssertionException.class, () -> lt((byte) 1, (byte) 0));
    exceptException(AssertionException.class, () -> lt((byte) 1, (byte) 0, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> lt((short) 1, (short) 0));
    exceptException(AssertionException.class, () -> lt((short) 1, (short) 0, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> lt(1, 0));
    exceptException(AssertionException.class, () -> lt(1, 0, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> lt(1L, 0L));
    exceptException(AssertionException.class, () -> lt(1L, 0L, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> lt(1.0F, 0.0F));
    exceptException(AssertionException.class, () -> lt(1.0F, 0.0F, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> lt(1.0, 0.0));
    exceptException(AssertionException.class, () -> lt(1.0, 0.0, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
  }

  @Test
  public void testLe() throws Exception {
    final String message = "expected message@" + Instant.now();

    le((byte) -1, (byte) 0);
    le((byte) -1, (byte) 0, message);
    le((short) -1, (short) 0);
    le((short) -1, (short) 0, message);
    le(-1, 0);
    le(-1, 0, message);
    le(-1L, 0L);
    le(-1L, 0L, message);
    le(-1.0F, 0.0F);
    le(-1.0F, 0.0F, message);
    le(-1.0, 0.0);
    le(-1.0, 0.0, message);

    le((byte) 0, (byte) 0);
    le((byte) 0, (byte) 0, message);
    le((short) 0, (short) 0);
    le((short) 0, (short) 0, message);
    le(0, 0);
    le(0, 0, message);
    le(0L, 0L);
    le(0L, 0L, message);
    le(0.0F, 0.0F);
    le(0.0F, 0.0F, message);
    le(0.0, 0.0);
    le(0.0, 0.0, message);

    exceptException(AssertionException.class, () -> le((byte) 1, (byte) 0));
    exceptException(AssertionException.class, () -> le((byte) 1, (byte) 0, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> le((short) 1, (short) 0));
    exceptException(AssertionException.class, () -> le((short) 1, (short) 0, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> le(1, 0));
    exceptException(AssertionException.class, () -> le(1, 0, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> le(1L, 0L));
    exceptException(AssertionException.class, () -> le(1L, 0L, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> le(1.0F, 0.0F));
    exceptException(AssertionException.class, () -> le(1.0F, 0.0F, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> le(1.0, 0.0));
    exceptException(AssertionException.class, () -> le(1.0, 0.0, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
  }

  @Test
  public void testGt() throws Exception {
    final String message = "expected message@" + Instant.now();

    exceptException(AssertionException.class, () -> gt((byte) -1, (byte) 0));
    exceptException(AssertionException.class, () -> gt((byte) -1, (byte) 0, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> gt((short) -1, (short) 0));
    exceptException(AssertionException.class, () -> gt((short) -1, (short) 0, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> gt(-1, 0));
    exceptException(AssertionException.class, () -> gt(-1, 0, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> gt(-1L, 0L));
    exceptException(AssertionException.class, () -> gt(-1L, 0L, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> gt(-1.0F, 0.0F));
    exceptException(AssertionException.class, () -> gt(-1.0F, 0.0F, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> gt(-1.0, 0.0));
    exceptException(AssertionException.class, () -> gt(-1.0, 0.0, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());

    exceptException(AssertionException.class, () -> gt((byte) 0, (byte) 0));
    exceptException(AssertionException.class, () -> gt((byte) 0, (byte) 0, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> gt((short) 0, (short) 0));
    exceptException(AssertionException.class, () -> gt((short) 0, (short) 0, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> gt(0, 0));
    exceptException(AssertionException.class, () -> gt(0, 0, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> gt(0L, 0L));
    exceptException(AssertionException.class, () -> gt(0L, 0L, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> gt(0.0F, 0.0F));
    exceptException(AssertionException.class, () -> gt(0.0F, 0.0F, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> gt(0.0, 0.0));
    exceptException(AssertionException.class, () -> gt(0.0, 0.0, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());

    gt((byte) 1, (byte) 0);
    gt((byte) 1, (byte) 0, message);
    gt((short) 1, (short) 0);
    gt((short) 1, (short) 0, message);
    gt(1, 0);
    gt(1, 0, message);
    gt(1L, 0L);
    gt(1L, 0L, message);
    gt(1.0F, 0.0F);
    gt(1.0F, 0.0F, message);
    gt(1.0, 0.0);
    gt(1.0, 0.0, message);
  }

  @Test
  public void testGe() throws Exception {
    final String message = "expected message@" + Instant.now();

    exceptException(AssertionException.class, () -> ge((byte) -1, (byte) 0));
    exceptException(AssertionException.class, () -> ge((byte) -1, (byte) 0, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> ge((short) -1, (short) 0));
    exceptException(AssertionException.class, () -> ge((short) -1, (short) 0, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> ge(-1, 0));
    exceptException(AssertionException.class, () -> ge(-1, 0, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> ge(-1L, 0L));
    exceptException(AssertionException.class, () -> ge(-1L, 0L, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> ge(-1.0F, 0.0F));
    exceptException(AssertionException.class, () -> ge(-1.0F, 0.0F, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> ge(-1.0, 0.0));
    exceptException(AssertionException.class, () -> ge(-1.0, 0.0, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());

    ge((byte) 0, (byte) 0);
    ge((byte) 0, (byte) 0, message);
    ge((short) 0, (short) 0);
    ge((short) 0, (short) 0, message);
    ge(0, 0);
    ge(0, 0, message);
    ge(0L, 0L);
    ge(0L, 0L, message);
    ge(0.0F, 0.0F);
    ge(0.0F, 0.0F, message);
    ge(0.0, 0.0);
    ge(0.0, 0.0, message);

    ge((byte) 1, (byte) 0);
    ge((byte) 1, (byte) 0, message);
    ge((short) 1, (short) 0);
    ge((short) 1, (short) 0, message);
    ge(1, 0);
    ge(1, 0, message);
    ge(1L, 0L);
    ge(1L, 0L, message);
    ge(1.0F, 0.0F);
    ge(1.0F, 0.0F, message);
    ge(1.0, 0.0);
    ge(1.0, 0.0, message);
  }

  @Test
  public void testIsNull() throws Exception {
    final String message = "expected message@" + Instant.now();

    isNull(null);
    isNull(null, message);

    exceptException(AssertionException.class, () -> isNull(new Object()));
    exceptException(AssertionException.class, () -> isNull(new Object(), message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
  }

  @Test
  public void testNotNull() throws Exception {
    final String message = "expected message@" + Instant.now();

    notNull(new Object());
    notNull(new Object(), message);

    exceptException(AssertionException.class, () -> notNull(null));
    exceptException(AssertionException.class, () -> notNull(null, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
  }

  @Test
  public void testAssignableForException() throws Exception {
    exceptException(IllegalArgumentException.class, () -> assignable(null, null));
    exceptException(IllegalArgumentException.class, () -> assignable(new Object(), null));
    exceptException(IllegalArgumentException.class, () -> assignable("", null));
  }

  @Test
  public void testAssignable() throws Exception {
    class A {
    }
    class B {
    }
    final String message = "expected message@" + Instant.now();

    assignable(new A(), Object.class);
    assignable(new A(), Object.class, message);
    assignable(null, Object.class);
    assignable(null, Object.class, message);
    assignable(null, A.class);
    assignable(null, A.class, message);

    exceptException(AssertionException.class, () -> assignable(new Object(), A.class));
    exceptException(AssertionException.class, () -> assignable(new Object(), A.class, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> assignable(new B(), A.class));
    exceptException(AssertionException.class, () -> assignable(new B(), A.class, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
  }

  @Test
  public void testHasLength() throws Exception {
    final String message = "expected message@" + Instant.now();

    hasLength(".");
    hasLength(".", message);

    exceptException(AssertionException.class, () -> hasLength(null));
    exceptException(AssertionException.class, () -> hasLength(null, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> hasLength(""));
    exceptException(AssertionException.class, () -> hasLength("", message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
  }

  @Test
  public void testLengthForException() throws Exception {
    final String message = "expected message@" + Instant.now();

    exceptException(IllegalArgumentException.class, () -> length(null, -1));
    exceptException(IllegalArgumentException.class, () -> length(null, -1, message));
    exceptException(IllegalArgumentException.class, () -> length("", -1));
    exceptException(IllegalArgumentException.class, () -> length("", -1, message));
    exceptException(IllegalArgumentException.class, () -> length(".", -1));
    exceptException(IllegalArgumentException.class, () -> length(".", -1, message));
    exceptException(IllegalArgumentException.class, () -> length(null, 0));
    exceptException(IllegalArgumentException.class, () -> length(null, 0, message));
    exceptException(IllegalArgumentException.class, () -> length(null, 1));
    exceptException(IllegalArgumentException.class, () -> length(null, 1, message));

    exceptException(IllegalArgumentException.class, () -> length(null, 0, 0));
    exceptException(IllegalArgumentException.class, () -> length(null, 0, 0, message));
    exceptException(IllegalArgumentException.class, () -> length(null, 0, -1));
    exceptException(IllegalArgumentException.class, () -> length(null, 0, -1, message));
    exceptException(IllegalArgumentException.class, () -> length("", 0, 0));
    exceptException(IllegalArgumentException.class, () -> length("", 0, 0, message));
    exceptException(IllegalArgumentException.class, () -> length("", 0, -1));
    exceptException(IllegalArgumentException.class, () -> length("", 0, -1, message));
  }

  @Test
  public void testLengthWithStringAndLength() throws Exception {
    final String message = "expected message@" + Instant.now();

    length("", 0);
    length("", 0, message);
    length(".", 1);
    length(".", 1, message);

    exceptException(AssertionException.class, () -> length(".", 0));
    exceptException(AssertionException.class, () -> length(".", 0, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> length("", 1));
    exceptException(AssertionException.class, () -> length("", 1, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
  }

  @Test
  public void testLengthWithStringAndMinAndMax() throws Exception {
    final String message = "expected message@" + Instant.now();

    length("", 0, 1);
    length("", 0, 1, message);
    length(".", 0, 1);
    length(".", 0, 1, message);

    exceptException(AssertionException.class, () -> length("", 1, 2));
    exceptException(AssertionException.class, () -> length("", 1, 2, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());

    exceptException(AssertionException.class, () -> length("111", 1, 2));
    exceptException(AssertionException.class, () -> length("111", 1, 2, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
  }

  @Test
  public void testShorterForException() throws Exception {
    final String message = "expected message@" + Instant.now();

    exceptException(IllegalArgumentException.class, () -> shorter(null, -1));
    exceptException(IllegalArgumentException.class, () -> shorter(null, -1, message));
    exceptException(IllegalArgumentException.class, () -> shorter(null, 0));
    exceptException(IllegalArgumentException.class, () -> shorter(null, 0, message));
    exceptException(IllegalArgumentException.class, () -> shorter(null, 1));
    exceptException(IllegalArgumentException.class, () -> shorter(null, 1, message));
    exceptException(IllegalArgumentException.class, () -> shorter("", -1));
    exceptException(IllegalArgumentException.class, () -> shorter("", -1, message));
    exceptException(IllegalArgumentException.class, () -> shorter("1", -1));
    exceptException(IllegalArgumentException.class, () -> shorter("1", -1, message));
  }

  @Test
  public void testShorter() throws Exception {
    final String message = "expected message@" + Instant.now();

    shorter("", 0);
    shorter("", 0, message);
    shorter("", 1);
    shorter("", 1, message);
    shorter("1", 1);
    shorter("1", 1, message);
    shorter("1", 2);
    shorter("1", 2, message);

    exceptException(AssertionException.class, () -> shorter("1", 0));
    exceptException(AssertionException.class, () -> shorter("1", 0, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> shorter("11", 1));
    exceptException(AssertionException.class, () -> shorter("11", 1, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
  }

  @Test
  public void testLongerForException() throws Exception {
    final String message = "expected message@" + Instant.now();

    exceptException(IllegalArgumentException.class, () -> longer(null, -1));
    exceptException(IllegalArgumentException.class, () -> longer(null, -1, message));
    exceptException(IllegalArgumentException.class, () -> longer(null, 0));
    exceptException(IllegalArgumentException.class, () -> longer(null, 0, message));
    exceptException(IllegalArgumentException.class, () -> longer(null, 1));
    exceptException(IllegalArgumentException.class, () -> longer(null, 1, message));
    exceptException(IllegalArgumentException.class, () -> longer("", -1));
    exceptException(IllegalArgumentException.class, () -> longer("", -1, message));
    exceptException(IllegalArgumentException.class, () -> longer("1", -1));
    exceptException(IllegalArgumentException.class, () -> longer("1", -1, message));
  }

  @Test
  public void testLonger() throws Exception {
    final String message = "expected message@" + Instant.now();

    longer("", 0);
    longer("", 0, message);
    longer("1", 0);
    longer("1", 0, message);
    longer("1", 1);
    longer("1", 1, message);

    exceptException(AssertionException.class, () -> longer("", 1));
    exceptException(AssertionException.class, () -> longer("", 1, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> longer("1", 2));
    exceptException(AssertionException.class, () -> longer("1", 2, message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
  }

  @Test
  public void testMatchesForException() throws Exception {
    final String message = "expected message@" + Instant.now();

    exceptException(IllegalArgumentException.class, () -> matches(null, null));
    exceptException(IllegalArgumentException.class, () -> matches(null, "["));
    exceptException(IllegalArgumentException.class, () -> matches("", null));
    exceptException(IllegalArgumentException.class, () -> matches("", "["));
    exceptException(IllegalArgumentException.class, () -> matches(null, ".*"));
    exceptException(IllegalArgumentException.class, () -> matches(null, ".*"));

    exceptException(IllegalArgumentException.class, () -> matches(null, null, message));
    exceptException(IllegalArgumentException.class, () -> matches(null, "[", message));
    exceptException(IllegalArgumentException.class, () -> matches("", null, message));
    exceptException(IllegalArgumentException.class, () -> matches("", "[", message));
    exceptException(IllegalArgumentException.class, () -> matches(null, ".*", message));
    exceptException(IllegalArgumentException.class, () -> matches(null, ".*", message));
  }

  @Test
  public void testMatches() throws Exception {
    final String message = "expected message@" + Instant.now();

    matches("", ".*");
    matches("", ".*", message);
    matches("0", "\\d*");
    matches("0", "\\d*", message);

    exceptException(AssertionException.class, () -> matches("", ".+"));
    exceptException(AssertionException.class, () -> matches("", ".+", message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> matches("0", "\\D+"));
    exceptException(AssertionException.class, () -> matches("0", "\\D+", message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
    exceptException(AssertionException.class, () -> matches("a", "\\d+"));
    exceptException(AssertionException.class, () -> matches("a", "\\d+", message),
        e -> e.getMessage().equals(message) ? null : e.getMessage());
  }
}
