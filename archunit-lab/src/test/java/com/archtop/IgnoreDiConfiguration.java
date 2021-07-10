//: com.archtop.IgnoreDiConfiguration.java

package com.archtop;


import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.core.importer.Location;


public class IgnoreDiConfiguration implements ImportOption {

    @Override
    public boolean includes(Location location) {
        return !location.contains("Configuration");
    }

}///:~