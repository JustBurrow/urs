package kr.lul.urs.spring.jpa.converter.auto;

public abstract class JpaAutoConverterAnchor {
  public static final String PACKAGE_NAME = JpaAutoConverterAnchor.class.getPackage().getName();

  protected JpaAutoConverterAnchor() {
    throw new UnsupportedOperationException();
  }
}
