//: com.account.BuckpalAccountArchitectureTest.java

package buckpal.account;


import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;


@AnalyzeClasses(
        packages = "buckpal.account",
        importOptions = {
                ImportOption.DoNotIncludeTests.class,
                ImportOption.DoNotIncludeJars.class,
                ImportOption.DoNotIncludeArchives.class,
                IgnoreDiConfigurationRule.class})
@DisplayName("Dependency Rules Test for Driving Ports - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BuckpalAccountArchitectureTest {

    @ArchTest
    static final ArchTests DEPENDENCY_RULES = ArchTests.in(DependencyRules.class);

    @ArchTest
    static final ArchTests NONCYCLE_RULES = ArchTests.in(CycleChecks.class);

}///:~