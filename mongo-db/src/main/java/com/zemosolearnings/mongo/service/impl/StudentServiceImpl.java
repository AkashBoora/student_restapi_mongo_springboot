package com.zemosolearnings.mongo.service.impl;

import com.zemosolearnings.mongo.entity.Student;
import com.zemosolearnings.mongo.exception.StudentNotFoundException;
import com.zemosolearnings.mongo.repository.StudentRepository;
import com.zemosolearnings.mongo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
	@Autowired
	StudentRepository studentRepository;


	@Override
	public Student getStudentByID(String id) {
		log.info("==================> StudentServiceImpl: getStudentByID");
		Optional<Student> result = studentRepository.findById(id);
		if (result.isPresent()) {
			return result.get();
		} else {
			throw new StudentNotFoundException("Student not found with id " + id);
		}
	}

	@Override
	public List<Student> getAllStudents() {
		try {
			log.info("==================> StudentServiceImpl: getAllStudents");
			List<Student> students = studentRepository.findAll();
			if (students.isEmpty()) {
				throw new StudentNotFoundException("No Students Found");
			}
			return students;
		} catch (Exception e) {
			throw new StudentNotFoundException(e.getMessage());
		}
	}

	@Override
	public Student updateStudent(Student student) {
		try {
			log.info("==================> StudentServiceImpl: updateStudent");
			Optional<Student> result = studentRepository.findById(student.getId());
			if (result.isEmpty()) {
				throw new StudentNotFoundException("Student not found with id " + student.getId());
			}
			return studentRepository.save(student);
		} catch (Exception e) {
			throw new StudentNotFoundException(e.getMessage());
		}
	}

	@Override
	public void deleteStudentById(String id) {
		try {
			log.info("==================> StudentServiceImpl: deleteStudentById");
			Optional<Student> result = studentRepository.findById(id);
			if (result.isEmpty()) {
				throw new StudentNotFoundException("Student not found with id " + id);
			}
			studentRepository.deleteById(id);
		} catch (Exception e) {
			throw new StudentNotFoundException(e.getMessage());
		}
	}

	@Override
	public List<Student> getAllStudentsByName(String name) {
		try {
			log.info("==================> StudentServiceImpl: getAllStudentsByName");
			List<Student> students = studentRepository.findByName(name);
			if (students.isEmpty()) {
				throw new StudentNotFoundException("No Students Found");
			}
			return students;
		} catch (Exception e) {
			throw new StudentNotFoundException(e.getMessage());
		}
	}

	@Override
	public List<Student> getAllStudentsByNameAndEmail(String name, String email) {
		try {
			log.info("==================> StudentServiceImpl: getAllStudentsByNameAndEmail");
			List<Student> students = studentRepository.findByNameAndEmail(name, email);
			if (students.isEmpty()) {
				throw new StudentNotFoundException("No Students Found");
			}
			return students;
		} catch (Exception e) {
			throw new StudentNotFoundException(e.getMessage());
		}
	}

	@Override
	public List<Student> getAllStudentsByNameOrEmail(String name, String email) {
		try {
			log.info("==================> StudentServiceImpl: getAllStudentsByNameOrEmail");
			List<Student> students = studentRepository.findByNameOrEmail(name, email);
			if (students.isEmpty()) {
				throw new StudentNotFoundException("No Students Found");
			}
			return students;
		} catch (Exception e) {
			throw new StudentNotFoundException(e.getMessage());
		}
	}

	@Override
	public List<Student> getAllStudentsByEmail(String email) {
		try {
			log.info("==================> StudentServiceImpl: getAllStudentsByEmail");
			List<Student> students = studentRepository.findByEmail(email);
			if (students.isEmpty()) {
				throw new StudentNotFoundException("No Students Found");
			}
			return students;
		} catch (Exception e) {
			throw new StudentNotFoundException(e.getMessage());
		}
	}

	@Override
	public List<Student> getAllStudentsWithPagination(int pageNo, int pageSize) {
		try {
			log.info("==================> StudentServiceImpl: getAllStudentsWithPagination");
			Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
			List<Student> students = studentRepository.findAll(pageable).getContent();
			if (students.isEmpty()) {
				throw new StudentNotFoundException("No Students Found");
			}
			return students;
		} catch (Exception e) {
			throw new StudentNotFoundException(e.getMessage());
		}
	}

	@Override
	public List<Student> getAllWithSorting(String field, String direction) {
		try {
			log.info("==================> StudentServiceImpl: getAllWithSorting");
			Sort.Direction sortDirection = direction.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
			Sort sort = Sort.by(sortDirection, field);
			List<Student> students = studentRepository.findAll(sort);
			if (students.isEmpty()) {
				throw new StudentNotFoundException("No Students Found");
			}
			return students;
		} catch (Exception e) {
			throw new StudentNotFoundException(e.getMessage());
		}
	}

	@Override
	public List<Student> getByDepartmentName(String departmentName) {
		try {
			log.info("==================> StudentServiceImpl: getByDepartmentName");
			List<Student> students = studentRepository.findByDepartmentDepartmentName(departmentName);
			if (students.isEmpty()) {
				throw new StudentNotFoundException("No Students Found");
			}
			return students;
		} catch (Exception e) {
			throw new StudentNotFoundException(e.getMessage());
		}
	}

	@Override
	public List<Student> getBySubjectName(String subjectName) {
		try {
			log.info("==================> StudentServiceImpl: getBySubjectName");
			List<Student> students = studentRepository.findBySubjectsSubjectName(subjectName);
			if (students.isEmpty()) {
				throw new StudentNotFoundException("No Students Found");
			}
			return students;
		} catch (Exception e) {
			throw new StudentNotFoundException(e.getMessage());
		}
	}

	@Override
	public List<Student> getByEmailLike(String email) {
		try {
			log.info("==================> StudentServiceImpl: getByEmailLike");
			List<Student> students = studentRepository.findByEmailIsLike(email);
			if (students.isEmpty()) {
				throw new StudentNotFoundException("No Students Found");
			}
			return students;
		} catch (Exception e) {
			throw new StudentNotFoundException(e.getMessage());
		}
	}

	@Override
	public Student saveStudent(Student student) {
		try {
			log.info("==================> StudentServiceImpl: saveStudent");
			return studentRepository.save(student);
		} catch (Exception e) {
			log.error("Can not add student");
			throw new StudentNotFoundException("Can not add student");
		}
	}
}
