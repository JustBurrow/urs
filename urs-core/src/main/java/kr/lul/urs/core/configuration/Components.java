/**
 *
 */
package kr.lul.urs.core.configuration;

import static kr.lul.urs.core.configuration.InjectionConstants.Beans.NAME_DEBUGGING_SUPPORT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.lul.urs.spring.debug.DebuggingSupport;
import kr.lul.urs.spring.debug.MemoryDebuggingSupport;
import kr.lul.urs.util.TimeProvider;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 5. 9.
 */
@Configuration
public class Components {
  @Autowired
  private TimeProvider timeProvider;

  @Bean(name = NAME_DEBUGGING_SUPPORT)
  public DebuggingSupport debuggingSupport() {
    MemoryDebuggingSupport debuggingSupport = new MemoryDebuggingSupport();
    debuggingSupport.setTimeProvider(this.timeProvider);
    return debuggingSupport;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
