/**
 *
 */
package kr.lul.urs.application.api;

/**
 * 대시보드 API.
 *
 * @since 2016. 5. 31.
 * @author Just Burrow just.burrow@lul.kr
 */
public abstract class DashboardApiConfiguration {
  /**
   * 대시보드 API 그룹.
   */
  public static final String PREFIX  = "/dashboard";

  /**
   * 대시보드 요약.
   */
  public static final String SUMMARY = "";

  protected DashboardApiConfiguration() {
    throw new UnsupportedOperationException();
  }
}
