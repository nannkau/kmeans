package edu.sgu.kmeans.repository;


import edu.sgu.kmeans.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {
    Role findByCode(String code);
}
