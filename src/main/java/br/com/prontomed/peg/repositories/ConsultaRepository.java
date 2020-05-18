package br.com.prontomed.peg.repositories;

import br.com.prontomed.peg.models.Consulta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByRealizadaAndPacienteCpf(boolean realizada, String pacienteCpf);
    
    List<Consulta> findByRealizadaAndMedicoCpf(boolean realizada, String medicoCpf);
    }
