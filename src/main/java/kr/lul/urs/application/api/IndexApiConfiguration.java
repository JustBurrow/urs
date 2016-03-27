/**
 *
 */
package kr.lul.urs.application.api;

/**
 * 인덱스 API 설정.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 28.
 */
public abstract class IndexApiConfiguration {
  /**
   * API 접두어. API 정보에서 생략되어있다.
   */
  public static final String PREFIX = "";

  /**
   * 도메인만으로 요청한 경우의 API.
   * API는 <code>'/'</code>로 시작하지만, 도메인으로 요청한 경우는 예외로 간주한다.
   *
   * @see #PREFIX
   */
  public static final String DOMAIN = "";
  /**
   * 인덱스 API.
   *
   * @see #PREFIX
   */
  public static final String INDEX  = "/";

  protected IndexApiConfiguration() {
    throw new UnsupportedOperationException();
  }
}
