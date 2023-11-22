package com.stream.controller;

import com.stream.model.Employee;
import com.stream.model.Project;
import com.stream.repository.ProjectRepository;
import com.stream.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProjectRepository projectRepository;

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @PostMapping("/project")
    public Project createProject(@RequestBody Project project) {
        return projectRepository.save(project);
    }

    @GetMapping("/distinctProject")
    public List<Project> showDistinctProject() {
        return employeeService.listAllDistinctProject();
    }

    @GetMapping("/startsWith")
    public void employeeStartsWithA() {
        employeeService.employeeStartsWith();
    }

    @GetMapping("/shortByFirstName")
    public List<Employee> sortedEmployeesByFirstName() {
        return employeeService.shortEmployeeByFirstName();
    }

    @GetMapping("/thirdHighestSalary")
    public Employee findEmployeeWithThirdHighestSalary() {
        return employeeService.printEmployeeWithThirdHighestSalary();
    }


    @GetMapping("/minimumSalary")
    public Employee findEmployeeWithMinimumSalary() {
        try {
            return employeeService.findEmployeeWithMinimumSalary();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/moreThanOneProject")
    public List<Employee> findEmployeeWorkingOnMoreThanOneProject(){
        return employeeService.workingOnMoreThanOneProject();
    }

    @GetMapping("/pm")
    public Long countOfProjectHavingPm(){
        return employeeService.listOfProjectWorkingWithPM();
    }

}
