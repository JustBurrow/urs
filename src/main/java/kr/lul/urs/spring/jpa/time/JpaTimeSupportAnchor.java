/**
 *
 */
package kr.lul.urs.spring.jpa.time;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 21.
 */
public abstract class JpaTimeSupportAnchor {
  public static final String PACKAGE_NAME = JpaTimeSupportAnchor.class.getPackage().getName();

  protected JpaTimeSupportAnchor() {
    throw new UnsupportedOperationException();
  }
}
