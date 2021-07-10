//: buckpal.account.IgnoreDiConfigurationRule.java

package buckpal.account;


import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.core.importer.Location;


public class IgnoreDiConfigurationRule implements ImportOption {

    @Override
    public boolean includes(Location location) {
        return !location.contains("Configuration");
    }

}///:~