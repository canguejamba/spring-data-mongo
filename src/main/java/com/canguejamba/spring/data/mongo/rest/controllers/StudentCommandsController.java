/**
 * Created by IntelliJ IDEA.
 * User: Cangue jamba
 * Project name: spring-data-mongo
 * To change this template use File | Settings | File and Code Templates.
 */

package com.canguejamba.spring.data.mongo.rest.controllers;

import com.canguejamba.spring.data.mongo.domain.Student;
import com.canguejamba.spring.data.mongo.exception.StudentCollectionException;
import com.canguejamba.spring.data.mongo.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/student")
public class StudentCommandsController {

    @Autowired
    private final IStudentService studentService;

    public StudentCommandsController(IStudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Student> postNewStudent(@Valid @RequestBody Student student){
        try {

            studentService.createStudent(student);
            return new ResponseEntity("Successfully added student "+ student.getEmail(), HttpStatus.OK);

        }catch (ConstraintViolationException violationException){
            return new ResponseEntity(violationException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }catch (StudentCollectionException studentCollectionException){
            return new ResponseEntity(studentCollectionException.getMessage(), HttpStatus.CONFLICT);

        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{studentId}")
    public ResponseEntity<Student> putStudentById(@PathVariable(value = "studentId") String studentId, @Valid @RequestBody Student newStudent){
        try {

            studentService.updateStudentById(studentId, newStudent);
            return new ResponseEntity("Successfully update student full Name: "+ newStudent.getFullName(), HttpStatus.OK);

        }catch (ConstraintViolationException violationException){
            return new ResponseEntity(violationException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }catch (StudentCollectionException studentCollectionException){
            return new ResponseEntity(studentCollectionException.getMessage(), HttpStatus.NOT_FOUND);

        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{studentId}")
    public ResponseEntity<Student> deleteStudentById(@PathVariable(value = "studentId") String studentId){
        try {

            studentService.deleteStudentById(studentId);
            return new ResponseEntity("Successfully deleted student id "+ studentId, HttpStatus.OK);

        }catch (StudentCollectionException studentCollectionException){
            return new ResponseEntity(studentCollectionException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
