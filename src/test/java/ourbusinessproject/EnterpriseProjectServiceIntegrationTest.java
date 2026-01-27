package ourbusinessproject;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class EnterpriseProjectServiceIntegrationTest {

    @Autowired
    private EnterpriseProjectService enterpriseProjectService;

    private Enterprise enterprise;
    private Project project;

    @BeforeEach
    public void setUp() {
        // given: a persisted enterprise
        enterprise = enterpriseProjectService.newEnterprise(
                "a name",
                "a description",
                "a contact name",
                "acontact@email.com"
        );
    }

    @Test
    @DisplayName("Test a valid project is saved when created")
    public void testProjectIsSavedWhenCreated() {
        // Given: a persisted project
        project = enterpriseProjectService.newProject(
                "a title",
                "a description",
                enterprise
        );
        // then: the project as generated id
        assertNotNull(project.getId());
        // when trying to find the project by id
        Project foundProject = enterpriseProjectService.findProjectById(project.getId());
        // then: the found project is the same as the created project
        assertEquals(project, foundProject);
        // and: the found project has the same enterprise as the created project
        assertEquals(enterprise, foundProject.getEnterprise());
        // and: the enterprise has the project in its list of projects
        assertTrue(enterprise.getProjects().contains(foundProject));
    }

    @Test
    @DisplayName("Test a valid project is saved when created even with a detached enterprise")
    public void testProjectIsSavedWhenCreatedWithDetachedEnterprise() {
        // Given: a detached enterprise
        enterpriseProjectService.getEntityManager().detach(enterprise);
        // Given: a persisted project
        project = enterpriseProjectService.newProject(
                "a title",
                "a description",
                enterprise
        );
        // then: the project as generated id
        assertNotNull(project.getId());
        // when trying to find the project by id
        Project foundProject = enterpriseProjectService.findProjectById(project.getId());
        // then: the found project is the same as the created project
        assertEquals(project, foundProject);
        // and: the found project has the same enterprise as the created project
        assertEquals(enterprise, foundProject.getEnterprise());
        // and: the enterprise has the project in its list of projects
        assertTrue(enterprise.getProjects().contains(foundProject));
    }

    @Test
    @DisplayName("Test a valid enterprise is saved when created")
    public void testEnterpriseIsSavedWhenCreated() {
        // Given: a persisted enterprise
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
                () -> enterpriseProjectService.newProject("title", "a description", null));
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

    @Test
    public void testFindAllProjects() {
        // given an enterprise

        // and three persisted projects
        enterpriseProjectService.newProject("p1", "p1 description", enterprise);
        enterpriseProjectService.newProject("p3", "p3 description", enterprise);
        enterpriseProjectService.newProject("p2", "p2 description", enterprise);

        // when searching for all projects
        List<Project> projects = enterpriseProjectService.findAllProjects();

        // then the three projects are fetched
        assertEquals(3, projects.size());

        // and projects are sorted by title
        assertEquals("p1", projects.get(0).getTitle());
        assertEquals("p2", projects.get(1).getTitle());
        assertEquals("p3", projects.get(2).getTitle());

    }
}