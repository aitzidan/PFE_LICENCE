package com.lus.dawm.repository;

import com.lus.dawm.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findOneByIce(String ice);
    Client findOneById(Long id);
}
