//: com.archtop.application.service.ServiceDiConfiguration.java

package com.archtop.application.service;


import com.archtop.adapter.out.persistence.PersistenceDiConfiguration;
import com.archtop.application.port.in.SendMoneyUseCase;
import com.archtop.application.port.out.LoadAccountPort;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@Import(PersistenceDiConfiguration.class)
@AllArgsConstructor
public class ServiceDiConfiguration {

    private final LoadAccountPort loadAccountPort;

    @Bean
    SendMoneyUseCase sendMoneyUseCase() {
        return new SendMoneyService(loadAccountPort);
    }

}///:~