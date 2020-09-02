package com.especializacion.ejemploelectiva;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.especializacion.ejemploelectiva");

        noClasses()
            .that()
            .resideInAnyPackage("com.especializacion.ejemploelectiva.service..")
            .or()
            .resideInAnyPackage("com.especializacion.ejemploelectiva.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.especializacion.ejemploelectiva.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
