package com.sqlcaching.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sqlcaching.server.entity.CacheObject;

public interface CacheObjectRepository extends MongoRepository<CacheObject, String>{
    CacheObject findByQuery(String query);
}
