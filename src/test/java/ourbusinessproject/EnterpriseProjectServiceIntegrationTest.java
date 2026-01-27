package ourbusinessproject;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class EnterpriseProjectServiceIntegrationTest {

    @Autowired
    private EnterpriseProjectService enterpriseProjectService;

    @Test
    @DisplayName("Test a valid project is saved when created")
    public void testProjectIsSavedWhenCreated() {
        // when: trying to get a new project with valid parameters
        Project project = enterpriseProjectService.newProject("a title", "a description");
        // then: the project as generated id
        assertNotNull(project.getId());
        // when trying to find the project by id
        Project foundProject = enterpriseProjectService.findProjectById(project.getId());
        // then: the found project is the same as the created project
        assertEquals(project, foundProject);
    }

    @Test
    @DisplayName("Test a valid enterprise is saved when created")
    public void testEnterpriseIsSavedWhenCreated() {
        // when: trying to get a new enterprise with valid parameters
        Enterprise enterprise = enterpriseProjectService.newEnterprise(
                "a name",
                "a description",
                "a contact name",
                "acontact@email.com"
        );
        // then: the enterprise as a generated id
        assertNotNull(enterprise.getId());
        // when: trying to find the enterprise by id
        Enterprise foundEnterprise = enterpriseProjectService.findEnterpriseById(enterprise.getId());
        // then: the found enterprise is the same as the created enterprise
        assertEquals(enterprise, foundEnterprise);
    }

    @Test
    @DisplayName("Test a non valid project is not saved when created")
    public void testProjectIsNotSavedWhenCreated() {
        // when: trying to get a new project with invalid parameters
        // then: ConstraintViolationException is thrown
        assertThrows(ConstraintViolationException.class,
                () -> enterpriseProjectService.newProject("", "a description"));
    }

    @Test
    @DisplayName("Test a non valid enterprise is not saved when created")
    public void testEnterpriseIsNotSavedWhenCreated() {
        // when: trying to get a new enterprise with invalid parameters
        // then: ConstraintViolationException is thrown
        assertThrows(ConstraintViolationException.class,
                () -> enterpriseProjectService.newEnterprise(
                        "a name",
                        "a description",
                        "a contact name",
                        null
                ));

    }
}