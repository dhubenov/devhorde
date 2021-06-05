package eu.diamox.devhorde;

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
            .importPackages("eu.diamox.devhorde");

        noClasses()
            .that()
            .resideInAnyPackage("eu.diamox.devhorde.service..")
            .or()
            .resideInAnyPackage("eu.diamox.devhorde.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..eu.diamox.devhorde.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
