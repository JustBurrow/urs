package kr.lul.urs.core.repository;

import kr.lul.urs.util.Anchor;

/**
 * 레포지토리 패키지 정보를 얻기 위한 정보를 제공하는 앵커.
 *
 * @author justburrow
 */
public abstract class RepositoryAnchor extends Anchor {
  public static final String PACKAGE_NAME = RepositoryAnchor.class.getPackage().getName();
}
