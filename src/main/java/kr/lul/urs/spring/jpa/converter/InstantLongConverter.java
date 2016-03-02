package kr.lul.urs.spring.jpa.converter;

import java.time.Instant;

import javax.persistence.AttributeConverter;

/**
 * Java8의 시각 정보의 표준인 {@link Instant}을 UTC millisecond와 상호 변환하는 기능을 제공한다.
 *
 * @author just.burrow@lul.kr
 */
public interface InstantLongConverter extends AttributeConverter<Instant, Long> {
}
