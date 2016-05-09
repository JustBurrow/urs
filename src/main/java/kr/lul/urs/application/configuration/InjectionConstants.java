package kr.lul.urs.application.configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.transaction.TransactionManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Springframework의 의존성 주입 관련된 상수.
 *
 * @author JustBurrow just.burrow@lul.kr
 */
public abstract class InjectionConstants {
  /**
   * {@link Value} 주입과 관련된 값.
   *
   * @author Just Burrow just.burrow@lul.kr
   */
  public static abstract class Properties {
    public static final String PREFIX_CORE            = "kr.lul.urs";
    /**
     * DAO가 도메인 엔티티를 저장할 때, 어떤 메서드를 사용할지 결정한다.
     *
     * @see JpaRepository#save(Object) <code>false</code>일 때.
     * @see JpaRepository#saveAndFlush(Object) <code>true</code>일 때.
     */
    public static final String KEY_DAO_SAVE_AND_FLUSH = PREFIX_CORE + ".dao.saveAndFlush";

    public static final String PREFIX_DATASOURCE      = "spring.datasource";
    public static final String PREFIX_JPA             = "spring.jpa";
    public static final String PREFIX_JPA_PROPERTIES  = PREFIX_JPA + ".properties";

    public static final String KEY_SHOW_SQL           = PREFIX_JPA + ".show-sql";
    public static final String KEY_GENERATE_DDL       = PREFIX_JPA + ".generate-ddl";

    protected Properties() {
      throw new UnsupportedOperationException();
    }
  }

  /**
   * {@link Component}의 주입과 관련된 값.
   *
   * @author JustBurrow just.burrow@lul.kr
   */
  public static abstract class Beans {
    /**
     * @see DataSource
     */
    public static final String NAME_DATA_SOURCE            = "dataSource";

    /**
     * @see EntityManagerFactory
     */
    public static final String NAME_ENTITY_MANAGER_FACTORY = "entityManagerFactory";

    /**
     * @see TransactionManager
     */
    public static final String NAME_TRANSACTION_MANAGER    = "transactionManager";

    public static final String NAME_DEBUGGING_SUPPORT      = "debuggingSupport";

    protected Beans() {
      throw new UnsupportedOperationException();
    }
  }

  protected InjectionConstants() {
    throw new UnsupportedOperationException();
  }
}
