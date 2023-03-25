package com.zemosolearnings.mongo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    private String departmentName;
    private String location;
}
