/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.repository;

import com.sales.market.model.Employee;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends GenericRepository<Employee> {
    @Query(value = "Select e.* from employee e INNER JOIN contract ON e.id = contract.employee_id where active = true and position_id = :idPosition", nativeQuery = true)
    List<Employee> findEmployeeActiveByPositionId(Long idPosition);
}
