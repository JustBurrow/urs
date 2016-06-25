/**
 *
 */
package kr.lul.urs;

import static kr.lul.urs.util.AnchorUtils.scanAndTest;

import org.junit.Test;

/**
 * @since 2016. 6. 25.
 * @author Just Burrow just.burrow@lul.kr
 */
public class ApplicationAnchorTest {
  @Test
  public void testAnchors() throws Exception {
    scanAndTest(ApplicationAnchorTest.class);
  }
}
