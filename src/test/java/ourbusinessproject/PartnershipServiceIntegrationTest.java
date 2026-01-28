package ourbusinessproject;


import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ourbusinessproject.domain.Enterprise;
import ourbusinessproject.domain.Partnership;
import ourbusinessproject.domain.Project;
import ourbusinessproject.service.EnterpriseProjectService;
import ourbusinessproject.service.PartnershipService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class PartnershipServiceIntegrationTest {

    @Autowired
    private EnterpriseProjectService enterpriseProjectService;

    @Autowired
    private PartnershipService partnershipService;

    private Enterprise partnerEnterprise;
    private Project project;

    @BeforeEach
    public void setUp() throws Exception {
        // given a project with its enterprise
        Enterprise projectEnterprise = enterpriseProjectService.newEnterprise(
                "The Firm",
                "The firm description",
                "Contact @ the firm",
                "contact@thefirm.com"
        );
        project = enterpriseProjectService.newProject(
                "The project",
                "The project description",
                projectEnterprise
        );
        // given the partner enterprise
        partnerEnterprise = enterpriseProjectService.newEnterprise(
                "The partner",
                "The partner description",
                "Contact @ partner",
                "contact@thepartner.com"
        );
    }

    @Test
    public void testSavePartnership() {

        // when saving the partnership
        Partnership partnership = partnershipService.newPartnership(project, partnerEnterprise);


        // and the returned partnership is stored in the database with an id
        assertNotNull(partnership.getId());

        // when fetching the corresponding partnership
        Partnership fetchedPartnership = partnershipService.findPartnershipById(partnership.getId());

        // then the properties of the fetched partnership are set as expected
        assertEquals(fetchedPartnership.getEnterprise().getId(), partnerEnterprise.getId());
        assertEquals(fetchedPartnership.getProject().getId(), project.getId());
        assertNotNull(fetchedPartnership.getCreationDate());

    }

    @Test
    public void testRemovePartnership() {
        // given a saved partnership
        Partnership partnership = partnershipService.newPartnership(project, partnerEnterprise);

        // when removing the partnership
        partnershipService.remove(partnership);

        // then the partnership is no more in the database
        assertNull(partnershipService.findPartnershipById(partnership.getId()));

    }

}