package com.example.service;

import com.example.dto.LectureDto;
import com.example.dto.LectureRefDto;

import java.util.List;

public interface LectureService {

    List<LectureRefDto> getAllLectureRefList();

    LectureDto getLectureById(String lectureId);

    LectureDto createLecture(LectureDto lecture);
}