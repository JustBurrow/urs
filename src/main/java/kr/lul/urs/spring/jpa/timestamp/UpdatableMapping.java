/**
 *
 */
package kr.lul.urs.spring.jpa.timestamp;

import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * {@link AbstractUpdatable}의 설정정보.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 27.
 */
public abstract class UpdatableMapping {
  /**
   * 엔티티 타입의 설정 정보.
   *
   * @author Just Burrow just.burrow@lul.kr
   * @since 2016. 3. 27.
   * @see Timestamp
   * @see Timestamps
   */
  public abstract static class UpdatableEntity {
    /**
     * 엔티티의 ID.
     * 복합PK를 사용하는 경우, 필드와 1:1 매핑이 되지 않기 때문에, {@link UpdatableTable}에는 정의하지 않는다.
     *
     * @see Id
     */
    public static final String ID     = "id";
    /**
     * 데이터 생성 시각의 필드명.
     *
     * @see PrePersist
     */
    public static final String CREATE = "create";
    /**
     * 마지막 데이터 갱신 시각의 필드명.
     *
     * @see PrePersist
     * @see PreUpdate
     */
    public static final String UPDATE = "update";

    protected UpdatableEntity() {
      throw new UnsupportedOperationException();
    }
  }

  /**
   * 테이블의 설정 정보.
   *
   * @author Just Burrow just.burrow@lul.kr
   * @since 2016. 3. 27.
   */
  public abstract static class UpdatableTable {
    /**
     * 데이터 생성 시각을 저장할 컬럼명.
     *
     * @see PrePersist
     */
    public static final String CREATE_UTC = "create_utc";
    /**
     * 마지막 데이터 갱신 시각을 저장할 컬럼명.
     *
     * @see PrePersist
     * @see PreUpdate
     */
    public static final String UPDATE_UTC = "update_utc";

    protected UpdatableTable() {
      throw new UnsupportedOperationException();
    }
  }

  protected UpdatableMapping() {
    throw new UnsupportedOperationException();
  }
}
