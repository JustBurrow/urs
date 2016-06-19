package kr.lul.urs.util.config;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;

public class UtilConstantsTest {
  @Test
  public void test() {
    assertThatThrownBy(() -> new UtilConstants() {
    }).isInstanceOf(UnsupportedOperationException.class);
  }
}
