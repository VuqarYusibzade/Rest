package az.code.demoweb.repository.impl;

import az.code.demoweb.model.Comment;
import az.code.demoweb.repository.CommentRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentRepositoryImpl implements CommentRepository {
    Map<Long, Comment> map=new HashMap<>();

    @Override
    public Comment save(Comment comment) {
        return null;
    }

    @Override
    public Comment findById(Long id) {
        return null;
    }

    @Override
    public List<Comment> findById(String name) {
        return null;
    }

    @Override
    public List<Comment> findByType(String category) {
        return null;
    }

    @Override
    public Comment deleteById(Long id) {
        return null;
    }
}
