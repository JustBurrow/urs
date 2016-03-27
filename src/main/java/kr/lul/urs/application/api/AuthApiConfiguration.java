/**
 *
 */
package kr.lul.urs.application.api;

/**
 * 인증 관련 API 설정 정보.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 28.
 */
public abstract class AuthApiConfiguration {
  /**
   * API 접두어. API 정보에서 생략되어있다.
   */
  public static final String PREFIX     = "/auth";

  /**
   * 인증 요청에 필요한 정보를 제공하는 API.
   */
  public static final String LOGIN_SPEC = "/login";

  protected AuthApiConfiguration() {
    throw new UnsupportedOperationException();
  }
}
