package dev.buga;

import dev.buga.data.GenericDAOImpl;
import dev.buga.entity.Department;
import dev.buga.entity.Employee;
import dev.buga.entity.Project;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        GenericDAOImpl<Department> departmentDAO = context.getBean("departmentDao", GenericDAOImpl.class);
        GenericDAOImpl<Employee> employeeDAO = context.getBean("employeeDao", GenericDAOImpl.class);
        GenericDAOImpl<Project> projectDAO = context.getBean("projectDao", GenericDAOImpl.class);

        Department department = new Department();
        department.setName("IT");
        departmentDAO.create(department);

//        context.getBean(GenericDAOImpl.class);
    }
}