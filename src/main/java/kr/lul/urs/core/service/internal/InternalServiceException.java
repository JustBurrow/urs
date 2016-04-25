/**
 *
 */
package kr.lul.urs.core.service.internal;

/**
 * 내부 서비스 레이어에서 발생한 예외의 공통화를 위한 예외.
 * 
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 21.
 */
public abstract class InternalServiceException extends Exception {
  public static final long  SERIAL_VERSION_UID = -3828519103390990541L;
  private static final long serialVersionUID   = SERIAL_VERSION_UID;

  public InternalServiceException() {
    super();
  }

  protected InternalServiceException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public InternalServiceException(String message, Throwable cause) {
    super(message, cause);
  }

  public InternalServiceException(String message) {
    super(message);
  }

  public InternalServiceException(Throwable cause) {
    super(cause);
  }
}
