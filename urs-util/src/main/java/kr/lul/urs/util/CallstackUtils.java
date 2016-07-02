/**
 *
 */
package kr.lul.urs.util;

import static kr.lul.urs.util.Asserts.hasLength;
import static kr.lul.urs.util.Asserts.notNull;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.reflections.ReflectionUtils;

import javassist.ClassPool;
import javassist.CtMethod;
import javassist.NotFoundException;

/**
 * 메서드, 코드를 호출한 코드의 정보를 제공하는 유틸리티.
 *
 * @since 2016. 7. 2.
 * @author Just Burrow just.burrow@lul.kr
 */
public abstract class CallstackUtils {
  /**
   * @param thread
   * @param cls
   * @param methodName
   * @param line
   * @return
   * @throws NotFoundException
   * @throws SecurityException
   * @throws NoSuchMethodException
   * @since 2016. 7. 2.
   */
  @Deprecated
  private static Caller overloadedCaller(final Thread thread, final Class<?> cls, final String methodName,
      final int line) throws NotFoundException, NoSuchMethodException, SecurityException {
    notNull(thread, "thread");
    notNull(cls, "cls");
    hasLength(methodName, "methodName");

    ClassPool pool = ClassPool.getDefault();
    CtMethod ctMethod = Stream.of(pool.get(cls.getName()).getDeclaredMethods())
        .filter(m -> m.getName().equals(methodName))
        .filter(m -> {
          int l = m.getMethodInfo().getLineNumber(0); // TODO 번호가 안맞는다. stack trace의 값보다 조금(4 정도?) 작음.
          return l == line;
        })
        .findFirst()
        .get();

    List<Class<?>> params = new ArrayList<>();
    Stream.of(ctMethod.getParameterTypes()).forEach(m -> {
      Class<?> c;
      try {
        c = Class.forName(m.getName());
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
      params.add(c);
    });
    Method method = cls.getDeclaredMethod(methodName, params.toArray(new Class<?>[params.size()]));

    return new Caller(thread, cls, method, line);
  }

  /**
   * 이 메서드를 호출한 코드를 호출한 코드의 정보를 반환한다.
   * 어딘가에서 {@code B.methodB()}를 호출한다면, <code>caller</code> 정보는 <code>A.methodA()</code>가 아닌 <code>B.methodB()</code>의
   * 정보가 된다.
   *
   * <pre>
   * <code class="java">
   * class A {
   *   public void methodA() {
   *     Caller caller = CallstackUtils.caller();
   *   }
   * }
   *
   * class B {
   *   public void methodB() {
   *     A a = new A();
   *     a.methodA();
   *   }
   * }
   * </code>
   * </pre>
   *
   * @return
   * @since 2016. 7. 2.
   */
  public static Caller caller() {
    Thread thread = Thread.currentThread();
    StackTraceElement stackTraceElement = thread.getStackTrace()[2];
    String className = stackTraceElement.getClassName();
    String methodName = stackTraceElement.getMethodName();
    int line = stackTraceElement.getLineNumber();

    try {
      Class<?> cls = Class.forName(className);
      @SuppressWarnings("unchecked")
      Set<Method> methods = ReflectionUtils.getAllMethods(cls, m -> m.getName().equals(methodName));
      switch (methods.size()) {
        case 0:
          throw new RuntimeException("no method");
        case 1:
          return new Caller(thread, cls, methods.iterator().next(), line);
        default:
          return overloadedCaller(thread, cls, methodName, line);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  protected CallstackUtils() {
    throw new UnsupportedOperationException();
  }
}
