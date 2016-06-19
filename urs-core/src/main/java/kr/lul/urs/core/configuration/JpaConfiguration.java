package kr.lul.urs.core.configuration;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toMap;
import static kr.lul.urs.core.configuration.InjectionConstants.Beans.NAME_DATA_SOURCE;
import static kr.lul.urs.core.configuration.InjectionConstants.Beans.NAME_ENTITY_MANAGER_FACTORY;
import static kr.lul.urs.core.configuration.InjectionConstants.Beans.NAME_TRANSACTION_MANAGER;
import static kr.lul.urs.core.configuration.InjectionConstants.Properties.KEY_GENERATE_DDL;
import static kr.lul.urs.core.configuration.InjectionConstants.Properties.KEY_SHOW_SQL;
import static kr.lul.urs.core.configuration.InjectionConstants.Properties.PREFIX_DATASOURCE;
import static kr.lul.urs.core.configuration.InjectionConstants.Properties.PREFIX_JPA_PROPERTIES;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import kr.lul.urs.core.domain.entity.EntityAnchor;
import kr.lul.urs.core.repository.RepositoryAnchor;
import kr.lul.urs.spring.jpa.time.JpaTimeSupportAnchor;

/**
 * Spring Data JPA의 DB 연동에 필요한 설정을 제공한다.
 *
 * @author justburrow
 */
@Configuration
@EnableJpaRepositories(basePackageClasses = { RepositoryAnchor.class },
    entityManagerFactoryRef = NAME_ENTITY_MANAGER_FACTORY,
    transactionManagerRef = NAME_TRANSACTION_MANAGER)
@EnableTransactionManagement
public class JpaConfiguration {
  @Value("${" + KEY_GENERATE_DDL + "}")
  private boolean     generateDdl;
  @Value("${" + KEY_SHOW_SQL + "}")
  private boolean     showSql;

  @Autowired
  private Environment env;

  /**
   * Spring Data JPA가 스캔해야 할 패키지의 목록을 제공한다.
   *
   * @return
   */
  private String[] getPackagesToScan() {
    return new String[] { EntityAnchor.PACKAGE_NAME, JpaTimeSupportAnchor.PACKAGE_NAME };
  }

  /**
   * JPA 구현체용 설정 정보를 제공한다.
   *
   * @return JPA 구현체용 설정.
   */
  private Map<String, Object> jpaProperties() {
    final String prefix = PREFIX_JPA_PROPERTIES + ".";
    return asList("hibernate.cache.use_second_level_cache",
        "hibernate.cache.use_query_cache",
        "hibernate.cache.region.factory_class",
        "hibernate.generateStatistics").stream()
            .collect(toMap(String::toString, k -> this.env.getProperty(prefix + k)));
  }

  @Bean(name = NAME_DATA_SOURCE)
  @ConfigurationProperties(prefix = PREFIX_DATASOURCE)
  public DataSource dataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name = NAME_ENTITY_MANAGER_FACTORY)
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
    adapter.setShowSql(this.showSql);
    adapter.setGenerateDdl(this.generateDdl);
    adapter.setDatabase(Database.MYSQL);

    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setDataSource(this.dataSource());
    factory.setJpaVendorAdapter(adapter);
    factory.setPackagesToScan(this.getPackagesToScan());
    factory.setJpaPropertyMap(this.jpaProperties());

    return factory;
  }

  @Bean(name = NAME_TRANSACTION_MANAGER)
  public PlatformTransactionManager transactionManager() {
    return new JpaTransactionManager(this.entityManagerFactory().getObject());
  }

  @Bean
  public HibernateExceptionTranslator hibernateExceptionTranslator() {
    return new HibernateExceptionTranslator();
  }
}
