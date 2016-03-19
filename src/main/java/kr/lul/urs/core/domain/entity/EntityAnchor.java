package kr.lul.urs.core.domain.entity;

/**
 * 엔티티 패키지 정보를 얻기 위한 정보를 제공하는 앵커.
 *
 * @author justburrow
 */
public abstract class EntityAnchor {
  /**
   * 앵커가 정보를 제공하는 패키지 이름.
   */
  public static final String PACKAGE_NAME = EntityAnchor.class.getPackage().getName();

  protected EntityAnchor() {
    throw new UnsupportedOperationException();
  }
}
