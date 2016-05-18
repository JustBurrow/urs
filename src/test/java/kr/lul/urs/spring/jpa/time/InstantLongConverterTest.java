/**
 *
 */
package kr.lul.urs.spring.jpa.time;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;

import org.junit.Before;
import org.junit.Test;

import kr.lul.urs.AbstractTest;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 21.
 */
public class InstantLongConverterTest extends AbstractTest {
  private Instant              now;
  private long                 currentUtc;
  private InstantLongConverter converter;

  @Before
  public void setUp() throws Exception {
    this.currentUtc = System.currentTimeMillis();
    this.now = Instant.ofEpochMilli(this.currentUtc);
    this.converter = new InstantLongConverter();
  }

  @Test
  public void testConvertToDatabaseColumnWithNull() {
    assertThat(this.converter.convertToDatabaseColumn(null)).isNull();
  }

  @Test
  public void testConvertToDatabaseColumn() {
    // When
    Long actual = this.converter.convertToDatabaseColumn(this.now);

    // Then
    assertThat(actual).isNotNull().isEqualTo(this.currentUtc);
  }

  @Test
  public void testConvertToEntityAttributeWithNull() throws Exception {
    assertThat(this.converter.convertToEntityAttribute(null)).isNull();
  }

  @Test
  public void testConvertToEntityAttribute() throws Exception {
    // When
    Instant actual = this.converter.convertToEntityAttribute(this.currentUtc);

    // Then
    assertThat(actual).isNotNull().isEqualTo(this.now);
  }
}
