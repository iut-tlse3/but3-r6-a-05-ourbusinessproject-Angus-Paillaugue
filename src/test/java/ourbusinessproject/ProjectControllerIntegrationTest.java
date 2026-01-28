package ourbusinessproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProjectControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private InitializationService initializationService;

    @Test
    public void testFindAllProjectsWithEnterprises() {

        // when requesting all projects
        String body = this.restTemplate.getForObject("/api/projects", String.class);

        // then the results provide three projects with their enterprise
        assertTrue(body.contains(initializationService.getProject1E1().getTitle()));
        assertTrue(body.contains(initializationService.getProject1E2().getTitle()));
        assertTrue(body.contains(initializationService.getProject2E1().getTitle()));
        assertTrue(body.contains(initializationService.getEnterprise1().getName()));
        assertTrue(body.contains(initializationService.getEnterprise2().getName()));


    }

}