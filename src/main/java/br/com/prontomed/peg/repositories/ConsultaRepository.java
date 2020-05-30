package br.com.prontomed.peg.repositories;

import br.com.prontomed.peg.models.Consulta;
import br.com.prontomed.peg.models.Medico;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByRealizadaAndPacienteCpf(boolean realizada, String pacienteCpf);

    List<Consulta> findByRealizadaAndMedicoCpf(boolean realizada, String medicoCpf);

	List<Consulta> findAllByDataAndMedicoIn(LocalDate data, List<Medico> medicosDisponiveis);
    
    //Long countByMedicoCpfAndInicioAndFimAndRealizadaAndConfirmada(String medicoCpf, LocalDateTime inicio, LocalDateTime fim, boolean realizada, boolean confirmada);
}
