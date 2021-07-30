//: buckpal.account.adapter.in.web.AutoWebConfig.java

package buckpal.account.adapter.in.web;


import buckpal.account.application.port.in.SendMoneyUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = {"buckpal.account.adapter.in.web"})
public class AutoWebConfig {

    @Bean
    SendMoneyUseCase sendMoneyUseCase() {
        return command -> true;
    }

}///:~