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
  public static interface Test<E extends Throwable> {
    /**
     * 테스트코드 실행.
     */
    public void run() throws E;
  }

  /**
   * 예외 인스턴스 검증기.
   *
   * @author just.burrow@lul.kr
   *
   * @param <E>
   *          예외 타입.
   */
  public static interface ExceptionValidator<E extends Throwable> {
    /**
     * 발생한 예외의 값을 검증한다.
     *
     * @param e
     *          확인할 예외.
     * @return 예외 인스턴스가 확인 로직을 통과하면 <code>null</code>, 실패하면 디테일 메시지.
     */
    public String validate(E e);
  }

  /**
   * 에외가 발생해야 하는 테스트를 실행하고, 기대한 타입의 예외 인스턴스를 던지는지 검증한다.
   *
   * @param exception
   *          테스트 코드가 던져야 하는 예외 타입.
   * @param test
   *          테스트 코드.
   * @throws AssertionError
   */
  public static <E1 extends Throwable, E2 extends Throwable> void exceptException(Class<E1> exception, Test<E2> test)
      throws AssertionError {
    if (null == exception) {
      throw new IllegalArgumentException("exception is null.");
    } else if (null == test) {
      throw new IllegalArgumentException("test code is null.");
    }

    try {
      test.run();
    } catch (Throwable e) {
      if (Conditions.instance(e, exception)) {
        return;
      } else {
        throw new AssertionError(String.format("expected exception is [%s] but actual exception is [%s]",
            exception.getName(), e.getClass().getName()), e);
      }
    }
    throw new AssertionError(String.format("expected exception [%s] does not occured.", exception.getName()));
  }

  /**
   * 예외가 발생해야 하는 테스트를 실행하고, 발생한 예외 인스턴스의 타입과 내용을 확인한다.
   *
   * @param exception
   *          테스트에서 발생해야 하는 예외.
   * @param test
   *          테스트 코드.
   * @param exceptionValidator
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
  public static <E1 extends Throwable, E2 extends Throwable> void exceptException(Class<E1> exception, Test<E2> test,
      ExceptionValidator<E1> exceptionValidator) throws IllegalArgumentException, AssertionError {
    if (null == exception) {
      throw new IllegalArgumentException("exception is null.");
    } else if (null == test) {
      throw new IllegalArgumentException("test code is null.");
    } else if (null == exceptionValidator) {
      throw new IllegalArgumentException("exceptionValidator is null.");
    }

    try {
      test.run();
    } catch (Throwable e) {
      if (!Conditions.instance(e, exception)) {
        throw new AssertionError(String.format("expected exception is [%s] but actual exception is [%s]",
            exception.getName(), e.getClass().getName()), e);
      }

      @SuppressWarnings("unchecked")
      String detailMessage = exceptionValidator.validate((E1) e);
      if (null != detailMessage) {
        throw new AssertionError(detailMessage, e);
      }
      return;
    }
    throw new AssertionError(String.format("expected exception [%s] does not occured.", exception.getName()));
  }

  protected Tests() {
    throw new UnsupportedOperationException();
  }
}
