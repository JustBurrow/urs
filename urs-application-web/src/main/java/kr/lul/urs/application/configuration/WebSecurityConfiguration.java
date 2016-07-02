/**
 *
 */
package kr.lul.urs.application.configuration;

import static kr.lul.urs.application.api.AuthApis.LOGIN_FORM;
import static kr.lul.urs.application.api.DashboardApis.SUMMARY;
import static kr.lul.urs.application.api.IndexApis.INDEX;
import static kr.lul.urs.application.api.OperatorApis.SIGN_UP;
import static kr.lul.urs.application.api.OperatorApis.SIGN_UP_FORM;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

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
  private static final Logger    log = LoggerFactory.getLogger(WebSecurityConfiguration.class);

  @Autowired
  private PasswordEncoder        passwordEncoder;
  @Autowired
  private OperatorDetailsService operatorDetailsService;

  /**
   * @return
   * @since 2016. 7. 2.
   */
  private String[] anonymousOnlyUriPatterns() {
    String[] patterns = new String[] { SIGN_UP.getUriTemplate(), SIGN_UP_FORM.getUriTemplate(),
        LOGIN_FORM.getUriTemplate() };
    if (log.isDebugEnabled()) {
      log.debug("anonymous only uri patterns : " + Arrays.toString(patterns));
    }
    return patterns;
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <A>WebSecurityConfigurerAdapter
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf();
    http.formLogin()
        .loginPage(LOGIN_FORM.getUriTemplate())
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
        .antMatchers(this.anonymousOnlyUriPatterns())
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
