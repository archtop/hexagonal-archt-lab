//: com.archtop.adapter.out.persistence.PersistenceDiConfiguration.java

package com.archtop.adapter.out.persistence;


import com.archtop.application.port.out.LoadAccountPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class PersistenceDiConfiguration {

    @Bean
    LoadAccountPort loadAccountPort() {
        return new AccountPersistenceAdapter();
    }

}///:~