package kr.lul.urs.util.converter;

/**
 * 한 인스턴스를 다른 타입의 인스턴스로 변환한다.
 *
 * @author just.burrow@lul.kr
 *
 * @param <S>
 *          원본 인스턴스 타입.
 * @param <T>
 *          결과 인스턴스 타입.
 */
public interface Converter<S, T> {
  /**
   * 인스턴스의 타입을 변환한다.
   *
   * @param source
   *          원본 인스턴스.
   * @return 변환한 인스턴스.
   */
  public T convert(S source);
}
