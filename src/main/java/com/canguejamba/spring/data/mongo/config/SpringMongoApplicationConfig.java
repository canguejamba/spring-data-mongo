/**
 * Created by IntelliJ IDEA.
 * User: Cangue jamba
 * Project name: spring-data-mongo
 * To change this template use File | Settings | File and Code Templates.
 */

package com.canguejamba.spring.data.mongo.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import java.util.Collection;
import java.util.Collections;

@Configuration
public class SpringMongoApplicationConfig extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.host:localhost}")
    private String mongoHost;

    @Value("${spring.data.mongodb.port:27017}")
    private int mongoPort;

    @Value("${spring.data.mongodb.database:canguejamba}")
    private String mongoDatabase;


    @Override
    @Bean
    public MongoClient mongoClient () {
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/canguejamba");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(mongoClientSettings);

    }
    @Override
    public Collection<String> getMappingBasePackages() {
        return Collections.singleton("com.canguejamba");
    }

    @Override
    protected String getDatabaseName() {
        return "canguejamba";
    }
}
