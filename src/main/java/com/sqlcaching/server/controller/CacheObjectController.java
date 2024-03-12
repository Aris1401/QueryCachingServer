package com.sqlcaching.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sqlcaching.server.dto.CacheObjectRequest;
import com.sqlcaching.server.entity.CacheObject;
import com.sqlcaching.server.service.CacheObjectService;

@RestController
@RequestMapping("/api/v1/caching")
public class CacheObjectController {
    @Autowired
    private CacheObjectService cacheObjectService;

    @GetMapping("")
    public List<CacheObject> getAllCaches() {
        return cacheObjectService.getAllCachedObjects();
    }

    @GetMapping(name = "", params = {"query"})
    public CacheObject getCacheByQuery(@RequestParam String query) {
        return cacheObjectService.getCacheObjectByQuery(query);
    }

    @PostMapping("")
    public CacheObject saveInCache(@RequestBody CacheObjectRequest cacheObjectRequest) {
        return cacheObjectService.saveCache(cacheObjectRequest.getQuery(), 
                                            cacheObjectRequest.getData(), 
                                            cacheObjectRequest.getLifetimeDuration());
    }

    @DeleteMapping("")
    public void deleteCache(@RequestParam String query) {
        cacheObjectService.deleteCache(query);
    }

    @PostMapping("/clean")
    public void cleanCache() {
        cacheObjectService.cleanCache();
    }
}
