/**
 *
 */
package kr.lul.urs.application.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import kr.lul.urs.core.CoreAnchor;

/**
 * @author Just Burrow just.burrow@lul.kr
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = { CoreAnchor.class })
public class ApplicationConfiguration {
}
