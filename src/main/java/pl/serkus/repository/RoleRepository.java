package pl.serkus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.serkus.model.Role;

@Repository("RoleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer>{

}