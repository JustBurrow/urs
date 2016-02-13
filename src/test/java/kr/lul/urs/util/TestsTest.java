package kr.lul.urs.util;

import static kr.lul.urs.util.Tests.exceptException;
import static org.junit.Assert.fail;

import org.junit.Test;

public class TestsTest {

  @Test(expected = UnsupportedOperationException.class)
  public void test() {
    new Tests() {
    };
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExpectExceptionWithNullAndNull() throws Exception {
    exceptException(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExpectExceptionWithTestAndNull() throws Exception {
    exceptException(null, () -> {
    });
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExpectExceptionWithNUllAndException() throws Exception {
    exceptException(Exception.class, null);
  }

  @Test
  public void testExpectExceptionWithExactclass() throws Exception {
    exceptException(Exception.class, () -> {
      throw new Exception();
    });
  }

  @Test(expected = AssertionError.class)
  public void testExpectExceptionWithAnother() throws Exception {
    class E extends Exception {
      private static final long serialVersionUID = 5405630845382475296L;
    }
    exceptException(IllegalStateException.class, () -> {
      throw new E();
    });
  }

  @Test(expected = AssertionError.class)
  public void testExpectExceptionWithSubclass() throws Exception {
    exceptException(Exception.class, () -> {
      throw new IllegalArgumentException();
    });
    fail();
  }

  @Test(expected = AssertionError.class)
  public void testExceptExceptionWithSuperclass() throws Exception {
    exceptException(RuntimeException.class, () -> {
      throw new IllegalArgumentException();
    });
    fail();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExceptExceptionForCheckerWithNullAndNullAndNull() throws Exception {
    exceptException(null, null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExpectExceptionWithExceptionAndNullAndNull() throws Exception {
    exceptException(Exception.class, null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExpectExceptionWithExceptionAndTestAndNull() throws Exception {
    exceptException(Exception.class, () -> {
      throw new Exception();
    } , null);
  }

  @Test(expected = AssertionError.class)
  public void testExpectException() throws Exception {
    final String message = new Object().toString();
    exceptException(UnsupportedOperationException.class, () -> {
      throw new UnsupportedOperationException(message);
    } , e -> new Object().toString());
  }
}
