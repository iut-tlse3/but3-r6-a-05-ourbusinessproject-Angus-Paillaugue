package ourbusinessproject.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ourbusinessproject.domain.Enterprise;
import ourbusinessproject.domain.Partnership;
import ourbusinessproject.domain.Project;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PartnershipService {
    public enum SORT_BY {
        DATE, PROJECT_TITLE, ENTERPRISE_NAME
    }

    public enum SORT_ORDER {
        ASC, DESC
    }

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

    public List<Partnership> searchPartnerships(String projectName, String enterpriseName, Integer page, Integer limit, SORT_BY sort_by, SORT_ORDER sort_order) {
        StringBuilder queryBuilder = new StringBuilder("SELECT p FROM Partnership p WHERE 1=1");
        if (projectName != null && !projectName.isEmpty()) {
            queryBuilder.append(" AND p.project.title LIKE :title");
        }
        if (enterpriseName != null && !enterpriseName.isEmpty()) {
            queryBuilder.append(" AND p.enterprise.name LIKE :enterpriseName");
        }
        queryBuilder.append(" ORDER BY ");
        switch (sort_by) {
            case DATE:
                queryBuilder.append("p.creationDate");
                break;
            case PROJECT_TITLE:
                queryBuilder.append("p.project.title");
                break;
            case ENTERPRISE_NAME:
                queryBuilder.append("p.enterprise.name");
                break;
            default:
                queryBuilder.append("p.creationDate");
        }
        queryBuilder.append(sort_order == SORT_ORDER.ASC ? " ASC" : " DESC");

        TypedQuery<Partnership> queryObj = entityManager.createQuery(queryBuilder.toString(), Partnership.class);

        if (projectName != null && !projectName.isEmpty()) {
            queryObj.setParameter("title", "%" + projectName + "%");
        }
        if (enterpriseName != null && !enterpriseName.isEmpty()) {
            queryObj.setParameter("enterpriseName", "%" + enterpriseName + "%");
        }

        queryObj.setFirstResult(page * limit);
        queryObj.setMaxResults(limit);

        return queryObj.getResultList();
    }
}

