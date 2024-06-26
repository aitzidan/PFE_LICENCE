package com.lus.dawm.repository;

import com.lus.dawm.model.Devis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DevisRepository extends JpaRepository<Devis, Long> {
    Devis findOneById(Long id);
    @Query("SELECT MAX(d.id) FROM Devis d")
    Long findMaxId();
}
