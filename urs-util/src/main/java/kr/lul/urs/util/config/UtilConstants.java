package kr.lul.urs.util.config;

import java.io.Serializable;

/**
 * 유틸리티 패키지용 상수 모음.
 *
 * @author just.burrow@lul.kr
 */
public abstract class UtilConstants {
  /**
   * @see Serializable
   */
  public static final long SERIAL_VERSION_UID = -7935086651820740124L;

  /**
   * 초 단위의 1분의 길이.
   */
  public static final long SECONDS_FOR_MINUTE = 60L;
  /**
   * 분 단위의 1시간의 길이.
   */
  public static final long MINUTES_FOR_HOUR   = 60L;
  /**
   * 시간 단위의 하루의 길이.
   */
  public static final long HOURS_FOR_DAY      = 24L;
  /**
   * 일 단위의 1달의 길이.
   */
  public static final long DAYS_FOR_MONTH     = 30L;
  /**
   * 일 단위의 1년의 길이.
   */
  public static final long DAYS_FOR_YEAR      = 365L;
  /**
   * 개월 단위의 1년의 길이.
   */
  public static final long MONTHS_FOR_YEAR    = 12L;

  /**
   * 1초의 길이.
   */
  public static final long SECOND             = 1000L;
  /**
   * 1분의 길이.
   *
   * @see #SECONDS_FOR_MINUTE
   * @see #SECOND
   */
  public static final long MINUTE             = SECONDS_FOR_MINUTE * SECOND;
  /**
   * 1시간의 길이.
   *
   * @see #MINUTES_FOR_HOUR
   * @see #MINUTE
   */
  public static final long HOUR               = MINUTES_FOR_HOUR * MINUTE;
  /**
   * 하루의 길이.
   *
   * @see #HOURS_FOR_DAY
   * @see #HOUR
   */
  public static final long DAY                = HOURS_FOR_DAY * HOUR;
  /**
   * 1달의 길이.
   *
   * @see #DAYS_FOR_MONTH
   * @see #DAY
   */
  public static final long MONTH              = DAYS_FOR_MONTH * DAY;
  /**
   * 1년의 길이.
   *
   * @see #MONTHS_FOR_YEAR
   * @see #MONTH
   */
  public static final long YEAR               = MONTHS_FOR_YEAR * MONTH;

  protected UtilConstants() {
    throw new UnsupportedOperationException();
  }
}
