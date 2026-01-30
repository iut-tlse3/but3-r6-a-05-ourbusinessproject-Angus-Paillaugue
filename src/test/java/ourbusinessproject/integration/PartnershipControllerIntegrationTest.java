package ourbusinessproject.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ourbusinessproject.InitializationService;
import ourbusinessproject.domain.Partnership;
import ourbusinessproject.service.PartnershipService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PartnershipControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private InitializationService initializationService;

    @Autowired
    private PartnershipService partnershipService;

    @Test
    public void testAddPartnership() {

        // when a request to add a partnership is triggered
        MultiValueMap<String, Long> map = new LinkedMultiValueMap<String, Long>();
        map.add("project_id", initializationService.getProject1E1().getId());
        map.add("enterprise_id", initializationService.getEnterprise2().getId());
        Partnership partnership = restTemplate.postForObject("/api/v1/partnerships",map, Partnership.class);

        // then the response provide data on the added partnership
        assertNotNull(partnership.getId());
        assertEquals(partnership.getProject().getId(), initializationService.getProject1E1().getId());
        assertEquals(partnership.getEnterprise().getId(), initializationService.getEnterprise2().getId());

    }

    @Test
    public void testRemovePartnership() {
        // when a partnership is requested to be removed
        restTemplate.delete("/api/v1/partnerships/"+initializationService.getPartnershipP1E2WithE1().getId());
        // then it is deleted from the database
        assertNull(partnershipService.findPartnershipById(initializationService.getPartnershipP1E2WithE1().getId()));
    }

    @Test
    public void testFindByEnterpriseName() {
        ResponseEntity<List<Partnership>> response = restTemplate.exchange(
                "/api/v1/partnerships?enterprise_name=" + initializationService.getEnterprise2().getName(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Partnership>>() {}
        );
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testFindByNameAndEnterprise() {
        String url = "/api/v1/partnerships?project_name=" + initializationService.getProject2E1().getTitle() +
                "&enterprise_name=" + initializationService.getEnterprise2().getName();
        ResponseEntity<List<Partnership>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Partnership>>() {
                }
        );
        assertEquals(1, response.getBody().size());
        assertEquals(initializationService.getPartnershipP2E1WithE2().getId(), response.getBody().get(0).getId());
        assertEquals(initializationService.getPartnershipP2E1WithE2().getProject().getId(), response.getBody().get(0).getProject().getId());
        assertEquals(initializationService.getPartnershipP2E1WithE2().getEnterprise().getId(), response.getBody().get(0).getEnterprise().getId());
    }

    @Test
    public void testFindAll() {
        String url = "/api/v1/partnerships";
        ResponseEntity<List<Partnership>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Partnership>>() {
                }
        );
        assertEquals(3, response.getBody().size());
    }

    @Test
    public void testLimit() {
        String url = "/api/v1/partnerships?page_size=1";
        ResponseEntity<List<Partnership>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Partnership>>() {
                }
        );
        assertEquals(1, response.getBody().size());
    }

    @Test
    public void testLimitAndPagination() {
        String url = "/api/v1/partnerships?page_size=1&page=1";
        ResponseEntity<List<Partnership>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Partnership>>() {
                }
        );
        assertEquals(1, response.getBody().size());
        assertEquals(initializationService.getPartnershipP1E2WithE1().getId(), response.getBody().get(0).getId());
    }

    @Test
    public void testSorting() {
        String url = "/api/v1/partnerships?sort_by=PROJECT_TITLE&sort_order=ASC";
        ResponseEntity<List<Partnership>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Partnership>>() {
                }
        );
        System.out.println(response.getBody());
        assertEquals(3, response.getBody().size());
        assertEquals(initializationService.getPartnershipP1E1WithE2().getId(), response.getBody().get(0).getId());
        assertEquals(initializationService.getPartnershipP1E2WithE1().getId(), response.getBody().get(1).getId());
        assertEquals(initializationService.getPartnershipP2E1WithE2().getId(), response.getBody().get(2).getId());
    }

}