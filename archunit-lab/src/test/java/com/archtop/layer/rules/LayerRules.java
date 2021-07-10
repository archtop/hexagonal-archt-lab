//: com.archtop.layer.rules.LayerRules.java

package com.archtop.layer.rules;


import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;


public class LayerRules {

    public static final String WEB_LAYER = "WEB_LAYER";
    public static final String PERSISTENCE_LAYER = "PERSISTENCE_LAYER";
    public static final String APPLICATION_LAYER = "APPLICATION_LAYER";
    public static final String DOMAIN_LAYER = "DOMAIN_LAYER";

    @ArchTest
    public static final ArchRule LAYER_RULE = layeredArchitecture()
            .layer(WEB_LAYER).definedBy(
                    "com.archtop.adapter.in.web")
            .layer(PERSISTENCE_LAYER).definedBy(
                    "com.archtop.adapter.out.persistence")
            .layer(DOMAIN_LAYER).definedBy(
                    "com.archtop.domain")
            .layer(APPLICATION_LAYER).definedBy(
                    "com.archtop.application.port.in",
                    "com.archtop.application.port.out",
                    "com.archtop.application.service")
            .whereLayer(WEB_LAYER).mayNotBeAccessedByAnyLayer()
            .whereLayer(APPLICATION_LAYER).mayOnlyBeAccessedByLayers(
                    WEB_LAYER,
                    PERSISTENCE_LAYER)
            .whereLayer(PERSISTENCE_LAYER).mayOnlyBeAccessedByLayers(
                    APPLICATION_LAYER)
            .whereLayer(DOMAIN_LAYER).mayOnlyBeAccessedByLayers(
                    APPLICATION_LAYER);
}///:~