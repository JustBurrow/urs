package kr.lul.urs.spring.jpa;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;

import kr.lul.urs.spring.AbstractSpringTest;

public class SpringJpaAnchorTest extends AbstractSpringTest {
  @Test
  public void testConstructor() {
    assertThatThrownBy(() -> new SpringJpaAnchor() {
    }).isInstanceOf(UnsupportedOperationException.class);
  }
}
