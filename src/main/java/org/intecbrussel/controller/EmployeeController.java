package org.intecbrussel.controller;

import org.intecbrussel.dto.EmployeeDTO;
import org.intecbrussel.exception.ResourceNotFoundException;
import org.intecbrussel.model.Employee;
import org.intecbrussel.repository.EmployeeRepository;
import org.intecbrussel.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;


    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/dto")
    public List<EmployeeDTO> getAllEmployeesDTO() {
        return employeeService.getAllEmployeesDTO();
    }

    @GetMapping("/dto/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return employeeService.convertToDTO(employee);
    }

    @PostMapping("/dto")
    public EmployeeDTO addEmployee(@RequestBody EmployeeDTO employee) {
        Employee employees = employeeService.saveEmployeeFromDTO(employee);
        return employeeService.convertToDTO(employees);
    }

}
