/**
 *
 */
package kr.lul.urs.spring.jpa.time;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;

import kr.lul.urs.spring.AbstractSpringTest;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 21.
 */
public class JpaTimeSupportAnchorTest extends AbstractSpringTest {
  @Test
  public void testConstructor() {
    assertThatThrownBy(() -> new JpaTimeSupportAnchor() {
    }).isInstanceOf(UnsupportedOperationException.class);
  }
}
