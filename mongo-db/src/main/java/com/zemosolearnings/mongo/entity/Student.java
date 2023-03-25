package com.zemosolearnings.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "student")
public class Student {
	@Id
	private String id;
	private String name;
	@Field(name = "mail")
	private String email;
	private Department department;
	private List<Subject> subjects;
	@Transient
	private double percentage;

	public double getPercentage() {
		if (subjects != null && subjects.size() > 0) {
			int total = 0;
			for (Subject subject : subjects) {
				total += subject.getMarksObtained();
			}
			return total / subjects.size();
		}
		return 0.00;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", department=" + department +
				", subjects=" + subjects +
				'}';
	}
}
