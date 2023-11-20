package dev.buga.service;

import dev.buga.data.GenericDAO;
import dev.buga.entity.Employee;
import dev.buga.entity.Project;
import jakarta.persistence.EntityManager;
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
        EntityManager entityManager = employeeDAO.getEntityManager();

        if (employee != null) {

        String sql = "UPDATE department SET manager_id = null WHERE manager_id = :employeeId";
        entityManager.createNativeQuery(sql)
                .setParameter("employeeId", id)
                .executeUpdate();

        employeeDAO.delete(id);
        }
    }
}