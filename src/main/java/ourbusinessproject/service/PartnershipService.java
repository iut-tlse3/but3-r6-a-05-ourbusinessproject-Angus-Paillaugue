package ourbusinessproject.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ourbusinessproject.domain.Enterprise;
import ourbusinessproject.domain.Partnership;
import ourbusinessproject.domain.Project;

import java.util.Date;

@Service
public class PartnershipService {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void remove(Partnership partnership) {
        this.entityManager.remove(partnership);
        this.entityManager.flush();
    }

    @Transactional
    public Partnership newPartnership(Project project, Enterprise partnerEnterprise) {
        Project managedProject = this.entityManager.merge(project);
        Enterprise managedEnterprise = this.entityManager.merge(partnerEnterprise);
        Partnership p = new Partnership();
        p.setEnterprise(managedEnterprise);
        p.setCreationDate(new Date());
        p.setProject(managedProject);
        this.entityManager.persist(p);
        this.entityManager.flush();
        return p;
    }

    public Partnership findPartnershipById(Long id) {
        return this.entityManager.find(Partnership.class, id);
    }
}
