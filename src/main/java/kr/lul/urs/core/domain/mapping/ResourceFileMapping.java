/**
 *
 */
package kr.lul.urs.core.domain.mapping;

import kr.lul.urs.core.domain.ResourceFile;
import kr.lul.urs.spring.jpa.timestamp.UpdatableMapping.UpdatableEntity;
import kr.lul.urs.spring.jpa.timestamp.UpdatableMapping.UpdatableTable;

/**
 * {@link ResourceFile}의 JPA 엔티티 매핑 정보.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 5.
 */
public abstract class ResourceFileMapping {
  public static abstract class Entity extends UpdatableEntity {
    public static final String ENTITY           = "ResourceFile";

    public static final String ID               = "id";
    public static final String OWNER            = "owner";
    public static final String CLIENT_PLATFORM  = "clientPlatform";
    public static final String NAME             = "name";
    public static final String CURRENT_REVISION = "currentRevision";

    protected Entity() {
      throw new UnsupportedOperationException();
    }
  }

  public static abstract class Table extends UpdatableTable {
    public static final String TABLE            = "op_resource_file";

    public static final String ID               = Entity.ID;
    public static final String OWNER            = Entity.OWNER;
    public static final String CLIENT_PLATFORM  = "client_platform";
    public static final String NAME             = Entity.NAME;
    public static final String CURRENT_REVISION = "current_revision";

    public static abstract class FK {
      public static final String PK_OPERATOR        = "FK_RESOURCE_FILE_PK_OPERATOR";
      public static final String PK_CLIENT_PLATFORM = "FK_RESOURCE_FILE_PK_CLIENT_PLATFORM";

      protected FK() {
        throw new UnsupportedOperationException();
      }
    }

    public static abstract class INDEX {
      public static final String PK_OPERATOR         = FK.PK_OPERATOR;
      public static final String PK_OPERATOR_COLUMNS = OWNER + " ASC";

      /**
       * @see Table#CLIENT_PLATFORM
       * @see Table#NAME
       */
      public static final String UQ_RESOURCE_FILE    = "UQ_RESOURCE_FILE";

      protected INDEX() {
        throw new UnsupportedOperationException();
      }
    }

    protected Table() {
      throw new UnsupportedOperationException();
    }
  }

  protected ResourceFileMapping() {
    throw new UnsupportedOperationException();
  }
}
