/**
 *
 */
package kr.lul.urs.spring.jpa.timestamp;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;

import kr.lul.urs.spring.AbstractSpringTest;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 16.
 */
public class UpdatableMappingTest extends AbstractSpringTest {
  @Test
  public void testConstructor() {
    assertThatThrownBy(() -> new UpdatableMapping() {
    }).isInstanceOf(UnsupportedOperationException.class);
  }

  @Test
  public void testUpdatableEntityConstructor() throws Exception {
    assertThatThrownBy(() -> new UpdatableMapping.UpdatableEntity() {
    }).isInstanceOf(UnsupportedOperationException.class);
  }

  @Test
  public void testUpdatableTableConstructor() throws Exception {
    assertThatThrownBy(() -> new UpdatableMapping.UpdatableTable() {
    }).isInstanceOf(UnsupportedOperationException.class);
  }
}
