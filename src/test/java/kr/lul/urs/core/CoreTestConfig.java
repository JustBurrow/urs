/**
 *
 */
package kr.lul.urs.core;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import kr.lul.urs.application.configuration.ApplicationConfiguration;

/**
 * @author Just Burrow just.burrow@lul.kr
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = { ApplicationConfiguration.class })
public class CoreTestConfig {
  public static final boolean ROLLBACK = true;
}
