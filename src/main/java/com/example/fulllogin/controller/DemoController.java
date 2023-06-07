package com.example.fulllogin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {


  @GetMapping
  public ResponseEntity<String> assertHello(){
    return ResponseEntity.ok("Hello with security");
  }
}
