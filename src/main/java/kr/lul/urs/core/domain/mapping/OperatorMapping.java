/**
 *
 */
package kr.lul.urs.core.domain.mapping;

import org.springframework.security.crypto.password.PasswordEncoder;

import kr.lul.urs.core.domain.Operator;
import kr.lul.urs.spring.jpa.timestamp.UpdatableMapping.UpdatableEntity;
import kr.lul.urs.spring.jpa.timestamp.UpdatableMapping.UpdatableTable;

/**
 * {@link Operator}의 매핑 설정 정보.
 *
 * @author Just Burrow just.burrow@lul.kr
 * @since 2016. 3. 27.
 */
public abstract class OperatorMapping {
  /**
   * 엔티티 설정.
   *
   * @author Just Burrow just.burrow@lul.kr
   * @since 2016. 3. 27.
   */
  public abstract static class Entity extends UpdatableEntity {
    /**
     * 엔티티 이름.
     */
    public static final String ENTITY                  = "Operator";

    /**
     * 관리자 계정과 1:1 대응하는 E메일.
     */
    public static final String EMAIL                   = "email";
    /**
     * 암호화된 비밀번호.
     *
     * @see PasswordEncoder
     */
    public static final String PASSWORD                = "password";
    public static final String NON_EXPIRED             = "nonExpired";
    public static final String NON_LOCKED              = "nonLocked";
    public static final String CREDENTIALS_NON_EXPIRED = "credentialsNonExpired";
    public static final String ENABLED                 = "enabled";

    protected Entity() {
      throw new UnsupportedOperationException();
    }
  }

  /**
   * 테이블 설정.
   *
   * @author Just Burrow just.burrow@lul.kr
   * @since 2016. 3. 27.
   */
  public abstract static class Table extends UpdatableTable {
    /**
     * 테이블 이름.
     */
    public static final String TABLE                   = "op_operators";

    /**
     * @see Entity#ID
     */
    public static final String ID                      = Entity.ID;
    /**
     * @see Entity#EMAIL
     */
    public static final String EMAIL                   = Entity.EMAIL;
    /**
     * @see Entity#PASSWORD
     */
    public static final String PASSWORD                = Entity.PASSWORD;
    /**
     * @see Entity#NON_EXPIRED
     */
    public static final String NON_EXPIRED             = "non_expired";
    /**
     * @see Entity#NON_LOCKED
     */
    public static final String NON_LOCKED              = "non_locked";
    /**
     * @see Entity#CREDENTIALS_NON_EXPIRED
     */
    public static final String CREDENTIALS_NON_EXPIRED = "credentials_non_expired";
    /**
     * @see Entity#ENABLED
     */
    public static final String ENABLED                 = Entity.ENABLED;

    /**
     * 테이블의 인덱스 설정 정보.
     *
     * @author Just Burrow just.burrow@lul.kr
     * @since 2016. 3. 27.
     */
    public abstract static class INDEX {
      /**
       * 1개의 E메일 주소로는 하나의 계정만 만들 수 있다.
       */
      public static final String UQ_OPERATORS_EMAIL = "UQ_OPERATORS_EMAIL";

      protected INDEX() {
        throw new UnsupportedOperationException();
      }
    }

    protected Table() {
      throw new UnsupportedOperationException();
    }
  }

  protected OperatorMapping() {
    throw new UnsupportedOperationException();
  }
}
