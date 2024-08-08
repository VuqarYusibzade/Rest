package az.code.demoweb.service.impl;


import az.code.demoweb.dto.CommentDto;
import az.code.demoweb.dto.StudentDto;
import az.code.demoweb.model.Comment;
import az.code.demoweb.model.Student;
import az.code.demoweb.repository.StudentRepository;
import az.code.demoweb.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService<StudentDto> {
    private final StudentRepository studentRepository;


    @Override
    public StudentDto save(StudentDto studentDto) {
        Student student = Student.builder()
                .id(studentDto.getId())
                .name(studentDto.getName())
                .surname(studentDto.getSurname())
                .comments(new ArrayList<>())
                .build();

        student  = studentRepository.save(student);
        return convertToDto(student);
    }

    @Override
    public StudentDto findById(Long id) {
        Student student = studentRepository.findById(id);

        return convertToDto(student);
    }

    @Override
    public List<StudentDto> findByName(String name) {
        List<Student> students = studentRepository.findByName(name);

        return students.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> findBySurname(String surname) {
        List<Student> bySurname = studentRepository.findBySurname(surname);

        return bySurname.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public StudentDto deleteById(Long id) {
        Student student = studentRepository.deleteById(id);

        return convertToDto(student);
    }

    @Override
    public StudentDto addComment(Long studentId, CommentDto commentDto) {
        Student student = studentRepository.findById(studentId);
        if (student == null) {
            throw new RuntimeException("Student not found with ID: " + studentId);
        }

       Comment comment = Comment.builder()
                .id(commentDto.getId())
                .name(commentDto.getName())
                .build();
        log.info("{},",commentDto);

        student.addComment(comment);

        Student newStudent = studentRepository.save(student);

        return convertToDto(newStudent);
    }

    private StudentDto convertToDto(Student student) {
        if (student == null) {
            return null;
        }
        StudentDto studentDTO = new StudentDto();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setSurname(student.getSurname());
        studentDTO.setComments(student.getComments());
        return studentDTO;
    }


    private CommentDto convertCommentToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(commentDto.getId());
        commentDto.setName(commentDto.getName());
        return commentDto;
    }
}