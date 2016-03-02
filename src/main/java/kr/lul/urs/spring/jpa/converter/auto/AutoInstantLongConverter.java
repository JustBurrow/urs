package kr.lul.urs.spring.jpa.converter.auto;

import java.time.Instant;

import javax.persistence.Converter;

import kr.lul.urs.spring.jpa.converter.InstantLongConverter;
import kr.lul.urs.util.converter.InstantToUtcMillisConverter;
import kr.lul.urs.util.converter.UtcMillisToInstantConverter;

@Converter(autoApply = true)
public class AutoInstantLongConverter implements InstantLongConverter {
  @Override
  public Long convertToDatabaseColumn(Instant attribute) {
    return InstantToUtcMillisConverter.INSTANCE.convert(attribute);
  }

  @Override
  public Instant convertToEntityAttribute(Long dbData) {
    return UtcMillisToInstantConverter.INSTANCE.convert(dbData);
  }
}
