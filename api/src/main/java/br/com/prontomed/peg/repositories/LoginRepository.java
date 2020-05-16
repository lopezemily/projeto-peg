package br.com.prontomed.peg.repositories;

import br.com.prontomed.peg.models.Credencial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Credencial, String>{
    
}
