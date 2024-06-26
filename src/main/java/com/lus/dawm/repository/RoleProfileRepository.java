package com.lus.dawm.repository;

import com.lus.dawm.model.Profile;
import com.lus.dawm.model.Role;
import com.lus.dawm.model.RoleProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleProfileRepository extends JpaRepository<RoleProfile, Long> {

    RoleProfile findOneById(Long id);
}
