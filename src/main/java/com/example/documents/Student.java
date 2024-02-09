package com.example.documents;

import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document(collection = "students")
public class Student implements Serializable {
    @Id
    private String id;
    private String name;
    private String grade;
    private List<Lecture> lectures;
}
