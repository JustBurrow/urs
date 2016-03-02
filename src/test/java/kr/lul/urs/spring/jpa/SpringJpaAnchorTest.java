package kr.lul.urs.spring.jpa;

import static org.junit.Assert.fail;

import org.junit.Test;

public class SpringJpaAnchorTest {
  @Test(expected = UnsupportedOperationException.class)
  public void testConstructor() {
    new SpringJpaAnchor() {
    };
    fail();
  }
}
