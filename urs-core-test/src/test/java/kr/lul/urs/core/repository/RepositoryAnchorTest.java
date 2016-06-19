/**
 *
 */
package kr.lul.urs.core.repository;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;

import kr.lul.urs.core.AbstractCoreTest;

/**
 * @author Just Burrow just.burrow@lul.kr
 */
public class RepositoryAnchorTest extends AbstractCoreTest {
  @Test
  public void test() {
    assertThatThrownBy(() -> new RepositoryAnchor() {
    }).isInstanceOf(UnsupportedOperationException.class);
  }
}
