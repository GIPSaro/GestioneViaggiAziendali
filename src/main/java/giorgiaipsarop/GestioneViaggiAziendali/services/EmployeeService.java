package giorgiaipsarop.GestioneViaggiAziendali.services;


import giorgiaipsarop.GestioneViaggiAziendali.entities.Employee;
import giorgiaipsarop.GestioneViaggiAziendali.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Optional<Employee> getEmployeeById(UUID id) {
        return employeeRepository.findById(id);
    }

    public Iterable<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee updateEmployee(UUID id, Employee employeeDetails) {
        return employeeRepository.findById(id).map(employee -> {
            employee.setUsername(employeeDetails.getUsername());
            employee.setFirstName(employeeDetails.getFirstName());
            employee.setLastName(employeeDetails.getLastName());
            employee.setEmail(employeeDetails.getEmail());
            employee.setProfileImage(employeeDetails.getProfileImage());
            return employeeRepository.save(employee);
        }).orElse(null);
    }

    public void deleteEmployee(UUID id) {
        employeeRepository.deleteById(id);
    }
}
