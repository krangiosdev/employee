package com.k9unit.employeedb.repositories;

import com.k9unit.employeedb.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*Add Custom methods here */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
