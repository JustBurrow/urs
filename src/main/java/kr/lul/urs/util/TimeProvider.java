/**
 *
 */
package kr.lul.urs.util;

import java.time.Instant;

/**
 * 시간관련 정보를 제공하기위한 어댑터.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 3.
 */
public interface TimeProvider {
  /**
   * 현재 시각 정보.
   *
   * @return 현재 시각.
   */
  public Instant now();
}
