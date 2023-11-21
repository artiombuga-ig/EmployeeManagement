package dev.buga.data;

import dev.buga.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Modifying
    @Query("UPDATE Employee e SET e.department = null WHERE e.department.id = :departmentId")
    void removeDepartment(@Param("departmentId") Long departmentId);
}
