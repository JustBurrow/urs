package kr.lul.urs.util;

/**
 * 단정 실패를 처리하기 위한 예외.
 *
 * @author just.burrow@lul.kr
 */
public class AssertionException extends AssertionError {
  private static final long serialVersionUID = UtilConstants.SERIAL_VERSION_UID;

  public AssertionException() {
  }

  public AssertionException(Object detailMessage) {
    super(detailMessage);
  }

  public AssertionException(boolean detailMessage) {
    super(detailMessage);
  }

  public AssertionException(char detailMessage) {
    super(detailMessage);
  }

  public AssertionException(int detailMessage) {
    super(detailMessage);
  }

  public AssertionException(long detailMessage) {
    super(detailMessage);
  }

  public AssertionException(float detailMessage) {
    super(detailMessage);
  }

  public AssertionException(double detailMessage) {
    super(detailMessage);
  }

  public AssertionException(String message, Throwable cause) {
    super(message, cause);
  }
}
