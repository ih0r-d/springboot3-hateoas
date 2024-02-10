package com.example.controller;


import com.example.common.Constants;
import com.example.documents.Student;
import com.example.dto.StudentDto;
import com.example.dto.StudentRefDto;
import com.example.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.constant.Constable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/students"
//        ,
//        consumes = MediaType.APPLICATION_JSON_VALUE,
//        produces = MediaType.APPLICATION_JSON_VALUE
)
@ResponseBody
@RequiredArgsConstructor
public class StudentRestApiController {

    private final StudentService studentService;

    @GetMapping(produces = {Constants.APPLICATION_JSON_HAL_VALUE})
    public ResponseEntity<CollectionModel<StudentRefDto>> getAllStudentRefList() {
        List<StudentRefDto> studentRefList = studentService.getAllStudentRefList();

        CollectionModel<StudentRefDto> studentRefCollectionModel = null;
        if (!studentRefList.isEmpty()) {
            final Link link = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder.methodOn(StudentRestApiController.class)
                            .getAllStudentRefList())
                    .withSelfRel();
            studentRefCollectionModel = CollectionModel.of(studentRefList, link);
        }

        return Optional.ofNullable(studentRefCollectionModel)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/{id}", produces = {Constants.APPLICATION_JSON_HAL_VALUE})
    public ResponseEntity<StudentDto> getStudentById(@PathVariable final String id) {
        final StudentDto student = studentService.getStudentById(id);

        return Optional.ofNullable(student)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto dto) {
        return ResponseEntity.ok(studentService.createStudent(dto));
    }
}
