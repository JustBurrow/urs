/**
 *
 */
package kr.lul.urs.core.domain.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import kr.lul.urs.application.configuration.JpaConfiguration;

/**
 * {@link JpaConfiguration} 테스트용 엔티티.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @see JpaConfiguration
 */
// @Entity(name = "Parent")
// @Table(name = "some_parent")
public class ParentEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, insertable = false, updatable = false)
  private int    id;
  @Column(name = "name", nullable = false)
  private String name;

  /**
   * @return the id
   */
  public int getId() {
    return this.id;
  }

  /**
   * @return the name
   */
  public String getName() {
    return this.name;
  }

  /**
   * @param name
   *          the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public int hashCode() {
    return this.id;
  }

  @Override
  public boolean equals(Object obj) {
    if (0 < this.id && null != obj && obj instanceof ParentEntity) {
      return this.id == ((ParentEntity) obj).id;
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    return new StringBuilder(ParentEntity.class.getSimpleName()).append("[id=").append(this.id).append(", name=")
        .append(this.name).append(']').toString();
  }
}
