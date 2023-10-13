package org.jsp.student_crud.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DataNotFoundException extends RuntimeException{
    String message="Data not found";
}
