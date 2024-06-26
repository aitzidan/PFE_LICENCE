package com.lus.dawm.repository;

import com.lus.dawm.model.ListeRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListeRoleRepository extends JpaRepository<ListeRole, Long> {
    ListeRole findOneById(Long id);
}
