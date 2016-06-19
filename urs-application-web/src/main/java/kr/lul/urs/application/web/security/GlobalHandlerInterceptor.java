/**
 *
 */
package kr.lul.urs.application.web.security;

import static kr.lul.urs.spring.security.UserDetailsContext.getCurrentUserDetails;
import static kr.lul.urs.util.Asserts.notNull;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.lul.urs.core.configuration.InjectionConstants.Beans;

/**
 * @since 2016. 5. 31.
 * @author Just Burrow just.burrow@lul.kr
 */
@Component(Beans.NAME_GLOBAL_HANDLER_INTERCEPTOR)
public class GlobalHandlerInterceptor extends HandlerInterceptorAdapter {
  private static final Logger log = LoggerFactory.getLogger(GlobalHandlerInterceptor.class);

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <A>HandlerInterceptorAdapter
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  /*
   * (non-Javadoc)
   * @see
   * org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest,
   * javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
   * @since 2016. 5. 31.
   */
  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav)
      throws Exception {
    notNull(request, "request");
    notNull(response, "response");
    notNull(handler, "handler");

    if (log.isDebugEnabled()) {
      log.debug(String.format("request:%s response:%s handler:%s mav:%s", request, response, handler, mav));
    }

    OperatorDetails operator = getCurrentUserDetails(OperatorDetails.class);
    if (null != operator) {
      mav.addObject("user", operator);
    }
  }
}
