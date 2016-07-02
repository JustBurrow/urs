/**
 *
 */
package kr.lul.urs.util;

import static kr.lul.urs.util.CallstackUtils.caller;
import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

/**
 * @since 2016. 7. 2.
 * @author Just Burrow just.burrow@lul.kr
 */
public class CallstackUtilsTest {
  @Test
  public void testCaller() throws Exception {
    // Given
    final String caseName = "testCaller";
    final Method method = CallstackUtilsTest.class.getDeclaredMethod(caseName);

    // When
    Caller caller = caller();

    // Then
    assertThat(caller).isNotNull();

    assertThat(caller.getThread()).isSameAs(Thread.currentThread());
    assertThat(caller.getPackage()).isSameAs(CallstackUtilsTest.class.getPackage());
    assertThat(caller.getCls()).isSameAs(CallstackUtilsTest.class);
    assertThat(caller.getMethod()).isEqualTo(method);
    assertThat(caller.getLine()).isGreaterThan(19)
        .isEqualTo(26)
        .isLessThan(42); // 메서드 바디.

    assertThat(caller.getPackageName()).isEqualTo(CallstackUtilsTest.class.getPackage().getName());
    assertThat(caller.getClassName()).isEqualTo(CallstackUtilsTest.class.getSimpleName());
    assertThat(caller.getMethodName()).isEqualTo(caseName);
  }

  @Test
  public void testCallerForOverload() throws Exception {
    // Given
    class Callee {
      Caller c1;
      Caller c2;

      public void method() {
        this.c1 = caller();
      }

      public void method(String str) {
        this.c2 = caller();
      }
    }
    Method m1 = Callee.class.getDeclaredMethod("method");
    Method m2 = Callee.class.getDeclaredMethod("method", String.class);
    String str = RandomStringUtils.randomAlphanumeric(Randoms.in(10, 20));

    // When
    Callee callee = new Callee();
    callee.method();
    callee.method(str);

    // Then
    assertThat(callee.c1).isNotNull();

    assertThat(callee.c1.getThread()).isSameAs(Thread.currentThread());
    assertThat(callee.c1.getPackage()).isSameAs(Callee.class.getPackage());
    assertThat(callee.c1.getCls()).isSameAs(Callee.class);
    assertThat(callee.c1.getMethod()).isEqualTo(m1);
    assertThat(callee.c1.getLine()).isGreaterThan(51)
        .isEqualTo(52)
        .isLessThan(53); // 메서드 바디.

    assertThat(callee.c1.getPackageName()).isEqualTo(Callee.class.getPackage().getName());
    assertThat(callee.c1.getClassName()).isEqualTo(Callee.class.getSimpleName());
    assertThat(callee.c1.getMethodName()).isEqualTo(m1.getName());

    assertThat(callee.c2.getThread()).isSameAs(Thread.currentThread());
    assertThat(callee.c2.getPackage()).isSameAs(Callee.class.getPackage());
    assertThat(callee.c2.getCls()).isSameAs(Callee.class);
    assertThat(callee.c2.getMethod()).isEqualTo(m2);
    assertThat(callee.c2.getLine()).isGreaterThan(55)
        .isEqualTo(56)
        .isLessThan(57); // 메서드 바디.

    assertThat(callee.c2.getPackageName()).isEqualTo(Callee.class.getPackage().getName());
    assertThat(callee.c2.getClassName()).isEqualTo(Callee.class.getSimpleName());
    assertThat(callee.c2.getMethodName()).isEqualTo(m2.getName());
  }
}
