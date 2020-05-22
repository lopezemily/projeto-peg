package br.com.prontomed.peg.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.prontomed.peg.models.Funcao;
import br.com.prontomed.peg.models.Usuario;
import br.com.prontomed.peg.repositories.FuncaoRepository;
import br.com.prontomed.peg.repositories.UsuarioRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@Service
public class UserService {

    private UsuarioRepository userRepository;
    private FuncaoRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(
                       UsuarioRepository userRepository,
                       FuncaoRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Optional<Usuario> findUserByCpf(String cpf) {
        return userRepository.findById(cpf);
    }

    public Usuario salvarPaciente(Usuario usuario) {
        usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
        Funcao userRole = roleRepository.findByRole("PACIENTE");
        usuario.setRoles(new HashSet<Funcao>(Arrays.asList(userRole)));
        return userRepository.save(usuario);
    }

}