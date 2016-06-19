package kr.lul.urs.util.converter;

import static kr.lul.urs.util.config.UtilConstants.DAY;
import static kr.lul.urs.util.config.UtilConstants.MONTH;
import static kr.lul.urs.util.config.UtilConstants.YEAR;

import java.time.Period;

import kr.lul.urs.util.config.UtilConstants;

/**
 * {@link Period}를 {@link Long}으로 변환하는 변환기.
 *
 * @author just.burrow@lul.kr
 */
public interface PeriodToLongConverter extends Converter<Period, Long> {
  public static final PeriodToLongConverter INSTANCE = new PeriodToLongConverter() {
  };

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Converter<Period, Long>
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * {@link Period}를 {@link Long}으로 인코딩한다.
   *
   * <p>
   * {@link Long}으로 표현하지만, 결과값은 milliseconds 단위가 아니다. 사전 정의한 각 단위를 특정한 값으로 인코딩한 결과일 뿐이다.
   * </p>
   *
   * @see kr.lul.urs.util.converter.Converter#convert(java.lang.Object)
   * @see UtilConstants
   */
  @Override
  default Long convert(Period period) {
    if (null == period) {
      return null;
    } else {
      return YEAR * period.getYears() + MONTH * period.getMonths() + DAY * period.getDays();
    }
  }
}
