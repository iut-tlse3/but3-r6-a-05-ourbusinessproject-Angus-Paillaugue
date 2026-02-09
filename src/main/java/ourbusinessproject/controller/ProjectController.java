package ourbusinessproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ourbusinessproject.domain.Project;
import ourbusinessproject.service.EnterpriseProjectService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(path = "/api/projects")
public class ProjectController {
    private final EnterpriseProjectService enterpriseProjectService;

    public ProjectController(EnterpriseProjectService enterpriseProjectService) {
        this.enterpriseProjectService = enterpriseProjectService;
    }

    @GetMapping
    public Collection<Project> findAllProjectsWithEnterprises() {
        return this.enterpriseProjectService.findAllProjects();
    }
}
