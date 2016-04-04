/**
 *
 */
package kr.lul.urs.spring.jpa.text;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import kr.lul.urs.util.Randoms;
import kr.lul.urs.util.Strings;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 4.
 */
public class CrcTextTest {
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithNull() {
    new CrcText(null);

    fail();
  }

  @Test
  public void testConstructor() throws Exception {
    // Given
    final String text = Strings.random(Randoms.in(0, 100));

    // When
    final CrcText ct = new CrcText(text);

    // Then
    assertNotNull(text);
    assertEquals(text, ct.getText());
    assertEquals(text, ct.toString());
    assertEquals(text.hashCode(), ct.hashCode());
    assertEquals(ct, new CrcText(text));
    assertEquals(ct.getCrc(), new CrcText(text).getCrc());
  }
}
