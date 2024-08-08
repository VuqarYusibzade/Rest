package az.code.demoweb.controller;

import az.code.demoweb.dto.CommentDto;
import az.code.demoweb.dto.StudentDto;
import az.code.demoweb.service.StudentEntityService;
import az.code.demoweb.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v2/students")
@Slf4j
public class StudentController {

    private final StudentService<StudentDto> studentService;

    public StudentController(@Qualifier("studentEntityService") StudentService<StudentDto> studentService) {
        this.studentService = studentService;
    }
    

//
//    @GetMapping("/name/{name}")
//    public ResponseEntity<List<StudentDto>> findStudentsByName(@PathVariable String name) {
//        List<StudentDto> students = new ArrayList<>(studentService.findByName(name));
//        return ResponseEntity.ok(students);
//    }
//
//
//    @GetMapping("/{id}")
//    public ResponseEntity<StudentDto> findStudentById(@PathVariable Long id) {
//        StudentDto studentDto = studentService.findById(id);
//        return studentDto != null ? ResponseEntity.ok(studentDto) : ResponseEntity.notFound().build();
//    }
//
//    @PostMapping
//    public ResponseEntity<StudentDto> saveStudent(@RequestBody StudentDto studentDto) {
//        StudentDto savedStudent = studentService.save(studentDto);
//        return new ResponseEntity<>(savedStudent, HttpStatusCode.valueOf(201));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<StudentDto> saveStudent(@PathVariable long id, @RequestBody StudentDto studentDto) {
//        return new ResponseEntity<>(studentDto, HttpStatusCode.valueOf(201));
//    }
//
//    @DeleteMapping
//    public ResponseEntity<Void> deleteStudentById(@RequestParam(required = false) String name, @RequestParam(required = false) String surname) {
//        return ResponseEntity.ok(null);
//
//    }
//    @PostMapping("/{studentId}/comments")
//    public ResponseEntity<StudentDto> addCommentToStudent(@PathVariable Long studentId, @RequestBody CommentDto commentDto) {
//        log.info("{}",commentDto);
//        StudentDto updatedStudent = studentService.addComment(studentId, commentDto);
//        return ResponseEntity.ok(updatedStudent);
//    }
//    @GetMapping("/comments/{commentId}")
//    public  ResponseEntity<StudentDto> findByComment(@PathVariable Long commentId ){
//        return ResponseEntity.ok(null);
//    }





    // Entity ile :
    @PostMapping
    public ResponseEntity<StudentDto> saveStudent(@RequestBody StudentDto studentDto) {
        StudentDto savedStudent = studentService.save(studentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> findStudentById(@PathVariable Long id) {
        StudentDto studentDto = studentService.findById(id);
        return studentDto != null ? ResponseEntity.ok(studentDto) : ResponseEntity.notFound().build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<StudentDto>> findStudentsByName(@PathVariable String name) {
        List<StudentDto> students = studentService.findByName(name);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/surname/{surname}")
    public ResponseEntity<List<StudentDto>> findStudentsBySurname(@PathVariable String surname) {
        List<StudentDto> students = studentService.findBySurname(surname);
        return ResponseEntity.ok(students);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudentDto> deleteStudentById(@PathVariable Long id) {
        StudentDto deletedStudent = studentService.deleteById(id);
        return deletedStudent != null ? ResponseEntity.ok(deletedStudent) : ResponseEntity.notFound().build();
    }

    @PostMapping("/{studentId}/comments")
    public ResponseEntity<StudentDto> addCommentToStudent(@PathVariable Long studentId, @RequestBody CommentDto commentDto) {
        StudentDto updatedStudent = studentService.addComment(studentId, commentDto);
        return ResponseEntity.ok(updatedStudent);
    }
}