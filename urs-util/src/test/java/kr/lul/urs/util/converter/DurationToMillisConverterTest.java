package kr.lul.urs.util.converter;

import static kr.lul.urs.util.converter.DurationToMillisConverter.INSTANCE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.Duration;
import java.time.Instant;

import org.junit.Test;

import kr.lul.urs.util.AbstractTest;
import kr.lul.urs.util.config.UtilConstants;

public class DurationToMillisConverterTest extends AbstractTest {
  @Test
  public void testConvertWithNull() throws Exception {
    assertNull(INSTANCE.convert(null));
  }

  @Test
  public void testConvert() throws Exception {
    final Instant from = Instant.now();
    final Duration duration = Duration.between(from, from.plusMillis(UtilConstants.DAY));

    Long actual = INSTANCE.convert(duration);

    assertNotNull(actual);
    assertEquals(UtilConstants.DAY, actual.longValue());
  }
}
