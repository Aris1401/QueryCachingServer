package com.sqlcaching.server.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Document
@Data
public class CacheObject {
    @Id
    String id;

    // Key
    String query;
    Date dateCreation;
    Date dateExpiration;
    double cacheDuration;
    
    String data;
}
