package com.zemosolearnings.mongo.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private String id;
    private String name;
    private String email;
    private DepartmentDTO department;
    private List<SubjectDTO> subjects;
    private double percentage;

}
