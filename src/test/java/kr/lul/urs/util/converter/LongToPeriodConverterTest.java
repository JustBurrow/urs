package kr.lul.urs.util.converter;

import static kr.lul.urs.util.config.UtilConstants.DAY;
import static kr.lul.urs.util.config.UtilConstants.MONTH;
import static kr.lul.urs.util.config.UtilConstants.MONTHS_FOR_YEAR;
import static kr.lul.urs.util.config.UtilConstants.YEAR;
import static kr.lul.urs.util.converter.LongToPeriodConverter.INSTANCE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.Period;

import org.junit.Test;

import kr.lul.urs.AbstractTest;

public class LongToPeriodConverterTest extends AbstractTest {
  @Test
  public void testConvertWithNull() throws Exception {
    assertNull(INSTANCE.convert(null));
  }

  @Test
  public void testConvertWithDay() throws Exception {
    Period period = INSTANCE.convert(DAY);

    assertNotNull(period);
    assertEquals(0, period.getYears());
    assertEquals(0, period.getMonths());
    assertEquals(1, period.getDays());
  }

  @Test
  public void testConvertWithMonth() throws Exception {
    Period period = INSTANCE.convert(MONTH);

    assertNotNull(period);
    assertEquals(0, period.getYears());
    assertEquals(1, period.getMonths());
    assertEquals(0, period.getDays());
  }

  @Test
  public void testConvertWithYear() throws Exception {
    Period period = INSTANCE.convert(YEAR);

    assertNotNull(period);
    assertEquals(1, period.getYears());
    assertEquals(0, period.getMonths());
    assertEquals(0, period.getDays());
  }

  @Test
  public void testConvertWithYearMonthDay() throws Exception {
    Period period = INSTANCE.convert(YEAR + MONTH + DAY);

    assertNotNull(period);
    assertEquals(1, period.getYears());
    assertEquals(1, period.getMonths());
    assertEquals(1, period.getDays());
  }

  @Test
  public void testConvertWithYearMinusMonth() throws Exception {
    Period period = INSTANCE.convert(YEAR - MONTH);

    assertNotNull(period);
    assertEquals(0, period.getYears());
    assertEquals(MONTHS_FOR_YEAR - 1L, period.getMonths());
    assertEquals(0, period.getDays());
  }
}
