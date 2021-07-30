//: buckpal.account.adapter.in.web.AutoWebConfigTest.java

package buckpal.account.adapter.in.web;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


@Slf4j
@DisplayName("Test AutoWebConfig - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AutoWebConfigTest {

    private static ApplicationContext appContext;

    @BeforeAll
    static void beforeAll() {
        appContext = new AnnotationConfigApplicationContext(AutoWebConfig.class);
    }

    @Test
    void testSimpleConfiguration() {
        for (String beanName : appContext.getBeanDefinitionNames()) {
            log.info(">>>>>>> Bean " + beanName);
        }
    }

}///:~