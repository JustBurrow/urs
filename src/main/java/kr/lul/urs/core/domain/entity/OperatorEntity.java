/**
 *
 */
package kr.lul.urs.core.domain.entity;

import static kr.lul.urs.util.Asserts.notNull;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.spring.jpa.annotation.Timestamp;
import kr.lul.urs.spring.jpa.annotation.Timestamps;
import kr.lul.urs.spring.jpa.listener.Timestamper;

/**
 * @author Just Burrow just.burrow@lul.kr
 */
@Entity(name = "Operator")
@Table(name = "op_operators",
    uniqueConstraints = { @UniqueConstraint(name = "UQ_OPERATORS_USERNAME", columnNames = { "email" }) })
@EntityListeners({ Timestamper.class })
@Timestamps({ @Timestamp(trigger = PrePersist.class, name = "create"),
    @Timestamp(trigger = PreUpdate.class, name = "update") })
public class OperatorEntity implements Operator {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, insertable = false, updatable = false)
  private int     id;
  @Column(name = "email", nullable = false, updatable = false)
  private String  email;
  @Column(name = "password", nullable = false)
  private String  password;
  @Column(name = "non_expired", nullable = false)
  private boolean nonExpired;
  @Column(name = "non_locked", nullable = false)
  private boolean nonLocked;
  @Column(name = "credentials_non_expired", nullable = false)
  private boolean credentialsNonExpired;
  @Column(name = "enabled", nullable = false)
  private boolean enabled;
  @Column(name = "create_utc", nullable = false, updatable = false)
  private Instant create;
  @Column(name = "update_utc", nullable = false)
  private Instant update;

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

  @Override
  public Instant getCreate() {
    return this.create;
  }

  @Override
  public Instant getUpdate() {
    return this.update;
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
        .append(", create=").append(this.create)
        .append(", update=").append(this.update)
        .append(']').toString();
  }
}
