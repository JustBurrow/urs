/**
 *
 */
package kr.lul.urs.spring.jpa.timestamp;

import java.time.Instant;

/**
 * 애플리케이션 로직에서 생성과 갱신이 가능한 엔티티.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 23.
 */
public interface Updatable {
  /**
   * 엔티티 생성 시각.
   *
   * @return 엔티티 생성 시각.
   */
  public Instant getCreate();

  /**
   * 엔티티 저장 시각.
   *
   * @return 엔티티 저장 시각.
   */
  public Instant getUpdate();
}
