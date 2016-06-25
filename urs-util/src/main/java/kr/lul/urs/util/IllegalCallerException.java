/**
 *
 */
package kr.lul.urs.util;

/**
 * 추상 클래스의 초기화 메서드 등의 특수한 목적의 메서드를 호출할 때의 예외.
 *
 * @since 2016. 6. 25.
 * @author Just Burrow just.burrow@lul.kr
 */
public class IllegalCallerException extends RuntimeException {
  private static final long serialVersionUID = 7398855870468688619L;

  public IllegalCallerException() {
    super();
  }

  public IllegalCallerException(String message, Throwable cause) {
    super(message, cause);
  }

  public IllegalCallerException(String message) {
    super(message);
  }

  public IllegalCallerException(Throwable cause) {
    super(cause);
  }
}
