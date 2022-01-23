/**
 * Created by IntelliJ IDEA.
 * User: Cangue jamba
 * Project name: spring-data-mongo
 * To change this template use File | Settings | File and Code Templates.
 */

package com.canguejamba.spring.data.mongo.repository;

import com.canguejamba.spring.data.mongo.domain.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface IStudentRepository extends MongoRepository<Student, String> {

    @Query("{'email':?0}")
    Optional<Student> findByEmail(String email);
}
