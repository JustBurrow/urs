/**
 *
 */
package kr.lul.urs.spring.jpa.time;

import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 21.
 */
public class JpaTimeSupportAnchorTest {
  @Test(expected = UnsupportedOperationException.class)
  public void test() {
    new JpaTimeSupportAnchor() {
    };
    fail();
  }
}
