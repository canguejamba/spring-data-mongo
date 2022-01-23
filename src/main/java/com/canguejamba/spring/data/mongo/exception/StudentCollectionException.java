package com.canguejamba.spring.data.mongo.exception;

public class StudentCollectionException extends Exception{

    public StudentCollectionException(String message) {
        super(message);
    }

    public static String notFoundException(String id){
        return "Student with " +id + " not found";
    }

    public static String studentAlreadyExists(){
        return "Student with give email already exists";
    }
}
