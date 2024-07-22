package com.example.curly_api.config;

import com.example.curly_api.dto.CreateUserDto;
import com.example.curly_api.entity.Role;
import com.example.curly_api.entity.User;
import com.example.curly_api.repository.RoleRepository;
import com.example.curly_api.repository.UserRepository;
import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public AdminUserConfig(RoleRepository roleRepository,
                           UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var userAdmin = userRepository.findByEmail("admin@gmail.com");

        var roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());

        if (userAdmin.isEmpty()) {
            var user = new User();
            user.setEmail("admin@gmail.com");
            user.setSenha(passwordEncoder.encode("admin123"));
            //user.setRoles(Set.of(roleAdmin) );
            user.setRoles((List<Role>) roleAdmin);
            userRepository.save(user);
        } else {
            System.out.println("Admin já existe");
        }

        // mostrar roles no banco


        var roleCli = roleRepository.existsById((long) 1);
        if (!roleCli) {
            var client = new Role();
            client.setName("CLIENT");
            client.setRoleId((long) 1);
            roleRepository.save(client);
        } else {
            System.out.println("cli já existe");
        }

        var rolePro = roleRepository.existsById((long) 2);
        if (!rolePro) {
            var professional = new Role();
            professional.setName("PROFESSIONAL");
            professional.setRoleId((long) 2);
            roleRepository.save(professional);
        } else {
            System.out.println("prof já existe");
        }



        var roleAdm = roleRepository.existsById((long) 3);
        if (!roleAdm) {
            var adm = new Role();
            adm.setName("ADMIN");
            adm.setRoleId((long) 3);
            roleRepository.save(adm);
        } else {
            System.out.println("adm já existe");
        }
    }
}
