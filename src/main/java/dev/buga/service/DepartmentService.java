package dev.buga.service;

import dev.buga.data.GenericDAO;
import dev.buga.entity.Department;
import dev.buga.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

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

    public void remove(long id) {
        Department department = readById(id);
        SessionFactory sessionFactory = departmentDAO.requestSessionFactory();

        if (department != null) {
            try (Session session = sessionFactory.openSession()) {
                Transaction transaction = null;

                try {
                    transaction = session.beginTransaction();

                    String sql = "UPDATE employee SET department_id = null WHERE department_id = :departmentId";
                    session.createNativeQuery(sql)
                            .setParameter("departmentId", id)
                            .executeUpdate();

                    transaction.commit();

                    departmentDAO.delete(id);
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
