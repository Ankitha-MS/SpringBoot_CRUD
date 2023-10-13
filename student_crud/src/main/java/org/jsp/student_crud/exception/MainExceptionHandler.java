package org.jsp.student_crud.exception;

import org.jsp.student_crud.helper.ResponseStrucrure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MainExceptionHandler {
    @ExceptionHandler(DataShouldNotRepeatException.class)
    public ResponseEntity<ResponseStrucrure<String>> handle(DataShouldNotRepeatException exception)
    {
        ResponseStrucrure<String> structure=new ResponseStrucrure<String>();
      structure.setMessage(exception.getMessage());
        structure.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        structure.setData("Data Not Saved");
        return new ResponseEntity<ResponseStrucrure<String>>(structure,HttpStatus.NOT_ACCEPTABLE);

    }
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ResponseStrucrure<String>> handle(DataNotFoundException exception)
    {
      ResponseStrucrure<String> structure = new ResponseStrucrure<String>();
      structure.setMessage(exception.getMessage());
      structure.setStatus(HttpStatus.NOT_FOUND.value());
      structure.setData("Data Not Found");
      return new ResponseEntity<ResponseStrucrure<String>>(structure,HttpStatus.NOT_FOUND);
    }
    
}
