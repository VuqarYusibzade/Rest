package az.code.demoweb.repository.impl;

import az.code.demoweb.model.Student;
import az.code.demoweb.repository.StudentRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository

public class StudentRepositoryImpl implements StudentRepository {
    Map<Long, Student> map = new HashMap<>();
    @Override
    public Student save(Student student) {
        map.put(student.getId(), student);
        return student;
    }

    @Override
    public Student findById(Long id) {
        return map.get(id);
    }

    @Override
    public List<Student> findByName(String name) {
        List<Student> resultList = new ArrayList<>();
        for (Student student : map.values()) {
            if (student.getName().equals(name)){
                resultList.add(student);
            }
        }
        return resultList;
    }

    @Override
    public List<Student> findBySurname(String surname) {
        ArrayList<Student> resultListU = new ArrayList<>();
        for (Student student: map.values()) {
            if (student.getSurname().equals(surname)){
                resultListU.add(student);
            }
        }
        return resultListU;
    }

    @Override
    public Student deleteById(Long id) {
        return map.remove(id);
    }
}
