package ph.txtdis.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ph.txtdis.domain.Employee;

@Repository
public interface EmployeeRepository extends SpunRepository<Employee, Long> {

    List<Employee> findBySurnameContainingOrNameContainingOrderBySurnameAsc(
            @Param("surname") String surname, @Param("name") String name);
}
