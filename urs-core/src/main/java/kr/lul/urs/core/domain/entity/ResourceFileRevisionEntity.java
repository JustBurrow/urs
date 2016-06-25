/**
 *
 */
package kr.lul.urs.core.domain.entity;

import static kr.lul.urs.core.domain.mapping.ResourceFileRevisionMapping.Entity.ENTITY;
import static kr.lul.urs.core.domain.mapping.ResourceFileRevisionMapping.Table.DESCRIPTION;
import static kr.lul.urs.core.domain.mapping.ResourceFileRevisionMapping.Table.RESOURCE_FILE;
import static kr.lul.urs.core.domain.mapping.ResourceFileRevisionMapping.Table.REVISION;
import static kr.lul.urs.core.domain.mapping.ResourceFileRevisionMapping.Table.SHA1;
import static kr.lul.urs.core.domain.mapping.ResourceFileRevisionMapping.Table.TABLE;
import static kr.lul.urs.util.Asserts.assignable;
import static kr.lul.urs.util.Asserts.hasLength;
import static kr.lul.urs.util.Asserts.notNull;
import static kr.lul.urs.util.Asserts.positive;

import java.io.InputStream;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import kr.lul.urs.core.domain.AgentPlatform;
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.domain.ResourceFile;
import kr.lul.urs.core.domain.ResourceFileRevision;
import kr.lul.urs.core.domain.mapping.ResourceFileMapping;
import kr.lul.urs.core.domain.mapping.ResourceFileRevisionMapping;
import kr.lul.urs.core.domain.mapping.ResourceFileRevisionMapping.Table.FK;
import kr.lul.urs.spring.jpa.timestamp.AbstractCreatable;
import kr.lul.urs.spring.jpa.timestamp.Timestamper;
import kr.lul.urs.util.InputStreamSupplier;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 16.
 */
@Entity(name = ENTITY)
@EntityListeners({ Timestamper.class })
@Table(name = TABLE)
public class ResourceFileRevisionEntity extends AbstractCreatable implements ResourceFileRevision {
  @Embeddable
  public static class ResourceFileRevisionId implements ResourceFileRevision.Identifier {
    private static final long serialVersionUID = EntityConstants.SERIAL_VERSION_UID;

    @Column(name = RESOURCE_FILE, nullable = false, updatable = false)
    private int               resourceFile;
    @Column(name = REVISION, nullable = false, updatable = false)
    private int               revision;

    public ResourceFileRevisionId() {
    }

    public ResourceFileRevisionId(int resourceFile, int revision) {
      positive(resourceFile);
      positive(revision);

      this.resourceFile = resourceFile;
      this.revision = revision;
    }

    public ResourceFileRevisionId(ResourceFile resourceFile, int revision) {
      notNull(resourceFile);
      assignable(resourceFile, ResourceFileEntity.class);
      positive(revision);

      this.resourceFile = resourceFile.getId();
      this.revision = revision;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // <I>ResourceFileRevision.Identifier
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public int resourceFile() {
      return this.resourceFile;
    }

    @Override
    public int revision() {
      return this.revision;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Object
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public int hashCode() {
      return this.resourceFile ^ this.revision;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if (0 < this.resourceFile && 0 < this.revision && null != obj && obj instanceof ResourceFileRevisionId) {
        ResourceFileRevisionId that = (ResourceFileRevisionId) obj;
        return this.resourceFile == that.resourceFile && this.revision == that.revision;
      } else {
        return false;
      }
    }

    @Override
    public String toString() {
      return String.format("(%d, %d)", this.resourceFile, this.revision);
    }
  }

  @EmbeddedId
  private ResourceFileRevisionId id;
  @MapsId(ResourceFileRevisionMapping.Entity.RESOURCE_FILE)
  @ManyToOne(targetEntity = ResourceFileEntity.class, fetch = FetchType.EAGER)
  @JoinColumn(name = RESOURCE_FILE,
      foreignKey = @ForeignKey(name = FK.PK_RESOURCE_FILE),
      referencedColumnName = ResourceFileMapping.Table.ID,
      nullable = false,
      updatable = false)
  private ResourceFile           resourceFile;
  @Column(name = REVISION, nullable = false, insertable = false, updatable = false)
  private int                    revision;
  @Column(name = DESCRIPTION)
  private String                 description;
  @Column(name = SHA1, nullable = false, updatable = false)
  private String                 sha1;

  /**
   * JPA용 생성자.
   */
  public ResourceFileRevisionEntity() {
  }

  /**
   * 엔티티 생성 로직용 생성자.
   * 새로운 {@link ResourceFileRevision}의 생성은 {@link ResourceFile}이 책임진다.
   * 따라서 다른 코드에서의 접근을 막기 위해 기본 접근자를 사용한다.
   *
   * @param resourceFile
   * @param revision
   *          생성할 {@link ResourceFileRevision}의 리비전. 1-based.
   * @param sha1
   *          현재 리비전의 SHA1 해시의 HEX값.
   */
  ResourceFileRevisionEntity(ResourceFile resourceFile, int revision, String sha1) {
    hasLength(sha1);
    this.id = new ResourceFileRevisionId(resourceFile, revision);
    this.resourceFile = resourceFile;
    this.revision = revision;
    this.sha1 = sha1;
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>ResourceFileRevision
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public Identifier getId() {
    return this.id;
  }

  @Override
  public Operator getOwner() {
    return this.resourceFile.getOwner();
  }

  @Override
  public ResourceFile getResourceFile() {
    return this.resourceFile;
  }

  @Override
  public AgentPlatform getAgentPlatform() {
    return this.resourceFile.getAgentPlatform();
  }

  @Override
  public String getName() {
    return this.resourceFile.getName();
  }

  @Override
  public int getRevision() {
    return this.revision;
  }

  @Override
  public String getSha1() {
    return this.sha1;
  }

  @Override
  public InputStreamSupplier<InputStream> getBinary() {
    // TODO Auto-generated method stub
    return null;
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Object
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public int hashCode() {
    return null == this.id ? 0 : this.id.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    } else if (null != this.id && null != obj && obj instanceof ResourceFileRevisionEntity) {
      return this.id.equals(((ResourceFileRevisionEntity) obj).id);
    } else {
      return false;
    }
  }
}
