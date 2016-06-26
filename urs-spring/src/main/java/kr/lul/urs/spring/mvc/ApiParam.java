/**
 *
 */
package kr.lul.urs.spring.mvc;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 리퀘스트의 파라미터 정보.
 *
 * @since 2016. 6. 26.
 * @author Just Burrow just.burrow@lul.kr
 */
public interface ApiParam<T> {
  public static enum Location {
    /**
     * @see PathVariable
     */
    PATH,
    /**
     * @see RequestParam
     */
    QUERY,
    /**
     * @see RequestHeader
     */
    header,
    /**
     * @see RequestBody
     */
    BODY
  }

  /**
   * 리퀘스트에서 파라미터가 나타나는 위치.
   *
   * @return
   * @since 2016. 6. 26.
   */
  public Location getLocation();

  /**
   * 필수 파라미터인지 여부.
   *
   * @return
   * @since 2016. 6. 26.
   */
  public boolean isOptional();

  /**
   * 기본값을 가지고 있는지 여부.
   *
   * @return
   * @since 2016. 6. 26.
   */
  public boolean hasDefault();

  /**
   * 바인딩 타입.
   *
   * @return
   * @since 2016. 6. 26.
   */
  public Class<T> getType();

  /**
   * 파라미터 이름.
   *
   * @return 파라미터 이름.
   * @since 2016. 6. 26.
   */
  public String getName();

  /**
   * 기본값. 기본값이 없는 경우 에러 발생.
   *
   * @return 기본값.
   * @throws UnsupportedOperationException
   *           기본값이 없는 경우.
   * @since 2016. 6. 26.
   */
  public T getDefault() throws UnsupportedOperationException;
}
