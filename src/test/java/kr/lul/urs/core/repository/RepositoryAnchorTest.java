/**
 *
 */
package kr.lul.urs.core.repository;

import org.junit.Test;

/**
 * @author Just Burrow just.burrow@lul.kr
 */
public class RepositoryAnchorTest {
  @Test(expected = UnsupportedOperationException.class)
  public void test() {
    new RepositoryAnchor() {
    };
  }
}
