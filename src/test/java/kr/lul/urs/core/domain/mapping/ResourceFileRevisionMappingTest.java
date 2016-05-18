/**
 *
 */
package kr.lul.urs.core.domain.mapping;

import static org.junit.Assert.fail;

import org.junit.Test;

import kr.lul.urs.AbstractTest;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 18.
 */
public class ResourceFileRevisionMappingTest extends AbstractTest {
  @Test(expected = UnsupportedOperationException.class)
  public void testConstructor() throws Exception {
    new ResourceFileRevisionMapping() {
    };
    fail();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testEntityConstructor() throws Exception {
    new ResourceFileRevisionMapping.Entity() {
    };
    fail();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testTableConstructor() throws Exception {
    new ResourceFileRevisionMapping.Table() {
    };
    fail();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testFKConstructor() throws Exception {
    new ResourceFileRevisionMapping.Table.FK() {
    };
    fail();
  }
}
