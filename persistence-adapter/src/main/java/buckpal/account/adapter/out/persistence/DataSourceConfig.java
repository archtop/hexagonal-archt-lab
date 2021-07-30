//: buckpal.account.adapter.in.web.DataSourceConfig.java

package buckpal.account.adapter.out.persistence;


import buckpal.account.application.port.out.LoadAccountPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.SQLException;


// https://www.baeldung.com/the-persistence-layer-with-spring-and-jpa
@Slf4j
@Configuration
@RequiredArgsConstructor
@PropertySource("classpath:db/datasource.properties")
@EnableTransactionManagement
public class DataSourceConfig {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    private final Environment environment;

    @Bean
    static PropertySourcesPlaceholderConfigurer
    propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory()
            throws SQLException {

        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] { "buckpal.account.adapter.out.persistence" });

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        // em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean
    EntityManager entityManager() throws SQLException {
        return entityManagerFactory().getNativeEntityManagerFactory()
                .createEntityManager();
    }

    @Bean
    PlatformTransactionManager transactionManager() throws SQLException {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    @Bean
    PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    DataSource dataSource() throws SQLException {

        DriverManagerDataSource driverManagerDataSource =
                new DriverManagerDataSource();

        driverManagerDataSource.setDriverClassName(this.driverClassName);
        driverManagerDataSource.setUrl(this.url);
        driverManagerDataSource.setUsername(this.username);
        driverManagerDataSource.setPassword(this.password);

        log.info(">>>>>> Access pw with Environment: {}",
                this.environment.getProperty("spring.datasource.password"));

        return driverManagerDataSource;
    }

    @Bean
    JpaRepositoryFactory jpaRepositoryFactory() throws SQLException {
        return new JpaRepositoryFactory(entityManager());
    }

    @Bean
    AccountRepository accountRepository() throws SQLException {
        return jpaRepositoryFactory().getRepository(AccountRepository.class);
    }

    @Bean
    ActivityRepository activityRepository() throws SQLException {
        return jpaRepositoryFactory().getRepository(ActivityRepository.class);
    }

    @Bean
    LoadAccountPort loadAccountPort() throws SQLException {
        return new LoadAccountAdapter(
                accountRepository(),
                activityRepository(),
                new AccountMapper());
    }

    @Bean
    AccountMapper accountMapper() {
        return new AccountMapper();
    }

}///:~