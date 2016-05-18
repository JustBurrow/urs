/**
 *
 */
package kr.lul.urs.core.repository;

import org.junit.Test;

import kr.lul.urs.AbstractTest;

/**
 * @author Just Burrow just.burrow@lul.kr
 */
public class RepositoryAnchorTest extends AbstractTest {
  @Test(expected = UnsupportedOperationException.class)
  public void test() {
    new RepositoryAnchor() {
    };
  }
}
