/**
 *
 */
package kr.lul.urs.util;

import static kr.lul.urs.util.Asserts.notNull;

import java.lang.reflect.Method;

/**
 * 어떤 코드를 호출한 코드의 메타데이터.
 *
 * @see CallstackUtils
 * @since 2016. 7. 2.
 * @author Just Burrow just.burrow@lul.kr
 */
public class Caller {
  private Thread   thread;
  private Class<?> cls;
  private Method   method;
  private int      line;

  private String   packageName;
  private String   className;
  private String   methodName;

  public Caller(Thread thread, Class<?> cls, Method method, int line) {
    notNull(thread, "thread");
    notNull(cls, "cls");

    this.thread = thread;
    this.cls = cls;
    this.method = method;
    this.line = line;

    this.packageName = cls.getPackage().getName();
    this.className = cls.getSimpleName();
    this.methodName = method.getName();
  }

  /**
   * @return the thread
   */
  public Thread getThread() {
    return this.thread;
  }

  public Package getPackage() {
    return this.cls.getPackage();
  }

  /**
   * @return the cls
   */
  public Class<?> getCls() {
    return this.cls;
  }

  /**
   * @return the method
   */
  public Method getMethod() {
    return this.method;
  }

  /**
   * @return the line
   */
  public int getLine() {
    return this.line;
  }

  /**
   * @return the packageName
   */
  public String getPackageName() {
    return this.packageName;
  }

  /**
   * @return the className
   */
  public String getClassName() {
    return this.className;
  }

  /**
   * @return the methodName
   */
  public String getMethodName() {
    return this.methodName;
  }
}
