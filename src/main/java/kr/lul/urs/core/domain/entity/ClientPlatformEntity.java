/**
 *
 */
package kr.lul.urs.core.domain.entity;

import static kr.lul.urs.core.domain.mapping.ClientPlatformMapping.Entity.ENTITY;
import static kr.lul.urs.core.domain.mapping.ClientPlatformMapping.Table.CODE;
import static kr.lul.urs.core.domain.mapping.ClientPlatformMapping.Table.DESCRIPTION;
import static kr.lul.urs.core.domain.mapping.ClientPlatformMapping.Table.ID;
import static kr.lul.urs.core.domain.mapping.ClientPlatformMapping.Table.LABEL;
import static kr.lul.urs.core.domain.mapping.ClientPlatformMapping.Table.OWNER;
import static kr.lul.urs.core.domain.mapping.ClientPlatformMapping.Table.TABLE;
import static kr.lul.urs.util.Asserts.assignable;
import static kr.lul.urs.util.Asserts.gt;
import static kr.lul.urs.util.Asserts.hasLength;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import kr.lul.urs.core.domain.ClientPlatform;
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.domain.mapping.ClientPlatformMapping.Table.FK;
import kr.lul.urs.core.domain.mapping.ClientPlatformMapping.Table.INDEX;
import kr.lul.urs.core.domain.mapping.OperatorMapping;
import kr.lul.urs.spring.jpa.timestamp.AbstractUpdatable;
import kr.lul.urs.spring.jpa.timestamp.Timestamper;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 5.
 */
@Entity(name = ENTITY)
@EntityListeners({ Timestamper.class })
@Table(name = TABLE,
    uniqueConstraints = { @UniqueConstraint(name = INDEX.UQ_CLIENT_PLATFORM_CODE, columnNames = { OWNER, CODE }) })
public class ClientPlatformEntity extends AbstractUpdatable implements ClientPlatform {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = ID, nullable = false, insertable = false, updatable = false)
  private int            id;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = OWNER,
      foreignKey = @ForeignKey(name = FK.PK_OPERATOR),
      referencedColumnName = OperatorMapping.Table.ID,
      nullable = false,
      updatable = false)
  private OperatorEntity owner;
  @Column(name = CODE, nullable = false, updatable = false)
  private String         code;
  @Column(name = LABEL, nullable = false)
  private String         label;
  @Column(name = DESCRIPTION, nullable = false)
  private String         description;

  public ClientPlatformEntity() {
  }

  public ClientPlatformEntity(Operator owner, String code) {
    assignable(owner, OperatorEntity.class);
    gt(owner.getId(), 0);
    hasLength(code);

    this.owner = (OperatorEntity) owner;
    this.code = code;
  }

  public ClientPlatformEntity(Operator owner, String code, String label) {
    this(owner, code);
    this.setLabel(label);
  }

  public ClientPlatformEntity(Operator owner, String code, String label, String description) {
    this(owner, code, label);
    this.setDescription(description);
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>ClientPlatform
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public int getId() {
    return this.id;
  }

  @Override
  public Operator getOwner() {
    return this.owner;
  }

  @Override
  public String getCode() {
    return this.code;
  }

  @Override
  public String getLabel() {
    return this.label;
  }

  @Override
  public void setLabel(String label) {
    hasLength(label);
    this.label = label;
  }

  @Override
  public String getDescription() {
    return this.description;
  }

  @Override
  public void setDescription(String description) {
    if (null == description) {
      this.description = "";
    } else {
      this.description = description;
    }
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
    if (this == obj) {
      return true;
    } else if (0 < this.id && null != obj && obj instanceof ClientPlatformEntity) {
      return this.id == ((ClientPlatformEntity) obj).id;
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(ClientPlatformEntity.class.getSimpleName())
        .append("[id=").append(this.id)
        .append(", owner=").append(this.owner.toSimpleString())
        .append(", code=").append(this.code)
        .append(", label=").append(this.label)
        .append(", description=");
    if (13 <= this.description.length()) {
      sb.append(this.description.substring(0, this.description.length() - 3)).append("...");
    } else {
      sb.append(this.description);
    }
    sb.append(", ").append(super.toString()).append(']');
    return sb.toString();
  }
}
