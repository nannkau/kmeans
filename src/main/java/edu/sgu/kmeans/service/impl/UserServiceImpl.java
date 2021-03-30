package edu.sgu.kmeans.service.impl;


import edu.sgu.kmeans.entity.Role;
import edu.sgu.kmeans.entity.User;
import edu.sgu.kmeans.repository.RoleRepository;
import edu.sgu.kmeans.repository.UserRepository;
import edu.sgu.kmeans.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    final private UserRepository userRepository;
    final private RoleRepository roleRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        List<Role> roles= new ArrayList<>();
        roles.add(roleRepository.findByCode("CUSTOMER"));
        user.setRoles(roles);
        user.setActive("0");
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}
