package kr.lul.urs.util.converter;

import static kr.lul.urs.util.converter.UtcMillisToInstantConverter.INSTANCE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.Instant;

import org.junit.Test;

import kr.lul.urs.util.AbstractTest;

public class UtcMillisToInstantConverterTest extends AbstractTest {
  @Test
  public void testConvertWithNull() throws Exception {
    assertNull(INSTANCE.convert(null));
  }

  @Test
  public void testConvert() throws Exception {
    Instant expected = Instant.now();

    Instant actual = INSTANCE.convert(expected.toEpochMilli());

    assertNotNull(actual);
    assertEquals(expected, actual);
  }
}
