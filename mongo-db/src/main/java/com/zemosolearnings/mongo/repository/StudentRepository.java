package com.zemosolearnings.mongo.repository;

import com.zemosolearnings.mongo.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
    public List<Student> findByName(String name);

    List<Student> findByNameAndEmail(String name, String mail);

    List<Student> findByNameOrEmail(String name, String email);

    List<Student> findByEmail(String email);

    List<Student> findByDepartmentDepartmentName(String departmentName);

    List<Student> findBySubjectsSubjectName(String subjectName);

    List<Student> findByEmailIsLike(String email);
}
