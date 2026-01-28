package ourbusinessproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ourbusinessproject.domain.Project;
import ourbusinessproject.service.EnterpriseProjectService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/projects")
public class ProjectController {
    private final EnterpriseProjectService enterpriseProjectService;

    public ProjectController(EnterpriseProjectService enterpriseProjectService) {
        this.enterpriseProjectService = enterpriseProjectService;
    }

    @GetMapping
    public ResponseEntity<List<Project>> findAllProjectsWithEnterprises() {
        return ResponseEntity.ok(this.enterpriseProjectService.findAllProjects());
    }
}
