package kr.lul.urs.core.domain.entity;

import java.io.Serializable;

/**
 * 엔티티 패키지 정보를 얻기 위한 정보를 제공하는 앵커.
 *
 * @author justburrow
 */
public abstract class EntityAnchor {
  /**
   * @see Serializable
   */
  public static final long   SERIAL_VERSION_UID = -3834797305341567369L;

  /**
   * 앵커가 정보를 제공하는 패키지 이름.
   */
  public static final String PACKAGE_NAME       = EntityAnchor.class.getPackage().getName();

  protected EntityAnchor() {
    throw new UnsupportedOperationException();
  }
}
