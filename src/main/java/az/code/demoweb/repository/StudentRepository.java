package az.code.demoweb.repository;
import az.code.demoweb.model.Student;

import java.util.List;

public interface StudentRepository {
    Student save(Student student);
    Student findById(Long id);
    List<Student> findByName(String name);
    List<Student> findBySurname(String surname);
    Student deleteById(Long id);
}
