//: buckpal.account.DependencyRules.java

package buckpal.account;


import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;


public class DependencyRules {

    @ArchTest
    public static final ArchRule APP_LAYER_ACCESSING_RULE = noClasses()
            .that()
            .resideInAnyPackage("buckpal.account.application..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("buckpal.account.adapter..");

    @ArchTest
    public static final ArchRule SERVICE_ACCESS_RULE = noClasses()
            .that()
            .resideOutsideOfPackages("buckpal.account.application.service..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("buckpal.account.application.service..");

    @ArchTest
    public static final ArchRule DOMAIN_ACCESSING_RULE = noClasses()
            .that()
            .resideInAnyPackage("buckpal.account.domain..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage(
                    "buckpal.account.application..",
                    "buckpal.account.adapter..")
            .because("Domain should always be the core of the whole application.");

}///:~