//: buckpal.PersistenceAdapterConfiguration.java

package buckpal;


import buckpal.account.adapter.out.persistence.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;


@Configuration
//@EnableConfigurationProperties
//@ConfigurationProperties("classpath")
@EnableJpaRepositories(basePackages = "buckpal.account.adapter.out.persistence")
@ComponentScan(basePackages = {"buckpal.account.adapter.out.persistence"})
public class PersistenceAdapterConfiguration {

    @Bean
    public LocalSessionFactoryBean entityManagerFactory() {
        return new LocalSessionFactoryBean();
    }

//    @Bean
//    LoadAccountAdapter loadAccountAdapter(
//            AccountRepository accountRepository,
//            ActivityRepository activityRepository,
//            AccountMapper accountMapper) {
//
//        return new LoadAccountAdapter(
//                accountRepository,
//                activityRepository,
//                accountMapper);
//    }
//
//    @Bean
//    UpdateAccountStateAdapter updateAccountStateAdapter(
//            AccountMapper accountMapper, ActivityRepository activityRepository) {
//
//        return new UpdateAccountStateAdapter(accountMapper, activityRepository);
//    }
//
//    @Bean
//    AccountMapper accountMapper(){
//        return new AccountMapper();
//    }

}///:~