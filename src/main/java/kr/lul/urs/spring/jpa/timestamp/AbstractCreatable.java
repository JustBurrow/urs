/**
 *
 */
package kr.lul.urs.spring.jpa.timestamp;

import static kr.lul.urs.spring.jpa.timestamp.CreatablMapping.CreatableTable.CREATE_UTC;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 5.
 */
@MappedSuperclass
public abstract class AbstractCreatable implements Creatable {
  @Column
  @Timestamp(name = CREATE_UTC, trigger = PrePersist.class)
  private Instant create;

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>Creatable
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public Instant getCreate() {
    return this.create;
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Object
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public String toString() {
    return String.format("create=%s", this.create);
  }
}
