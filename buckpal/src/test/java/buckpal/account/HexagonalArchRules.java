//: buckpal.account.HexagonalArchRules.java

package buckpal.account;


import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.onionArchitecture;


public class HexagonalArchRules {

    @ArchTest
    public static final ArchRule ARCHITECTURE_RULES = onionArchitecture()
            .domainModels("buckpal.account.domain..")
            .domainServices("buckpal.account.application.service..")
            .applicationServices("buckpal.account.application..")
            .adapter(
                    "web",
                    "buckpal.account.adapter.in.web..")
            .adapter(
                    "persistence",
                    "buckpal.account.adapter.out.persistence..");

}///:~