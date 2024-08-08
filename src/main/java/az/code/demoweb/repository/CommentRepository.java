package az.code.demoweb.repository;

import az.code.demoweb.model.Comment;

import java.util.List;

public interface CommentRepository {
    Comment save(Comment comment);
    Comment findById(Long id);
    List<Comment> findById(String name);
    List<Comment> findByType(String category);
    Comment deleteById(Long id);
}
