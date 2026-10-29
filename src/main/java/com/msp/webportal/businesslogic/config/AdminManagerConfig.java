package com.msp.webportal.businesslogic.config;


import com.msp.webportal.businesslogic.model.adminmagement.AdminManager;
import com.msp.webportal.businesslogic.repository.adminmanagement.AdminCreationRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminManagerConfig {

    @Bean
    public CommandLineRunner adminManagerInitializer(AdminCreationRepo adminCreationRepo, PasswordEncoder passwordEncoder) {
        return args -> {
            // Check if the "superadmin" already exists
            if (adminCreationRepo.findByEmail("superadmin@example.com") == null) {
                // Create a new AdminManager object for superadmin
                AdminManager superAdmin = new AdminManager();
                superAdmin.setEmail("superadmin@example.com");
                superAdmin.setPassword(passwordEncoder.encode("SuperAdmin")); // Ensure to encode password
                superAdmin.setFirstname("SuperAdmin");
                superAdmin.setLastname("SuperAdmin");
                superAdmin.setRole("SuperAdmin"); // You can set the role based on your requirement

                // Save the superadmin to the database
                adminCreationRepo.save(superAdmin);
                System.out.println("Superadmin added successfully.");
            } else {
                System.out.println("Superadmin already exists.");
            }
        };
    }
}
