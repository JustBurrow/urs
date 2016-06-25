/**
 *
 */
package kr.lul.urs.util;

import static kr.lul.urs.util.Asserts.equal;
import static kr.lul.urs.util.Asserts.hasLength;
import static kr.lul.urs.util.Asserts.notNull;
import static kr.lul.urs.util.Asserts.starts;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import org.reflections.Reflections;

/**
 * {@link Anchor}관련 도구.
 *
 * @since 2016. 6. 25.
 * @author Just Burrow just.burrow@lul.kr
 */
public abstract class AnchorUtils {
  /**
   * 클래스가 있는 패키지와 하위 패키지에서 {@link Anchor} 클래스를 찾는다.
   *
   * @param classForPackage
   * @return
   * @since 2016. 6. 25.
   */
  public static Set<Class<? extends Anchor>> scan(Class<?> classForPackage) {
    notNull(classForPackage, "classForPackage");

    return scan(classForPackage.getPackage().getName());
  }

  /**
   * 지정한 패키지와 하위 패키지에서 {@link Anchor} 클래스를 찾는다.
   *
   * @param packageName
   * @return
   * @since 2016. 6. 25.
   */
  public static Set<Class<? extends Anchor>> scan(String packageName) {
    hasLength(packageName, "packageName");

    Reflections ref = new Reflections(packageName);
    Set<Class<? extends Anchor>> anchors = ref.getSubTypesOf(Anchor.class);
    return anchors;
  }

  /**
   * 클래스의 패키지를 스캔해서, 정상적으로 초기화되지 않은 {@link Anchor}가 있는지 확인한다.
   *
   * @param classForPackage
   *          스캔할 패키지에 있는 클래스.
   * @throws AssertionException
   * @since 2016. 6. 25.
   */
  public static void scanAndTest(Class<?> classForPackage) throws AssertionException {
    notNull(classForPackage, "classForPackage");

    scanAndTest(classForPackage.getPackage().getName());
  }

  /**
   * 클래스의 패키지를 스캔해서, 정상적으로 초기화되지 않은 {@link Anchor}가 있는지 확인한다.
   *
   * @param packageName
   *          스캔할 패키지 이름.
   * @since 2016. 6. 25.
   */
  public static void scanAndTest(String packageName) {
    Set<Class<? extends Anchor>> anchors = scan(packageName);
    for (Class<? extends Anchor> anchor : anchors) {
      try {
        Field packageNameField = anchor.getField(Anchor.FIELD_NAME);
        String actualName = (String) packageNameField.get(null);
        starts(actualName, packageName);

        Constructor<?>[] constructors = anchor.getDeclaredConstructors();
        equal(constructors.length, 1, anchor.getName());
        equal(constructors[0].getParameterTypes().length, 0, anchor.getName());

        try {
          constructors[0].newInstance();
          throw new AssertionException("instance not allowed : " + anchor);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
            | InvocationTargetException ex) {
          // OK.
        }
      } catch (Exception e) {
        throw new AssertionException(e);
      }
    }
  }

  protected AnchorUtils() {
    throw new UnsupportedOperationException();
  }
}
