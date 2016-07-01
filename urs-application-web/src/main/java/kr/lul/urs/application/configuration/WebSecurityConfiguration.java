/**
 *
 */
package kr.lul.urs.application.configuration;

import static kr.lul.urs.application.api.AuthApis.LOGIN_FORM;
import static kr.lul.urs.application.api.DashboardApis.SUMMARY;
import static kr.lul.urs.application.api.IndexApis.INDEX;
import static kr.lul.urs.application.api.OperatorApis.SIGN_UP_FORM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.lul.urs.application.api.AuthApiConstants.C;
import kr.lul.urs.application.api.AuthApiConstants.M;
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
        .loginPage(C.PREFIX + C.LOGIN_FORM)
        .defaultSuccessUrl(SUMMARY.getUriTemplate())
        .usernameParameter(M.EMAIL)
        .passwordParameter(M.PASSWORD);
    http.logout()
        .logoutUrl("/auth/logout")
        .logoutSuccessUrl(INDEX.getUriTemplate());
    http.authorizeRequests()
        .antMatchers(INDEX.getUriTemplate())
        .permitAll();
    http.authorizeRequests()
        .antMatchers(SIGN_UP_FORM.getUriTemplate(), LOGIN_FORM.getUriTemplate())
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
