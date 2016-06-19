/**
 *
 */
package kr.lul.urs.spring;

import java.time.Instant;

/**
 * @since 2016. 6. 19.
 * @author Just Burrow just.burrow@lul.kr
 */
public abstract class AbstractSpringTest {
  protected Instant now;

  /**
   * {@link #now}에 현재 시각을 설정한다.
   *
   * @since 2016. 6. 19.
   */
  protected void setNow() {
    this.now = Instant.now();
  }
}
