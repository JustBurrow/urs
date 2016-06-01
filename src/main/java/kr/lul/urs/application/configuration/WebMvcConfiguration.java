/**
 *
 */
package kr.lul.urs.application.configuration;

import static kr.lul.urs.util.Asserts.notNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import kr.lul.urs.application.configuration.InjectionConstants.Beans;

/**
 * Web MVC 설
 *
 * @since 2016. 5. 31.
 * @author Just Burrow just.burrow@lul.kr
 */
@Configuration
@EnableWebMvc
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
  @Autowired
  @Qualifier(Beans.NAME_GLOBAL_HANDLER_INTERCEPTOR)
  private HandlerInterceptor globalHandlerInterceptor;

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <A>WebMvcConfigurerAdapter
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  /*
   * (non-Javadoc)
   * @see
   * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addInterceptors(org.springframework.web.
   * servlet.config.annotation.InterceptorRegistry)
   * @since 2016. 5. 31.
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    notNull(registry);

    registry.addInterceptor(this.globalHandlerInterceptor);
  }
}
