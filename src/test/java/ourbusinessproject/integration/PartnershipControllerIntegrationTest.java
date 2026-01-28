package ourbusinessproject.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ourbusinessproject.InitializationService;
import ourbusinessproject.domain.Partnership;
import ourbusinessproject.service.PartnershipService;

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

}