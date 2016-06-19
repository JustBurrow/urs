/**
 *
 */
package kr.lul.urs.application.api;

/**
 * 프로덕트 관리자 관련 API 설정 정보.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 27.
 */
public abstract class OperatorApiConfiguration {
  /**
   * API 접두어. API 정보에서 생략되어있다.
   */
  public static final String PREFIX       = "/operators";

  /**
   * 계정 등록에 필요한 정보의 사양을 제공하는 API.
   *
   * @see #PREFIX
   */
  public static final String SIGN_UP_SPEC = "/signup";
  /**
   * 계정 등록요청을 처리하는 API.
   *
   * @see #PREFIX
   */
  public static final String SIGN_UP      = "";

  protected OperatorApiConfiguration() {
    throw new UnsupportedOperationException();
  }
}
