package com.lus.dawm.repository;

import com.lus.dawm.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long>{
    Profile findOneById(Long id);
    Profile findOneByNom(String name);
}
