package com.sqlcaching.server.singleton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.sqlcaching.server.entity.CacheObject;

@Component
public class CacheManager {
    private static CacheManager instance;

    public static CacheManager getInstance() {
        if (instance == null) {
            CacheManager cacheManager = new CacheManager();
            instance = cacheManager;
        }

        return instance;
    }

    // FIELDS
    HashMap<String, CacheObject> cache;
    HashMap<String, CacheObject> tempCache;
    boolean isLoaded = false;

    public List<CacheObject> allCache() {
        List<CacheObject> caches = new ArrayList<>();

        caches.addAll((Collection<? extends CacheObject>) cache.entrySet().stream().map((object) -> {
            return object;
        }).collect(Collectors.toList()));

        caches.addAll((Collection<? extends CacheObject>) tempCache.entrySet().stream().map((object) -> {
            return object;
        }).collect(Collectors.toList()));

        return caches;
    }

    public void loadCache(HashMap<String, CacheObject> cacheToLoad) {
        cache = cacheToLoad;
        isLoaded = true;
    }

    public void addToCache(CacheObject cacheObject) {
        if (tempCache == null) tempCache = new HashMap<>();
        tempCache.put(cacheObject.getQuery(), cacheObject);
    }

    public CacheObject getFromCache(String query) {
        if (cache.containsKey(query)) return cache.get(query);
        if (tempCache.containsKey(query)) return tempCache.get(query);

        return null;
    }

    public void addToCache(CacheObject cacheObject, boolean isTemp) {
        if (isTemp) addToCache(cacheObject);
        else {
            if (cache == null) cache = new HashMap<>();
            cache.put(cacheObject.getQuery(), cacheObject);
        }
    }
}
