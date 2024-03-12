package com.sqlcaching.server.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqlcaching.server.entity.CacheObject;
import com.sqlcaching.server.repository.CacheObjectRepository;

@Service
public class CacheObjectService {
    @Autowired
    private CacheObjectRepository cacheObjectRepository;

    /**
     * Obtenir tout les caches en cours.
     * @return La liste des caches en cours.
     */
    public List<CacheObject> getAllCachedObjects() {
        return cacheObjectRepository.findAll();
    }

    /**
     * Obtenir un cache par la requete correspondante.
     * @param query Requete de recherche.
     * @return Le cache correspondant a la requete.
     */
    public CacheObject getCacheObjectByQuery(String query) {
        return cacheObjectRepository.findByQuery(query);
    }

    /**
     * Cache une requete.
     * @param query La requete correspondante.
     * @param data Les donnees de la requete.
     * @param cacheDuration La duree de sauvegarde d'une cache.
     * @return Le cache sauvegarder.
     */
    public CacheObject saveCache(String query, String data, double cacheDuration) {
        CacheObject cacheObject = new CacheObject();
        cacheObject.setData(data);
        cacheObject.setQuery(query);

        cacheObject.setDateCreation(new Date());
        cacheObject.setCacheDuration(cacheDuration);
        cacheObject.setDateExpiration(new Date((long) (cacheObject.getDateCreation().getTime() + cacheDuration)));
        
        return cacheObjectRepository.save(cacheObject);
    }

    /**
     * Permet de supprimer un cache par sa requete.
     * @param query La requete correspondante.
     */
    public void deleteCache(String query) {
        CacheObject cacheObject = getCacheObjectByQuery(query);
        cacheObjectRepository.delete(cacheObject);
    }

    /**
     * Permet de vider le cache.
     */
    public void cleanCache() {
        cacheObjectRepository.deleteAll(); 
    }
}
