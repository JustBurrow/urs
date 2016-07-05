/**
 *
 */
package kr.lul.urs.core.domain;

import org.springframework.security.core.userdetails.UserDetails;

import kr.lul.urs.spring.jpa.ownership.Owner;
import kr.lul.urs.spring.jpa.timestamp.Updatable;
import kr.lul.urs.util.ToSimpleString;

/**
 * 프로덕트 관리자로, 다음 기능에서 사용한다.
 * <ul>
 * <li>프로덕트를 비롯한 데이터를 등록, 사용할 수 있는 소유자.</li>
 * <li>URS 사용자를 관리하는 단위.</li>
 * <li>인증 및 데이터 접근 등 권한 확인의 단위.</li>
 * </ul>
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
