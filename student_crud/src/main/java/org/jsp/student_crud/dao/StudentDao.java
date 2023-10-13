package org.jsp.student_crud.dao;

import java.util.List;

import org.jsp.student_crud.dto.Students;
import org.jsp.student_crud.exception.DataNotFoundException;
import org.jsp.student_crud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao {
   @Autowired
   StudentRepository repository;

   public Students save(Students students) {
      return repository.save(students);
   }

   public List<Students> saveAll(List<Students> students) {
      return repository.saveAll(students);
   }

   public List<Students> findAll() {
      return repository.findAll();
   }

   public Students findById(int id) {
      return repository.findById(id).orElseThrow(() -> {
         throw new DataNotFoundException("Data Not Found With id" + id);
      });
   }

   public Students findByMobile(long mobile) {
      return repository.findByMobile(mobile);
   }

   public List<Students> findByName(String name) {
      return repository.findByName(name);
   }

   public Students findByEmail(String email) {
      return repository.findByEmail(email);
   }

   public List<Students> findByPercentageGreaterThanEqual(double percentage) {
      return repository.findByPercentageGreaterThan(percentage);
   }

   public List<Students> findByPercentageLesserThanEqual(double percentage) {
      return repository.findByPercentageLessThanEqual(percentage);
   }

   public List<Students> findByResult(String result) {
      return repository.findByResult(result);
   }

   public List<Students> findByMathsEnglishGreater(int marks) {
      return repository.findByMathsEnglishGreater(marks);
   }

public void delete(Students findById) {
    repository.delete(findById);
}

public Students update(Students students) {
   return repository.save(students);
}

}
