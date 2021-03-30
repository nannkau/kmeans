package edu.sgu.kmeans.service;



import edu.sgu.kmeans.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Integer id);
    User save(User user);
    void delete(User user);
    void deleteById(Integer id);
}
