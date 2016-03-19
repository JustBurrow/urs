/**
 *
 */
package kr.lul.urs.core.domain.entity;

import org.junit.Test;

/**
 * @author Just Burrow just.burrow@lul.kr
 */
public class EntityAnchorTest {
  @Test(expected = UnsupportedOperationException.class)
  public void test() {
    new EntityAnchor() {
    };
  }
}
