//: com.archtop.CycleChecks.java

package com.archtop;


import static  com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;


public class CycleChecks {

    @ArchTest
    public static final ArchRule CYCLE_CHECKS =
            slices()
                    .matching("com.archtop.(*)..")
                    .should()
                    .beFreeOfCycles();

}///:~