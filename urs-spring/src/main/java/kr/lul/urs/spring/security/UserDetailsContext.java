/**
 *
 */
package kr.lul.urs.spring.security;

import static kr.lul.urs.util.Asserts.notNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import kr.lul.urs.util.Conditions;

/**
 * 현재 로그인 유저 정보를 제공하는 컨텍스트 유틸리티.
 *
 * @since 2016. 6. 6.
 * @author Just Burrow just.burrow@lul.kr
 */
public abstract class UserDetailsContext {
  private static Logger log = LoggerFactory.getLogger(UserDetailsContext.class);

  /**
   * @return
   * @since 2016. 6. 6.
   */
  public static Authentication getCurrentAuthentication() {
    return SecurityContextHolder.getContext().getAuthentication();
  }

  /**
   * @return
   * @since 2016. 6. 6.
   */
  private static Object getCurrentPrincipal() {
    Authentication authentication = SecurityContextHolder.getContext()
        .getAuthentication();

    if (null == authentication) {
      if (log.isDebugEnabled()) {
        log.debug("authentication is null.");
      }
      return null;
    } else {
      if (log.isDebugEnabled()) {
        log.debug(String.format("authentication : %s", authentication));
      }
      return authentication.getPrincipal();
    }
  }

  /**
   * 현재 세션의 유저 정보를 반환한다. 로그인을 하지 않은 경우에는 <code>null</code>.
   *
   * @return 현재 로그인 유저의 정보. 없으면 <code>null</code>.
   * @since 2016. 6. 6.
   */
  public static UserDetails getCurrentUserDetails() {
    Object principal = getCurrentPrincipal();
    if (null == principal) {
      return null;
    } else if (Conditions.assignable(principal, UserDetails.class)) {
      return (UserDetails) principal;
    } else {
      return null;
    }
  }

  /**
   * 현재 세션의 유저 정보를 반환한다. 로그인을 하지 않은 경우에는 <code>null</code>.
   *
   * @return 현재 로그인 유저의 정보. 없으면 <code>null</code>.
   * @since 2016. 6. 6.
   */
  @SuppressWarnings("unchecked")
  public static <D extends UserDetails> D getCurrentUserDetails(Class<D> details) {
    notNull(details, "details class.");

    Object principal = getCurrentPrincipal();
    if (null == principal) {
      if (log.isDebugEnabled()) {
        log.debug("principal is null.");
      }
      return null;
    } else if (Conditions.assignable(principal, details)) {
      return (D) principal;
    } else {
      if (log.isDebugEnabled()) {
        log.debug(String.format("unknown principal type : %s", principal));
      }
      return null;
    }
  }

  protected UserDetailsContext() {
    throw new UnsupportedOperationException();
  }
}
