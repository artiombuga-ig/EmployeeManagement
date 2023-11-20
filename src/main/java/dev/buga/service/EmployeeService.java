package dev.buga.service;

import dev.buga.data.GenericDAO;
import dev.buga.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final GenericDAO<Employee> employeeDAO;

    public EmployeeService(GenericDAO<Employee> employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public void add(Employee employee) {
        employeeDAO.create(employee);
    }

    public List<Employee> readAll() {
        return employeeDAO.readAll();
    }

    public Employee readById(Long id) {
        return employeeDAO.read(id);
    }

    public void update(Employee employee) {
        employeeDAO.update(employee);
    }

    public void remove(Long id) {

        Employee employee = readById(id);
        SessionFactory sessionFactory = employeeDAO.requestSessionFactory();

        if (employee != null) {
            try (Session session = sessionFactory.openSession()) {
                Transaction transaction = null;

                try {
                    transaction = session.beginTransaction();

                    String sql = "UPDATE department SET manager_id = null WHERE manager_id = :employeeId";
                    session.createNativeQuery(sql)
                            .setParameter("employeeId", id)
                            .executeUpdate();

                    transaction.commit();

                    employeeDAO.delete(id);
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
