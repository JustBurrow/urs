package kr.lul.urs.util.converter;

import static kr.lul.urs.util.converter.PeriodToLongConverter.INSTANCE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.Period;

import org.junit.Test;

import kr.lul.urs.util.AbstractTest;
import kr.lul.urs.util.config.UtilConstants;

public class PeriodToLongConverterTest extends AbstractTest {
  @Test
  public void testConvertWithNull() throws Exception {
    assertNull(INSTANCE.convert(null));
  }

  @Test
  public void testConvertWithDay() {
    Period period = Period.ofDays(1);

    Long actual = INSTANCE.convert(period);

    assertNotNull(actual);
    assertEquals(UtilConstants.DAY, actual.longValue());
  }

  @Test
  public void testConvertWithMonth() throws Exception {
    Period period = Period.ofMonths(1);

    Long actual = INSTANCE.convert(period);

    assertNotNull(actual);
    assertEquals(UtilConstants.MONTH, actual.longValue());
  }

  @Test
  public void testConvertWithYear() throws Exception {
    Period period = Period.ofYears(1);

    Long actual = INSTANCE.convert(period);

    assertNotNull(actual);
    assertEquals(UtilConstants.YEAR, actual.longValue());
  }

  @Test
  public void testConverWithYearMonthDay() throws Exception {
    Period period = Period.of(1, 1, 1);

    Long actual = INSTANCE.convert(period);

    assertNotNull(actual);
    assertEquals(UtilConstants.YEAR + UtilConstants.MONTH + UtilConstants.DAY, actual.longValue());
  }

  @Test
  public void testConvertForYearMinusMonth() throws Exception {
    Period period = Period.of(1, -1, 0);

    Long actual = INSTANCE.convert(period);

    assertNotNull(actual);
    assertEquals(11L * UtilConstants.MONTH, actual.longValue());
  }
}
