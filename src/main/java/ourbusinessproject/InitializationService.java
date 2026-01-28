package ourbusinessproject;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitializationService {
    private Project p1e1;
    private Project p2e1;
    private Project p1e2;
    private Enterprise e1;
    private Enterprise e2;

    @Autowired
    private EnterpriseProjectService enterpriseProjectService;

    @Transactional
    public void initProjects() {
        this.e1 = this.enterpriseProjectService.newEnterprise("Enterprise 1","this is enterprise 1", "Enterprise 1", "contact@enterpise1.com");
        this.e2 = this.enterpriseProjectService.newEnterprise("Enterprise 2","this is enterprise 2", "Enterprise 2", "contact@enterpise2.com");
        this.p1e1 = this.enterpriseProjectService.newProject("~Project 1 of enterprise 1", "This is the description of the project 1 of enterprise 1", this.e1);
        this.p2e1 = this.enterpriseProjectService.newProject("~Project 2 of enterprise 1", "This is the description of the project 2 of enterprise 1", this.e1);
        this.p1e2 = this.enterpriseProjectService.newProject("~Project 1 of enterprise 2", "This is the description of the project 1 of enterprise 2", this.e2);
    }

    public Project getProject1E1() {
        return this.p1e1;
    }

    public Project getProject1E2() {
        return this.p1e2;
    }

    public Project getProject2E1() {
        return this.p2e1;
    }

    public Enterprise getEnterprise1() {
        return this.e1;
    }

    public Enterprise getEnterprise2() {
        return this.e2;
    }
}
