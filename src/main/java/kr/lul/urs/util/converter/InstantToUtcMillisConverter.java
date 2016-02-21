package kr.lul.urs.util.converter;

import java.time.Instant;

/**
 * {@link Instant}를 UTC 밀리세컨드의 {@link Long}으로 변환하는 변환기.
 *
 * @author just.burrow@lul.kr
 */
public interface InstantToUtcMillisConverter extends Converter<Instant, Long> {
  /**
   * 기본 구현체 인스턴스.
   */
  public static final InstantToUtcMillisConverter INSTANCE = new InstantToUtcMillisConverter() {
  };

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Converter<Instant, Long>
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  default Long convert(Instant instant) {
    return null == instant ? null : instant.toEpochMilli();
  }
}
