/**
 *
 */
package kr.lul.urs.spring.debug;

import java.util.List;

/**
 * 현재 쓰레드의 디버깅 정보를 수집하고, 수집한 정보를 제공한다.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 8.
 */
public interface DebuggingSupport {
  /**
   * 스택에 디버깅 정보를 추가한다.
   *
   * @param data
   *          추가하려는 정보. {@link String}일 경우 <code>message</code>로 취급한다.
   * @return 실재 추가된 디버깅 정보.
   * @since 2016. 5. 8.
   * @see #append(String, Object)
   */
  public DebugEntry append(Object data);

  /**
   * 스택에 디버깅 정보를 추가한다.
   *
   * @param message
   * @param data
   *          추가하려는 정보.
   * @return 실재 추가된 디버깅 정보.
   * @since 2016. 5. 8.
   */
  public DebugEntry append(String message, Object data);

  /**
   * 현재 수집한 정보를 반환한다.
   * 작은 인젝스가 먼저 수집한 정보이다.
   *
   * @return 변경 불가.
   * @since 2016. 5. 8.
   */
  public List<DebugEntry> stack();

  /**
   * 수집한 정보를 반환하고, 수집된 정보를 삭제한다.
   *
   * @return 변경 불가.
   * @since 2016. 5. 8.
   */
  public List<DebugEntry> clear();
}
