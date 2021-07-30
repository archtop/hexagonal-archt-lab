//: buckpal.account.adapter.in.web.WebConfig.java

package buckpal.account.adapter.in.web;


import buckpal.account.adapter.in.web.bean.DependentBean;
import buckpal.account.adapter.in.web.bean.DependentBeanImpl;
import buckpal.account.adapter.in.web.bean.SimpleBean;
import buckpal.account.adapter.in.web.bean.SimpleBeanImpl;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration
public class WebConfig {

    @Bean
    SimpleBean simpleBean() {
        log.info(">>>>>>> Creating an instance of SimpleBean interface");
        return new SimpleBeanImpl();
    }

    @Bean
    DependentBean dependentBean(@NonNull SimpleBean simpleBean) {
        return new DependentBeanImpl(simpleBean);
    }

}///:~