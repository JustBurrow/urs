/**
 *
 */
package kr.lul.urs.application;

import kr.lul.urs.util.Anchor;

/**
 * 애플리케이션 패키지 정보를 얻기 위한 정보를 제공하는 앵커.
 *
 * @author Just Burrow just.burrow@lul.kr
 */
public abstract class ApplicationAnchor extends Anchor {
  public static final String PACKAGE_NAME = ApplicationAnchor.class.getPackage().getName();
}
