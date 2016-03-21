/**
 *
 */
package kr.lul.urs.spring.tx.util;

/**
 * 트랜잭션 관리하의 메서드의 반환값 래퍼.
 * 엔티티 관리자에서 생성하는 값을 가공해 반환해야 할 경우, 변환 로직을 트랜잭션 관리자의 실행 이후로 미룬다.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 21.
 */
public interface Return<T> {
  /**
   * @return
   */
  public T value();
}
