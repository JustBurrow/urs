/**
 *
 */
package kr.lul.urs.core.domain.entity;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static kr.lul.urs.core.domain.mapping.ResourceFileMapping.Entity.ENTITY;
import static kr.lul.urs.core.domain.mapping.ResourceFileMapping.Table.CLIENT_PLATFORM;
import static kr.lul.urs.core.domain.mapping.ResourceFileMapping.Table.CURRENT_REVISION;
import static kr.lul.urs.core.domain.mapping.ResourceFileMapping.Table.ID;
import static kr.lul.urs.core.domain.mapping.ResourceFileMapping.Table.NAME;
import static kr.lul.urs.core.domain.mapping.ResourceFileMapping.Table.OWNER;
import static kr.lul.urs.core.domain.mapping.ResourceFileMapping.Table.TABLE;
import static kr.lul.urs.util.Asserts.assignable;
import static kr.lul.urs.util.Asserts.hasLength;
import static kr.lul.urs.util.Asserts.notNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

import kr.lul.urs.core.domain.ClientPlatform;
import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.core.domain.ResourceFile;
import kr.lul.urs.core.domain.ResourceFileRevision;
import kr.lul.urs.core.domain.ResourceFileUpdateException;
import kr.lul.urs.core.domain.mapping.ClientPlatformMapping;
import kr.lul.urs.core.domain.mapping.OperatorMapping;
import kr.lul.urs.core.domain.mapping.ResourceFileMapping.Table.FK;
import kr.lul.urs.core.domain.mapping.ResourceFileMapping.Table.INDEX;
import kr.lul.urs.core.domain.mapping.ResourceFileRevisionMapping;
import kr.lul.urs.spring.jpa.timestamp.AbstractUpdatable;
import kr.lul.urs.spring.jpa.timestamp.Timestamper;
import kr.lul.urs.util.OutputStreamFactory;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 5.
 */
@Entity(name = ENTITY)
@EntityListeners({ Timestamper.class })
@Table(name = TABLE,
    uniqueConstraints = {
        @UniqueConstraint(name = INDEX.UQ_RESOURCE_FILE, columnNames = { CLIENT_PLATFORM, NAME }) })
public class ResourceFileEntity extends AbstractUpdatable implements ResourceFile {
  private static OutputStreamFactory<OutputStream, ResourceFileRevision> OUTPUT_STREAM_FACTORY;

  /**
   * @param factory
   * @since 2016. 5. 11.
   */
  public static void setOutputStreamFactory(OutputStreamFactory<OutputStream, ResourceFileRevision> factory) {
    notNull(factory);
    if (null != OUTPUT_STREAM_FACTORY) {
      return;
    }
    OUTPUT_STREAM_FACTORY = factory;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = ID, nullable = false, insertable = false, updatable = false)
  private int                        id;
  @ManyToOne(targetEntity = OperatorEntity.class, fetch = FetchType.EAGER)
  @JoinColumn(name = OWNER,
      foreignKey = @ForeignKey(name = FK.PK_OPERATOR),
      referencedColumnName = OperatorMapping.Table.ID,
      nullable = false,
      updatable = false)
  private Operator                   owner;
  @ManyToOne(targetEntity = ClientPlatformEntity.class, fetch = FetchType.EAGER)
  @JoinColumn(name = CLIENT_PLATFORM,
      foreignKey = @ForeignKey(name = FK.PK_CLIENT_PLATFORM),
      referencedColumnName = ClientPlatformMapping.Table.ID,
      nullable = false,
      updatable = false)
  private ClientPlatform             clientPlatform;
  @Column(name = NAME, nullable = false, updatable = false)
  private String                     name;
  @Column(name = CURRENT_REVISION, nullable = false)
  private int                        revision;
  @OneToMany(targetEntity = ResourceFileRevisionEntity.class,
      cascade = { PERSIST, MERGE },
      mappedBy = ResourceFileRevisionMapping.Entity.RESOURCE_FILE)
  @OrderBy(ResourceFileRevisionMapping.Entity.REVISION + " ASC")
  private List<ResourceFileRevision> history;

  @Transient
  private ResourceFileRevision       currentRevision;

  public ResourceFileEntity() {
    this.history = new ArrayList<>();
  }

  public ResourceFileEntity(ClientPlatform clientPlatform, String name) {
    this();
    notNull(clientPlatform);
    assignable(clientPlatform, ClientPlatformEntity.class);
    hasLength(name);

    this.owner = clientPlatform.getOwner();
    this.clientPlatform = clientPlatform;
    this.name = name;
  }

  @PostLoad
  private void postLoad() {
    if (0 < this.revision) {
      this.currentRevision = this.history.get(this.revision - 1);
    }
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>ResourceFile
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
  public ClientPlatform getClientPlatform() {
    return this.clientPlatform;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public ResourceFileRevision getCurrentRevision() {
    return this.currentRevision;
  }

  @Override
  public ResourceFileRevision update(final File file) throws ResourceFileUpdateException {
    notNull(file);

    try {
      FileInputStream input = new FileInputStream(file);
      String sha1 = DigestUtils.sha1Hex(input);
      input.close();
      ResourceFileRevisionEntity revision = new ResourceFileRevisionEntity(this, this.revision + 1, sha1);

      input.close();
      this.history.add(revision);
      this.revision = revision.getRevision();
      this.currentRevision = revision;

      input = new FileInputStream(file);
      OutputStream output = OUTPUT_STREAM_FACTORY.getOutputStream(revision);
      IOUtils.copy(input, output);
      input.close();

      return revision;
    } catch (IOException e) {
      throw new ResourceFileUpdateException(this.id, this.currentRevision.getRevision(), file, e);
    }
  }

  @Override
  public int getCurrentRevisionNumber() {
    return this.revision;
  }

  @Override
  public List<ResourceFileRevision> getHistory() {
    return Collections.unmodifiableList(this.history);
  }

  @Override
  public List<ResourceFileRevision> getHistory(int fromRevision) {
    return Collections.unmodifiableList(this.history.subList(fromRevision - 1, this.history.size()));
  }

  @Override
  public List<ResourceFileRevision> getHistory(int from, int limit) {
    return Collections.unmodifiableList(this.history.subList(from, Math.min(from + limit, this.history.size())));
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
    } else if (0 < this.id && null != obj && obj instanceof ResourceFileEntity) {
      ResourceFileEntity that = (ResourceFileEntity) obj;
      return this.id == that.id && this.clientPlatform.equals(that.clientPlatform) && this.name.equals(that.name);
    } else {
      return false;
    }
  }
}
