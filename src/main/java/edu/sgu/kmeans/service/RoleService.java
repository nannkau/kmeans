package edu.sgu.kmeans.service;


import edu.sgu.kmeans.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();
    Role findById(String id);
    Role save(Role role);
    void delete(Role role);
    void deleteById(String id);
}
