package br.com.prontomed.peg.controllers;

import br.com.prontomed.peg.dto.CadastroPaciente;
import br.com.prontomed.peg.models.Paciente;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.prontomed.peg.models.Usuario;
import br.com.prontomed.peg.services.PacienteService;
import br.com.prontomed.peg.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private PacienteService pacienteService;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String login(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:home";
        } else {
            return "login";
        }
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.GET)
    public ModelAndView cadastro() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cadastro");
        return modelAndView;
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
    public String createNewUser(@Valid CadastroPaciente cadastroPaciente, BindingResult bindingResult)
            throws JsonProcessingException {
        Optional<Usuario> userExists = userService.findUserByCpf(cadastroPaciente.getCpf());
        if (userExists.isPresent()) {
            bindingResult.rejectValue("userName", "error.user",
                    "There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()) {
            return "cadastro";
        } else {
            Usuario usuario = new Usuario(cadastroPaciente.getCpf(), cadastroPaciente.getSenha(), null);
            userService.salvarPaciente(usuario);

            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Paciente paciente = objectMapper.readValue(objectMapper.writeValueAsString(cadastroPaciente), Paciente.class);
            pacienteService.inserirPaciente(paciente);

            return "redirect:/login?mensagem=Usuario foi cadastrado com sucesso. Acesse sua conta com o login cadastrado.";
        }
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String sucesso(Authentication autenticacao) throws Exception {
        GrantedAuthority authority = autenticacao.getAuthorities().iterator().next();
        String funcao = authority.getAuthority();
        return String.format("redirect:/%s/home", funcao.toLowerCase());
    }
<<<<<<< HEAD
    
    @RequestMapping(value = "/recephome", method = RequestMethod.GET)
    public ModelAndView recepcionista() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("recephome");
        return modelAndView;
    }
=======

    @RequestMapping(value = "/recepcionista", method = RequestMethod.GET)
    public ModelAndView recepcionista() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("recepcionista");
        return modelAndView;
    }

    @RequestMapping(value = "/novomedico", method = RequestMethod.GET)
    public ModelAndView novomedico() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("novomedico");
        return modelAndView;
    }

>>>>>>> master
}
