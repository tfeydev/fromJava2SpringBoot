package br.dev.techthor.cruddemo.dao;

import br.dev.techthor.cruddemo.entity.Student;

public interface StudentDAO {

    void save(Student student);

    Student findById(Integer id);
}
