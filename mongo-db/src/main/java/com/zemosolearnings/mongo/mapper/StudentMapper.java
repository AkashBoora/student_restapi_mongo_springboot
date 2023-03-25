package com.zemosolearnings.mongo.mapper;

import com.zemosolearnings.mongo.dto.StudentDTO;
import com.zemosolearnings.mongo.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

@Slf4j
public class StudentMapper {
    ModelMapper modelMapper;

    public StudentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public StudentDTO convertToStudentDTO(Student student){
        log.info(" >>> INSIDE StudentMapper: convertToStudentDTO");
        return modelMapper.map(student,StudentDTO.class);
    }

    public Student convertToStudent(StudentDTO studentDTO) {
        log.info(" >>> INSIDE StudentMapper: convertToStudent");
        return modelMapper.map(studentDTO, Student.class);
    }
}
