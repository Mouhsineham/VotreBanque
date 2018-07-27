package org.sid.dao;

import org.sid.entities.CompteCourante;
import org.sid.entities.CompteEpargne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteCourantRepository extends JpaRepository<CompteCourante, String> {
//interface genereque 
}
