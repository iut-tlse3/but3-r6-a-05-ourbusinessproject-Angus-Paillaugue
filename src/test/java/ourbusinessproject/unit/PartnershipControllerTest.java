package ourbusinessproject.unit;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ourbusinessproject.controller.PartnershipController;
import ourbusinessproject.domain.Enterprise;
import ourbusinessproject.domain.Partnership;
import ourbusinessproject.domain.Project;
import ourbusinessproject.service.EnterpriseProjectService;
import ourbusinessproject.service.PartnershipService;

import java.util.List;

import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PartnershipControllerTest {

    private PartnershipController partnershipController;

    @Mock
    private EnterpriseProjectService enterpriseProjectService;

    @Mock
    private PartnershipService partnershipService;

    @Mock
    private Project project;

    @Mock
    private Enterprise enterprise;

    @BeforeEach
    public void setUp() throws Exception {
        partnershipController = new PartnershipController(enterpriseProjectService, partnershipService);
    }

    @Test
    public void testAddPartnership() {
        // when add request is triggered
        when(enterpriseProjectService.findProjectById(1L)).thenReturn(project);
        when(enterpriseProjectService.findEnterpriseById(2L)).thenReturn(enterprise);

        partnershipController.addPartnership(1L,2L);

        // then several collaborations are triggered
        verify(enterpriseProjectService).findProjectById(1L);
        verify(enterpriseProjectService).findEnterpriseById(2L);
        verify(partnershipService).newPartnership(project, enterprise);
    }

    @Test
    public void testRemovePartnership() {
        // when add request is triggered
        partnershipController.removePartnership(1L);

        // then several collaborations are triggered
        verify(partnershipService).findPartnershipById(1L);
        verify(partnershipService).remove(nullable(Partnership.class));
    }

//    @Test
//    public void testSearchPartnershipWithProjectTitle() {
//        List<Partnership> searchResults = partnershipController.searchPartnerships("Project 1", "");
//
//    }

}