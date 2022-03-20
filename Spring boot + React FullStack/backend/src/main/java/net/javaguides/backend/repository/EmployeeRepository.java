package net.javaguides.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.backend.model.Employee;

@Repository
// JpaRepository = 데이터를 찾는 많을 방법과 정렬 지원 제공
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
