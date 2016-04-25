/**
 *
 */
package kr.lul.urs.util;

/**
 * 기본 {@link Object#toString()}와 별도로, 인스턴스의 요약 설명을 제공하는 인터페이스.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 21.
 */
public interface ToSimpleString {
  /**
   * {@link Object#toString()}의 요약본.
   *
   * @return 인스턴스의 짧은 설명.
   */
  public String toSimpleString();
}
