package com.springboot.h2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.h2.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

}
