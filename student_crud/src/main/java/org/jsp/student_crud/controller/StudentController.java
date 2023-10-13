package org.jsp.student_crud.controller;

import java.util.List;

import org.jsp.student_crud.dto.Students;
import org.jsp.student_crud.helper.ResponseStrucrure;
import org.jsp.student_crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
 
@RestController
public class StudentController {
    
    @Autowired
    StudentService service;
    
    @PostMapping("/students")
    public ResponseEntity<ResponseStrucrure<Students>> save(@RequestBody Students students)
    {
        // return service.save(students);
        return new ResponseEntity<ResponseStrucrure<Students>>(service.save(students),HttpStatus.CREATED);
   
    }
    @PostMapping("/students/many")
    public ResponseEntity<ResponseStrucrure<List <Students>>> saveAll(@RequestBody List<Students> students)
    {
        return new ResponseEntity<ResponseStrucrure <List<Students>>>(service.saveAll(students),HttpStatus.CREATED);
    }
    @Operation(summary = "Fetch all records")
    @GetMapping("/students")
    public ResponseEntity<ResponseStrucrure<List <Students>>> findAll()
    {
        return new ResponseEntity<ResponseStrucrure <List<Students>>>(service.findAll(),HttpStatus.FOUND);
    }
    // fetch by id
    @GetMapping("/students/{id}")//path varialble  "/students/id?id=1" this is Requestparam
    public ResponseEntity<ResponseStrucrure<Students>> findById(@PathVariable int id)
    {
        return new ResponseEntity<ResponseStrucrure <Students>>(service.findById(id),HttpStatus.FOUND);
    }

    @GetMapping("/students/name")
        public ResponseEntity<ResponseStrucrure<List<Students>>> findByName(@RequestParam String name)
        {
            return new ResponseEntity<ResponseStrucrure <List<Students>>>(service.findByName(name),HttpStatus.FOUND);

        }
        @GetMapping("students/mobile/{mobile}")
        public ResponseEntity<ResponseStrucrure<Students>> findByMobile(@PathVariable long mobile)
        {
            return new ResponseEntity<ResponseStrucrure <Students>>(service.findByMobile(mobile),HttpStatus.FOUND);
        }

    @GetMapping("/students/email/{email}")
    public ResponseEntity<ResponseStrucrure<Students>> findByEmail(@PathVariable String email)
        {
            return new ResponseEntity<ResponseStrucrure <Students>>(service.findByEmail(email),HttpStatus.FOUND);

        }

        @GetMapping("/students/percentage/greater/{percentage}")
         public ResponseEntity<ResponseStrucrure<List<Students>>> findByPercentageGreaterThan(@PathVariable double percentage)
        {
            return new ResponseEntity<ResponseStrucrure <List<Students>>>(service.findByPercentageGreaterThan(percentage),HttpStatus.FOUND);

        }

        @GetMapping("/students/percentage/lesser/{percentage}")
         public ResponseEntity<ResponseStrucrure<List<Students>>> findByPercentageLesserThan(@PathVariable double percentage)
        {
            return new ResponseEntity<ResponseStrucrure <List<Students>>>(service.findByPercentageLesserThan(percentage),HttpStatus.FOUND);

        }

        @GetMapping("/students/result/{result}")
         public ResponseEntity<ResponseStrucrure<List<Students>>> findByResult(@PathVariable String result)
        {
            return new ResponseEntity<ResponseStrucrure <List<Students>>>(service.findByResult(result),HttpStatus.FOUND);

        }
        @GetMapping("/students/percentage/maths/english/greater/{marks}")
        public ResponseEntity<ResponseStrucrure<List<Students>>>findByMathsEnglishGreater(@PathVariable int marks)
        {
            return new ResponseEntity<ResponseStrucrure <List<Students>>>(service.findByMathsEnglishGreater(marks),HttpStatus.FOUND);

        }
        @DeleteMapping("/students/{id}")
        public ResponseEntity<ResponseStrucrure<Students>>delete(@PathVariable int id)
        {
            return new ResponseEntity<ResponseStrucrure <Students>>(service.delete(id),HttpStatus.FOUND);
        }

        @PutMapping("/students")
        public ResponseEntity<ResponseStrucrure<Students>>update(@RequestBody Students students)
        {
            return  new ResponseEntity<ResponseStrucrure <Students>>(service.update(students),HttpStatus.OK);
        }

}
