package com.example.controller;


import com.example.common.Constants;
import com.example.dto.LectureDto;
import com.example.dto.LectureRefDto;
import com.example.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/lectures",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
@ResponseBody
@RequiredArgsConstructor
public class LectureRestApiController {
    private final LectureService lectureService;

    @GetMapping(produces = {Constants.APPLICATION_JSON_HAL_VALUE})
    public ResponseEntity<CollectionModel<LectureRefDto>> getAllLectureRefList() {
        var lectureRefs = lectureService.getAllLectureRefList();

        CollectionModel<LectureRefDto> lectureRefCollectionModel = null;
        if (!lectureRefs.isEmpty()) {
            final Link link = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder.methodOn(LectureRestApiController.class)
                            .getAllLectureRefList())
                    .withSelfRel();
            lectureRefCollectionModel = CollectionModel.of(lectureRefs, link);
        }

        return Optional.ofNullable(lectureRefCollectionModel)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/{id}", produces = {Constants.APPLICATION_JSON_HAL_VALUE})
    public ResponseEntity<LectureDto> getStudentById(@PathVariable final String id) {
        final LectureDto lecture = this.lectureService.getLectureById(id);

        return Optional.ofNullable(lecture)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LectureDto> createLecture(@RequestBody LectureDto lecture) {
        return ResponseEntity.ok(this.lectureService.createLecture(lecture));
    }
}
