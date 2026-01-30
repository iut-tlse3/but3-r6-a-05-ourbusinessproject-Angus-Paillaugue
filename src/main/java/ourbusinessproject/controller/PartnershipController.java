package ourbusinessproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ourbusinessproject.domain.Enterprise;
import ourbusinessproject.domain.Partnership;
import ourbusinessproject.domain.Project;
import ourbusinessproject.service.EnterpriseProjectService;
import ourbusinessproject.service.PartnershipService;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;


@RestController
@RequestMapping(path = "/api/v1/partnerships")
public class PartnershipController {

    private final EnterpriseProjectService enterpriseProjectService;

    private final PartnershipService partnershipService;

    public PartnershipController(EnterpriseProjectService enterpriseProjectService, PartnershipService partnershipService) {
        this.enterpriseProjectService = enterpriseProjectService;
        this.partnershipService = partnershipService;
    }

    @PostMapping
    public ResponseEntity<Partnership> addPartnership(@RequestParam("project_id") Long projectId, @RequestParam("enterprise_id") long enterpriseId) {
        Enterprise enterprise = this.enterpriseProjectService.findEnterpriseById(enterpriseId);
        Project project = this.enterpriseProjectService.findProjectById(projectId);
        Partnership partnership = this.partnershipService.newPartnership(project, enterprise);
        return ResponseEntity.ok(partnership);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> removePartnership(@PathVariable Long id) {
        try {
            Partnership p = this.partnershipService.findPartnershipById(id);
            this.partnershipService.remove(p);
            if(p == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.noContent().build();
        } catch(RuntimeException ex) {
            Logger.getLogger("partnership").warning("An error occurred in remove partnership :\n"+ Arrays.toString(ex.getStackTrace()));
            return ResponseEntity.status(500).body("An exception occurred");
        } catch (Exception ex) {
            Logger.getLogger("partnership").warning("An error occurred in remove partnership :\n"+ Arrays.toString(ex.getStackTrace()));
            return ResponseEntity.status(400).body("An exception occurred");
        }
    }


    @GetMapping
    public ResponseEntity<List<Partnership>> searchPartnerships(
            @RequestParam(name="project_name", required = false) String projectName,
            @RequestParam(name="enterprise_name", required = false) String enterpriseName,
            @RequestParam(name="page", required = false) Integer page,
            @RequestParam(name="page_size", required = false) Integer page_size,
            @RequestParam(name="sort_by", required = false) PartnershipService.SORT_BY sort_by,
            @RequestParam(name="sort_order", required = false) PartnershipService.SORT_ORDER sort_order
    ) {
        page_size = (page_size != null && page_size > 0) ? page_size : 20;
        page = (page != null && page >= 0) ? page : 0;
        sort_by = (sort_by != null) ? sort_by : PartnershipService.SORT_BY.DATE;
        sort_order = (sort_order != null) ? sort_order : PartnershipService.SORT_ORDER.DESC;
        List<Partnership> searchResults = this.partnershipService.searchPartnerships(projectName, enterpriseName, page, page_size, sort_by, sort_order);
        return ResponseEntity.ok(searchResults);
    }
}
