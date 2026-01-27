package ourbusinessproject;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;


@Service
public class EnterpriseProjectService {
    @PersistenceContext
    private EntityManager entityManager;

    public EnterpriseProjectService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Project newProject(String aTitle, String aDescription) {
        Project p = new Project();
        p.setTitle(aTitle);
        p.setDescription(aDescription);
        this.entityManager.persist(p);
        this.entityManager.flush();
        return p;
    }

    public Enterprise newEnterprise(String aName, String aDescription, String aContactName, String mail) {
        Enterprise e = new Enterprise();
        e.setName(aName);
        e.setDescription(aDescription);
        e.setContactName(aContactName);
        e.setContactEmail(mail);
        this.entityManager.persist(e);
        this.entityManager.flush();
        return e;
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    public Project findProjectById(Long projectId) {
        return entityManager.find(Project.class, projectId);
    }

    public Enterprise findEnterpriseById(Long enterpriseId) {
        return entityManager.find(Enterprise.class, enterpriseId);
    }
}
