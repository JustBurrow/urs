package kr.lul.urs.util.converter;

import java.time.Duration;

/**
 * {@link Duration}을 밀리세컨드로 변환하는 변환기.
 *
 * @author just.burrow@lul.kr
 */
public interface DurationToMillisConverter extends Converter<Duration, Long> {
  /**
   * 기본 구현체 인스턴스.
   */
  public static final DurationToMillisConverter INSTANCE = new DurationToMillisConverter() {
  };

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Converter<Duration, Long>
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  default Long convert(Duration duration) {
    return null == duration ? null : duration.toMillis();
  }
}
