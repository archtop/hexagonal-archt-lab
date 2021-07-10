//: com.archtop.coding.rules.CodingRules.java

package com.archtop.coding.rules;


import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.GeneralCodingRules;


public class CodingRules {

    @ArchTest
    public static final ArchRule NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS =
            GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;

    @ArchTest
    public static final ArchRule NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS =
            GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;

    @ArchTest
    public static final ArchRule NO_CLASSES_SHOULD_USE_FIELD_INJECTION =
            GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION;

    @ArchTest
    public static final ArchRule NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING =
            GeneralCodingRules.NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;

}///:~