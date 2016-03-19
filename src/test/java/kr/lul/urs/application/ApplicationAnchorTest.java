/**
 *
 */
package kr.lul.urs.application;

import org.junit.Test;

/**
 * @author Just Burrow just.burrow@lul.kr
 */
public class ApplicationAnchorTest {
  @Test(expected = UnsupportedOperationException.class)
  public void test() {
    new ApplicationAnchor() {
    };
  }
}
