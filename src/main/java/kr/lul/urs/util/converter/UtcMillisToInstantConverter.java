package kr.lul.urs.util.converter;

import java.time.Instant;

/**
 * UTC 밀리세컨드를 {@link Instant}로 변환하는 변환기.
 *
 * @author just.burrow@lul.kr
 */
public interface UtcMillisToInstantConverter extends Converter<Long, Instant> {
  /**
   * 기본 구현체 인스턴스.
   */
  public static final UtcMillisToInstantConverter INSTANCE = new UtcMillisToInstantConverter() {
  };

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Converter<Long, Instant>
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  default Instant convert(Long utcMillis) {
    return null == utcMillis ? null : Instant.ofEpochMilli(utcMillis);
  }
}
