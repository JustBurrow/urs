/**
 *
 */
package kr.lul.urs.core.domain.entity;

import static kr.lul.urs.core.domain.mapping.OperatorMapping.Entity.ENTITY;
import static kr.lul.urs.core.domain.mapping.OperatorMapping.Table.CREDENTIALS_NON_EXPIRED;
import static kr.lul.urs.core.domain.mapping.OperatorMapping.Table.EMAIL;
import static kr.lul.urs.core.domain.mapping.OperatorMapping.Table.ENABLED;
import static kr.lul.urs.core.domain.mapping.OperatorMapping.Table.ID;
import static kr.lul.urs.core.domain.mapping.OperatorMapping.Table.NON_EXPIRED;
import static kr.lul.urs.core.domain.mapping.OperatorMapping.Table.NON_LOCKED;
import static kr.lul.urs.core.domain.mapping.OperatorMapping.Table.PASSWORD;
import static kr.lul.urs.core.domain.mapping.OperatorMapping.Table.TABLE;
import static kr.lul.urs.util.Asserts.notNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.domain.mapping.OperatorMapping.Table.INDEX;
import kr.lul.urs.spring.jpa.timestamp.AbstractUpdatable;
import kr.lul.urs.spring.jpa.timestamp.Timestamper;

/**
 * @author Just Burrow just.burrow@lul.kr
 */
@Entity(name = ENTITY)
@Table(name = TABLE,
    uniqueConstraints = { @UniqueConstraint(name = INDEX.UQ_OPERATORS_EMAIL, columnNames = { EMAIL }) })
@EntityListeners({ Timestamper.class })
public class OperatorEntity extends AbstractUpdatable implements Operator {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = ID, nullable = false, insertable = false, updatable = false)
  private int     id;
  @Column(name = EMAIL, nullable = false, updatable = false)
  private String  email;
  @Column(name = PASSWORD, nullable = false)
  private String  password;
  @Column(name = NON_EXPIRED, nullable = false)
  private boolean nonExpired;
  @Column(name = NON_LOCKED, nullable = false)
  private boolean nonLocked;
  @Column(name = CREDENTIALS_NON_EXPIRED, nullable = false)
  private boolean credentialsNonExpired;
  @Column(name = ENABLED, nullable = false)
  private boolean enabled;

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  public OperatorEntity() {
  }

  public OperatorEntity(String email, String password) {
    this.setEmail(email);
    this.setPassword(password);
  }

  private void setEmail(String email) {
    notNull(email);
    this.email = email;
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>Operator
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public int getId() {
    return this.id;
  }

  @Override
  public String getEmail() {
    return this.email;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public void setPassword(String password) {
    notNull(password);
    this.password = password;
  }

  @Override
  public boolean isNonExpired() {
    return this.nonExpired;
  }

  @Override
  public void setNonExpired(boolean nonExpired) {
    this.nonExpired = nonExpired;
  }

  @Override
  public boolean isNonLocked() {
    return this.nonLocked;
  }

  @Override
  public void setNonLocked(boolean nonLocked) {
    this.nonLocked = nonLocked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return this.credentialsNonExpired;
  }

  @Override
  public void setCredentialsNonExpired(boolean credentialsNonExpired) {
    this.credentialsNonExpired = credentialsNonExpired;
  }

  @Override
  public boolean isEnabled() {
    return this.enabled;
  }

  @Override
  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Object
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public int hashCode() {
    return this.id;
  }

  @Override
  public boolean equals(Object obj) {
    if (0 < this.id && null != this.email && null != obj && obj instanceof OperatorEntity) {
      OperatorEntity that = (OperatorEntity) obj;
      return this.id == that.id && this.email.equals(that.getEmail());
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    return new StringBuilder(OperatorEntity.class.getSimpleName())
        .append("[id=").append(this.id)
        .append(", email=").append(this.email).append(", password=******, nonExpired=").append(this.nonExpired)
        .append(", nonLocked=").append(this.nonLocked)
        .append(", credentialsNonExpired=").append(this.credentialsNonExpired)
        .append(", enabled=").append(this.enabled)
        .append(", ").append(super.toString())
        .append(']').toString();
  }
}
