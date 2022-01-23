/**
 * Created by IntelliJ IDEA.
 * User: Cangue jamba
 * Project name: spring-data-mongo
 * To change this template use File | Settings | File and Code Templates.
 */

package com.canguejamba.spring.data.mongo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {
    private String country;
    private String city;
    private String postcode;

}
