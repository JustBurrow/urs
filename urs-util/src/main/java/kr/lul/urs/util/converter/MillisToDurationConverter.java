package kr.lul.urs.util.converter;

import java.time.Duration;

/**
 * 밀리세컨드를 {@link Duration}으로 변환하는 변환기.
 *
 * @author just.burrow@lul.kr
 */
public interface MillisToDurationConverter extends Converter<Long, Duration> {
  /**
   * 기본 구현체 인스턴스.
   */
  public static final MillisToDurationConverter INSTANCE = new MillisToDurationConverter() {
  };

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Converter<Long, Duration>
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  default Duration convert(Long millis) {
    return null == millis ? null : Duration.ofMillis(millis);
  }
}
