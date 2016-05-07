/**
 *
 */
package kr.lul.urs.spring.jpa.text;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;

import kr.lul.urs.util.Randoms;
import kr.lul.urs.util.Strings;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 4.
 */
public class CrcTextTest {
  @Test
  public void testConstructorWithNull() {
    assertThatThrownBy(() -> new CrcText(null)).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void testConstructor() throws Exception {
    // Given
    final String text = Strings.random(Randoms.in(0, 100));

    // When
    final CrcText ct = new CrcText(text);

    // Then
    assertThat(ct).isNotNull();
    assertThat(text).isEqualTo(ct.getText()).isEqualTo(ct.toString());
    assertThat(ct.toString()).isEqualTo(text);
    assertThat(new CrcText(text)).isEqualTo(ct).isNotSameAs(ct);
    assertThat(new CrcText(text).getCrc()).isEqualTo(ct.getCrc());
  }
}
