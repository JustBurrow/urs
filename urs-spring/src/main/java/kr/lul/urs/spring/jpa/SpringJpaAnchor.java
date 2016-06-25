package kr.lul.urs.spring.jpa;

import kr.lul.urs.util.Anchor;

/**
 * 모듈 스캔을 위한 정보를 제공하는 앵커.
 *
 * @author just.burrow@lul.kr
 */
public abstract class SpringJpaAnchor extends Anchor {
  public static final String PACKAGE_NAME = SpringJpaAnchor.class.getPackage().getName();
}
