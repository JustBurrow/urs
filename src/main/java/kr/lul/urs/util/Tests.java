package kr.lul.urs.util;

/**
 * 단위테스트용 유틸리티.
 *
 * @author just.burrow@lul.kr
 */
public abstract class Tests {
  /**
   * 테스트코드.
   *
   * @author just.burrow@lul.kr
   */
  public static interface TestCode {
    /**
     * 테스트코드 실행.
     */
    public void run() throws Exception;
  }

  /**
   * 코드를 실행한 결과 예외가 발생하지 않으면 에러를 발생한다.
   *
   * @param runner
   *          테스트 코드.
   * @param exception
   * @throws AssertionError
   */
  public static <E extends Throwable> void exceptException(TestCode runner, Class<E> exception) throws AssertionError {
    try {
      runner.run();
    } catch (Throwable e) {
      if (exception == e.getClass()) {
        return;
      } else {
        throw new AssertionError(String.format("expected exception is [%s] but actual exception is [%s]",
            exception.getName(), e.getClass().getName()), e);
      }
    }
    throw new AssertionError(String.format("expected exception [%s] does not occured.", exception.getName()));
  }

  protected Tests() {
    throw new UnsupportedOperationException();
  }
}
