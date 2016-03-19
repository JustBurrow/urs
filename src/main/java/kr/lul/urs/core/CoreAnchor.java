/**
 *
 */
package kr.lul.urs.core;

/**
 * 코어 패키지의 정보를 얻기 위한 정보를 제공하는 앵커.
 *
 * @author Just Burrow just.burrow@lul.kr
 */
public abstract class CoreAnchor {
  /**
   * 앵커가 정보를 제공하는 패키지 이름.
   */
  public static final String PACKAGE_NAME = CoreAnchor.class.getPackage().getName();

  protected CoreAnchor() {
    throw new UnsupportedOperationException();
  }
}
