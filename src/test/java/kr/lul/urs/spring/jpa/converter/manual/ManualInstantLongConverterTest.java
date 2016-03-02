package kr.lul.urs.spring.jpa.converter.manual;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.Instant;

import org.junit.Before;
import org.junit.Test;

import kr.lul.urs.spring.jpa.converter.InstantLongConverter;

public class ManualInstantLongConverterTest {
  private Instant              now;
  private long                 currentUtc;
  private InstantLongConverter converter;

  @Before
  public void setUp() throws Exception {
    this.currentUtc = System.currentTimeMillis();
    this.now = Instant.ofEpochMilli(this.currentUtc);
    this.converter = new ManualInstantLongConverter();
  }

  @Test
  public void testConvertToDatabaseColumnWithNull() {
    assertNull(this.converter.convertToDatabaseColumn(null));
  }

  @Test
  public void testConvertToDatabaseColumn() {
    // When
    Long actual = this.converter.convertToDatabaseColumn(this.now);

    // Then
    assertNotNull(actual);
    assertEquals(this.currentUtc, (long) actual);
  }

  @Test
  public void testConvertToEntityAttributeWithNull() throws Exception {
    assertNull(this.converter.convertToEntityAttribute(null));
  }

  @Test
  public void testConvertToEntityAttribute() throws Exception {
    // When
    Instant actual = this.converter.convertToEntityAttribute(this.currentUtc);

    // Then
    assertNotNull(actual);
    assertEquals(this.now, actual);
  }
}
