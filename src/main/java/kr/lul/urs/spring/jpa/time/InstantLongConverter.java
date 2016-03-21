package kr.lul.urs.spring.jpa.time;

import java.time.Instant;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import kr.lul.urs.util.converter.InstantToUtcMillisConverter;
import kr.lul.urs.util.converter.UtcMillisToInstantConverter;

/**
 * Java8의 시각 정보의 표준인 {@link Instant}을 UTC millisecond와 상호 변환하는 기능을 제공한다.
 *
 * @author just.burrow@lul.kr
 */
@Converter(autoApply = true)
public class InstantLongConverter implements AttributeConverter<Instant, Long> {
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>AttributeConverter<Instant, Long>
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public Long convertToDatabaseColumn(Instant attribute) {
    return InstantToUtcMillisConverter.INSTANCE.convert(attribute);
  }

  @Override
  public Instant convertToEntityAttribute(Long dbData) {
    return UtcMillisToInstantConverter.INSTANCE.convert(dbData);
  }
}
