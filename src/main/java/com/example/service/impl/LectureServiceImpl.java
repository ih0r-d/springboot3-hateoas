package com.example.service.impl;

import com.example.controller.LectureRestApiController;
import com.example.documents.Lecture;
import com.example.dto.LectureDto;
import com.example.dto.LectureRefDto;
import com.example.mapper.LectureMapper;
import com.example.repository.LectureRepository;
import com.example.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService {

    private final LectureRepository lectureRepository;
    private final LectureMapper lectureMapper;

    @Override
    public List<LectureRefDto> getAllLectureRefList() {
        List<Lecture> lectures = lectureRepository.findAll();
        if (lectures.isEmpty()) {
            return null;
        }
        List<LectureRefDto> refList = lectureMapper.toDtoRefList(lectures);
        refList.forEach(ref -> {
            var selfLink = WebMvcLinkBuilder.linkTo(LectureRestApiController.class)
                    .slash(ref.getId())
                    .withSelfRel();
            ref.add(selfLink);
        });
        return refList;
    }

    @Override
    public LectureDto getLectureById(String lectureId) {
        return null;
    }

    @Override
    public LectureDto createLecture(LectureDto lecture) {
        return null;
    }
}
