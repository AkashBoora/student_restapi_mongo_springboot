package com.zemosolearnings.mongo.service;

import com.zemosolearnings.mongo.entity.Student;

import java.util.List;

public interface StudentService {

    Student saveStudent(Student student);

    Student getStudentByID(String id);

    List<Student> getAllStudents();

    Student updateStudent(Student student);

    void deleteStudentById(String id);

    List<Student> getAllStudentsByName(String name);

    List<Student> getAllStudentsByNameAndEmail(String name, String email);

    List<Student> getAllStudentsByNameOrEmail(String name, String email);

    List<Student> getAllStudentsByEmail(String email);

    List<Student> getAllStudentsWithPagination(int pageNo, int pageSize);

    List<Student> getAllWithSorting(String field, String direction);

    List<Student> getByDepartmentName(String departmentName);

    List<Student> getBySubjectName(String subjectName);

    List<Student> getByEmailLike(String email);
}
