package az.code.demoweb.service;

import az.code.demoweb.dto.CommentDto;
import az.code.demoweb.model.Comment;
import az.code.demoweb.model.Student;

import java.util.List;

public interface StudentService <T>{
    T save(T studentDto);
    T findById(Long id);
    List<T> findByName(String name);
    List<T> findBySurname(String surname);
    T deleteById(Long id);
    T addComment(Long studentId, CommentDto commentDto);
}
