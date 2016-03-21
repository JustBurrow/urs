/**
 *
 */
package kr.lul.urs.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.lul.urs.core.service.internal.OperatorInternalService;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 21.
 */
@Service
class OperatorServiceImpl implements OperatorService {
  @Autowired
  private OperatorInternalService operatorInternalService;

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>UserDetailsService
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return null;
  }
}
