package br.com.prontomed.peg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.prontomed.peg.models.Funcao;

@Repository
public interface FuncaoRepository extends JpaRepository<Funcao, Integer> {
    Funcao findByRole(String role);
}