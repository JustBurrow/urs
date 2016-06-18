/**
 *
 */
package kr.lul.urs.application.web.view;

/**
 * 플랫폼 정보에 관련된 뷰 정보.
 *
 * @since 2016. 6. 3.
 * @author Just Burrow just.burrow@lul.kr
 */
public abstract class AgentPlatformView {
  private static final String TPL_PREFIX = "platform/";

  /**
   * 에이전트 플랫폼 목록 템플릿.
   */
  public static final String  TPL_LIST   = TPL_PREFIX + "list";

  /**
   * 에이전트 플랫폼 신규 작성 폼 템플릿.
   */
  public static final String  TPL_CREATE = TPL_PREFIX + "create";

  /**
   * 에이전트 플랫폼 상세 정보 템플릿.
   */
  public static final String  TPL_DETAIL = TPL_PREFIX + "detail";

  /**
   * 에이전트 플랫폼 수정 폼 템플릿.
   */
  public static final String  TPL_UPDATE = TPL_PREFIX + "update";

  protected AgentPlatformView() {
    throw new UnsupportedOperationException();
  }
}
