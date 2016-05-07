/**
 *
 */
package kr.lul.urs.application;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;

/**
 * @author Just Burrow just.burrow@lul.kr
 */
public class ApplicationAnchorTest {
  @Test
  public void test() {
    assertThatThrownBy(() -> new ApplicationAnchor() {
    }).isInstanceOf(UnsupportedOperationException.class);
  }
}
