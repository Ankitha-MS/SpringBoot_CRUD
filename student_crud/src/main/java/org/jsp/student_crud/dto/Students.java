package org.jsp.student_crud.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
// @Component  it is optional
public class Students {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String name;
    private String email;
    private long mobile;
    private int maths;
    private int english;
    private int science;
    private double percentage;
    private String result;
}
