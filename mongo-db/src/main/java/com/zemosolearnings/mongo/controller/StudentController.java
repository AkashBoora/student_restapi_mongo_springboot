package com.zemosolearnings.mongo.controller;

import com.zemosolearnings.mongo.dto.StudentDTO;
import com.zemosolearnings.mongo.entity.Student;
import com.zemosolearnings.mongo.mapper.StudentMapper;
import com.zemosolearnings.mongo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
@Slf4j
public class StudentController {
	@Autowired
	StudentService studentService;

	@Autowired
	StudentMapper studentMapper;


	@PostMapping("/")
	public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
		try {
			log.info("==================> StudentController: createStudent");
			Student student = studentMapper.convertToStudent(studentDTO);
			student = studentService.saveStudent(student);
			return ResponseEntity.ok(studentMapper.convertToStudentDTO(student));
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<StudentDTO> getStudentById(@PathVariable String id) {
		try {
			log.info("==================> StudentController: getStudentById");
			Student student = studentService.getStudentByID(id);
			return ResponseEntity.ok(studentMapper.convertToStudentDTO(student));
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<StudentDTO> updateStudentById(@PathVariable String id, @RequestBody StudentDTO studentDTO) {
		try {
			log.info("==================> StudentController: updateStudentById");
			Student student = studentMapper.convertToStudent(studentDTO);
			student = studentService.updateStudent(student);
			return ResponseEntity.ok(studentMapper.convertToStudentDTO(student));
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable String id) {
		try {
			log.info("==================> StudentController: deleteStudent");
			studentService.deleteStudentById(id);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<StudentDTO>> getAllStudents() {
		try {
			log.info("==================> StudentController: getAllStudents");
			List<Student> students = studentService.getAllStudents();
			return new ResponseEntity<>(students.stream()
					.map(studentMapper::convertToStudentDTO)
					.collect(Collectors.toList()), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("")
	public ResponseEntity<List<StudentDTO>> getStudentsByWithParams(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "email", required = false) String email, @RequestParam(value = "type", required = false) String type) {
		try {
			log.info("==================> StudentController: getStudentsByWithParams");
			log.info(name + " " + email + " " + type);
			List<Student> students;
			if (email != null && name != null && type.equalsIgnoreCase("OR")) {
				students = studentService.getAllStudentsByNameOrEmail(name, email);
			} else if (email != null && name != null && type.equalsIgnoreCase("AND")) {
				students = studentService.getAllStudentsByNameAndEmail(name, email);
			} else if (email != null) {
				students = studentService.getAllStudentsByEmail(email);
			} else {
				students = studentService.getAllStudentsByName(name);
			}
			return new ResponseEntity<>(students.stream()
					.map(studentMapper::convertToStudentDTO)
					.collect(Collectors.toList()), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/allWIthPagination")
	public ResponseEntity<List<StudentDTO>> getAllStudentsWithPagination(@RequestParam int pageNo, @RequestParam int pageSize) {
		try {
			log.info("==================> StudentController: getAllStudentsWithPagination");
			List<Student> students = studentService.getAllStudentsWithPagination(pageNo, pageSize);
			return new ResponseEntity<>(students.stream()
					.map(studentMapper::convertToStudentDTO)
					.collect(Collectors.toList()), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/allWithSorting")
	public ResponseEntity<List<StudentDTO>> allWithSorting(@RequestParam String field, @RequestParam String direction) {
		try {
			log.info("==================> StudentController: allWithSorting");
			List<Student> students = studentService.getAllWithSorting(field, direction);
			return new ResponseEntity<>(students.stream()
					.map(studentMapper::convertToStudentDTO)
					.collect(Collectors.toList()), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/byDepartmentName")
	public ResponseEntity<List<StudentDTO>> byDepartmentName(@RequestParam String departmentName) {
		try {
			log.info("==================> StudentController: createStudent");
			List<Student> students = studentService.getByDepartmentName(departmentName);
			return new ResponseEntity<>(students.stream()
					.map(studentMapper::convertToStudentDTO)
					.collect(Collectors.toList()), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/bySubjectName")
	public ResponseEntity<List<StudentDTO>> bySubjectName(@RequestParam String subjectName) {
		try {
			log.info("==================> StudentController: bySubjectName");
			List<Student> students = studentService.getBySubjectName(subjectName);
			return new ResponseEntity<>(students.stream()
					.map(studentMapper::convertToStudentDTO)
					.collect(Collectors.toList()), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/emailLike")
	public ResponseEntity<List<StudentDTO>> emailLike(@RequestParam String email) {
		try {
			log.info("==================> StudentController: emailLike");
			List<Student> students = studentService.getByEmailLike(email);
			return new ResponseEntity<>(students.stream()
					.map(studentMapper::convertToStudentDTO)
					.collect(Collectors.toList()), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
}
