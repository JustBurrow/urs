/**
 *
 */
package kr.lul.urs.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * URS 애플리케이션의 보안 설정.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 21.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <A>WebSecurityConfigurerAdapter
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
        .and().formLogin().loginPage("/auth/login").defaultSuccessUrl("/dashboard")
        .and().logout().logoutUrl("/auth/logout").logoutSuccessUrl("/")
        .and().authorizeRequests().antMatchers("/").permitAll()
        .and().authorizeRequests().antMatchers("/operators/new", "/auth/login").anonymous();
  }
}
