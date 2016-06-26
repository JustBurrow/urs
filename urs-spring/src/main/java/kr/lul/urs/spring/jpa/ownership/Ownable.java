/**
 *
 */
package kr.lul.urs.spring.jpa.ownership;

/**
 * {@link Owner}가 소유할 수 있는 데이터.
 * 소유자만이 데이터 조작 권한을 가진다.
 *
 * @since 2016. 6. 26.
 * @author Just Burrow just.burrow@lul.kr
 */
public interface Ownable<O extends Owner> {
  public O getOwner();
}
