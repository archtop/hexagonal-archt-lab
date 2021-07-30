//: buckpal.account.adapter.in.web.WebConfigTest.java

package buckpal.account.adapter.in.web;


import buckpal.account.adapter.in.web.bean.DependentBean;
import buckpal.account.adapter.in.web.bean.SimpleBean;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@DisplayName("Test WebConfigTest - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class WebConfigTest {

    private static ApplicationContext appContext;

    @BeforeAll
    static void beforeAll() {
        appContext = new AnnotationConfigApplicationContext(WebConfig.class);
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

    @Test
    void able_To_Create_Singleton_Bean_Instance_By_Default() {

        // Given
        SimpleBean simpleBean_1 = appContext.getBean(SimpleBean.class);
        SimpleBean simpleBean_2 = appContext.getBean(SimpleBean.class);

        // When & Then
        assertThat(simpleBean_1).isSameAs(simpleBean_2);
    }

    @Test
    void the_Bean_Annotated_Method_Is_Only_Called_Once() {

        // Given
        SimpleBean simpleBean = appContext.getBean(SimpleBean.class);

        // When
        DependentBean dependentBean = appContext.getBean(DependentBean.class);

        // Then
        assertThat(dependentBean.getSimpleBean()).isSameAs(simpleBean);
    }

}///:~