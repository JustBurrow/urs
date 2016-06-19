/**
 *
 */
package kr.lul.urs.application.api;

/**
 * 프로덕트 에이전트가 지원할 플랫폼 정보를 다룰 API 정의.
 *
 * @since 2016. 6. 2.
 * @author Just Burrow just.burrow@lul.kr
 */
public abstract class AgentPlatformApiConfiguration {
  public static final String PREFIX      = "/platform";

  /**
   * 신규 생성에 필요한 정보 보기.
   */
  public static final String CREATE_FORM = "/create";

  /**
   * 신규 생성.
   */
  public static final String CREATE      = "";
  /**
   * 플랫폼 상세 정보 보기.
   */
  public static final String READ        = "/{id:\\d+}";

  /**
   * 자신의 목록 보기.
   */
  public static final String LIST        = "";

  /**
   * 플랫폼 변경에 필요한 정보.
   */
  public static final String UPDATE_FORM = READ + "/update";

  /**
   * 플랫폼 변경.
   */
  public static final String UPDATE      = READ;

  protected AgentPlatformApiConfiguration() {
    throw new UnsupportedOperationException();
  }
}
