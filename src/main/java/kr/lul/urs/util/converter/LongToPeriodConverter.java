package kr.lul.urs.util.converter;

import static kr.lul.urs.util.config.UtilConstants.DAY;
import static kr.lul.urs.util.config.UtilConstants.MONTH;
import static kr.lul.urs.util.config.UtilConstants.YEAR;

import java.time.Period;

import kr.lul.urs.util.config.UtilConstants;

/**
 * {@link Long}으로 인코딩한 값을 {@link Period}로 디코딩한다.
 *
 * @author just.burrow@lul.kr
 */
public interface LongToPeriodConverter extends Converter<Long, Period> {
  /**
   * 기본 구현체 인스턴스.
   */
  public static final LongToPeriodConverter INSTANCE = new LongToPeriodConverter() {
  };

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Converter<Long, Period>
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * {@link Long}을 {@link Period}로 디코딩한다.
   *
   * <p>
   * {@link Long}을 입력받지만 milliseconds 단위가 아니다. 사전 정의한 각 단위를 특정한 값으로 인코딩한 {@link Long}을 사용한다.
   * </p>
   *
   * @see kr.lul.urs.util.converter.Converter#convert(java.lang.Object)
   * @see UtilConstants
   * @see PeriodToLongConverter#convert(Period)
   */
  @Override
  default Period convert(Long encoded) {
    if (null == encoded) {
      return null;
    }
    return Period.of((int) (encoded / YEAR), (int) (encoded % YEAR / MONTH), (int) (encoded % MONTH / DAY));
  }
}
