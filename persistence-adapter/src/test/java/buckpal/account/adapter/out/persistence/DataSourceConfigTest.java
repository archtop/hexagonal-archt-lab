//: buckpal.account.adapter.out.persistence.DataSourceConfigTest.java

package buckpal.account.adapter.out.persistence;


import buckpal.account.application.port.out.LoadAccountPort;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@DisplayName("Test Loading Properties - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DataSourceConfigTest {

    private static ApplicationContext appCtx;

    @BeforeAll
    static void beforeAll() {
        appCtx = new AnnotationConfigApplicationContext(DataSourceConfig.class);
        Arrays.stream(appCtx.getBeanDefinitionNames())
                .forEach(name -> log.info(">>>>>>> Bean: {}", name));
    }

    @ParameterizedTest
    @ValueSource(strings = {"loadAccountPort"})
    void able_To_Have_A_Bean_Of_Class_LoadAccountPort_With_A_Unique_Name(
            String beanName) {

        // Given

        // When
        LoadAccountPort loadAccountPort = appCtx.getBean(beanName,
                LoadAccountPort.class);

        // Then
        assertThat(loadAccountPort).isNotNull().isInstanceOf(
                LoadAccountAdapter.class);
    }

}///:~