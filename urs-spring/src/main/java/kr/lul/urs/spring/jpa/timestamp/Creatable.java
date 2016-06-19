/**
 *
 */
package kr.lul.urs.spring.jpa.timestamp;

import java.time.Instant;

/**
 * 생성할 수는 있지만 수정할 수 없는 엔티티.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 5.
 */
public interface Creatable {
  /**
   * 엔티티 생성 시각.
   *
   * @return 엔티티 생성 시각.
   */
  public Instant getCreate();
}
