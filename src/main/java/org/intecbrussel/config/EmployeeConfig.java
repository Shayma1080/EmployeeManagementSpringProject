package org.intecbrussel.config;

import org.intecbrussel.model.Employee;
import org.intecbrussel.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeConfig {
    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository employeeRepository) {
        return args -> {
            Employee employee1 = new Employee();
            employee1.setFirstName("chaima");
            employee1.setLastName("hajji");
            employee1.setEmail("chaima@gmail.com");

            Employee employee2 = new Employee();
            employee2.setFirstName("Reda");
            employee2.setLastName("Hajji");
            employee2.setEmail("reda@gmail.com");

            employeeRepository.save(employee1);
            employeeRepository.save(employee2);

        };
    }
}
