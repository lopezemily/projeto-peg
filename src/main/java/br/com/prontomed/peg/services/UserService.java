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

    public Usuario findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Usuario findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public Usuario saveUser(Usuario user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Funcao userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Funcao>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

}