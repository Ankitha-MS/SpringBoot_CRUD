package org.jsp.student_crud.repository;


import java.util.List;

import org.jsp.student_crud.dto.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Students,Integer>
{

    List<Students> findByName(String name);

    Students findByEmail(String email);

    Students findByMobile(long mobile);
    
    List<Students> findByPercentageGreaterThan(double percentage);

    List<Students> findByPercentageLessThanEqual(double percentage);

    List<Students> findByResult(String result);

    @Query("select x from Students x where maths >= ?1 and english >= ?1 ")
	List<Students> findByMathsEnglishGreater(int marks);
  

    
}
