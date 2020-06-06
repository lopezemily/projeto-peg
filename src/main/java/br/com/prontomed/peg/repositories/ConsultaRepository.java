package br.com.prontomed.peg.repositories;

import br.com.prontomed.peg.models.Consulta;
import br.com.prontomed.peg.models.Medico;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByRealizadaAndPacienteCpf(boolean realizada, String pacienteCpf);

    @Query("SELECT c FROM Consulta c WHERE c.data >= CURDATE() AND c.paciente.cpf = :pacienteCpf AND c.realizada = FALSE")
    List<Consulta> findConsultasProximasByPacienteCpf(String pacienteCpf);

    @Query("SELECT c FROM Consulta c WHERE c.data = CURDATE() AND c.medico.cpf = :medicoCpf AND c.realizada = FALSE")
    List<Consulta> findConsultasDoDiaByMedicoCpf(@Param("medicoCpf") String medicoCpf);
    
    @Query("SELECT c FROM Consulta c WHERE c.data = CURDATE() AND c.realizada = FALSE")
    List<Consulta> findConsultasDoDia();

    List<Consulta> findAllByDataAndMedicoIn(LocalDate data, List<Medico> medicosDisponiveis);

    @Query("SELECT COUNT(c) FROM Consulta c WHERE (c.data BETWEEN DATE_FORMAT(NOW() ,'%Y-%m-01') AND NOW()) AND c.medico.cpf = :medicoCpf")
    int countConsultaMesByMedicoCpf(@Param("medicoCpf") String medicoCpf);

    @Query("SELECT COUNT(c) FROM Consulta c WHERE (c.data BETWEEN DATE_FORMAT(NOW() ,'%Y-%m-01') AND NOW()) AND c.medico.cpf = :medicoCpf AND c.realizada = :realizada AND c.confirmada = :confirmada")
    int countConsultaMesByMedicoCpfAndRealizadaAndConfirmada(@Param("medicoCpf") String medicoCpf, @Param("realizada") Boolean realizada, @Param("confirmada") Boolean confirmada);

    @Query("SELECT COUNT(c) FROM Consulta c WHERE (c.data BETWEEN CURDATE() AND LAST_DAY(CURDATE())) AND c.medico.cpf = :medicoCpf AND c.realizada = FALSE AND c.confirmada = TRUE")
    int countConsultaMesConfirmadosByMedicoCpf(@Param("medicoCpf") String medicoCpf);

    @Query("SELECT COUNT(c) FROM Consulta c WHERE (c.data BETWEEN DATE_FORMAT(NOW() ,'%Y-%m-01') AND CURDATE()-1) AND c.medico.cpf = :medicoCpf AND c.realizada = FALSE")
    int countConsultaMesAusenteByMedicoCpf(@Param("medicoCpf") String medicoCpf);
}
