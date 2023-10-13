package org.jsp.student_crud.helper;

import lombok.Data;

@Data
public class ResponseStrucrure<X> {
     
    public static final String HttpStatus = null;
    String message;
    int status;
    X data;
    
}
