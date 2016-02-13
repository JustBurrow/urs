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
   * 예외 인스턴스의 값 확인한다.
   *
   * @author just.burrow@lul.kr
   *
   * @param <E>
   *          예외 타입.
   */
  public static interface ExceptionChecker<E extends Throwable> {
    /**
     * 발생한 예외의 값을 확인한다.
     *
     * @param e
     *          확인할 예외.
     * @return 예외 인스턴스가 확인 로직을 통과하면 <code>null</code>, 실패하면 디테일 메시지.
     */
    public String check(E e);
  }

  /**
   * 코드를 실행한 결과 예외가 발생하지 않으면 에러를 발생한다.
   * 
   * @param exception
   * @param test
   *          테스트 코드.
   *
   * @throws AssertionError
   */
  public static <E extends Throwable> void exceptException(Class<E> exception, TestCode test) throws AssertionError {
    if (null == exception) {
      throw new IllegalArgumentException("exception is null.");
    } else if (null == test) {
      throw new IllegalArgumentException("test code is null.");
    }

    try {
      test.run();
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

  /**
   * 예외가 발생해야 하는 테스트를 실행하고, 발생한 예외 인스턴스의 내용을 확인한다.
   *
   * @param exception
   *          테스트에서 발생해야 하는 예외.
   * @param test
   *          테스트 코드.
   * @param checker
   *          테스트 코드가 던진 예외의 확인로직.
   * @throws IllegalArgumentException
   *           인자가 <code>null</code>인 경우.
   * @throws AssertionError
   *           <ul>
   *           <li>테스트 코드가 예외를 던지지 않는 경우.</li>
   *           <li>테스트 코드가 기대하지 않은 타입의 예외를 던지는 경우.</li>
   *           <li>테스트 코드가 던진 예외가 예외 확인 로직을 통과하지 못할 때.</li>
   *           </ul>
   */
  @SuppressWarnings("unchecked")
  public static <E extends Throwable> void exceptException(Class<E> exception, TestCode test,
      ExceptionChecker<E> checker) throws IllegalArgumentException, AssertionError {
    if (null == exception) {
      throw new IllegalArgumentException("exception is null.");
    } else if (null == test) {
      throw new IllegalArgumentException("test code is null.");
    } else if (null == checker) {
      throw new IllegalArgumentException("checker is null.");
    }

    try {
      test.run();
    } catch (Throwable e) {
      if (e.getClass() != exception) {
        throw new AssertionError(String.format("expected exception is [%s] but actual exception is [%s]",
            exception.getName(), e.getClass().getName()), e);
      }

      String detailMessage = checker.check((E) e);
      if (null != detailMessage) {
        throw new AssertionError(detailMessage, e);
      }
    }
    throw new AssertionError(String.format("expected exception [%s] does not occured.", exception.getName()));
  }

  protected Tests() {
    throw new UnsupportedOperationException();
  }
}
