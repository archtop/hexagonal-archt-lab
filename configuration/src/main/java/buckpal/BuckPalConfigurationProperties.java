//: buckpal.BuckPalConfigurationProperties.java

package buckpal;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@Component
@ConfigurationProperties(prefix = "buckpal")
public class BuckPalConfigurationProperties {

    private long transferThreshold = Long.MAX_VALUE;

}///:~