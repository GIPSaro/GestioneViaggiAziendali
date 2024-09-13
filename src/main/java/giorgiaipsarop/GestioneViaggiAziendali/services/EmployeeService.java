package giorgiaipsarop.GestioneViaggiAziendali.services;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import giorgiaipsarop.GestioneViaggiAziendali.entities.Employee;
import giorgiaipsarop.GestioneViaggiAziendali.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private Cloudinary cloudinary;
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
    public Employee createEmployee(Employee employee, byte[] profileImage) throws IOException {
        if (profileImage != null) {
            Map uploadResult = cloudinary.uploader().upload(profileImage, ObjectUtils.emptyMap());
            String imageUrl = (String) uploadResult.get("url");
            employee.setProfileImage(imageUrl);
        }
        return employeeRepository.save(employee);
    }
}
