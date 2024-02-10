package com.example.mapper;

import com.example.documents.Lecture;
import com.example.dto.LectureDto;
import com.example.dto.LectureRefDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Objects;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
@MapperConfig(unmappedTargetPolicy = ReportingPolicy.ERROR, unmappedSourcePolicy = ReportingPolicy.ERROR)
public interface LectureMapper {

    LectureMapper INSTANCE = Mappers.getMapper(LectureMapper.class);

    @Mappings(value = {@Mapping(target = "id", ignore = true)})
    Lecture toEntity(LectureDto dto);

    LectureDto toDto(Lecture lecture);

    List<Lecture> toEntityList(List<LectureDto> dtos);

    List<LectureDto> toDtoList(List<Lecture> lectures);

    @BeanMapping(ignoreUnmappedSourceProperties = {"name", "grade"})
    LectureRefDto toDtoRef(Lecture lecture);

    List<LectureRefDto> toDtoRefList(List<Lecture> lectures);

    @AfterMapping
    default void setEntityId(LectureDto dto, @MappingTarget Lecture lecture) {
        if (Objects.nonNull(dto.getId()))
            lecture.setId(lecture.getId());
    }
}