package kr.lul.urs.util.converter;

import static kr.lul.urs.util.converter.MillisToDurationConverter.INSTANCE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.Duration;

import org.junit.Test;

import kr.lul.urs.util.AbstractTest;
import kr.lul.urs.util.config.UtilConstants;

public class MillisToDurationConverterTest extends AbstractTest {
  @Test
  public void testConvertWithNull() throws Exception {
    assertNull(INSTANCE.convert(null));
  }

  @Test
  public void testConvertWithZero() throws Exception {
    Duration actual = INSTANCE.convert(0L);

    assertNotNull(actual);
    assertEquals(0L, actual.toMillis());
  }

  @Test
  public void testConvert() throws Exception {
    Duration actual = INSTANCE.convert(UtilConstants.DAY);

    assertNotNull(actual);
    assertEquals(UtilConstants.DAY, actual.toMillis());
  }
}
