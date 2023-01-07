package com.bmac.store.architecture;


import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "com.bmac")
public class ArchitectureTest {

    static final String ADAPTERS = "com.bmac.store.adapters..";
    static final String CORE = "com.bmac.store.core..";
    static final String DOMAIN = "com.bmac.store.domain..";
    static final String PORTS = "com.bmac.store.port..";

//    static final ArchRule domainShouldNotDependOnAnyOtherLayer =
//            noClasses().that().resideInAnyPackage(DOMAIN).should();




}
