//: com.archtop.config.ArchTopDiConfiguration.java

package com.archtop.config;


import com.archtop.adapter.in.web.SendMoneyController;
import com.archtop.application.port.in.SendMoneyUseCase;
import com.archtop.application.service.ServiceDiConfiguration;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@Import(ServiceDiConfiguration.class)
@AllArgsConstructor
public class ArchTopDiConfiguration {

    private final SendMoneyUseCase sendMoneyUseCase;

    @Bean
    SendMoneyController sendMoneyController() {
        return new SendMoneyController(sendMoneyUseCase);
    }

}///:~