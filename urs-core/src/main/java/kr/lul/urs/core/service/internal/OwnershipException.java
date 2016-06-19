/**
 *
 */
package kr.lul.urs.core.service.internal;

/**
 * 관리자의 소유권 정보가 잘못되어 생긴 에러.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 21.
 */
public class OwnershipException extends InternalServiceException {
  private static final long  serialVersionUID = SERIAL_VERSION_UID;

  public static final String UNKNOWN          = "UNKNOWN";

  /**
   * {@link #UNKNOWN} 혹은 예상 관리자의 ID.
   */
  private Object             expected;
  /**
   * {@link #UNKNOWN} 혹은 실재 관리자의 ID.
   */
  private Object             actual;

  public OwnershipException(String message) {
    this(message, UNKNOWN, UNKNOWN);
  }

  /**
   * @param message
   * @param expected
   *          정상적인 관리자.
   * @param actual
   *          실재 요청받은 관리자.
   */
  public OwnershipException(String message, Object expected, Object actual) {
    super(message);

    this.expected = null == expected ? UNKNOWN : expected;
    this.actual = null == actual ? UNKNOWN : actual;
  }

  public Object getExpected() {
    return this.expected;
  }

  public Object getActual() {
    return this.actual;
  }

  @Override
  public String toString() {
    return new StringBuilder(OwnershipException.class.getSimpleName()).append("[expected=").append(this.expected)
        .append(", actual=").append(this.actual).append(", message=").append(this.getMessage()).append(']').toString();
  }
}
