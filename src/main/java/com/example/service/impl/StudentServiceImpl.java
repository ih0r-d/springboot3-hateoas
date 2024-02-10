package com.example.service.impl;


import com.example.controller.LectureRestApiController;
import com.example.controller.StudentRestApiController;
import com.example.documents.Student;
import com.example.dto.StudentDto;
import com.example.dto.StudentRefDto;
import com.example.mapper.StudentMapper;
import com.example.repository.StudentRepository;
import com.example.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<StudentRefDto> getAllStudentRefList() {
        List<Student> studentEntityList = studentRepository.findAll();

        if (!studentEntityList.isEmpty()) {
            List<StudentRefDto> studentRefList = studentMapper.toDtoRefList(studentEntityList);

            studentRefList.forEach(studentRef -> {
                final Link selfLink = WebMvcLinkBuilder
                        .linkTo(StudentRestApiController.class)
                        .slash(studentRef.getId())
                        .withSelfRel();
                studentRef.add(selfLink);
            });

            return  studentRefList;
        }

        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public StudentDto getStudentById(String studentId) {

        Optional<Student> studentOptional = Optional.of(this.studentRepository.findById(studentId)).orElse(null);

        if (studentOptional.isPresent()) {
            StudentDto student = studentMapper.toDto(studentOptional.get());

            final Link selfLink = WebMvcLinkBuilder.linkTo(StudentRestApiController.class).slash(student.getId()).withSelfRel();
            student.add(selfLink);

            student.getLectures().forEach(lectureRef -> {
                final Link selfLectureLink = WebMvcLinkBuilder
                        .linkTo(LectureRestApiController.class)
                        .slash(lectureRef.getId())
                        .withSelfRel();
                lectureRef.add(selfLectureLink);
            });

            return student;
        }

        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public StudentDto createStudent(StudentDto dto) {
        var student = this.studentRepository.save(this.studentMapper.toEntity(dto));
        return this.studentMapper.toDto(student);
    }
}