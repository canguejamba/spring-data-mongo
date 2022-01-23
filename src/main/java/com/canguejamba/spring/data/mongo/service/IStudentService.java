package com.canguejamba.spring.data.mongo.service;

import com.canguejamba.spring.data.mongo.domain.Student;
import com.canguejamba.spring.data.mongo.exception.StudentCollectionException;

import java.util.List;

public interface IStudentService {

   void createStudent(Student student) throws StudentCollectionException;
   void updateStudentById(String studentId, Student newStudent) throws StudentCollectionException;
   void deleteStudentById(String studentId) throws StudentCollectionException;

   List<Student> getAllStudent();
}
