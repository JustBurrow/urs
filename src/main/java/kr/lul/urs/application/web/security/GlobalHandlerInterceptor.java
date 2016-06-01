/**
 *
 */
package kr.lul.urs.application.web.security;

import static kr.lul.urs.util.Asserts.notNull;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.lul.urs.application.configuration.InjectionConstants.Beans;
import kr.lul.urs.util.Conditions;

/**
 * @since 2016. 5. 31.
 * @author Just Burrow just.burrow@lul.kr
 */
@Component(Beans.NAME_GLOBAL_HANDLER_INTERCEPTOR)
public class GlobalHandlerInterceptor extends HandlerInterceptorAdapter {
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

    Authentication authentication = SecurityContextHolder.getContext()
        .getAuthentication();
    if (null != authentication && Conditions.assignable(authentication.getPrincipal(), User.class)) {
      mav.addObject("user", authentication.getPrincipal());
    } else {
      mav.addObject("user", null);
    }
  }
}
