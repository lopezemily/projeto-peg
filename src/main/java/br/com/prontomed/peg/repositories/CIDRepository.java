package br.com.prontomed.peg.repositories;

import br.com.prontomed.peg.models.CID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CIDRepository extends JpaRepository<CID, String>{
    
}
