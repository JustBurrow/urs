/**
 *
 */
package kr.lul.urs.application.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.lul.urs.application.web.security.OperatorDetailsService;

/**
 * URS 애플리케이션의 보안 설정.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 21.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
  @Autowired
  private OperatorDetailsService operatorDetailsService;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <A>WebSecurityConfigurerAdapter
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf();
    http.formLogin().loginPage("/auth/login").defaultSuccessUrl("/dashboard")
        .usernameParameter("email").passwordParameter("password");
    http.logout().logoutUrl("/auth/logout").logoutSuccessUrl("/");
    http.authorizeRequests().antMatchers("/").permitAll();
    http.authorizeRequests().antMatchers("/operators/new", "/auth/login").anonymous();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(this.operatorDetailsService)
        .passwordEncoder(this.passwordEncoder());
  }
}
