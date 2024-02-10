package com.example.service;

import com.example.dto.LectureDto;
import com.example.dto.LectureRefDto;
import com.example.dto.StudentDto;
import com.example.dto.StudentRefDto;

import java.util.List;

public interface StudentService {

    List<StudentRefDto> getAllStudentRefList();

    StudentDto getStudentById(String id);

    StudentDto createStudent(StudentDto student);
}