package com.win.twitter.repository;

import java.util.List;
import java.util.Arrays;
import java.util.HashSet;

import com.win.twitter.model.Role;
import com.win.twitter.model.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.security.core.context.SecurityContextHolder;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByRole(String role);

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> findAll(){
        return (List<User>) userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User saveNewUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

    public User saveNewUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

    public User getLoggedInUser() {
        String loggedInUsername = SecurityContextHolder.
          getContext().getAuthentication().getName();
        
        return findByUsername(loggedInUsername);
    }
}