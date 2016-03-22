/**
 *
 */
package kr.lul.urs.application.web.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.repository.OperatorRepository;
import kr.lul.urs.util.AssertionException;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 23.
 */
@Service
class OperatorDetailsServiceImpl implements OperatorDetailsService {
  @Autowired
  private OperatorRepository operatorRepository;

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>OperatorDetailsService
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Operator operator;
    try {
      operator = this.operatorRepository.findByEmail(username);
      if (null == operator) {
        throw new UsernameNotFoundException(String.format("operator does not exist for [%s].", username));
      }
    } catch (AssertionException e) {
      throw new UsernameNotFoundException(String.format("operator does not exist for [%s].", username), e);
    }

    User user = new User(operator.getEmail(), operator.getPassword(),
        operator.isEnabled(), operator.isNonExpired(),
        operator.isCredentialsNonExpired(), operator.isNonLocked(),
        Arrays.asList(new SimpleGrantedAuthority("ROLE_OPERATOR")));
    return user;
  }
}
