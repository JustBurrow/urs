/**
 *
 */
package kr.lul.urs.util;

import static kr.lul.urs.util.AnchorUtils.scan;
import static kr.lul.urs.util.AnchorUtils.scanAndTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;

/**
 * @since 2016. 6. 25.
 * @author Just Burrow just.burrow@lul.kr
 */
public class AnchorUtilsTest {
  public static class NotInitializedAnchor extends Anchor {
  }

  public static class InitializedAnchor extends Anchor {
    public static final String PACKAGE_NAME = InitializedAnchor.class.getPackage().getName();
  }

  @Test
  public void testScanAndTestWithClass() throws Exception {
    assertThatThrownBy(() -> scanAndTest(AnchorUtilsTest.class))
        .isInstanceOf(AssertionException.class)
        .hasCauseInstanceOf(NoSuchFieldException.class);
  }

  @Test
  public void testScanAndTestWithPackageName() throws Exception {
    assertThatThrownBy(() -> scanAndTest(AnchorUtilsTest.class.getPackage().getName()))
        .isInstanceOf(AssertionException.class)
        .hasCauseInstanceOf(NoSuchFieldException.class);
  }

  @SuppressWarnings("unchecked")
  @Test
  public void testScanWithPackage() throws Exception {
    assertThat(scan(AnchorUtilsTest.class))
        .containsOnly(NotInitializedAnchor.class, InitializedAnchor.class);
  }

  @SuppressWarnings("unchecked")
  @Test
  public void testScanWithParentPackage() throws Exception {
    String p1 = AnchorUtilsTest.class.getPackage().getName();

    assertThat(scan(p1.substring(0, p1.lastIndexOf('.'))))
        .containsOnly(NotInitializedAnchor.class, InitializedAnchor.class);
  }
}
