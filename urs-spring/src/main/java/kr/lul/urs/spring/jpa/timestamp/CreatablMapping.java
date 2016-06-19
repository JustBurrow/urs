/**
 *
 */
package kr.lul.urs.spring.jpa.timestamp;

/**
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 4. 5.
 */
public abstract class CreatablMapping {
  public static abstract class CreatableEntity {
    public static final String CREATE = "create";

    protected CreatableEntity() {
      throw new UnsupportedOperationException();
    }
  }

  public static abstract class CreatableTable {
    public static final String CREATE_UTC = "create_utc";

    protected CreatableTable() {
      throw new UnsupportedOperationException();
    }
  }

  protected CreatablMapping() {
    throw new UnsupportedOperationException();
  }
}
