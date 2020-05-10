package br.com.prontomed.peg.repositories;

import br.com.prontomed.peg.models.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, String> {
    
}
