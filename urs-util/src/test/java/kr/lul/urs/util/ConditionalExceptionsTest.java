package kr.lul.urs.util;

import static kr.lul.urs.util.ConditionalExceptions.after;
import static kr.lul.urs.util.ConditionalExceptions.assignable;
import static kr.lul.urs.util.ConditionalExceptions.before;
import static kr.lul.urs.util.ConditionalExceptions.ge;
import static kr.lul.urs.util.ConditionalExceptions.gt;
import static kr.lul.urs.util.ConditionalExceptions.hasLength;
import static kr.lul.urs.util.ConditionalExceptions.in;
import static kr.lul.urs.util.ConditionalExceptions.instance;
import static kr.lul.urs.util.ConditionalExceptions.isNull;
import static kr.lul.urs.util.ConditionalExceptions.le;
import static kr.lul.urs.util.ConditionalExceptions.length;
import static kr.lul.urs.util.ConditionalExceptions.longer;
import static kr.lul.urs.util.ConditionalExceptions.lt;
import static kr.lul.urs.util.ConditionalExceptions.matches;
import static kr.lul.urs.util.ConditionalExceptions.negative;
import static kr.lul.urs.util.ConditionalExceptions.notAfter;
import static kr.lul.urs.util.ConditionalExceptions.notBefore;
import static kr.lul.urs.util.ConditionalExceptions.notNegative;
import static kr.lul.urs.util.ConditionalExceptions.notNull;
import static kr.lul.urs.util.ConditionalExceptions.notPositive;
import static kr.lul.urs.util.ConditionalExceptions.positive;
import static kr.lul.urs.util.ConditionalExceptions.shorter;
import static kr.lul.urs.util.ConditionalExceptions.zero;
import static kr.lul.urs.util.Tests.exceptException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Instant;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import kr.lul.urs.util.ConditionalExceptions.Pitcher;
import kr.lul.urs.util.Tests.ExceptionValidator;
import kr.lul.urs.util.config.UtilConstants;

public class ConditionalExceptionsTest extends AbstractTest {
  public static class TestException extends Exception {
    private static final long serialVersionUID = UtilConstants.SERIAL_VERSION_UID;

    int                       i;
    String                    s;

    public TestException(int i, String s) {
      Asserts.notNull(s);
      this.i = i;
      this.s = s;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if (null != obj && obj instanceof TestException) {
        TestException that = (TestException) obj;
        return this.i == that.i && this.s.equals(that.s);
      } else {
        return false;
      }
    }

    @Override
    public String toString() {
      return String.format("(i=%d, s=%s)", this.i, this.s);
    }
  }

  private Random                            r;
  private TestException                     testException;
  private Pitcher<Exception>                emptyPitcher  = () -> {
                                                          };
  private Pitcher<TestException>            testPitcher   = () -> {
                                                            throw this.testException;
                                                          };
  private ExceptionValidator<TestException> testValidator = (
      e) -> ConditionalExceptionsTest.this.testException.equals(e) ? null : e.getMessage();

  @Before
  public void setUp() throws Exception {
    this.r = new Random(new Random().nextLong());
    this.testException = new TestException(this.r.nextInt(), Integer.toString(this.r.nextInt()));
  }

  @Test
  public void test() {
    assertThatThrownBy(() -> new ConditionalExceptions() {
    }).isInstanceOf(UnsupportedOperationException.class);
  }

  @Test
  public void testNegativeWithIllegalConstructorArguments() throws Exception {
    exceptException(IllegalArgumentException.class, () -> negative((byte) 0, null, ""));
    exceptException(IllegalArgumentException.class, () -> negative((short) 0, null, ""));
    exceptException(IllegalArgumentException.class, () -> negative(0, null, ""));
    exceptException(IllegalArgumentException.class, () -> negative(0L, null, ""));
    exceptException(IllegalArgumentException.class, () -> negative(0.0F, null, ""));
    exceptException(IllegalArgumentException.class, () -> negative(0.0, null, ""));

    exceptException(IllegalArgumentException.class, () -> negative((byte) 1, null, ""));
    exceptException(IllegalArgumentException.class, () -> negative((short) 1, null, ""));
    exceptException(IllegalArgumentException.class, () -> negative(1, null, ""));
    exceptException(IllegalArgumentException.class, () -> negative(1L, null, ""));
    exceptException(IllegalArgumentException.class, () -> negative(0.1F, null, ""));
    exceptException(IllegalArgumentException.class, () -> negative(0.1, null, ""));

    exceptException(IllegalArgumentException.class, () -> negative((byte) 0, TestException.class));
    exceptException(IllegalArgumentException.class, () -> negative((short) 0, TestException.class));
    exceptException(IllegalArgumentException.class, () -> negative(0, TestException.class));
    exceptException(IllegalArgumentException.class, () -> negative(0L, TestException.class));
    exceptException(IllegalArgumentException.class, () -> negative(0.0F, TestException.class));
    exceptException(IllegalArgumentException.class, () -> negative(0.0, TestException.class));

    exceptException(IllegalArgumentException.class, () -> negative((byte) 1, TestException.class));
    exceptException(IllegalArgumentException.class, () -> negative((short) 1, TestException.class));
    exceptException(IllegalArgumentException.class, () -> negative(1, TestException.class));
    exceptException(IllegalArgumentException.class, () -> negative(1L, TestException.class));
    exceptException(IllegalArgumentException.class, () -> negative(0.1F, TestException.class));
    exceptException(IllegalArgumentException.class, () -> negative(0.1, TestException.class));
  }

  @Test
  public void testNegativeWithNumberAndPitcherForException() throws Exception {
    exceptException(IllegalArgumentException.class, () -> negative((byte) 0, null));
    exceptException(IllegalArgumentException.class, () -> negative((short) 0, null));
    exceptException(IllegalArgumentException.class, () -> negative(0, null));
    exceptException(IllegalArgumentException.class, () -> negative(0L, null));
    exceptException(IllegalArgumentException.class, () -> negative(0.0F, null));
    exceptException(IllegalArgumentException.class, () -> negative(0.0, null));

    exceptException(IllegalArgumentException.class, () -> negative((byte) 1, null));
    exceptException(IllegalArgumentException.class, () -> negative((short) 1, null));
    exceptException(IllegalArgumentException.class, () -> negative(1, null));
    exceptException(IllegalArgumentException.class, () -> negative(1L, null));
    exceptException(IllegalArgumentException.class, () -> negative(1.0F, null));
    exceptException(IllegalArgumentException.class, () -> negative(1.0, null));
  }

