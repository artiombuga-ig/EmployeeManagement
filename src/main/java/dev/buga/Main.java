package dev.buga;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

//        GenericDAOImpl<Department> genericDAO = context.getBean(GenericDAOImpl.class);
//
//        Department department = new Department();
//        department.setName("IT");
//        genericDAO.create(department);
//
//        context.getBean(GenericDAOImpl.class);
    }
}