package com.example.fuleana.controller;

import com.example.fuleana.utility.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TopController {

    @GetMapping("/")
    public ResponseEntity test(){
        return ResponseEntity.ok("Fuelana");
    }

}
