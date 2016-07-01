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
public abstract class DashboardApiConstants {
  public static abstract class M {
    protected M() {
      throw new UnsupportedOperationException();
    }
  }

  public static abstract class C {
    /**
     * 대시보드 API 그룹.
     */
    public static final String PREFIX  = "/dashboard";
    /**
     * 대시보드 요약.
     */
    public static final String SUMMARY = "";

    protected C() {
      throw new UnsupportedOperationException();
    }
  }

  protected DashboardApiConstants() {
    throw new UnsupportedOperationException();
  }
}
