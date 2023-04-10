package com.example.fuleana.controller;

import com.example.fuleana.utility.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @Autowired
    IdGenerator idGenerator;

    @GetMapping("/")
    public ResponseEntity test(){
        return ResponseEntity.ok("Fuelana");
    }


    @GetMapping("/test/{id}")
    public ResponseEntity<?> testById(@PathVariable String id){
        return ResponseEntity.ok(id);
    }

}
