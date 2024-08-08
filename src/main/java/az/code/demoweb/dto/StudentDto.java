package az.code.demoweb.dto;

import az.code.demoweb.model.Comment;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data

public class StudentDto {
    private long id;
    private String name;
    private String surname;
    private List<Comment> comments;
}
