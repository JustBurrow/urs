/**
 *
 */
package kr.lul.urs.core.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;

import kr.lul.urs.AbstractTest;
import kr.lul.urs.core.domain.entity.EntityAnchor;

/**
 * @author Just Burrow just.burrow@lul.kr
 */
public class EntityAnchorTest extends AbstractTest {
  @Test
  public void test() {
    assertThatThrownBy(() -> new EntityAnchor() {
    }).isInstanceOf(UnsupportedOperationException.class);
  }
}
