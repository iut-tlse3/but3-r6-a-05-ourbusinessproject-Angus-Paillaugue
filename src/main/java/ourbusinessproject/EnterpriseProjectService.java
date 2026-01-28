package ourbusinessproject;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class EnterpriseProjectService {

    @PersistenceContext
    private EntityManager entityManager;

    public EnterpriseProjectService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public Project newProject(String aTitle, String aDescription, Enterprise entreprise) {
        Project p = new Project();
        p.setTitle(aTitle);
        p.setDescription(aDescription);
        p.setEnterprise(entreprise);
        this.entityManager.persist(p);
        this.entityManager.flush();
        entreprise.addProject(p);
        return p;
    }

    @Transactional
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
        String query = "SELECT p from Project p ORDER BY p.title";
        TypedQuery<Project> queryObj = entityManager.createQuery(query,Project.class);
        return queryObj.getResultList();
    }
}
