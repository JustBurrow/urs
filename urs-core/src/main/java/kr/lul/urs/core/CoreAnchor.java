/**
 *
 */
package kr.lul.urs.core;

import kr.lul.urs.util.Anchor;

/**
 * 코어 패키지의 정보를 얻기 위한 정보를 제공하는 앵커.
 *
 * @author Just Burrow just.burrow@lul.kr
 */
public abstract class CoreAnchor extends Anchor {
  public static final String PACKAGE_NAME = CoreAnchor.class.getPackage().getName();
}
