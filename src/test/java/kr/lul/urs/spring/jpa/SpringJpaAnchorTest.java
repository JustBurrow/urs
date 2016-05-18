package kr.lul.urs.spring.jpa;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;

import kr.lul.urs.AbstractTest;

public class SpringJpaAnchorTest extends AbstractTest {
  @Test
  public void testConstructor() {
    assertThatThrownBy(() -> new SpringJpaAnchor() {
    }).isInstanceOf(UnsupportedOperationException.class);
  }
}
