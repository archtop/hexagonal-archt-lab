//: com.archtop.ArchitectureTest.java

package com.archtop;


import com.archtop.coding.rules.CodingRules;
import com.archtop.containment.rules.PortContainmentRules;
import com.archtop.dependency.rules.ApplicationRules;
import com.archtop.dependency.rules.DomainRules;
import com.archtop.inheritance.rules.PortInheritanceRules;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.core.importer.Location;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;


@AnalyzeClasses(
        packages = "com.archtop",
        importOptions = {
                ImportOption.DoNotIncludeTests.class,
                ImportOption.DoNotIncludeJars.class,
                ImportOption.DoNotIncludeArchives.class,
                IgnoreDiConfiguration.class})
@DisplayName("Dependency Rules Test for Driving Ports - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ArchitectureTest {

    static final String DI_CONFIG_CLASS_NAME_SUFFIX = "DiConfiguration";
    ImportOption ignoreTests = new ImportOption() {
        @Override
        public boolean includes(Location location) {
            return !location.contains(DI_CONFIG_CLASS_NAME_SUFFIX);
        }
    };

    @ArchTest
    static final ArchTests ARCH_RULES = ArchTests.in(ArchitectureRules.class);

    @ArchTest
    static final ArchTests DOMAIN_RULES = ArchTests.in(DomainRules.class);

    @ArchTest
    static final ArchTests APP_RULES = ArchTests.in(ApplicationRules.class);

    @ArchTest
    static final ArchTests PORT_CONTAINMENT_RULES =
            ArchTests.in(PortContainmentRules.class);

    @ArchTest
    static final ArchTests PORT_INHERITANCE_RULES =
            ArchTests.in(PortInheritanceRules.class);

    @ArchTest
    static final ArchTests CYCLE_CHECKE = ArchTests.in(CycleChecks.class);

    @ArchTest
    static final ArchTests CODING_RULES = ArchTests.in(CodingRules.class);

}///:~