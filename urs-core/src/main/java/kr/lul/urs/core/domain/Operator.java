/**
 *
 */
package kr.lul.urs.core.domain;

import org.springframework.security.core.userdetails.UserDetails;

import kr.lul.urs.spring.jpa.ownership.Owner;
import kr.lul.urs.spring.jpa.timestamp.Updatable;
import kr.lul.urs.util.ToSimpleString;

/**
 * 프로덕트 관리자.
 *
 * @author Just Burrow just.burrow@lul.kr
 */
public interface Operator extends Owner, Updatable, ToSimpleString {
  /**
   * 관리자 인증용 Email.
   *
   * @return 인증용 Email.
   */
  public String getEmail();

  /**
   * 관리자 인증용 비밀번호.
   *
   * @return 인증용 비밀번호.
   */
  public String getPassword();

  /**
   * 관리자 인증용 비밀번호를 변경한다.
   *
   * @param password
   *          새 비밀번호.
   */
  public void setPassword(String password);

  /**
   * @return
   * @see UserDetails#isAccountNonExpired()
   */
  public boolean isNonExpired();

  /**
   * @param nonExpired
   * @see UserDetails#isAccountNonExpired()
   */
  public void setNonExpired(boolean nonExpired);

  /**
   * @return
   * @see UserDetails#isAccountNonLocked()
   */
  public boolean isNonLocked();

  /**
   * @param nonLocked
   * @see UserDetails#isAccountNonLocked()
   */
  public void setNonLocked(boolean nonLocked);

  /**
   * @return
   * @see UserDetails#isCredentialsNonExpired()
   */
  public boolean isCredentialsNonExpired();

  /**
   * @param credentialsNonExpired
   * @see UserDetails#isCredentialsNonExpired()
   */
  public void setCredentialsNonExpired(boolean credentialsNonExpired);

  /**
   * @return
   * @see UserDetails#isEnabled()
   */
  public boolean isEnabled();

  /**
   * @param enabled
   * @see UserDetails#isEnabled()
   */
  public void setEnabled(boolean enabled);
}
