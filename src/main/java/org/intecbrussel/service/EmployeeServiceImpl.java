package org.intecbrussel.service;

//import org.intecbrussel.exception.Exception;
import org.intecbrussel.dto.EmployeeDTO;
import org.intecbrussel.exception.ResourceNotFoundException;
import org.intecbrussel.model.Employee;
import org.intecbrussel.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with id " + id + " not found"));
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee existing = optionalEmployee.get();
            existing.setFirstName(employee.getFirstName());
            existing.setLastName(employee.getLastName());
            existing.setEmail(employee.getEmail());
            return employeeRepository.save(existing);
        } else {
            return null;
        }
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public EmployeeDTO convertToDTO(Employee employee) {
        return new EmployeeDTO(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName()
        );
    }

    public List<EmployeeDTO> getAllEmployeesDTO() {
        return getAllEmployees().stream() // Ze haalt alle Employees op via getAllEmplyees(), loopt de lijst door met .stream()
                .map(this::convertToDTO) // ze roept convertToDTO() voor elk item in lijst -> 1 voor 1
                .collect(Collectors.toList()); // Ze verzamelt alle DTO's in een List<EmployeeDTO>
    }

    public Employee saveEmployeeFromDTO(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        return employeeRepository.save(employee);
    }
}
