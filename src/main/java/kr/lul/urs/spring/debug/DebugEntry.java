/**
 *
 */
package kr.lul.urs.spring.debug;

import java.time.Instant;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 8.
 */
public abstract class DebugEntry {
  private Instant timestamp;
  private Thread  thread;
  private String  message;
  private Object  data;

  protected DebugEntry(Instant timestamp, String message, Object data) {
    if (null == timestamp) {
      throw new IllegalArgumentException("timestamp is null.");
    }
    this.timestamp = timestamp;
    this.thread = Thread.currentThread();
    this.message = message;
    this.data = data;
  }

  /**
   * 디버깅 정보 수집 시점.
   *
   * @return 타임스템프.
   * @since 2016. 5. 9.
   */
  public Instant getTimestamp() {
    return this.timestamp;
  }

  /**
   * @return 쓰레드 이름.
   * @since 2016. 5. 9.
   */
  public String getThread() {
    return this.thread.getName();
  }

  public String getMessage() {
    return this.message;
  }

  public Object getData() {
    return this.data;
  }
}
