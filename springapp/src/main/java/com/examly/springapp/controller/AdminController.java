package com.examly.springapp.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")  
public class AdminController {

    @GetMapping
    public void adminHome() {}

    @PostMapping
    public void addAdmin() {}

    @PutMapping("/{id}")
    public void updateAdmin(@PathVariable int id) {}

    @DeleteMapping("/{id}")
    public void deleteAdmin(@PathVariable int id) {}
}
