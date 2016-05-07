package kr.lul.urs.spring.jpa;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;

public class SpringJpaAnchorTest {
  @Test
  public void testConstructor() {
    assertThatThrownBy(() -> new SpringJpaAnchor() {
    }).isInstanceOf(UnsupportedOperationException.class);
  }
}
