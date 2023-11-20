package dev.buga.service;

import dev.buga.data.GenericDAO;
import dev.buga.entity.Department;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    private final GenericDAO<Department> departmentDAO;

    public DepartmentService(GenericDAO<Department> departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    public void add(Department department) {
        departmentDAO.create(department);
    }

    public List<Department> readAll() {
        return departmentDAO.readAll();
    }

    public Department readById(long id) {
        return departmentDAO.read(id);
    }

    public void update(Department department) {
        departmentDAO.update(department);
    }

    @Transactional
    public void remove(long id) {
        Department department = readById(id);
        EntityManager entityManager = departmentDAO.getEntityManager();

        if (department != null) {
            String sql = "UPDATE employee SET department_id = null WHERE department_id = :departmentId";
            entityManager.createNativeQuery(sql)
                    .setParameter("departmentId", id)
                    .executeUpdate();

            departmentDAO.delete(id);
        }
    }
}

