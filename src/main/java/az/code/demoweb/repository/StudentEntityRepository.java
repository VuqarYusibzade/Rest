package az.code.demoweb.repository;

import az.code.demoweb.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentEntityRepository extends JpaRepository<Student, Long> {
    List<Student> findByName(String name);
    List<Student> findBySurname(String surname);
}
