package dev.buga.service;

import dev.buga.data.EmployeeRepository;
import dev.buga.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void add(Employee employee) {
        employeeRepository.save(employee);
    }

    public List<Employee> readAll() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> readById(Long id) {
        return employeeRepository.findById(id);
    }

    public void update(Employee employee) {
        employeeRepository.save(employee);
    }

    @Transactional
    public void remove(long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employeeRepository.removeEmployee(employee.getId());
        }
    }
}
