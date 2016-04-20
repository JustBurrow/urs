/**
 *
 */
package kr.lul.urs.core.domain.mapping;

import kr.lul.urs.spring.jpa.timestamp.CreatablMapping.CreatableEntity;
import kr.lul.urs.spring.jpa.timestamp.CreatablMapping.CreatableTable;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 18.
 */
public abstract class ResourceFileRevisionMapping {
  public static abstract class Entity extends CreatableEntity {
    public static final String ENTITY        = "ResourceFileRevision";

    public static final String ID            = "id";
    public static final String RESOURCE_FILE = "resourceFile";
    public static final String REVISION      = "revision";
    public static final String DESCRIPTION   = "description";
    public static final String SHA1          = "sha1";

    protected Entity() {
      throw new UnsupportedOperationException();
    }
  }

  public static abstract class Table extends CreatableTable {
    public static final String TABLE         = "op_resource_file_revision";

    public static final String RESOURCE_FILE = "resource_file";
    public static final String REVISION      = Entity.REVISION;
    public static final String DESCRIPTION   = Entity.DESCRIPTION;
    public static final String SHA1          = Entity.SHA1;

    public static abstract class FK {
      public static final String PK_RESOURCE_FILE = "FK_RESOURCE_FILE_REVISION_PK_RESOURCE_FILE";

      protected FK() {
        throw new UnsupportedOperationException();
      }
    }

    protected Table() {
      throw new UnsupportedOperationException();
    }
  }

  protected ResourceFileRevisionMapping() {
    throw new UnsupportedOperationException();
  }
}
