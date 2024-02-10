package com.example.mapper;


import com.example.documents.Student;
import com.example.dto.StudentDto;
import com.example.dto.StudentRefDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Objects;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {LectureMapper.class}
)
@MapperConfig(unmappedTargetPolicy = ReportingPolicy.ERROR, unmappedSourcePolicy = ReportingPolicy.ERROR)
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mappings(value={
            @Mapping(target = "id", ignore = true)
    })
    Student toEntity(StudentDto student);

    StudentDto toDto(Student student);

    List<Student> toEntityList(List<StudentDto> dtos);

    List<StudentDto> toDtoList(List<Student> students);

    @BeanMapping(ignoreUnmappedSourceProperties = {"name", "lectures"})
    StudentRefDto toDtoRef(Student student);

    List<StudentRefDto> toDtoRefList(List<Student> students);

    @AfterMapping
    default void setEntityId(StudentDto dto, @MappingTarget Student student) {
        if(Objects.nonNull(dto.getId()))
            student.setId(student.getId());
    }

}