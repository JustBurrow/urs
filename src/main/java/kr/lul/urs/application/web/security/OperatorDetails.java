/**
 *
 */
package kr.lul.urs.application.web.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * @since 2016. 6. 6.
 * @author Just Burrow just.burrow@lul.kr
 */
public class OperatorDetails extends User {
  private static final long serialVersionUID = -121458043724580241L;

  private int               id;

  public OperatorDetails(int id, String username, String password, boolean enabled, boolean accountNonExpired,
      boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
    super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

    this.id = id;
  }

  /**
   * @return the id
   */
  public int getId() {
    return this.id;
  }
}
