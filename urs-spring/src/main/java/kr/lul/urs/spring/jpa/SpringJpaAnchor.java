package kr.lul.urs.spring.jpa;

/**
 * 모듈 스캔을 위한 정보를 제공하는 앵커.
 *
 * @author just.burrow@lul.kr
 */
public abstract class SpringJpaAnchor {
  /**
   * 모듈 앵커의 패키지명.
   */
  public static final String PACKAGE_NAME = SpringJpaAnchor.class.getPackage().getName();

  protected SpringJpaAnchor() {
    throw new UnsupportedOperationException();
  }
}
