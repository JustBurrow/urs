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

  @Test
  public void testExceptionWithExactclass() throws Exception {
    exceptException(() -> {
      throw new Exception();
    } , Exception.class);
  }

  @Test(expected = AssertionError.class)
  public void testExceptionWithAnother() throws Exception {
    class E extends Exception {
      private static final long serialVersionUID = 5405630845382475296L;
    }
    exceptException(() -> {
      throw new E();
    } , IllegalStateException.class);
  }

  @Test(expected = AssertionError.class)
  public void testExceptionWithSubclass() throws Exception {
    exceptException(() -> {
      throw new IllegalArgumentException();
    } , Exception.class);
    fail();
  }

  @Test(expected = AssertionError.class)
  public void testExceptionWithSuperclass() throws Exception {
    exceptException(() -> {
      throw new IllegalArgumentException();
    } , RuntimeException.class);
    fail();
  }
}
