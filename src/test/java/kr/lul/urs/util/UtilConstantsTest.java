package kr.lul.urs.util;

import static org.junit.Assert.fail;

import org.junit.Test;

public class UtilConstantsTest {
  @Test(expected = UnsupportedOperationException.class)
  public void test() {
    new UtilConstants() {
    };
    fail();
  }
}
