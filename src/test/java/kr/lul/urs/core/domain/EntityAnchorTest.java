/**
 *
 */
package kr.lul.urs.core.domain;

import org.junit.Test;

import kr.lul.urs.core.domain.entity.EntityAnchor;

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
