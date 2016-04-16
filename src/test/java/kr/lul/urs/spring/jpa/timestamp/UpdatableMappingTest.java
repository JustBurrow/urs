/**
 *
 */
package kr.lul.urs.spring.jpa.timestamp;

import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 16.
 */
public class UpdatableMappingTest {
  @Test(expected = UnsupportedOperationException.class)
  public void testConstructor() {
    new UpdatableMapping() {
    };
    fail();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testUpdatableEntityConstructor() throws Exception {
    new UpdatableMapping.UpdatableEntity() {
    };
    fail();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testUpdatableTableConstructor() throws Exception {
    new UpdatableMapping.UpdatableTable() {
    };
    fail();
  }
}
