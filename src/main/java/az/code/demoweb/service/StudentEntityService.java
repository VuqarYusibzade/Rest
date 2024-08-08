package az.code.demoweb.service;

import az.code.demoweb.dto.CommentDto;
import az.code.demoweb.dto.StudentDto;
import az.code.demoweb.model.Comment;
import az.code.demoweb.model.Student;
import az.code.demoweb.repository.StudentEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentEntityService implements StudentService<StudentDto> {
    private final StudentEntityRepository studentEntityRepository;

    @Override
    public StudentDto save(StudentDto studentDto) {
        Student student = mapDtoToEntity(studentDto);
        student = studentEntityRepository.save(student);
        return mapEntityToDto(student);
    }

    @Override
    public StudentDto findById(Long id) {
        Optional<Student> studentOptional = studentEntityRepository.findById(id);
        return studentOptional.map(this::mapEntityToDto).orElse(null);
    }

    @Override
    public List<StudentDto> findByName(String name) {
        List<Student> students = studentEntityRepository.findByName(name);
        return students.stream().map(this::mapEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> findBySurname(String surname) {
        List<Student> students = studentEntityRepository.findBySurname(surname);
        return students.stream().map(this::mapEntityToDto).collect(Collectors.toList());
    }

    @Override
    public StudentDto deleteById(Long id) {
        Optional<Student> deletedStudent = studentEntityRepository.findById(id);
        if (deletedStudent.isPresent()) {
            studentEntityRepository.deleteById(id);
            return mapEntityToDto(deletedStudent.get());
        } else {
            throw new RuntimeException("Student with id " + id + " not found.");
        }
    }

    @Override
    public StudentDto addComment(Long studentId, CommentDto commentDto) {
        Optional<Student> studentOptional = studentEntityRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            Comment comment = new Comment();
            comment.setName(commentDto.getName());
            student.getComments().add(comment);
            student = studentEntityRepository.save(student);
            return mapEntityToDto(student);
        } else {
            return null;
        }
    }

    private StudentDto mapEntityToDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setSurname(student.getSurname());
        studentDto.setComments(student.getComments());
        return studentDto;
    }

    private Student mapDtoToEntity(StudentDto studentDto) {
        Student student = new Student();
        student.setId(studentDto.getId());
        student.setName(studentDto.getName());
        student.setSurname(studentDto.getSurname());
        student.setComments(studentDto.getComments());
        return student;
    }
}
