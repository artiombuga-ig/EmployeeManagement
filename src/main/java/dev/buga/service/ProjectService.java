package dev.buga.service;

import dev.buga.data.GenericDAO;
import dev.buga.entity.Project;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.awt.event.TextEvent;
import java.util.List;

@Service
public class ProjectService {
    private final GenericDAO<Project> projectDAO;

    public ProjectService(GenericDAO<Project> projectDAO) {
        this.projectDAO = projectDAO;
    }

    public List<Project> readAll() {
        return projectDAO.readAll();
    }

    public Project readById(long id) {
        return projectDAO.read(id);
    }

    public void update(Project project) {
        projectDAO.update(project);
    }

    public void add(Project project) {
        projectDAO.create(project);
    }

    public void remove(long id) {
        Project project = readById(id);
        EntityManager entityManager = projectDAO.getEntityManager();

        if (project != null) {

            String sql = "DELETE FROM employee_project WHERE project_id = :projectId";
            entityManager.createNativeQuery(sql)
                    .setParameter("projectId", id)
                    .executeUpdate();

            projectDAO.delete(id);
        }
    }
}
