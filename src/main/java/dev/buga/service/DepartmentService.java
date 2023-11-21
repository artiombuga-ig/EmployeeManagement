package dev.buga.service;

import dev.buga.data.DepartmentRepository;
import dev.buga.entity.Department;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public void add(Department department) {
        departmentRepository.save(department);
    }

    public List<Department> readAll() {
        return departmentRepository.findAll();
    }

    public Optional<Department> readById(long id) {
        return departmentRepository.findById(id);
    }

    public void update(Department department) {
        departmentRepository.save(department);
    }

    @Transactional
    public void remove(long id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);

        if (optionalDepartment.isPresent()) {
            Department department = optionalDepartment.get();
            departmentRepository.removeDepartment(department.getId());
        }

    }
}

