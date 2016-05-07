/**
 *
 */
package kr.lul.urs.spring.tx;

import java.util.List;

/**
 * 도메인 엔티티의 인스턴스를 DTO 인스턴스로 변경하는
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 6.
 * @param <D>
 *          도메인 엔티티 타입.
 * @param <T>
 *          DTO 타입.
 */
public interface ReturnFactory<D, T> {
  /**
   * 하나의 도메인 엔티티 인스턴스를 DTO로 변환하는 변환기를 만든다.
   *
   * @param domain
   *          원본 도메인 엔티티 인스턴스. not null.
   * @return 도메인 엔티티를 DTO로 변환하는 변환기.
   * @since 2016. 5. 6.
   */
  public Return<T> converter(D domain);

  /**
   * 도메인 엔티티 리스트를 DTO 리스트로 변환하는 변환기를 만든다.
   * 반환하는 DTO 리스트는 원본 리스트의 순서를 보존한다.
   *
   * @param list
   *          원본 도메인 엔티티 인스턴스의 리스트. not null.
   * @return 도메인 엔티티 리스트를 DTO 리스트로 변환하는 변환기.
   * @since 2016. 5. 6.
   */
  public Return<List<T>> converter(List<D> list);
}
