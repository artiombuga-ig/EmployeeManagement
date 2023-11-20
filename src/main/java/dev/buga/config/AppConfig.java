package dev.buga.config;

import dev.buga.data.GenericDAO;
import dev.buga.data.GenericDAOImpl;
import dev.buga.entity.Department;
import dev.buga.entity.Employee;
import dev.buga.entity.Project;
import dev.buga.service.DepartmentService;
import dev.buga.service.EmployeeService;
import dev.buga.service.ProjectService;
import dev.buga.utility.UserInputHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@ComponentScan(basePackages = "dev.buga")
@Configuration
@Import(HibernateConfig.class)
public class AppConfig {

    @Bean
    GenericDAO<Department> departmentDao(@Qualifier("sessionFactory") LocalSessionFactoryBean sessionFactory) {
        return new GenericDAOImpl<>(Department.class, sessionFactory);
    }

    @Bean
    GenericDAO<Employee> employeeDao(@Qualifier("sessionFactory") LocalSessionFactoryBean sessionFactory) {
        return new GenericDAOImpl<>(Employee.class, sessionFactory);
    }

    @Bean
    GenericDAO<Project> projectDao(@Qualifier("sessionFactory") LocalSessionFactoryBean sessionFactory) {
        return new GenericDAOImpl<>(Project.class, sessionFactory);
    }

}
