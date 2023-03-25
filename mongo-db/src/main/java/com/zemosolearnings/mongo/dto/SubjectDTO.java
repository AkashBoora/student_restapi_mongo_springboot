package com.zemosolearnings.mongo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO {
    private String subjectName;
    private int marksObtained;
}
