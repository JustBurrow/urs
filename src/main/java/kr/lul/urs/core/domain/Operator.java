/**
 *
 */
package kr.lul.urs.core.domain;

import kr.lul.urs.spring.jpa.timestamp.Updatable;

/**
 * 프로덕트 관리자.
 *
 * @author Just Burrow just.burrow@lul.kr
 */
public interface Operator extends Updatable {
  /**
   * 관리자 계정 ID.
   *
   * @return ID.
   */
  public int getId();

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

  public boolean isNonExpired();

  public void setNonExpired(boolean nonExpired);

  public boolean isNonLocked();

  public void setNonLocked(boolean nonLocked);

  public boolean isCredentialsNonExpired();

  public void setCredentialsNonExpired(boolean credentialsNonExpired);

  public boolean isEnabled();

  public void setEnabled(boolean enabled);
}
