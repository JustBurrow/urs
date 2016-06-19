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
public class CreatablMappingTest extends AbstractSpringTest {
  @Test
  public void testConstructor() {
    assertThatThrownBy(() -> new CreatablMapping() {
    }).isInstanceOf(UnsupportedOperationException.class);
  }

  @Test
  public void testCreatableEntityConstructor() throws Exception {
    assertThatThrownBy(() -> new CreatablMapping.CreatableEntity() {
    }).isInstanceOf(UnsupportedOperationException.class);
  }

  @Test
  public void testCreatableTableConstructor() throws Exception {
    assertThatThrownBy(() -> new CreatablMapping.CreatableTable() {
    }).isInstanceOf(UnsupportedOperationException.class);
  }
}
