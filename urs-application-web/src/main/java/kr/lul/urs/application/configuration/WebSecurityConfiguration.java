/**
 *
 */
package kr.lul.urs.application.configuration;

import static kr.lul.urs.application.api.AuthApiConfiguration.LOGIN_SPEC;
import static kr.lul.urs.application.api.AuthApiConfiguration.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.lul.urs.application.api.DashboardApiConfiguration;
import kr.lul.urs.application.api.OperatorApiConfiguration;
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
  private PasswordEncoder        passwordEncoder;
  @Autowired
  private OperatorDetailsService operatorDetailsService;

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <A>WebSecurityConfigurerAdapter
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf();
    http.formLogin()
        .loginPage(PREFIX + LOGIN_SPEC)
        .defaultSuccessUrl(DashboardApiConfiguration.PREFIX + DashboardApiConfiguration.SUMMARY)
        .usernameParameter("email")
        .passwordParameter("password");
    http.logout()
        .logoutUrl("/auth/logout")
        .logoutSuccessUrl("/");
    http.authorizeRequests()
        .antMatchers("/")
        .permitAll();
    http.authorizeRequests()
        .antMatchers(OperatorApiConfiguration.PREFIX + OperatorApiConfiguration.SIGN_UP_SPEC, PREFIX + LOGIN_SPEC)
        .anonymous();
    http.authorizeRequests()
        .anyRequest()
        .authenticated();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(this.operatorDetailsService)
        .passwordEncoder(this.passwordEncoder);
  }
}
