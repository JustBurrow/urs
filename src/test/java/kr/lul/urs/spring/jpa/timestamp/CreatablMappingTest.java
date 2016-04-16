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
public class CreatablMappingTest {
  @Test(expected = UnsupportedOperationException.class)
  public void testConstructor() {
    new CreatablMapping() {
    };
    fail();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testCreatableEntityConstructor() throws Exception {
    new CreatablMapping.CreatableEntity() {
    };
    fail();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testCreatableTableConstructor() throws Exception {
    new CreatablMapping.CreatableTable() {
    };
    fail();
  }
}
