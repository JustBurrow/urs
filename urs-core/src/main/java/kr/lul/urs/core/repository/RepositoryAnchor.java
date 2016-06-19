package kr.lul.urs.core.repository;

/**
 * 레포지토리 패키지 정보를 얻기 위한 정보를 제공하는 앵커.
 *
 * @author justburrow
 */
public abstract class RepositoryAnchor {
  /**
   * 앵커가 정보를 제공하는 패키지 이름.
   */
  public static final String PACKAGE_NAME = RepositoryAnchor.class.getPackage().getName();

  protected RepositoryAnchor() {
    throw new UnsupportedOperationException();
  }
}
