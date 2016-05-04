package kr.lul.urs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class TestConfig {
  public static final String            PACKAGE_NAME            = TestConfig.class.getPackage().getName();
  public static final String            TEST_RESOURCE_BASE_PATH = "src/test/resources/junit";

  public static final Logger            log                     = LoggerFactory.getLogger(PACKAGE_NAME + ".TEST");

  protected TestConfig() {
    throw new UnsupportedOperationException();
  }
}
