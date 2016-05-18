/**
 *
 */
package kr.lul.urs.spring.jpa.timestamp;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;

import kr.lul.urs.AbstractTest;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 16.
 */
public class UpdatableMappingTest extends AbstractTest {
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
