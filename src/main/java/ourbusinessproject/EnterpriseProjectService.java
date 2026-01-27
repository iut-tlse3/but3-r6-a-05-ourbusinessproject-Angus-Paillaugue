package ourbusinessproject;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.validation.ConstraintViolationException;
import org.springframework.stereotype.Service;

import java.util.List;


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

    public Project newProject(String aTitle, String aDescription, Enterprise entreprise) {
        if (entreprise == null) {
            throw new ConstraintViolationException("Enterprise cannot be null", null);
        }
        Project p = new Project();
        p.setTitle(aTitle);
        p.setDescription(aDescription);
        p.setEnterprise(entreprise);
        entreprise.addProject(p);
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
        return this.entityManager.find(Project.class, projectId);
    }

    public Enterprise findEnterpriseById(Long enterpriseId) {
        return this.entityManager.find(Enterprise.class, enterpriseId);
    }

    public List<Project> findAllProjects() {
        String query = "SELECT p from Project p WHERE 1=1 ORDER BY p.title";
        TypedQuery<Project> queryObj = entityManager.createQuery(query,Project.class);
        return queryObj.getResultList();
    }
}
