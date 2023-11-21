package dev.buga.utility;

import dev.buga.entity.Department;
import dev.buga.entity.Employee;
import dev.buga.entity.Project;
import dev.buga.service.DepartmentService;
import dev.buga.service.EmployeeService;
import dev.buga.service.ProjectService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Scanner;

@Component
public class UserInputHandler {
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final ProjectService projectService;
    Scanner scanner = new Scanner(System.in);

    public UserInputHandler(EmployeeService employeeService, DepartmentService departmentService, ProjectService projectService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.projectService = projectService;
    }

    public void addEmployee() {
        Employee employee = new Employee();
        System.out.println("Input employee first name");
        employee.setFirstName(scanner.next());
        System.out.println("Input employee last name");
        employee.setLastName(scanner.next());
        System.out.println("Input employee salary");
        employee.setSalary(scanner.nextInt());

        employeeService.add(employee);
    }

    public void addDepartment() {
        Department department = new Department();
        System.out.println("Input department name");

        department.setName(scanner.next());

        departmentService.add(department);
    }

    public void addProject() {
        Project project = new Project();
        System.out.println("Input project name");

        project.setName(scanner.next());
        project.setStartDate(new Date());
        project.setEndDate(new Date());

        projectService.add(project);
    }

    public void linkEmployeeToDepartment() {
        Employee chosenEmployee = selectEmployee();
        Department chosenDepartment = selectDepartment();

        chosenDepartment.addEmployee(chosenEmployee);
        chosenEmployee.setDepartment(chosenDepartment);
        employeeService.update(chosenEmployee);
        departmentService.update(chosenDepartment);
    }

    public void setEmployeeAsManager() {
        Department chosenDepartment = selectDepartment();

        Employee chosenEmployee = selectEmployee();

        chosenDepartment.setManager(chosenEmployee);
        departmentService.update(chosenDepartment);
    }

    public void linkEmployeeToProject() {
        Employee chosenEmployee = selectEmployee();
        Project chosenProject = selectProject();

        chosenEmployee.addProject(chosenProject);
        employeeService.update(chosenEmployee);
    }

    public void showEmployees() {
        for (Employee e : employeeService.readAll()) {
            System.out.println(e.toString());
        };
    }

    public void showDepartments() {
        for (Department d : departmentService.readAll()) {
            System.out.println(d.toString());
        };
    }

    public void showProjects() {
        for (Project p: projectService.readAll()) {
            System.out.println(p.toString());
        };
    }

    private Department selectDepartment() {
        System.out.println("Select a department:");
        showDepartments();

        long departmentId = (long) scanner.nextInt();

        return departmentService.readById(departmentId).get();
    }

    private Employee selectEmployee() {
        System.out.println("Select an employee:");
        showEmployees();

        long employeeId = (long) scanner.nextInt();

        return employeeService.readById(employeeId).get();
    }

    private Project selectProject() {
        System.out.println("Select a project:");
        showProjects();

        long projectId = (long) scanner.nextInt();

        return projectService.readById(projectId).get();
    }

    public void removeEmployee() {
        System.out.println("Select an employee:");
        showEmployees();

        long employeeId = (long) scanner.nextInt();

        employeeService.remove(employeeId);
    }

    public void removeDepartment() {
        System.out.println("Select a department:");
        showDepartments();

        long departmentId = (long) scanner.nextInt();

        departmentService.remove(departmentId);
    }

    public void removeProject() {
        System.out.println("Select a project:");
        showProjects();

        long projectId = (long) scanner.nextInt();

        projectService.remove(projectId);
    }
}
