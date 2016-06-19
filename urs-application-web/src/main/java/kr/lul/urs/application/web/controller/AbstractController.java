/**
 *
 */
package kr.lul.urs.application.web.controller;

import kr.lul.urs.application.web.security.OperatorDetails;
import kr.lul.urs.spring.security.UserDetailsContext;

/**
 * 컨트롤러 공통 기능.
 *
 * @since 2016. 6. 6.
 * @author Just Burrow just.burrow@lul.kr
 */
abstract class AbstractController {
  /**
   * 현재 로그인한 운영자 정보.
   *
   * @return
   * @since 2016. 6. 6.
   */
  protected OperatorDetails currentOperator() {
    return UserDetailsContext.getCurrentUserDetails(OperatorDetails.class);
  }
}
