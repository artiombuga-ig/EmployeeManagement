package dev.buga.service;

import dev.buga.data.GenericDAO;
import dev.buga.entity.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

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
        SessionFactory sessionFactory = projectDAO.requestSessionFactory();

        if (project != null) {
            try (Session session = sessionFactory.openSession()) {
                Transaction transaction = null;

                try {
                    transaction = session.beginTransaction();

                    String sql = "DELETE FROM employee_project WHERE project_id = :projectId";
                    session.createNativeQuery(sql)
                            .setParameter("projectId", id)
                            .executeUpdate();

                    transaction.commit();

                    projectDAO.delete(id);
                } catch (Exception e) {
                    if (transaction != null) {
                        transaction.rollback();
                    }
                    e.printStackTrace();
                }
            }
        }
    }

}
