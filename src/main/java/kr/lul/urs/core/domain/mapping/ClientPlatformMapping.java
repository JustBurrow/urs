/**
 *
 */
package kr.lul.urs.core.domain.mapping;

import kr.lul.urs.spring.jpa.timestamp.UpdatableMapping.UpdatableEntity;
import kr.lul.urs.spring.jpa.timestamp.UpdatableMapping.UpdatableTable;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 5.
 */
public abstract class ClientPlatformMapping {
  public static abstract class Entity extends UpdatableEntity {
    public static final String ENTITY      = "ClientPlatform";

    public static final String ID          = "id";
    public static final String OWNER       = "owner";
    public static final String CODE        = "code";
    public static final String LABEL       = "label";
    public static final String DESCRIPTION = "description";

    protected Entity() {
      throw new UnsupportedOperationException();
    }
  }

  public static abstract class Table extends UpdatableTable {
    public static final String TABLE       = "op_client_platform";

    public static final String ID          = Entity.ID;
    public static final String OWNER       = Entity.OWNER;
    public static final String CODE        = Entity.CODE;
    public static final String LABEL       = Entity.LABEL;
    public static final String DESCRIPTION = Entity.DESCRIPTION;

    public static abstract class INDEX {
      public static final String UQ_CLIENT_PLATFORM_CODE = "UQ_CLIENT_PLATFORM_CODE";

      protected INDEX() {
        throw new UnsupportedOperationException();
      }
    }

    public static abstract class FK {
      public static final String PK_OPERATOR = "FK_CLIENT_PLATFORM_PK_OPERATOR";

      protected FK() {
        throw new UnsupportedOperationException();
      }
    }

    protected Table() {
      throw new UnsupportedOperationException();
    }
  }

  protected ClientPlatformMapping() {
    throw new UnsupportedOperationException();
  }
}
