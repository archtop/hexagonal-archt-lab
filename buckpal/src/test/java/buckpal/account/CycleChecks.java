//: buckpal.account.CycleChecks.java

package buckpal.account;


import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;


public class CycleChecks {

    @ArchTest
    public static final ArchRule CYCLE_CHECKS =
            slices()
                    .matching("com.archtop.(*)..")
                    .should()
                    .beFreeOfCycles();

}///:~