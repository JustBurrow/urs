package kr.lul.urs.util;

import static kr.lul.urs.util.Tests.exceptException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import kr.lul.urs.AbstractTest;

public class TestsTest extends AbstractTest {
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
  public void testExpectExceptionWithExceptionAndNullAndNull() throws Exception {
    exceptException(Exception.class, null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExpectExceptionWithExceptionAndTestAndNull() throws Exception {
    exceptException(Exception.class, () -> {
      throw new Exception();
    }, null);
  }

  @Test(expected = AssertionError.class)
  public void testExpectException() throws Exception {
    final String message = new Object().toString();
    exceptException(UnsupportedOperationException.class, () -> {
      throw new UnsupportedOperationException(message);
    }, e -> new Object().toString());
  }

  @Test
  public void testExcepectExceptionWithMessage() throws Exception {
    class A extends Exception {
      private static final long serialVersionUID = -8802613277395273268L;
    }
    class B extends Exception {
      private static final long serialVersionUID = -8562381616964890213L;
    }

    try {
      exceptException(null, A.class, () -> {
        throw new B();
      });
    } catch (AssertionError e) {
      assertNotNull(e.getMessage());
    }

    final String msg = RandomStringUtils.randomAlphanumeric(10);
    try {
      exceptException(msg, A.class, () -> {
        throw new B();
      });
    } catch (AssertionError e) {
      assertEquals(msg, e.getMessage());
    }
  }
}
