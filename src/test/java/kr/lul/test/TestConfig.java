package kr.lul.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class TestConfig {
  public static final String PACKAGE_NAME = TestConfig.class.getPackage().getName();

  public static final Logger log          = LoggerFactory.getLogger(PACKAGE_NAME + ".TEST");

  protected TestConfig() {
    throw new UnsupportedOperationException();
  }
}
