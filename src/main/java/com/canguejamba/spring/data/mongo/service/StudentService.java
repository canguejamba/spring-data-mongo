/**
 * Created by IntelliJ IDEA.
 * User: Cangue jamba
 * Project name: spring-data-mongo
 * To change this template use File | Settings | File and Code Templates.
 */

package com.canguejamba.spring.data.mongo.service;

import com.canguejamba.spring.data.mongo.domain.Student;
import com.canguejamba.spring.data.mongo.exception.StudentCollectionException;
import com.canguejamba.spring.data.mongo.repository.IStudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService implements IStudentService {

    @Autowired
    private final IStudentRepository repository;

    public StudentService(IStudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createStudent(Student student) throws ConstraintViolationException,StudentCollectionException {

        Optional<Student> optionalStudentEmail = repository.findByEmail(student.getEmail());


        if (optionalStudentEmail.isPresent()) {
            System.out.println(optionalStudentEmail.get());
            throw new StudentCollectionException(StudentCollectionException.studentAlreadyExists());

        }
        else {

            String studentId = UUID.randomUUID().toString();

            student.setId(studentId);
            student.setFullName( student.getFirstname()+" "+ student.getLastname());
            student.setCreated(LocalDateTime.now());
            repository.save(student);
        }

    }

    @Override
    public void updateStudentById(String studentId, Student newStudent) throws StudentCollectionException {
        Optional<Student> optionalStudentWithId = repository.findById(studentId);
        Optional<Student> optionalStudentWithEmail = repository.findByEmail(newStudent.getEmail());
        if (optionalStudentWithId.isPresent()) {
            if (optionalStudentWithEmail.isPresent() && !optionalStudentWithEmail.get().getId().equals(studentId)) {
                throw new StudentCollectionException(StudentCollectionException.studentAlreadyExists());
            }

            Student studentToUpdate = optionalStudentWithId.get();
            BeanUtils.copyProperties(newStudent,studentToUpdate);

            studentToUpdate.setId(studentId);
            repository.save(studentToUpdate);
        }
        else {
            throw new StudentCollectionException(StudentCollectionException.notFoundException(studentId));
        }

    }

    @Override
    public void deleteStudentById(String studentId) throws StudentCollectionException {
        Optional<Student> optionalStudentWithId = repository.findById(studentId);
        if (optionalStudentWithId.isEmpty()) {
            throw new StudentCollectionException(StudentCollectionException.notFoundException(studentId));

        }else {
            repository.deleteById(studentId);
        }
    }

    @Override
    public List<Student> getAllStudent() {
        List<Student> findAllStudentListSize = repository.findAll();
        if (findAllStudentListSize.size() > 0) {
            return findAllStudentListSize;
        }else {
            return new ArrayList<>();
        }
    }
}
