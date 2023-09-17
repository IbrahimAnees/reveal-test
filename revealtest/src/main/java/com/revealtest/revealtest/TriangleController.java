package com.revealtest.revealtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TriangleController {

    private final TriangleService triangleService;

    @Autowired
    public TriangleController(TriangleService triangleService) {
        this.triangleService = triangleService;
    }

    @GetMapping("/test")
    public String testController() {
        return triangleService.testService();
    }
}
