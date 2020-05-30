package br.com.prontomed.peg.repositories;

import br.com.prontomed.peg.models.Especialidade;
import br.com.prontomed.peg.models.Medico;

import java.time.DayOfWeek;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, String> {

	List<Medico> findAllByEspecialidadesAndDisponibilidade(Especialidade especialidade, DayOfWeek diaSemana);
    
}
