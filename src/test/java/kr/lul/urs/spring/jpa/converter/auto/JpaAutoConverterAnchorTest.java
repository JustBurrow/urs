package kr.lul.urs.spring.jpa.converter.auto;

import static org.junit.Assert.fail;

import org.junit.Test;

public class JpaAutoConverterAnchorTest {
  @Test(expected = UnsupportedOperationException.class)
  public void test() {
    new JpaAutoConverterAnchor() {
    };
    fail();
  }
}
