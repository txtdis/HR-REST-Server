package ph.txtdis.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.NoArgsConstructor;
import ph.txtdis.domain.Employee;
import ph.txtdis.repository.EmployeeRepository;

@RestController
@NoArgsConstructor
public class EmployeeController {

    @Autowired
    EmployeeRepository repository;

    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public ResponseEntity<?> saveEmployee(@RequestBody Employee e) {
        Employee employee = repository.save(e);
        URI uri = buildURI(employee);
        return new ResponseEntity<Employee>(employee, httpHeaders(uri),
                HttpStatus.CREATED);
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public ResponseEntity<?> listEmployees() {
        Iterable<Employee> employees = repository.findAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @RequestMapping(value = "/employees/search", method = RequestMethod.GET)
    public ResponseEntity<?> searchEmployees(
            @RequestParam("name") String name) {
        List<Employee> employees = repository
                .findBySurnameContainingOrNameContainingOrderBySurnameAsc(name,
                        name);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findEmployee(@PathVariable Long id) {
        Employee employee = repository.findOne(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        repository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/employees/last", method = RequestMethod.GET)
    public ResponseEntity<?> lastEmployee() {
        Employee employee = repository.findFirstByOrderByIdDesc();
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @RequestMapping(value = "/employees/first", method = RequestMethod.GET)
    public ResponseEntity<?> firstEmployee() {
        Employee employee = repository.findFirstByOrderByIdAsc();
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @RequestMapping(value = "/employees/next", method = RequestMethod.GET)
    public ResponseEntity<?> nextEmployee(@RequestParam("id") Long id) {
        Employee employee = repository.findFirstByIdGreaterThanOrderByIdAsc(id);
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @RequestMapping(value = "/employees/previous", method = RequestMethod.GET)
    public ResponseEntity<?> previousEmployee(@RequestParam("id") Long id) {
        Employee employee = repository.findFirstByIdLessThanOrderByIdDesc(id);
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    private URI buildURI(Employee employee) {
        return employee == null ? null
                : ServletUriComponentsBuilder.fromCurrentContextPath().path(
                        "/employees/{id}").buildAndExpand(employee.getId())
                        .toUri();
    }

    private MultiValueMap<String, String> httpHeaders(URI uri) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);
        return httpHeaders;
    }
}