  @Test
  public void testNegative() throws Exception {
    negative((byte) -1, TestException.class, (byte) -1);
    negative((short) -1, TestException.class, (short) -1);
    negative(-1, TestException.class, -1);
    negative(-1L, TestException.class, -1L);
    negative(-1.0F, TestException.class, -1.0F);
    negative(-1.0, TestException.class, -1.0);

    negative((byte) -1, this.emptyPitcher);
    negative((short) -1, this.emptyPitcher);
    negative(-1, this.emptyPitcher);
    negative(-1L, this.emptyPitcher);
    negative(-1.0F, this.emptyPitcher);
    negative(-1.0, this.emptyPitcher);

    negative((byte) -1, this.testPitcher);
    negative((short) -1, this.testPitcher);
    negative(-1, this.testPitcher);
    negative(-1L, this.testPitcher);
    negative(-1.0F, this.testPitcher);
    negative(-1.0, this.testPitcher);

    negative((byte) -1, null);
    negative((short) -1, null);
    negative(-1, null);
    negative(-1L, null);
    negative(-1.0F, null);
    negative(-1.0, null);

    exceptException(TestException.class,
        () -> negative((byte) 0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> negative((byte) 0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> negative((short) 0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> negative((short) 0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> negative(0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> negative(0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> negative(0L, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> negative(0L, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> negative(0.0F, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> negative(0.0F, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> negative(0.0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> negative(0.0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());

    exceptException(TestException.class,
        () -> negative((byte) 1, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> negative((byte) 1, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> negative((short) 1, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> negative((short) 1, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> negative(1, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> negative(1, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> negative(1L, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> negative(1L, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> negative(0.1F, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> negative(0.1F, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> negative(0.1, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> negative(0.1, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
  }

  @Test
  public void testZeroWithIlleagalArguments() throws Exception {
    exceptException(IllegalArgumentException.class, () -> zero((byte) -1, TestException.class));
    exceptException(IllegalArgumentException.class, () -> zero((short) -1, TestException.class));
    exceptException(IllegalArgumentException.class, () -> zero(-1, TestException.class));
    exceptException(IllegalArgumentException.class, () -> zero(-1L, TestException.class));
    exceptException(IllegalArgumentException.class, () -> zero(-1.0F, TestException.class));
    exceptException(IllegalArgumentException.class, () -> zero(-1.0, TestException.class));

    exceptException(IllegalArgumentException.class, () -> zero((byte) 1, TestException.class));
    exceptException(IllegalArgumentException.class, () -> zero((short) 1, TestException.class));
    exceptException(IllegalArgumentException.class, () -> zero(1, TestException.class));
    exceptException(IllegalArgumentException.class, () -> zero(1L, TestException.class));
    exceptException(IllegalArgumentException.class, () -> zero(1.0F, TestException.class));
    exceptException(IllegalArgumentException.class, () -> zero(1.0, TestException.class));

    exceptException(IllegalArgumentException.class, () -> zero((byte) -1, null, ""));
    exceptException(IllegalArgumentException.class, () -> zero((short) -1, null, ""));
    exceptException(IllegalArgumentException.class, () -> zero(-1, null, ""));
    exceptException(IllegalArgumentException.class, () -> zero(-1L, null, ""));
    exceptException(IllegalArgumentException.class, () -> zero(-1.0F, null, ""));
    exceptException(IllegalArgumentException.class, () -> zero(-1.0, null, ""));

    exceptException(IllegalArgumentException.class, () -> zero((byte) 1, null, ""));
    exceptException(IllegalArgumentException.class, () -> zero((short) 1, null, ""));
    exceptException(IllegalArgumentException.class, () -> zero(1, null, ""));
    exceptException(IllegalArgumentException.class, () -> zero(1L, null, ""));
    exceptException(IllegalArgumentException.class, () -> zero(1.0F, null, ""));
    exceptException(IllegalArgumentException.class, () -> zero(1.0, null, ""));
  }

  @Test
  public void testZeeroWithExceptionAndArguments() throws Exception {
    zero((byte) 0, TestException.class, 0, "");
    zero((short) 0, TestException.class, 0, "");
    zero(0, TestException.class, 0, "");
    zero(0L, TestException.class, 0, "");
    zero(0.0F, TestException.class, 0, "");
    zero(0.0, TestException.class, 0, "");

    zero((byte) 0, TestException.class);
    zero((short) 0, TestException.class);
    zero(0, TestException.class);
    zero(0L, TestException.class);
    zero(0.0F, TestException.class);
    zero(0.0, TestException.class);

    exceptException(TestException.class,
        () -> zero((byte) -1, TestException.class, this.testException.i, this.testException.s),
        e -> e.equals(this.testException) ? null : e.toString());
    exceptException(TestException.class,
        () -> zero((short) -1, TestException.class, this.testException.i, this.testException.s),
        e -> e.equals(this.testException) ? null : e.toString());
    exceptException(TestException.class,
        () -> zero(-1, TestException.class, this.testException.i, this.testException.s),
        e -> e.equals(this.testException) ? null : e.toString());
    exceptException(TestException.class,
        () -> zero(-1L, TestException.class, this.testException.i, this.testException.s),
        e -> e.equals(this.testException) ? null : e.toString());
    exceptException(TestException.class,
        () -> zero(-1.0F, TestException.class, this.testException.i, this.testException.s),
        e -> e.equals(this.testException) ? null : e.toString());
    exceptException(TestException.class,
        () -> zero(-1.0, TestException.class, this.testException.i, this.testException.s),
        e -> e.equals(this.testException) ? null : e.toString());

    exceptException(TestException.class,
        () -> zero((byte) -1, TestException.class, this.testException.i, this.testException.s),
        e -> e.equals(this.testException) ? null : e.toString());
    exceptException(TestException.class,
        () -> zero((short) -1, TestException.class, this.testException.i, this.testException.s),
        e -> e.equals(this.testException) ? null : e.toString());
    exceptException(TestException.class,
        () -> zero(1, TestException.class, this.testException.i, this.testException.s),
        e -> e.equals(this.testException) ? null : e.toString());
    exceptException(TestException.class,
        () -> zero(1L, TestException.class, this.testException.i, this.testException.s),
        e -> e.equals(this.testException) ? null : e.toString());
    exceptException(TestException.class,
        () -> zero(1.0F, TestException.class, this.testException.i, this.testException.s),
        e -> e.equals(this.testException) ? null : e.toString());
    exceptException(TestException.class,
        () -> zero(1.0, TestException.class, this.testException.i, this.testException.s),
        e -> e.equals(this.testException) ? null : e.toString());
  }

  @Test
  public void testZeroWithPitcher() throws Exception {
    zero((byte) 0, this.emptyPitcher);
    zero((short) 0, this.emptyPitcher);
    zero(0, this.emptyPitcher);
    zero(0L, this.emptyPitcher);
    zero(0.0F, this.emptyPitcher);
    zero(0.0, this.emptyPitcher);

    zero((byte) 0, null);
    zero((short) 0, null);
    zero(0, null);
    zero(0L, null);
    zero(0.0F, null);
    zero(0.0, null);

    exceptException(TestException.class, () -> zero((byte) -1, () -> {
      throw new TestException(this.testException.i, this.testException.s);
    }), e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> zero((short) -1, () -> {
      throw new TestException(this.testException.i, this.testException.s);
    }), e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> zero(-1, () -> {
      throw new TestException(this.testException.i, this.testException.s);
    }), e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> zero(-1L, () -> {
      throw new TestException(this.testException.i, this.testException.s);
    }), e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> zero(-1.0F, () -> {
      throw new TestException(this.testException.i, this.testException.s);
    }), e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> zero(-1.0, () -> {
      throw new TestException(this.testException.i, this.testException.s);
    }), e -> this.testException.equals(e) ? null : e.toString());

    exceptException(TestException.class, () -> zero((byte) 1, () -> {
      throw new TestException(this.testException.i, this.testException.s);
    }), e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> zero((short) 1, () -> {
      throw new TestException(this.testException.i, this.testException.s);
    }), e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> zero(1, () -> {
      throw new TestException(this.testException.i, this.testException.s);
    }), e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> zero(1L, () -> {
      throw new TestException(this.testException.i, this.testException.s);
    }), e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> zero(1.0F, () -> {
      throw new TestException(this.testException.i, this.testException.s);
    }), e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> zero(1.0, () -> {
      throw new TestException(this.testException.i, this.testException.s);
    }), e -> this.testException.equals(e) ? null : e.toString());
  }

  @Test
  public void testPositiveWithExceptionAndIllegalArgs() throws Exception {
    exceptException(IllegalArgumentException.class, () -> positive((byte) -1, null, ""));
    exceptException(IllegalArgumentException.class, () -> positive((short) -1, null, ""));
    exceptException(IllegalArgumentException.class, () -> positive(-1, null, ""));
    exceptException(IllegalArgumentException.class, () -> positive(-1L, null, ""));
    exceptException(IllegalArgumentException.class, () -> positive(-1.0F, null, ""));
    exceptException(IllegalArgumentException.class, () -> positive(-1.0, null, ""));

    exceptException(IllegalArgumentException.class, () -> positive((byte) 0, null, ""));
    exceptException(IllegalArgumentException.class, () -> positive((short) 0, null, ""));
    exceptException(IllegalArgumentException.class, () -> positive(0, null, ""));
    exceptException(IllegalArgumentException.class, () -> positive(0L, null, ""));
    exceptException(IllegalArgumentException.class, () -> positive(0.0F, null, ""));
    exceptException(IllegalArgumentException.class, () -> positive(0.0, null, ""));

    exceptException(IllegalArgumentException.class, () -> positive((byte) -1, TestException.class));
    exceptException(IllegalArgumentException.class, () -> positive((short) -1, TestException.class));
    exceptException(IllegalArgumentException.class, () -> positive(-1, TestException.class));
    exceptException(IllegalArgumentException.class, () -> positive(-1L, TestException.class));
    exceptException(IllegalArgumentException.class, () -> positive(-1.0F, TestException.class));
    exceptException(IllegalArgumentException.class, () -> positive(-1.0, TestException.class));

    exceptException(IllegalArgumentException.class, () -> positive((byte) 0, TestException.class));
    exceptException(IllegalArgumentException.class, () -> positive((short) 0, TestException.class));
    exceptException(IllegalArgumentException.class, () -> positive(0, TestException.class));
    exceptException(IllegalArgumentException.class, () -> positive(0L, TestException.class));
    exceptException(IllegalArgumentException.class, () -> positive(0.0F, TestException.class));
    exceptException(IllegalArgumentException.class, () -> positive(0.0, TestException.class));
  }

  @Test
  public void testPositiveWithException() throws Exception {
    exceptException(TestException.class,
        () -> positive((byte) -1, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> positive((short) -1, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> positive(-1, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> positive(-1L, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> positive(-1.0F, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> positive(-1.0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());

    exceptException(TestException.class,
        () -> positive((byte) 0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> positive((short) 0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> positive(0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> positive(0L, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> positive(0.0F, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> positive(0.0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());

    positive((byte) 1, null, "");
    positive((short) 1, null, "");
    positive(1, null, "");
    positive(1L, null, "");
    positive(1.0F, null, "");
    positive(1.0, null, "");

    positive((byte) 1, TestException.class);
    positive((short) 1, TestException.class);
    positive(1, TestException.class);
    positive(1L, TestException.class);
    positive(1.0F, TestException.class);
    positive(1.0, TestException.class);
  }

  @Test
  public void testPositiveWithPitcherForException() throws Exception {
    exceptException(TestException.class, () -> positive((byte) -1, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> positive((short) -1, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> positive(-1, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> positive(-1L, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> positive(-1.0F, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> positive(-1.0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());

    exceptException(TestException.class, () -> positive((byte) 0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> positive((short) 0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> positive(0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> positive(0L, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> positive(0.0F, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> positive(0.0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
  }

  @Test
  public void testPositiveWithPitcher() throws Exception {
    exceptException(TestException.class, () -> positive((byte) -1, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> positive((short) -1, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> positive(-1, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> positive(-1L, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> positive(-1.0F, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> positive(-1.0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());

    exceptException(TestException.class, () -> positive((byte) 0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> positive((short) 0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> positive(0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> positive(0L, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> positive(0.0F, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> positive(0.0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());

    positive((byte) 1, null);
    positive((short) 1, null);
    positive(1, null);
    positive(1L, null);
    positive(1.0F, null);
    positive(1.0, null);

    positive((byte) 1, this.testPitcher);
    positive((short) 1, this.testPitcher);
    positive(1, this.testPitcher);
    positive(1L, this.testPitcher);
    positive(1.0F, this.testPitcher);
    positive(1.0, this.testPitcher);
  }

  @Test
  public void testNotNegativeWithExceptionTypeForThrowsException() throws Exception {
    exceptException(IllegalArgumentException.class, () -> notNegative((byte) -1, TestException.class));
    exceptException(IllegalArgumentException.class, () -> notNegative((short) -1, TestException.class));
    exceptException(IllegalArgumentException.class, () -> notNegative(-1, TestException.class));
    exceptException(IllegalArgumentException.class, () -> notNegative(-1L, TestException.class));
    exceptException(IllegalArgumentException.class, () -> notNegative(-1.0F, TestException.class));
    exceptException(IllegalArgumentException.class, () -> notNegative(-1.0, TestException.class));

    exceptException(IllegalArgumentException.class,
        () -> notNegative((byte) -1, null, 1));
    exceptException(IllegalArgumentException.class,
        () -> notNegative((short) -1, null, 1));
    exceptException(IllegalArgumentException.class,
        () -> notNegative(-1, null, 1));
    exceptException(IllegalArgumentException.class,
        () -> notNegative(-1L, null, 1));
    exceptException(IllegalArgumentException.class,
        () -> notNegative(-1.0F, null, 1));
    exceptException(IllegalArgumentException.class,
        () -> notNegative(-1.0, null, 1));
  }

  @Test
  public void testNotNegativeWithExceptionType() throws Exception {
    exceptException(TestException.class,
        () -> notNegative((byte) -1, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> notNegative((short) -1, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> notNegative(-1, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> notNegative(-1L, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> notNegative(-1.0F, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> notNegative(-1.0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());

    notNegative((byte) 0, null, 1);
    notNegative((short) 0, null, 1);
    notNegative(0, null, 1);
    notNegative(0L, null, 1);
    notNegative(0.0F, null, 1);
    notNegative(0.0, null, 1);

    notNegative((byte) 0, TestException.class);
    notNegative((short) 0, TestException.class);
    notNegative(0, TestException.class);
    notNegative(0L, TestException.class);
    notNegative(0.0F, TestException.class);
    notNegative(0.0, TestException.class);
  }

  @Test
  public void testNotNegativeWithPitcherForThrows() throws Exception {
    exceptException(IllegalArgumentException.class, () -> notNegative((byte) -1, null));
    exceptException(IllegalArgumentException.class, () -> notNegative((short) -1, null));
    exceptException(IllegalArgumentException.class, () -> notNegative(-1, null));
    exceptException(IllegalArgumentException.class, () -> notNegative(-1L, null));
    exceptException(IllegalArgumentException.class, () -> notNegative(-1.0F, null));
    exceptException(IllegalArgumentException.class, () -> notNegative(-1.0, null));
  }

  @Test
  public void testNotNegativeWithPitcher() throws Exception {
    exceptException(TestException.class, () -> notNegative((byte) -1, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> notNegative((short) -1, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> notNegative(-1, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> notNegative(-1L, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> notNegative(-1.0F, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> notNegative(-1.0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());

    notNegative((byte) 0, this.testPitcher);
    notNegative((short) 0, this.testPitcher);
    notNegative(0, this.testPitcher);
    notNegative(0L, this.testPitcher);
    notNegative(0.0F, this.testPitcher);
    notNegative(0.0, this.testPitcher);

    notNegative((byte) 0, null);
    notNegative((short) 0, null);
    notNegative(0, null);
    notNegative(0L, null);
    notNegative(0.0F, null);
    notNegative(0.0, null);

    notNegative((byte) 1, this.testPitcher);
    notNegative((short) 1, this.testPitcher);
    notNegative(1, this.testPitcher);
    notNegative(1L, this.testPitcher);
    notNegative(1.0F, this.testPitcher);
    notNegative(1.0, this.testPitcher);

    notNegative((byte) 1, null);
    notNegative((short) 1, null);
    notNegative(1, null);
    notNegative(1L, null);
    notNegative(1.0F, null);
    notNegative(1.0, null);
  }

  @Test
  public void testNotPositiveWithExceptionTypeForThrows() throws Exception {
    exceptException(IllegalArgumentException.class, () -> notPositive((byte) 1, null));
    exceptException(IllegalArgumentException.class, () -> notPositive((short) 1, null));
    exceptException(IllegalArgumentException.class, () -> notPositive(1, null));
    exceptException(IllegalArgumentException.class, () -> notPositive(1L, null));
    exceptException(IllegalArgumentException.class, () -> notPositive(1.0F, null));
    exceptException(IllegalArgumentException.class, () -> notPositive(1.0, null));

    exceptException(IllegalArgumentException.class, () -> notPositive((byte) 1, TestException.class));
    exceptException(IllegalArgumentException.class, () -> notPositive((short) 1, TestException.class));
    exceptException(IllegalArgumentException.class, () -> notPositive(1, TestException.class));
    exceptException(IllegalArgumentException.class, () -> notPositive(1L, TestException.class));
    exceptException(IllegalArgumentException.class, () -> notPositive(1.0F, TestException.class));
    exceptException(IllegalArgumentException.class, () -> notPositive(1.0, TestException.class));
  }

  @Test
  public void testNotPositiveWithExceptionType() throws Exception {
    notPositive((byte) -1, TestException.class);
    notPositive((short) -1, TestException.class);
    notPositive(-1, TestException.class);
    notPositive(-1L, TestException.class);
    notPositive(-1.0F, TestException.class);
    notPositive(-1.0, TestException.class);

    notPositive((byte) -1, null, 1);
    notPositive((short) -1, null, 1);
    notPositive(-1, null, 1);
    notPositive(-1L, null, 1);
    notPositive(-1.0F, null, 1);
    notPositive(-1.0, null, 1);

    notPositive((byte) 0, TestException.class);
    notPositive((short) 0, TestException.class);
    notPositive(0, TestException.class);
    notPositive(0L, TestException.class);
    notPositive(0.0F, TestException.class);
    notPositive(0.0, TestException.class);

    notPositive((byte) 0, null, 1);
    notPositive((short) 0, null, 1);
    notPositive(0, null, 1);
    notPositive(0L, null, 1);
    notPositive(0.0F, null, 1);
    notPositive(0.0, null, 1);

    exceptException(TestException.class,
        () -> notPositive((byte) 1, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> notPositive((short) 1, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> notPositive(1, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> notPositive(1L, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> notPositive(1.0F, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> notPositive(1.0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
  }

  @Test
  public void testNotPositiveWithPitcherForThrows() throws Exception {
    exceptException(IllegalArgumentException.class, () -> notPositive((byte) 1, null));
    exceptException(IllegalArgumentException.class, () -> notPositive((short) 1, null));
    exceptException(IllegalArgumentException.class, () -> notPositive(1, null));
    exceptException(IllegalArgumentException.class, () -> notPositive(1L, null));
    exceptException(IllegalArgumentException.class, () -> notPositive(1.0F, null));
    exceptException(IllegalArgumentException.class, () -> notPositive(1.0, null));
  }

  @Test
  public void testNotPositiveWithPitcher() throws Exception {
    notPositive((byte) -1, null);
    notPositive((short) -1, null);
    notPositive(-1, null);
    notPositive(-1L, null);
    notPositive(-1.0F, null);
    notPositive(-1.0, null);

    notPositive((byte) -1, this.testPitcher);
    notPositive((short) -1, this.testPitcher);
    notPositive(-1, this.testPitcher);
    notPositive(-1L, this.testPitcher);
    notPositive(-1.0F, this.testPitcher);
    notPositive(-1.0, this.testPitcher);

    notPositive((byte) 0, null);
    notPositive((short) 0, null);
    notPositive(0, null);
    notPositive(0L, null);
    notPositive(0.0F, null);
    notPositive(0.0, null);

    notPositive((byte) 0, this.testPitcher);
    notPositive((short) 0, this.testPitcher);
    notPositive(0, this.testPitcher);
    notPositive(0L, this.testPitcher);
    notPositive(0.0F, this.testPitcher);
    notPositive(0.0, this.testPitcher);

    exceptException(TestException.class, () -> notPositive((byte) 1, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> notPositive((short) 1, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> notPositive(1, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> notPositive(1L, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> notPositive(1.0F, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> notPositive(1.0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
  }

  @Test
  public void testInWithExceptionForThrows() throws Exception {
    exceptException(IllegalArgumentException.class, () -> in((byte) 0, (byte) 0, (byte) -1, null));
    exceptException(IllegalArgumentException.class, () -> in((short) 0, (short) 0, (short) -1, null));
    exceptException(IllegalArgumentException.class, () -> in(0, 0, -1, null));
    exceptException(IllegalArgumentException.class, () -> in(0L, 0L, -1L, null));
    exceptException(IllegalArgumentException.class, () -> in(0.0F, 0.0F, -1.0F, null));
    exceptException(IllegalArgumentException.class, () -> in(0.0, 0.0, -1.0, null));

    exceptException(IllegalArgumentException.class, () -> in((byte) 0, (byte) 0, (byte) -1, TestException.class));
    exceptException(IllegalArgumentException.class, () -> in((short) 0, (short) 0, (short) -1, TestException.class));
    exceptException(IllegalArgumentException.class, () -> in(0, 0, -1, TestException.class));
    exceptException(IllegalArgumentException.class, () -> in(0L, 0L, -1L, TestException.class));
    exceptException(IllegalArgumentException.class, () -> in(0.0F, 0.0F, -1.0F, TestException.class));
    exceptException(IllegalArgumentException.class, () -> in(0.0, 0.0, -1.0, TestException.class));
  }

  @Test
  public void testInWithExceptionType() throws Exception {
    exceptException(TestException.class,
        () -> in((byte) -1, (short) 0, (short) 2, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> in((short) -1, (short) 0, (short) 2, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> in(-1, 0, 2, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> in(-1L, 0L, 2L, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> in(-1.0F, 0.0F, 2.0F, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> in(-1.0, 0.0, 2.0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());

    in((byte) 0, (byte) 0, (byte) 2, null, 1);
    in((short) 0, (short) 0, (short) 2, null, 1);
    in(0, 0, 2, null, 1);
    in(0L, 0L, 2L, null, 1);
    in(0.0F, 0.0F, 2.0F, null, 1);
    in(0.0, 0.0, 2.0, null, 1);

    in((byte) 0, (byte) 0, (byte) 2, TestException.class);
    in((short) 0, (short) 0, (short) 2, TestException.class);
    in(0, 0, 2, TestException.class);
    in(0L, 0L, 2L, TestException.class);
    in(0.0F, 0.0F, 2.0F, TestException.class);
    in(0.0, 0.0, 2.0, TestException.class);

    in((byte) 0, (byte) 0, (byte) 2, null, 1);
    in((short) 0, (short) 0, (short) 2, null, 1);
    in(0, 0, 2, null, 1);
    in(0L, 0L, 2L, null, 1);
    in(0.0F, 0.0F, 2.0F, null, 1);
    in(0.0, 0.0, 2.0, null, 1);

    in((byte) 1, (byte) 0, (byte) 2, TestException.class);
    in((short) 1, (short) 0, (short) 2, TestException.class);
    in(1, 0, 2, TestException.class);
    in(1L, 0L, 2L, TestException.class);
    in(1.0F, 0.0F, 2.0F, TestException.class);
    in(1.0, 0.0, 2.0, TestException.class);

    exceptException(TestException.class,
        () -> in((byte) 2, (short) 0, (short) 2, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> in((short) 2, (short) 0, (short) 2, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> in(2, 0, 2, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> in(2L, 0L, 2L, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> in(2.0F, 0.0F, 2.0F, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> in(2.0, 0.0, 2.0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());

    exceptException(TestException.class,
        () -> in((byte) 3, (short) 0, (short) 2, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> in((short) 3, (short) 0, (short) 2, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> in(3, 0, 2, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> in(3L, 0L, 2L, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> in(3.0F, 0.0F, 2.0F, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> in(3.0, 0.0, 2.0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
  }

  @Test
  public void testInWithPitcherForThrow() throws Exception {
    exceptException(IllegalArgumentException.class, () -> in((byte) 0, (byte) 0, (byte) -1, null));
    exceptException(IllegalArgumentException.class, () -> in((short) 0, (short) 0, (short) -1, null));
    exceptException(IllegalArgumentException.class, () -> in(0, 0, -1, null));
    exceptException(IllegalArgumentException.class, () -> in(0L, 0L, -1L, null));
    exceptException(IllegalArgumentException.class, () -> in(0.0F, 0.0F, -1.0F, null));
    exceptException(IllegalArgumentException.class, () -> in(0.0, 0.0, -1.0, null));

    exceptException(IllegalArgumentException.class, () -> in((byte) 0, (byte) 0, (byte) 0, null));
    exceptException(IllegalArgumentException.class, () -> in((short) 0, (short) 0, (short) 0, null));
    exceptException(IllegalArgumentException.class, () -> in(0, 0, 0, null));
    exceptException(IllegalArgumentException.class, () -> in(0L, 0L, 0L, null));
    exceptException(IllegalArgumentException.class, () -> in(0.0F, 0.0F, 0.0F, null));
    exceptException(IllegalArgumentException.class, () -> in(0.0, 0.0, 0.0, null));

    exceptException(IllegalArgumentException.class, () -> in((byte) -1, (byte) 0, (byte) 1, null));
    exceptException(IllegalArgumentException.class, () -> in((short) -1, (short) 0, (short) 1, null));
    exceptException(IllegalArgumentException.class, () -> in(-1, 0, 1, null));
    exceptException(IllegalArgumentException.class, () -> in(-1L, 0L, 1L, null));
    exceptException(IllegalArgumentException.class, () -> in(-1.0F, 0.0F, 1.0F, null));
    exceptException(IllegalArgumentException.class, () -> in(-1.0, 0.0, 1.0, null));

    exceptException(IllegalArgumentException.class, () -> in((byte) -1, (byte) 0, (byte) 1, null));
    exceptException(IllegalArgumentException.class, () -> in((short) -1, (short) 0, (short) 1, null));
    exceptException(IllegalArgumentException.class, () -> in(-1, 0, 1, null));
    exceptException(IllegalArgumentException.class, () -> in(-1L, 0L, 1L, null));
    exceptException(IllegalArgumentException.class, () -> in(-1.0F, 0.0F, 1.0F, null));
    exceptException(IllegalArgumentException.class, () -> in(-1.0, 0.0, 1.0, null));

    exceptException(IllegalArgumentException.class, () -> in((byte) 1, (byte) 0, (byte) 1, null));
    exceptException(IllegalArgumentException.class, () -> in((short) 1, (short) 0, (short) 1, null));
    exceptException(IllegalArgumentException.class, () -> in(1, 0, 1, null));
    exceptException(IllegalArgumentException.class, () -> in(1L, 0L, 1L, null));
    exceptException(IllegalArgumentException.class, () -> in(1.0F, 0.0F, 1.0F, null));
    exceptException(IllegalArgumentException.class, () -> in(1.0, 0.0, 1.0, null));

    exceptException(IllegalArgumentException.class, () -> in((byte) 2, (byte) 0, (byte) 1, null));
    exceptException(IllegalArgumentException.class, () -> in((short) 2, (short) 0, (short) 1, null));
    exceptException(IllegalArgumentException.class, () -> in(2, 0, 1, null));
    exceptException(IllegalArgumentException.class, () -> in(2L, 0L, 1L, null));
    exceptException(IllegalArgumentException.class, () -> in(2.0F, 0.0F, 1.0F, null));
    exceptException(IllegalArgumentException.class, () -> in(2.0, 0.0, 1.0, null));
  }

  @Test
  public void testInWithPitcher() throws Exception {
    exceptException(TestException.class, () -> in((byte) -1, (byte) 0, (byte) 2, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> in((short) -1, (short) 0, (short) 2, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> in(-1, 0, 2, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> in(-1L, 0L, 2L, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> in(-1.0F, 0.0F, 2.0F, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> in(-1, 0.0, 2.0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());

    in((byte) 0, (byte) 0, (byte) 2, this.testPitcher);
    in((short) 0, (short) 0, (short) 2, this.testPitcher);
    in(0, 0, 2, this.testPitcher);
    in(0L, 0L, 2L, this.testPitcher);
    in(0.0F, 0.0F, 2.0F, this.testPitcher);
    in(0.0, 0.0, 2.0, this.testPitcher);

    in((byte) 0, (byte) 0, (byte) 2, null);
    in((short) 0, (short) 0, (short) 2, null);
    in(0, 0, 2, null);
    in(0L, 0L, 2L, null);
    in(0.0F, 0.0F, 2.0F, null);
    in(0.0, 0.0, 2.0, null);

    in((byte) 1, (byte) 0, (byte) 2, this.testPitcher);
    in((short) 1, (short) 0, (short) 2, this.testPitcher);
    in(1, 0, 2, this.testPitcher);
    in(1L, 0L, 2L, this.testPitcher);
    in(1.0F, 0.0F, 2.0F, this.testPitcher);
    in(1.0, 0.0, 2.0, this.testPitcher);

    in((byte) 1, (byte) 0, (byte) 2, null);
    in((short) 1, (short) 0, (short) 2, null);
    in(1, 0, 2, null);
    in(1L, 0L, 2L, null);
    in(1.0F, 0.0F, 2.0F, null);
    in(1.0, 0.0, 2.0, null);

    exceptException(TestException.class, () -> in((byte) 2, (byte) 0, (byte) 2, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> in((short) 2, (short) 0, (short) 2, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> in(2, 0, 2, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> in(2L, 0L, 2L, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> in(2.0F, 0.0F, 2.0F, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> in(2, 0.0, 2.0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());

    exceptException(TestException.class, () -> in((byte) 3, (byte) 0, (byte) 2, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> in((short) 3, (short) 0, (short) 2, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> in(3, 0, 2, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> in(3L, 0L, 2L, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> in(3.0F, 0.0F, 2.0F, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> in(3, 0.0, 2.0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
  }

  @Test
  public void testLtWithExceptionForThrowsIllegalArgumentException() throws Exception {
    exceptException(IllegalArgumentException.class, () -> lt((byte) 0, (byte) 0, null));
    exceptException(IllegalArgumentException.class, () -> lt((short) 0, (short) 0, null));
    exceptException(IllegalArgumentException.class, () -> lt(0, 0, null));
    exceptException(IllegalArgumentException.class, () -> lt(0L, 0L, null));
    exceptException(IllegalArgumentException.class, () -> lt(0.0F, 0.0F, null));
    exceptException(IllegalArgumentException.class, () -> lt(0.0, 0.0, null));

    exceptException(IllegalArgumentException.class, () -> lt((byte) 1, (byte) 0, null));
    exceptException(IllegalArgumentException.class, () -> lt((short) 1, (short) 0, null));
    exceptException(IllegalArgumentException.class, () -> lt(1, 0, null));
    exceptException(IllegalArgumentException.class, () -> lt(1L, 0L, null));
    exceptException(IllegalArgumentException.class, () -> lt(1.0F, 0.0F, null));
    exceptException(IllegalArgumentException.class, () -> lt(1.0, 0.0, null));

    exceptException(IllegalArgumentException.class, () -> lt((byte) 0, (byte) 0, TestException.class));
    exceptException(IllegalArgumentException.class, () -> lt((short) 0, (short) 0, TestException.class));
    exceptException(IllegalArgumentException.class, () -> lt(0, 0, TestException.class));
    exceptException(IllegalArgumentException.class, () -> lt(0L, 0L, TestException.class));
    exceptException(IllegalArgumentException.class, () -> lt(0.0F, 0.0F, TestException.class));
    exceptException(IllegalArgumentException.class, () -> lt(0.0, 0.0, TestException.class));

    exceptException(IllegalArgumentException.class, () -> lt((byte) 1, (byte) 0, TestException.class));
    exceptException(IllegalArgumentException.class, () -> lt((short) 1, (short) 0, TestException.class));
    exceptException(IllegalArgumentException.class, () -> lt(1, 0, TestException.class));
    exceptException(IllegalArgumentException.class, () -> lt(1L, 0L, TestException.class));
    exceptException(IllegalArgumentException.class, () -> lt(1.0F, 0.0F, TestException.class));
    exceptException(IllegalArgumentException.class, () -> lt(1.0, 0.0, TestException.class));
  }

  @Test
  public void testLtWithException() throws Exception {
    lt((byte) 0, (byte) 1, null, 1);
    lt((short) 0, (short) 1, null, 1);
    lt(0, 1, null, 1);
    lt(0L, 1L, null, 1);
    lt(0.0F, 1.0F, null, 1);
    lt(0.0, 1.0, null, 1);

    lt((byte) 0, (byte) 1, TestException.class);
    lt((short) 0, (short) 1, TestException.class);
    lt(0, 1, TestException.class);
    lt(0L, 1L, TestException.class);
    lt(0.0F, 1.0F, TestException.class);
    lt(0.0, 1.0, TestException.class);

    lt((byte) 0, (byte) 1, TestException.class, this.testException.i, this.testException.s);
    lt((short) 0, (short) 1, TestException.class, this.testException.i, this.testException.s);
    lt(0, 1, TestException.class, this.testException.i, this.testException.s);
    lt(0L, 1L, TestException.class, this.testException.i, this.testException.s);
    lt(0.0F, 1.0F, TestException.class, this.testException.i, this.testException.s);
    lt(0.0, 1.0, TestException.class, this.testException.i, this.testException.s);

    exceptException(TestException.class,
        () -> lt((byte) 0, (byte) 0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> lt((short) 0, (short) 0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> lt(0, 0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> lt(0L, 0L, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> lt(0.0F, 0.0F, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> lt(0.0, 0.0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());

    exceptException(TestException.class,
        () -> lt((byte) 1, (byte) 0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> lt((short) 1, (short) 0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> lt(1, 0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> lt(1L, 0L, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> lt(1.0F, 0.0F, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> lt(1.0, 0.0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
  }

  @Test
  public void testLtWithPitcherForThrows() throws Exception {
    exceptException(IllegalArgumentException.class, () -> lt((byte) 1, (byte) 0, null));
    exceptException(IllegalArgumentException.class, () -> lt((short) 1, (short) 0, null));
    exceptException(IllegalArgumentException.class, () -> lt(1, 0, null));
    exceptException(IllegalArgumentException.class, () -> lt(1L, 0L, null));
    exceptException(IllegalArgumentException.class, () -> lt(1.0F, 0.0F, null));
    exceptException(IllegalArgumentException.class, () -> lt(1.0, 0.0, null));
  }

  @Test
  public void testWithPitcher() throws Exception {
    lt((byte) 0, (byte) 1, null);
    lt((short) 0, (short) 1, null);
    lt(0, 1, null);
    lt(0L, 1L, null);
    lt(0.0F, 1.0F, null);
    lt(0.0, 1.0, null);

    lt((byte) 0, (byte) 1, this.testPitcher);
    lt((short) 0, (short) 1, this.testPitcher);
    lt(0, 1, this.testPitcher);
    lt(0L, 1L, this.testPitcher);
    lt(0.0F, 1.0F, this.testPitcher);
    lt(0.0, 1.0, this.testPitcher);

    exceptException(TestException.class, () -> lt((byte) 0, (byte) 0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> lt((short) 0, (short) 0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> lt(0, 0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> lt(0L, 0L, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> lt(0.0F, 0.0F, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> lt(0.0, 0.0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());

    exceptException(TestException.class, () -> lt((byte) 1, (byte) 0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> lt((short) 1, (short) 0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> lt(1, 0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> lt(1L, 0L, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> lt(1.0F, 0.0F, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> lt(1.0, 0.0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
  }

  @Test
  public void testLeWithExceptionForThrows() throws Exception {
    exceptException(IllegalArgumentException.class, () -> le((byte) 1, (byte) 0, null, ""));
    exceptException(IllegalArgumentException.class, () -> le((short) 1, (short) 0, null, ""));
    exceptException(IllegalArgumentException.class, () -> le(1, 0, null, ""));
    exceptException(IllegalArgumentException.class, () -> le(1L, 0L, null, ""));
    exceptException(IllegalArgumentException.class, () -> le(1.0F, 0.0F, null, ""));
    exceptException(IllegalArgumentException.class, () -> le(1.0, 0.0, null, ""));

    exceptException(IllegalArgumentException.class, () -> le((byte) 1, (byte) 0, TestException.class));
    exceptException(IllegalArgumentException.class, () -> le((short) 1, (short) 0, TestException.class));
    exceptException(IllegalArgumentException.class, () -> le(1, 0, TestException.class));
    exceptException(IllegalArgumentException.class, () -> le(1L, 0L, TestException.class));
    exceptException(IllegalArgumentException.class, () -> le(1.0F, 0.0F, TestException.class));
    exceptException(IllegalArgumentException.class, () -> le(1.0, 0.0, TestException.class));
  }

  @Test
  public void testLeWithException() throws Exception {
    le((byte) -1, (byte) 0, null, 1);
    le((short) -1, (short) 0, null, 1);
    le(-1, 0, null, 1);
    le(-1L, 0L, null, 1);
    le(-1.0F, 0.0F, null, 1);
    le(-1.0, 0.0, null, 1);

    le((byte) -1, (byte) 0, TestException.class);
    le((short) -1, (short) 0, TestException.class);
    le(-1, 0, TestException.class);
    le(-1L, 0L, TestException.class);
    le(-1.0F, 0.0F, TestException.class);
    le(-1.0, 0.0, TestException.class);

    le((byte) -1, (byte) 0, TestException.class, this.testException.i, this.testException.s);
    le((short) -1, (short) 0, TestException.class, this.testException.i, this.testException.s);
    le(-1, 0, TestException.class, this.testException.i, this.testException.s);
    le(-1L, 0L, TestException.class, this.testException.i, this.testException.s);
    le(-1.0F, 0.0F, TestException.class, this.testException.i, this.testException.s);
    le(-1.0, 0.0, TestException.class, this.testException.i, this.testException.s);

    le((byte) 0, (byte) 0, null, 1);
    le((short) 0, (short) 0, null, 1);
    le(0, 0, null, 1);
    le(0L, 0L, null, 1);
    le(0.0F, 0.0F, null, 1);
    le(0.0, 0.0, null, 1);

    le((byte) 0, (byte) 0, TestException.class);
    le((short) 0, (short) 0, TestException.class);
    le(0, 0, TestException.class);
    le(0L, 0L, TestException.class);
    le(0.0F, 0.0F, TestException.class);
    le(0.0, 0.0, TestException.class);

    le((byte) 0, (byte) 0, TestException.class, this.testException.i, this.testException.s);
    le((short) 0, (short) 0, TestException.class, this.testException.i, this.testException.s);
    le(0, 0, TestException.class, this.testException.i, this.testException.s);
    le(0L, 0L, TestException.class, this.testException.i, this.testException.s);
    le(0.0F, 0.0F, TestException.class, this.testException.i, this.testException.s);
    le(0.0, 0.0, TestException.class, this.testException.i, this.testException.s);

    exceptException(TestException.class,
        () -> le((byte) 1, (byte) 0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> le((short) 1, (short) 0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> le(1, 0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> le(1L, 0L, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> le(1.0F, 0.0F, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> le(1.0, 0.0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
  }

  @Test
  public void testLeWithPitcherForThrows() throws Exception {
    exceptException(IllegalArgumentException.class, () -> le((byte) 1, (byte) 0, null));
    exceptException(IllegalArgumentException.class, () -> le((short) 1, (short) 0, null));
    exceptException(IllegalArgumentException.class, () -> le(1, 0, null));
    exceptException(IllegalArgumentException.class, () -> le(1L, 0L, null));
    exceptException(IllegalArgumentException.class, () -> le(1.0F, 0.0F, null));
    exceptException(IllegalArgumentException.class, () -> le(1.0, 0.0, null));
  }

  @Test
  public void testLeWithPitcher() throws Exception {
    le((byte) -1, (byte) 0, null);
    le((short) -1, (short) 0, null);
    le(-1, 0, null);
    le(-1L, 0L, null);
    le(-1.0F, 0.0F, null);
    le(-1.0, 0.0, null);

    le((byte) -1, (byte) 0, this.testPitcher);
    le((short) -1, (short) 0, this.testPitcher);
    le(-1, 0, this.testPitcher);
    le(-1L, 0L, this.testPitcher);
    le(-1.0F, 0.0F, this.testPitcher);
    le(-1.0, 0.0, this.testPitcher);

    le((byte) 0, (byte) 0, null);
    le((short) 0, (short) 0, null);
    le(0, 0, null);
    le(0L, 0L, null);
    le(0.0F, 0.0F, null);
    le(0.0, 0.0, null);

    le((byte) 0, (byte) 0, this.testPitcher);
    le((short) 0, (short) 0, this.testPitcher);
    le(0, 0, this.testPitcher);
    le(0L, 0L, this.testPitcher);
    le(0.0F, 0.0F, this.testPitcher);
    le(0.0, 0.0, this.testPitcher);

    exceptException(TestException.class, () -> le((byte) 1, (byte) 0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> le((short) 1, (short) 0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> le(1, 0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> le(1L, 0L, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> le(1.0F, 0.0F, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> le(1.0, 0.0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
  }

  @Test
  public void testGtWithExceptionForThrows() throws Exception {
    exceptException(IllegalArgumentException.class, () -> gt((byte) -1, (byte) 0, null, ""));
    exceptException(IllegalArgumentException.class, () -> gt((short) -1, (short) 0, null, ""));
    exceptException(IllegalArgumentException.class, () -> gt(-1, 0, null, ""));
    exceptException(IllegalArgumentException.class, () -> gt(-1L, 0L, null, ""));
    exceptException(IllegalArgumentException.class, () -> gt(-1.0F, 0.0F, null, ""));
    exceptException(IllegalArgumentException.class, () -> gt(-1.0, 0.0, null, ""));

    exceptException(IllegalArgumentException.class, () -> gt((byte) -1, (byte) 0, TestException.class));
    exceptException(IllegalArgumentException.class, () -> gt((short) -1, (short) 0, TestException.class));
    exceptException(IllegalArgumentException.class, () -> gt(-1, 0, TestException.class));
    exceptException(IllegalArgumentException.class, () -> gt(-1L, 0L, TestException.class));
    exceptException(IllegalArgumentException.class, () -> gt(-1.0F, 0.0F, TestException.class));
    exceptException(IllegalArgumentException.class, () -> gt(-1.0, 0.0, TestException.class));

    exceptException(IllegalArgumentException.class, () -> gt((byte) 0, (byte) 0, null, ""));
    exceptException(IllegalArgumentException.class, () -> gt((short) 0, (short) 0, null, ""));
    exceptException(IllegalArgumentException.class, () -> gt(0, 0, null, ""));
    exceptException(IllegalArgumentException.class, () -> gt(0L, 0L, null, ""));
    exceptException(IllegalArgumentException.class, () -> gt(0.0F, 0.0F, null, ""));
    exceptException(IllegalArgumentException.class, () -> gt(0.0, 0.0, null, ""));

    exceptException(IllegalArgumentException.class, () -> gt((byte) 0, (byte) 0, TestException.class));
    exceptException(IllegalArgumentException.class, () -> gt((short) 0, (short) 0, TestException.class));
    exceptException(IllegalArgumentException.class, () -> gt(0, 0, TestException.class));
    exceptException(IllegalArgumentException.class, () -> gt(0L, 0L, TestException.class));
    exceptException(IllegalArgumentException.class, () -> gt(0.0F, 0.0F, TestException.class));
    exceptException(IllegalArgumentException.class, () -> gt(0.0, 0.0, TestException.class));
  }

  @Test
  public void testGtWithException() throws Exception {
    exceptException(TestException.class,
        () -> gt((byte) -1, (byte) 0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> gt((short) -1, (short) 0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> gt(-1, 0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> gt(-1L, 0L, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> gt(-1.0F, 0.0F, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> gt(-1.0, 0.0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());

    exceptException(TestException.class,
        () -> gt((byte) 0, (byte) 0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> gt((short) 0, (short) 0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> gt(0, 0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> gt(0L, 0L, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> gt(0.0F, 0.0F, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> gt(0.0, 0.0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());

    gt((byte) 1, (byte) 0, null, 1);
    gt((short) 1, (short) 0, null, 1);
    gt(1, 0, null, 1);
    gt(1L, 0L, null, 1);
    gt(1.0F, 0.0F, null, 1);
    gt(1.0, 0.0, null, 1);

    gt((byte) 1, (byte) 0, TestException.class);
    gt((short) 1, (short) 0, TestException.class);
    gt(1, 0, TestException.class);
    gt(1L, 0L, TestException.class);
    gt(1.0F, 0.0F, TestException.class);
    gt(1.0, 0.0, TestException.class);
  }

  @Test
  public void testGtWithPitcherForThrows() throws Exception {
    exceptException(IllegalArgumentException.class, () -> gt((byte) -1, (byte) 0, null));
    exceptException(IllegalArgumentException.class, () -> gt((short) -1, (short) 0, null));
    exceptException(IllegalArgumentException.class, () -> gt(-1, 0, null));
    exceptException(IllegalArgumentException.class, () -> gt(-1L, 0L, null));
    exceptException(IllegalArgumentException.class, () -> gt(-1.0F, 0.0F, null));
    exceptException(IllegalArgumentException.class, () -> gt(-1.0, 0.0, null));

    exceptException(IllegalArgumentException.class, () -> gt((byte) 0, (byte) 0, null));
    exceptException(IllegalArgumentException.class, () -> gt((short) 0, (short) 0, null));
    exceptException(IllegalArgumentException.class, () -> gt(0, 0, null));
    exceptException(IllegalArgumentException.class, () -> gt(0L, 0L, null));
    exceptException(IllegalArgumentException.class, () -> gt(0.0F, 0.0F, null));
    exceptException(IllegalArgumentException.class, () -> gt(0.0, 0.0, null));
  }

  @Test
  public void testGtWithPitcher() throws Exception {
    exceptException(TestException.class, () -> gt((byte) -1, (byte) 0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> gt((short) -1, (short) 0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> gt(-1, 0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> gt(-1L, 0L, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> gt(-1.0F, 0.0F, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> gt(-1.0, 0.0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());

    exceptException(TestException.class, () -> gt((byte) 0, (byte) 0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> gt((short) 0, (short) 0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> gt(0, 0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> gt(0L, 0L, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> gt(0.0F, 0.0F, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> gt(0.0, 0.0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());

    gt((byte) 1, (byte) 0, null);
    gt((short) 1, (short) 0, null);
    gt(1, 0, null);
    gt(1L, 0L, null);
    gt(1.0F, 0.0F, null);
    gt(1.0, 0.0, null);

    gt((byte) 1, (byte) 0, this.testPitcher);
    gt((short) 1, (short) 0, this.testPitcher);
    gt(1, 0, this.testPitcher);
    gt(1L, 0L, this.testPitcher);
    gt(1.0F, 0.0F, this.testPitcher);
    gt(1.0, 0.0, this.testPitcher);
  }

  @Test
  public void testGeWithExceptionForThrows() throws Exception {
    exceptException(IllegalArgumentException.class, () -> ge((byte) -1, (byte) 0, TestException.class));
    exceptException(IllegalArgumentException.class, () -> ge((short) -1, (short) 0, TestException.class));
    exceptException(IllegalArgumentException.class, () -> ge(-1, 0, TestException.class));
    exceptException(IllegalArgumentException.class, () -> ge(-1L, 0L, TestException.class));
    exceptException(IllegalArgumentException.class, () -> ge(-1.0F, 0.0F, TestException.class));
    exceptException(IllegalArgumentException.class, () -> ge(-1.0, 0.0, TestException.class));

    exceptException(IllegalArgumentException.class, () -> ge((byte) -1, (byte) 0, null, ""));
    exceptException(IllegalArgumentException.class, () -> ge((short) -1, (short) 0, null, ""));
    exceptException(IllegalArgumentException.class, () -> ge(-1, 0, null, ""));
    exceptException(IllegalArgumentException.class, () -> ge(-1L, 0L, null, ""));
    exceptException(IllegalArgumentException.class, () -> ge(-1.0F, 0.0F, null, ""));
    exceptException(IllegalArgumentException.class, () -> ge(-1.0, 0.0, null, ""));
  }

  @Test
  public void testGeWithException() throws Exception {
    exceptException(TestException.class,
        () -> ge((byte) -1, (byte) 0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> ge((short) -1, (short) 0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> ge(-1, 0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> ge(-1L, 0L, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> ge(-1.0F, 0.0F, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> ge(-1.0, 0.0, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());

    ge((byte) 0, (byte) 0, null, "");
    ge((short) 0, (short) 0, null, "");
    ge(0, 0, null, "");
    ge(0L, 0L, null, "");
    ge(0.0F, 0.0F, null, "");
    ge(0.0, 0.0, null, "");

    ge((byte) 0, (byte) 0, TestException.class);
    ge((short) 0, (short) 0, TestException.class);
    ge(0, 0, TestException.class);
    ge(0L, 0L, TestException.class);
    ge(0.0F, 0.0F, TestException.class);
    ge(0.0, 0.0, TestException.class);

    ge((byte) 1, (byte) 0, null, "");
    ge((short) 1, (short) 0, null, "");
    ge(1, 0, null, "");
    ge(1L, 0L, null, "");
    ge(1.0F, 0.0F, null, "");
    ge(1.0, 0.0, null, "");

    ge((byte) 1, (byte) 0, TestException.class);
    ge((short) 1, (short) 0, TestException.class);
    ge(1, 0, TestException.class);
    ge(1L, 0L, TestException.class);
    ge(1.0F, 0.0F, TestException.class);
    ge(1.0, 0.0, TestException.class);
  }

  @Test
  public void testGeWithPitcherForThrows() throws Exception {
    exceptException(IllegalArgumentException.class, () -> ge((byte) -1, (byte) 0, null));
    exceptException(IllegalArgumentException.class, () -> ge((short) -1, (short) 0, null));
    exceptException(IllegalArgumentException.class, () -> ge(-1, 0, null));
    exceptException(IllegalArgumentException.class, () -> ge(-1L, 0L, null));
    exceptException(IllegalArgumentException.class, () -> ge(-1.0F, 0.0F, null));
    exceptException(IllegalArgumentException.class, () -> ge(-1.0, 0.0, null));
  }

  @Test
  public void testGeWithPitcher() throws Exception {
    exceptException(TestException.class, () -> ge((byte) -1, (byte) 0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> ge((short) -1, (short) 0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> ge(-1, 0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> ge(-1L, 0L, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> ge(-1.0F, 0.0F, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> ge(-1.0, 0.0, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());

    ge((byte) 0, (byte) 0, null);
    ge((short) 0, (short) 0, null);
    ge(0, 0, null);
    ge(0L, 0L, null);
    ge(0.0F, 0.0F, null);
    ge(0.0, 0.0, null);

    ge((byte) 0, (byte) 0, this.testPitcher);
    ge((short) 0, (short) 0, this.testPitcher);
    ge(0, 0, this.testPitcher);
    ge(0L, 0L, this.testPitcher);
    ge(0.0F, 0.0F, this.testPitcher);
    ge(0.0, 0.0, this.testPitcher);

    ge((byte) 1, (byte) 0, null);
    ge((short) 1, (short) 0, null);
    ge(1, 0, null);
    ge(1L, 0L, null);
    ge(1.0F, 0.0F, null);
    ge(1.0, 0.0, null);

    ge((byte) 1, (byte) 0, this.testPitcher);
    ge((short) 1, (short) 0, this.testPitcher);
    ge(1, 0, this.testPitcher);
    ge(1L, 0L, this.testPitcher);
    ge(1.0F, 0.0F, this.testPitcher);
    ge(1.0, 0.0, this.testPitcher);
  }

  @Test
  public void testIsNullWithExceptionForThrows() throws Exception {
    exceptException(IllegalArgumentException.class, () -> isNull(new Object(), null, ""));
    exceptException(IllegalArgumentException.class, () -> isNull(new Object(), TestException.class));
  }

  @Test
  public void testIsNullWithException() throws Exception {
    isNull(null, TestException.class, this.testException.i, this.testException.s);
    isNull(null, null, "");
    isNull(null, TestException.class);

    exceptException(TestException.class,
        () -> isNull(new Object(), TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
  }

  @Test
  public void testIsNullWithPitcherForThrows() throws Exception {
    exceptException(IllegalArgumentException.class, () -> isNull(new Object(), null));
  }

  @Test
  public void testIsNullWithPitcher() throws Exception {
    isNull(null, null);
    isNull(null, this.testPitcher);
    exceptException(TestException.class, () -> isNull(new Object(), this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
  }

  @Test
  public void testNotNullWithExceptionForThrows() throws Exception {
    exceptException(IllegalArgumentException.class, () -> notNull(null, null, ""));
    exceptException(IllegalArgumentException.class, () -> notNull(null, TestException.class));
  }

  @Test
  public void testNotNullWithException() throws Exception {
    notNull(new Object(), TestException.class, this.testException.i, this.testException.s);
    notNull(new Object(), TestException.class);
    notNull(new Object(), null, "");

    exceptException(TestException.class,
        () -> notNull(null, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
  }

  @Test
  public void testNotNullWithPitcherForThrows() throws Exception {
    exceptException(IllegalArgumentException.class, () -> notNull(null, null));
  }

  @Test
  public void testNotNullWithPitcher() throws Exception {
    notNull(new Object(), this.testPitcher);
    notNull(new Object(), null);
    exceptException(TestException.class, () -> notNull(null, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
  }

  @Test
  public void testInstanceForThrows() throws Exception {
    class A {
    }
    class B {
    }

    exceptException(IllegalArgumentException.class, () -> instance(null, null, null, ""));
    exceptException(IllegalArgumentException.class, () -> instance(new A(), B.class, null, ""));
    exceptException(IllegalArgumentException.class, () -> instance(new B(), A.class, null, ""));
    exceptException(IllegalArgumentException.class, () -> instance(new A(), Object.class, null, ""));
    exceptException(IllegalArgumentException.class, () -> instance(new Object(), A.class, null, ""));
  }

  @Test
  public void testInstanceWithException() throws Exception {
    class A {
    }
    class B {
    }

    instance(new Object(), Object.class, null, "");
    instance(new A(), A.class, null, "");
    instance(new B(), B.class, null, "");

    exceptException(TestException.class,
        () -> instance(new A(), Object.class, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> instance(new Object(), A.class, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> instance(new A(), B.class, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
  }

  @Test
  public void testAssignableWithException() throws Exception {
    class A {
    }
    class B {
    }

    assignable(new A(), A.class, null, "");
    assignable(new A(), Object.class, null, "");

    assignable(new A(), A.class, TestException.class, "");
    assignable(new A(), Object.class, TestException.class, "");

    assignable(new A(), A.class, TestException.class, this.testException.i, this.testException.s);
    assignable(new A(), Object.class, TestException.class, this.testException.i, this.testException.s);

    exceptException(TestException.class,
        () -> assignable(new A(), B.class, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> assignable(new Object(), A.class, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
  }

  @Test
  public void testAssignableWithPitcherForThrows() throws Exception {
    class A {
    }
    exceptException(IllegalArgumentException.class, () -> assignable(new Object(), null, null));
    exceptException(IllegalArgumentException.class, () -> assignable(new A(), null, null));
    exceptException(IllegalArgumentException.class, () -> assignable(new Object(), null, this.testPitcher));
    exceptException(IllegalArgumentException.class, () -> assignable(new A(), null, this.testPitcher));
  }

  @Test
  public void testAssignableWithPitcher() throws Exception {
    class A {
    }
    class B {
    }

    assignable(new A(), Object.class, null);
    assignable(new A(), A.class, null);
    assignable(new B(), Object.class, null);
    assignable(new B(), B.class, null);

    assignable(new A(), Object.class, this.testPitcher);
    assignable(new A(), A.class, this.testPitcher);
    assignable(new B(), Object.class, this.testPitcher);
    assignable(new B(), B.class, this.testPitcher);

    exceptException(TestException.class, () -> assignable(new Object(), A.class, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> assignable(new A(), B.class, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
  }

  @Test
  public void testHasLengthWithException() throws Exception {
    hasLength("1", null, "");
    hasLength("11", null, "");
    hasLength("1", TestException.class);
    hasLength("11", TestException.class);
    hasLength("1", TestException.class, this.testException.i, this.testException.s);
    hasLength("11", TestException.class, this.testException.i, this.testException.s);

    exceptException(IllegalArgumentException.class, () -> hasLength(null, null, ""));
    exceptException(IllegalArgumentException.class, () -> hasLength("", null, ""));
    exceptException(IllegalArgumentException.class, () -> hasLength(null, TestException.class));
    exceptException(IllegalArgumentException.class, () -> hasLength("", TestException.class));

    exceptException(TestException.class,
        () -> hasLength(null, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> hasLength("", TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
  }

  @Test
  public void testHasLengthWithPitcher() throws Exception {
    hasLength("1", null);
    hasLength("11", null);
    hasLength("1", this.testPitcher);
    hasLength("11", this.testPitcher);

    exceptException(IllegalArgumentException.class, () -> hasLength(null, null));
    exceptException(IllegalArgumentException.class, () -> hasLength("", null));

    exceptException(TestException.class, () -> hasLength(null, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> hasLength("", this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
  }

  @Test
  public void testLengthWithException() throws Exception {
    length("", 0, null, "");
    length("1", 1, null, "");
    length("", 0, TestException.class);
    length("1", 1, TestException.class);
    length("", 0, TestException.class, this.testException.i, this.testException.s);
    length("1", 1, TestException.class, this.testException.i, this.testException.s);

    exceptException(IllegalArgumentException.class, () -> length(null, -1, null, ""));
    exceptException(IllegalArgumentException.class, () -> length(null, -1, TestException.class));
    exceptException(IllegalArgumentException.class,
        () -> length(null, -1, TestException.class, this.testException.i, this.testException.s));
    exceptException(IllegalArgumentException.class, () -> length(null, 0, null, ""));
    exceptException(IllegalArgumentException.class, () -> length(null, 0, TestException.class));
    exceptException(IllegalArgumentException.class,
        () -> length(null, 0, TestException.class, this.testException.i, this.testException.s));
    exceptException(IllegalArgumentException.class, () -> length("", -1, null, ""));
    exceptException(IllegalArgumentException.class, () -> length("", -1, TestException.class));
    exceptException(IllegalArgumentException.class,
        () -> length("", -1, TestException.class, this.testException.i, this.testException.s));
    exceptException(IllegalArgumentException.class, () -> length("1", -1, null, ""));
    exceptException(IllegalArgumentException.class, () -> length("1", -1, TestException.class));
    exceptException(IllegalArgumentException.class,
        () -> length("1", -1, TestException.class, this.testException.i, this.testException.s));

    exceptException(TestException.class,
        () -> length("", 1, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> length("11", 1, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
  }

  @Test
  public void testLengthWithPitcher() throws Exception {
    length("", 0, null);
    length("1", 1, null);
    length("", 0, this.testPitcher);
    length("1", 1, this.testPitcher);

    exceptException(IllegalArgumentException.class, () -> length(null, -1, null));
    exceptException(IllegalArgumentException.class, () -> length(null, 0, null));
    exceptException(IllegalArgumentException.class, () -> length("", -1, null));
    exceptException(IllegalArgumentException.class, () -> length("11", -1, null));

    exceptException(TestException.class, () -> length("", 1, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> length("11", 1, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
  }

  @Test
  public void testLengthWithRangeAndException() throws Exception {
    exceptException(IllegalArgumentException.class, () -> length("", 0, 0, null, ""));
    exceptException(IllegalArgumentException.class, () -> length("", 0, 0, TestException.class));
    exceptException(IllegalArgumentException.class,
        () -> length("", 0, 0, TestException.class, this.testException.i, this.testException.s));

    exceptException(IllegalArgumentException.class, () -> length(null, 1, 0, null, ""));
    exceptException(IllegalArgumentException.class, () -> length(null, 1, 0, TestException.class));
    exceptException(IllegalArgumentException.class,
        () -> length(null, 1, 0, TestException.class, this.testException.i, this.testException.s));

    exceptException(IllegalArgumentException.class, () -> length("1", 1, 0, null, ""));
    exceptException(IllegalArgumentException.class, () -> length("1", 1, 0, TestException.class));
    exceptException(IllegalArgumentException.class,
        () -> length("1", 1, 0, TestException.class, this.testException.i, this.testException.s));

    length("1", 1, 3, null, "");
    length("1", 1, 3, TestException.class);
    length("1", 1, 3, TestException.class, this.testException.i, this.testException.s);

    length("11", 1, 3, null, "");
    length("11", 1, 3, TestException.class);
    length("11", 1, 3, TestException.class, this.testException.i, this.testException.s);

    length("111", 1, 3, null, "");
    length("111", 1, 3, TestException.class);
    length("111", 1, 3, TestException.class, this.testException.i, this.testException.s);

    exceptException(TestException.class,
        () -> length("", 1, 2, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> length("111", 1, 2, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
  }

  @Test
  public void testLengthWithRangeAndPitcher() throws Exception {
    exceptException(IllegalArgumentException.class, () -> length("", 0, 0, null));
    exceptException(IllegalArgumentException.class, () -> length("", 0, 0, this.testPitcher));

    exceptException(IllegalArgumentException.class, () -> length(null, 1, 0, null));
    exceptException(IllegalArgumentException.class, () -> length(null, 1, 0, this.testPitcher));

    exceptException(IllegalArgumentException.class, () -> length("1", 1, 0, null));
    exceptException(IllegalArgumentException.class, () -> length("1", 1, 0, this.testPitcher));

    length("1", 1, 3, null);
    length("1", 1, 3, this.testPitcher);

    length("11", 1, 3, null);
    length("11", 1, 3, this.testPitcher);

    length("111", 1, 3, null);
    length("111", 1, 3, this.testPitcher);

    exceptException(TestException.class,
        () -> length("", 1, 2, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> length("111", 1, 2, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());

    exceptException(TestException.class, () -> length("", 1, 3, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> length("1111", 1, 3, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
  }

  @Test
  public void testShorterWithException() throws Exception {
    shorter("", 1, null, "");
    shorter("", 1, TestException.class);
    shorter("", 1, TestException.class, this.testException.i, this.testException.s);

    shorter("1", 1, null, "");
    shorter("1", 1, TestException.class);
    shorter("1", 1, TestException.class, this.testException.i, this.testException.s);

    exceptException(TestException.class,
        () -> shorter("11", 1, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());

    exceptException(IllegalArgumentException.class, () -> shorter(null, 0, null, ""));
    exceptException(IllegalArgumentException.class, () -> shorter(null, -1, null, ""));
    exceptException(IllegalArgumentException.class, () -> shorter("", -1, null, ""));

    exceptException(IllegalArgumentException.class, () -> shorter(null, 0, TestException.class));
    exceptException(IllegalArgumentException.class, () -> shorter(null, -1, TestException.class));
    exceptException(IllegalArgumentException.class, () -> shorter("", -1, TestException.class));

    exceptException(IllegalArgumentException.class,
        () -> shorter(null, 0, TestException.class, this.testException.i, this.testException.s));
    exceptException(IllegalArgumentException.class,
        () -> shorter(null, -1, TestException.class, this.testException.i, this.testException.s));
    exceptException(IllegalArgumentException.class,
        () -> shorter("", -1, TestException.class, this.testException.i, this.testException.s));
  }

  @Test
  public void testShorterWithPitcher() throws Exception {
    shorter("", 1, null);
    shorter("", 1, this.testPitcher);
    shorter("1", 1, null);
    shorter("1", 1, this.testPitcher);
    exceptException(TestException.class, () -> shorter("22", 1, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());

    exceptException(IllegalArgumentException.class, () -> shorter(null, -1, null));
    exceptException(IllegalArgumentException.class, () -> shorter(null, -1, this.testPitcher));
    exceptException(IllegalArgumentException.class, () -> shorter("", -1, null));
    exceptException(IllegalArgumentException.class, () -> shorter("", -1, this.testPitcher));
  }

  @Test
  public void testLongerWithException() throws Exception {
    longer("", 0, null, "");
    longer("", 0, TestException.class);
    longer("", 0, TestException.class, this.testException.i, this.testException.s);

    longer("1", 0, null, "");
    longer("1", 0, TestException.class);
    longer("1", 0, TestException.class, this.testException.i, this.testException.s);

    exceptException(TestException.class,
        () -> longer("", 1, TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());

    exceptException(IllegalArgumentException.class, () -> longer(null, -1, null, ""));
    exceptException(IllegalArgumentException.class, () -> longer(null, -1, TestException.class));
    exceptException(IllegalArgumentException.class,
        () -> longer(null, -1, TestException.class, this.testException.i, this.testException.s));

    exceptException(IllegalArgumentException.class, () -> longer("", -1, null, ""));
    exceptException(IllegalArgumentException.class, () -> longer("", -1, TestException.class));
    exceptException(IllegalArgumentException.class,
        () -> longer("", -1, TestException.class, this.testException.i, this.testException.s));

    exceptException(IllegalArgumentException.class, () -> longer(null, 0, null, ""));
    exceptException(IllegalArgumentException.class, () -> longer(null, 0, TestException.class));
    exceptException(IllegalArgumentException.class,
        () -> longer(null, 0, TestException.class, this.testException.i, this.testException.s));

    exceptException(IllegalArgumentException.class, () -> longer("", 1, null, ""));
    exceptException(IllegalArgumentException.class, () -> longer("", 1, TestException.class));
  }

  @Test
  public void testLongerWithPitcher() throws Exception {
    longer("", 0, null);
    longer("", 0, this.testPitcher);
    longer("1", 0, null);
    longer("1", 0, this.testPitcher);

    exceptException(TestException.class, () -> longer("", 1, this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());

    exceptException(IllegalArgumentException.class, () -> longer(null, 0, null));
    exceptException(IllegalArgumentException.class, () -> longer(null, 0, this.testPitcher));
    exceptException(IllegalArgumentException.class, () -> longer(null, -1, null));
    exceptException(IllegalArgumentException.class, () -> longer(null, -1, this.testPitcher));
    exceptException(IllegalArgumentException.class, () -> longer("", -1, null));
    exceptException(IllegalArgumentException.class, () -> longer("", -1, this.testPitcher));
  }

  @Test
  public void testMatchesWithException() throws Exception {
    matches("", "", null, "");
    matches("", "", TestException.class);
    matches("", "", TestException.class, this.testException.i, this.testException.s);

    matches("1", ".*", null, "");
    matches("1", ".*", TestException.class);
    matches("1", ".*", TestException.class, this.testException.i, this.testException.s);

    exceptException(TestException.class,
        () -> matches("", ".+", TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class,
        () -> matches("1", "\\D+", TestException.class, this.testException.i, this.testException.s),
        e -> this.testException.equals(e) ? null : e.toString());

    exceptException(IllegalArgumentException.class, () -> matches(null, null, null, ""));
    exceptException(IllegalArgumentException.class, () -> matches(null, null, TestException.class));
    exceptException(IllegalArgumentException.class,
        () -> matches(null, null, TestException.class, this.testException.i, this.testException.s));

    exceptException(IllegalArgumentException.class, () -> matches(null, "{[", null, ""));
    exceptException(IllegalArgumentException.class, () -> matches(null, "{[", TestException.class));
    exceptException(IllegalArgumentException.class,
        () -> matches(null, "{[", TestException.class, this.testException.i, this.testException.s));

    exceptException(IllegalArgumentException.class, () -> matches("", null, null, ""));
    exceptException(IllegalArgumentException.class, () -> matches("", null, TestException.class));
    exceptException(IllegalArgumentException.class,
        () -> matches("", null, TestException.class, this.testException.i, this.testException.s));

    exceptException(IllegalArgumentException.class, () -> matches("1", null, null, ""));
    exceptException(IllegalArgumentException.class, () -> matches("1", null, TestException.class));
    exceptException(IllegalArgumentException.class,
        () -> matches("1", null, TestException.class, this.testException.i, this.testException.s));

    exceptException(IllegalArgumentException.class, () -> matches("1", "{[", null, ""));
    exceptException(IllegalArgumentException.class, () -> matches("1", "{[", TestException.class));
    exceptException(IllegalArgumentException.class,
        () -> matches("1", "{[", TestException.class, this.testException.i, this.testException.s));
  }

  @Test
  public void testMatchesWithPitcher() throws Exception {
    matches("", "", null);
    matches("", "", this.testPitcher);
    matches("1", "\\d+", null);
    matches("1", "\\d+", this.testPitcher);

    exceptException(TestException.class, () -> matches("", ".+", this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());
    exceptException(TestException.class, () -> matches("1", "\\D*", this.testPitcher),
        e -> this.testException.equals(e) ? null : e.toString());

    exceptException(IllegalArgumentException.class, () -> matches(null, null, null));
    exceptException(IllegalArgumentException.class, () -> matches(null, ".*", null));
    exceptException(IllegalArgumentException.class, () -> matches(null, "{[", null));
    exceptException(IllegalArgumentException.class, () -> matches(null, null, this.testPitcher));
    exceptException(IllegalArgumentException.class, () -> matches(null, ".*", this.testPitcher));
    exceptException(IllegalArgumentException.class, () -> matches(null, "{[", this.testPitcher));

    exceptException(IllegalArgumentException.class, () -> matches("1", null, null));
    exceptException(IllegalArgumentException.class, () -> matches("1", "{[", null));
    exceptException(IllegalArgumentException.class, () -> matches("1", null, this.testPitcher));
    exceptException(IllegalArgumentException.class, () -> matches("1", "{[", this.testPitcher));
  }

  @Test
  public void testBeforeWithException() throws Exception {
    // Given
    final Instant now = Instant.now();
    final Instant before = now.minusMillis(1L);

    // When & Then
    before(before, now, null);
    before(before, now, TestException.class);

    exceptException(TestException.class,
        () -> before(now, before, TestException.class, this.testException.i, this.testException.s), this.testValidator);
    exceptException(TestException.class,
        () -> before(now, now, TestException.class, this.testException.i, this.testException.s), this.testValidator);

    exceptException(IllegalArgumentException.class, () -> before(null, null, null));
    exceptException(IllegalArgumentException.class, () -> before(null, now, null));
    exceptException(IllegalArgumentException.class, () -> before(now, null, null));

    exceptException(IllegalArgumentException.class, () -> before(null, null, TestException.class));
    exceptException(IllegalArgumentException.class, () -> before(null, now, TestException.class));
    exceptException(IllegalArgumentException.class, () -> before(now, null, TestException.class));
  }

  @Test
  public void testBeforeWithPitcher() throws Exception {
    // Given
    final Instant now = Instant.now();
    final Instant before = now.minusMillis(1L);

    // When & Then
    before(before, now, this.testPitcher);

    exceptException(TestException.class, () -> before(now, before, this.testPitcher), this.testValidator);
    exceptException(TestException.class, () -> before(now, now, this.testPitcher), this.testValidator);

    exceptException(IllegalArgumentException.class, () -> before(null, null, this.testPitcher));
    exceptException(IllegalArgumentException.class, () -> before(null, now, this.testPitcher));
    exceptException(IllegalArgumentException.class, () -> before(now, null, this.testPitcher));
  }

  @Test
  public void testAfterWithException() throws Exception {
    // Given
    final Instant now = Instant.now();
    final Instant after = now.plusMillis(1L);

    // When & Then
    after(after, now, null);
    after(after, now, TestException.class);

    exceptException(TestException.class,
        () -> after(now, after, TestException.class, this.testException.i, this.testException.s), this.testValidator);
    exceptException(TestException.class,
        () -> after(now, now, TestException.class, this.testException.i, this.testException.s), this.testValidator);
    exceptException(TestException.class,
        () -> after(after, after, TestException.class, this.testException.i, this.testException.s), this.testValidator);
    exceptException(TestException.class, () -> after(now, Instant.ofEpochMilli(now.toEpochMilli()), TestException.class,
        this.testException.i, this.testException.s), this.testValidator);
    exceptException(TestException.class, () -> after(after, Instant.ofEpochMilli(after.toEpochMilli()),
        TestException.class, this.testException.i, this.testException.s), this.testValidator);

    exceptException(IllegalArgumentException.class, () -> after(null, null, null));
    exceptException(IllegalArgumentException.class, () -> after(null, now, null));
    exceptException(IllegalArgumentException.class, () -> after(now, null, null));
    exceptException(IllegalArgumentException.class, () -> after(null, null, null, TestException.class));
    exceptException(IllegalArgumentException.class, () -> after(null, now, null, TestException.class));
    exceptException(IllegalArgumentException.class, () -> after(now, null, null, TestException.class));
  }

  @Test
  public void testAfterWithPitcher() throws Exception {
    // Given
    final Instant now = Instant.now();
    final Instant after = now.plusMillis(1L);

    // When & Then
    after(after, now, this.testPitcher);
    exceptException(TestException.class, () -> after(now, after, this.testPitcher), this.testValidator);
    exceptException(TestException.class, () -> after(now, now, this.testPitcher), this.testValidator);
    exceptException(TestException.class, () -> after(after, after, this.testPitcher), this.testValidator);
    exceptException(TestException.class, () -> after(now, Instant.ofEpochMilli(now.toEpochMilli()), this.testPitcher),
        this.testValidator);
    exceptException(TestException.class,
        () -> after(after, Instant.ofEpochMilli(after.toEpochMilli()), this.testPitcher), this.testValidator);

    exceptException(IllegalArgumentException.class, () -> after(null, null, this.testPitcher));
    exceptException(IllegalArgumentException.class, () -> after(null, now, this.testPitcher));
    exceptException(IllegalArgumentException.class, () -> after(now, null, this.testPitcher));
  }

  @Test
  public void testNotBeforeWithException() throws Exception {
    // Given
    final Instant now = Instant.now();
    final Instant after = now.plusMillis(1L);

    // When & Then
    notBefore(now, now, null);
    notBefore(now, now, TestException.class);
    notBefore(now, Instant.ofEpochMilli(now.toEpochMilli()), null);
    notBefore(now, Instant.ofEpochMilli(now.toEpochMilli()), TestException.class);

    notBefore(after, now, null);
    notBefore(after, now, TestException.class);
    notBefore(after, Instant.ofEpochMilli(now.toEpochMilli()), null);
    notBefore(after, Instant.ofEpochMilli(now.toEpochMilli()), TestException.class);

    exceptException(TestException.class,
        () -> notBefore(now, after, TestException.class, this.testException.i, this.testException.s),
        this.testValidator);

    exceptException(IllegalArgumentException.class,
        () -> notBefore(null, null, TestException.class, this.testException.i, this.testException.s));
    exceptException(IllegalArgumentException.class,
        () -> notBefore(null, now, TestException.class, this.testException.i, this.testException.s));
    exceptException(IllegalArgumentException.class,
        () -> notBefore(now, null, TestException.class, this.testException.i, this.testException.s));
  }

  @Test
  public void testNotBeforeWithPitcher() throws Exception {
    // Given
    final Instant now = Instant.now();
    final Instant after = now.plusMillis(1L);

    // When & Then
    notBefore(now, now, this.testPitcher);
    notBefore(after, now, this.testPitcher);
    notBefore(now, Instant.ofEpochMilli(now.toEpochMilli()), this.testPitcher);
    notBefore(after, Instant.ofEpochMilli(now.getEpochSecond()), this.testPitcher);

    exceptException(TestException.class, () -> notBefore(now, after, this.testPitcher), this.testValidator);
    exceptException(TestException.class,
        () -> notBefore(now, Instant.ofEpochMilli(after.toEpochMilli()), this.testPitcher), this.testValidator);

    exceptException(IllegalArgumentException.class, () -> notBefore(null, null, this.testPitcher));
    exceptException(IllegalArgumentException.class, () -> notBefore(null, now, this.testPitcher));
    exceptException(IllegalArgumentException.class, () -> notBefore(now, null, this.testPitcher));
  }

  @Test
  public void testNotAfterWithException() throws Exception {
    // Given
    final Instant now = Instant.now();
    final Instant before = now.minusMillis(1L);

    // When & Then
    notAfter(now, now, null);
    notAfter(now, Instant.ofEpochMilli(now.toEpochMilli()), null);
    notAfter(now, now, TestException.class);
    notAfter(now, Instant.ofEpochMilli(now.toEpochMilli()), TestException.class);
    notAfter(now, now, TestException.class, this.testException.i, this.testException.s);
    notAfter(now, Instant.ofEpochMilli(now.toEpochMilli()), TestException.class, this.testException.i,
        this.testException.s);

    notAfter(before, now, null);
    notAfter(before, Instant.ofEpochMilli(now.toEpochMilli()), null);
    notAfter(before, now, TestException.class);
    notAfter(before, Instant.ofEpochMilli(now.toEpochMilli()), TestException.class);
    notAfter(before, now, TestException.class, this.testException.i, this.testException.s);
    notAfter(before, Instant.ofEpochMilli(now.toEpochMilli()), TestException.class, this.testException.i,
        this.testException.s);

    exceptException(IllegalArgumentException.class, () -> notAfter(now, before, TestException.class));
    exceptException(IllegalArgumentException.class,
        () -> notAfter(now, Instant.ofEpochMilli(before.toEpochMilli()), TestException.class));
    exceptException(IllegalArgumentException.class, () -> notAfter(now, before, null));
    exceptException(IllegalArgumentException.class,
        () -> notAfter(now, Instant.ofEpochMilli(before.toEpochMilli()), null));
    exceptException(TestException.class,
        () -> notAfter(now, before, TestException.class, this.testException.i, this.testException.s),
        this.testValidator);
    exceptException(TestException.class, () -> notAfter(now, Instant.ofEpochMilli(before.toEpochMilli()),
        TestException.class, this.testException.i, this.testException.s), this.testValidator);

    exceptException(IllegalArgumentException.class, () -> notAfter(null, null, null));
    exceptException(IllegalArgumentException.class, () -> notAfter(null, now, null));
    exceptException(IllegalArgumentException.class, () -> notAfter(now, null, null));
    exceptException(IllegalArgumentException.class, () -> notAfter(null, null, TestException.class));
    exceptException(IllegalArgumentException.class, () -> notAfter(null, now, TestException.class));
    exceptException(IllegalArgumentException.class, () -> notAfter(now, null, TestException.class));
    exceptException(IllegalArgumentException.class,
        () -> notAfter(null, null, TestException.class, this.testException.i, this.testException.s));
    exceptException(IllegalArgumentException.class,
        () -> notAfter(null, now, TestException.class, this.testException.i, this.testException.s));
    exceptException(IllegalArgumentException.class,
        () -> notAfter(now, null, TestException.class, this.testException.i, this.testException.s));
  }

  @Test
  public void testNotAfterWithPitcher() throws Exception {
    // Given
    final Instant now = Instant.now();
    final Instant before = now.minusMillis(1L);

    // When & Then
    notAfter(now, now, this.testPitcher);
    notAfter(now, Instant.ofEpochMilli(now.toEpochMilli()), this.testPitcher);
    notAfter(before, now, this.testPitcher);
    notAfter(before, Instant.ofEpochMilli(now.toEpochMilli()), this.testPitcher);

    exceptException(TestException.class, () -> notAfter(now, before, this.testPitcher), this.testValidator);
    exceptException(TestException.class,
        () -> notAfter(now, Instant.ofEpochMilli(before.toEpochMilli()), this.testPitcher), this.testValidator);

    exceptException(IllegalArgumentException.class, () -> notAfter(null, null, this.testPitcher));
    exceptException(IllegalArgumentException.class, () -> notAfter(null, now, this.testPitcher));
    exceptException(IllegalArgumentException.class, () -> notAfter(now, null, this.testPitcher));
  }
}
