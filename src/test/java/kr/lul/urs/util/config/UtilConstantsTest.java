package kr.lul.urs.util.config;

import static org.junit.Assert.fail;

import org.junit.Test;

import kr.lul.urs.util.config.UtilConstants;

public class UtilConstantsTest {
  @Test(expected = UnsupportedOperationException.class)
  public void test() {
    new UtilConstants() {
    };
    fail();
  }
}
