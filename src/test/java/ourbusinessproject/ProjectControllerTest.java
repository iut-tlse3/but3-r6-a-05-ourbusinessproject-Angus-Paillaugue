package ourbusinessproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProjectControllerTest {

    @Mock
    private EnterpriseProjectService enterpriseProjectService;

    private ProjectController projectController;

    @BeforeEach
    public void setUp() {
        projectController = new ProjectController(enterpriseProjectService);
    }

    @Test
    public void testControllerDelegationToService() {
        // when requesting for all projects
        projectController.findAllProjectsWithEnterprises();

        // then the request is performed by the enterprise project service
        verify(enterpriseProjectService).findAllProjects();
    }

}