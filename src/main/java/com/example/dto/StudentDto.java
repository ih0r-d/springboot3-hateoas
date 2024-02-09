package com.example.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.List;

@Getter @Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class StudentDto extends RepresentationModel<StudentDto> implements Serializable {
    private String id;
    private String name;
    private List<LectureDto> lectures;
}