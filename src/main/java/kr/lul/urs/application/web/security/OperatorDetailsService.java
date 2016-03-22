/**
 *
 */
package kr.lul.urs.application.web.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import kr.lul.urs.application.configuration.InjectionConstants.Beans;

/**
 * Spring Security에 프로덕트 관리자의 인증정보를 제공하는 컴포넌트.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 23.
 */
@Transactional(transactionManager = Beans.NAME_TRANSACTION_MANAGER)
public interface OperatorDetailsService extends UserDetailsService {
}
