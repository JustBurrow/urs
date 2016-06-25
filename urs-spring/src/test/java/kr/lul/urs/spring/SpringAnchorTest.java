/**
 *
 */
package kr.lul.urs.spring;

import org.junit.Test;

import kr.lul.urs.util.AnchorUtils;

/**
 * @since 2016. 6. 25.
 * @author Just Burrow just.burrow@lul.kr
 */
public class SpringAnchorTest {
  @Test
  public void testAnchors() throws Exception {
    AnchorUtils.scanAndTest(SpringAnchorTest.class);
  }
}
