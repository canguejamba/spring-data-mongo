/**
 * Created by IntelliJ IDEA.
 * User: Cangue jamba
 * Project name: spring-data-mongo
 * To change this template use File | Settings | File and Code Templates.
 */

package com.canguejamba.spring.data.mongo.rest.controllers;

import com.canguejamba.spring.data.mongo.domain.Student;
import com.canguejamba.spring.data.mongo.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/student")
public class StudentQueriesController {


    @Autowired
    private final IStudentService studentService;

    public StudentQueriesController(IStudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAllStudent(){
        List<Student> listWithAllStudent = studentService.getAllStudent();
        return new ResponseEntity(listWithAllStudent, listWithAllStudent.size() > 0? HttpStatus.OK: HttpStatus.NOT_FOUND);

    }

}
