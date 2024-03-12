package com.sqlcaching.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sqlcaching.server.entity.CacheObject;
import com.sqlcaching.server.service.CacheObjectService;

@Controller
public class CacheInterfaceController {
    @Autowired
    private CacheObjectService cacheObjectService;

    @RequestMapping("/")
    public String index(Model model) {
        List<CacheObject> cacheObjects = cacheObjectService.getAllCachedObjects();
        
        model.addAttribute("cacheObjects", cacheObjects);
        return "index.html";
    }
}
