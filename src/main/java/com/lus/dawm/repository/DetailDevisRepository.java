package com.lus.dawm.repository;

import com.lus.dawm.model.DetailDevis;
import com.lus.dawm.model.Devis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailDevisRepository extends JpaRepository<DetailDevis, Long> {
}
