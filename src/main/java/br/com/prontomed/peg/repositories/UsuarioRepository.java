package br.com.prontomed.peg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.prontomed.peg.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

}