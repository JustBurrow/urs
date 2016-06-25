package kr.lul.urs.core.domain.entity;

import kr.lul.urs.util.Anchor;

/**
 * 엔티티 패키지 정보를 얻기 위한 정보를 제공하는 앵커.
 *
 * @author justburrow
 */
public abstract class EntityAnchor extends Anchor {
  public static final String PACKAGE_NAME = EntityAnchor.class.getPackage().getName();
}
