package com.cavalari.studentservices;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, Long> {	

	List<Student> findByNameContaining(String name);
	
	@Query("SELECT s FROM Student s WHERE MONTH(s.dateOfBirth) = :month")
	List<Student> findByDateOfBirthAtCurrentMonth(@Param("month") Integer month);
	
}
