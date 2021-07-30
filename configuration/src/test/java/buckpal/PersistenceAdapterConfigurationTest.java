//: buckpal.PersistenceAdapterConfigurationTest.java

package buckpal;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


@Disabled
@Slf4j
@DisplayName("Test PersistenceAdapterConfiguration - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PersistenceAdapterConfigurationTest {

    private static ApplicationContext appContext;

    @BeforeAll
    static void beforeAll() {
        appContext = new AnnotationConfigApplicationContext(
                PersistenceAdapterConfiguration.class);
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    void testSimpleConfiguration() {
        for (String beanName : appContext.getBeanDefinitionNames()) {
            log.info(">>>>>>> Bean " + beanName);
        }
    }

}///:~