package com.ecomm.project.repositories;

import com.ecomm.project.models.AppRole;
import com.ecomm.project.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(AppRole appRole);

}
