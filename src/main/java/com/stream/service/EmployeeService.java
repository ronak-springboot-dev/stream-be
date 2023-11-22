package com.stream.service;


import com.stream.model.Employee;
import com.stream.model.Project;
import com.stream.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }


    //list all distinct project
    public List<Project> listAllDistinctProject() {
        return getAllEmployees().stream()
                .filter(p -> p.getProjects().stream().distinct().isParallel())
                .flatMap(p -> p.getProjects().stream())
                .distinct().collect(Collectors.toList());

    }

    public void employeeStartsWith() {
        getAllEmployees()
                .stream()
                .filter(name -> name.getFirstName().startsWith("J"))
                .map(e -> " " + e.getFirstName() + " " + e.getLastName())
                .forEach(System.out::println);

    }

    //short employee by firstName and salary (both in one method)
    public List<Employee> shortEmployeeByFirstName() {

        return getAllEmployees()
                .stream()
//                .filter(fn -> Boolean.parseBoolean(fn.getFirstName()))
                // FIXME: 22/11/2023 uncomment this if needed
//                .sorted(Comparator.comparing(Employee::getFirstName))
                .sorted(Comparator.comparing(Employee::getSalary))
                .collect(Collectors.toList());
    }

    public Employee printEmployeeWithThirdHighestSalary() {
        return getAllEmployees()
                .stream()
                .sorted(Comparator.comparingInt(Employee::getSalary).reversed())
                //This will skip initial 2 elements and then we'll find very first element using findFirst()
                .skip(2)
                .findFirst()
                .get();
    }


    public Employee findEmployeeWithMinimumSalary() throws Exception {

        return getAllEmployees()
                .stream()
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new Exception());

    }


    public List<Employee> workingOnMoreThanOneProject() {

        return getAllEmployees().stream()
                .filter(employee -> employee.getProjects().size() > 1)
                .collect(Collectors.toList());

    }


    public Long listOfProjectWorkingWithPM(){

       return getAllEmployees()
                .stream()
                .flatMap(pm->pm.getProjects().stream())
                .filter(project -> "Robert Downey Jr".equalsIgnoreCase(project.getProjectManager()))
                .count();

    }

}
