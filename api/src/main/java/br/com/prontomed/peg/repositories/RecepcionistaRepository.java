package br.com.prontomed.peg.repositories;

import br.com.prontomed.peg.models.Recepcionista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecepcionistaRepository extends JpaRepository <Recepcionista, String> {
    
}
