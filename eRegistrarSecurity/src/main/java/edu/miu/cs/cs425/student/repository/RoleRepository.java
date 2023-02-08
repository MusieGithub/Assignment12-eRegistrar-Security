package edu.miu.cs.cs425.student.repository;


import edu.miu.cs.cs425.student.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
