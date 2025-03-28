package com.example.demo.controller; 
import com.example.demo.entity.Admin;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin") 
public class AdminController {
    
    @Autowired
    private AdminService adminService;

    @GetMapping("/all")
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @GetMapping("/{id}")
    public Optional<Admin> getAdminById(@PathVariable Long id) {
        return adminService.getAdminById(id);
    }

    @PostMapping("/create")
    public Admin createAdmin(
            @RequestParam String name, 
            @RequestParam String email) {
        Admin admin = new Admin();
        admin.setName(name);
        admin.setEmail(email);
        return adminService.createAdmin(admin);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return "Admin with ID " + id + " deleted successfully!";
    }
}
