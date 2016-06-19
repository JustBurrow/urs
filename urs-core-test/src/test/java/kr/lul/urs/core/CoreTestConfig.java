/**
 *
 */
package kr.lul.urs.core;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Just Burrow just.burrow@lul.kr
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = { CoreAnchor.class })
public class CoreTestConfig {
  public static final boolean ROLLBACK                              = true;

  public static final String  MSG_AUTO_INCREMENT_ID_DOES_NOT_SETTED = "AUTO_INCREMENT id does not setted.";
  public static final String  MSG_TIMESTAMPER_DOES_NOT_WORK         = "Timestamper does not work : %s";

  public static final String  TEST_RESOURCE_BASE_PATH               = "src/test/resources/junit";
}
