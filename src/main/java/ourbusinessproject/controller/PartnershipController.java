package ourbusinessproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ourbusinessproject.domain.Enterprise;
import ourbusinessproject.domain.Partnership;
import ourbusinessproject.domain.Project;
import ourbusinessproject.service.EnterpriseProjectService;
import ourbusinessproject.service.PartnershipService;

import java.util.Arrays;
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
    public ResponseEntity<Partnership> addPartnership(@RequestParam("project_id") Long project_id, @RequestParam("enterprise_id") long enterprise_id) {
        Enterprise enterprise = this.enterpriseProjectService.findEnterpriseById(enterprise_id);
        Project project = this.enterpriseProjectService.findProjectById(project_id);
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
}
