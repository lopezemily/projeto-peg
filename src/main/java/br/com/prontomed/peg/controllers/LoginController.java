package br.com.prontomed.peg.controllers;

import br.com.promomed.peg.dto.CadastroPaciente;
import br.com.prontomed.peg.models.Paciente;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
    private UserService userService;
    
    @Autowired
    private PacienteService pacienteService;

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @RequestMapping(value="/cadastro", method = RequestMethod.GET)
    public ModelAndView cadastro(){
        ModelAndView modelAndView = new ModelAndView();
        Usuario user = new Usuario();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("cadastro");
        return modelAndView;
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid CadastroPaciente cadastroPaciente, BindingResult bindingResult) throws JsonProcessingException {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Usuario> userExists = userService.findUserByCpf(cadastroPaciente.getCpf());
        if (userExists.isPresent()) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("cadastro");
        } else {
            Usuario usuario = new Usuario(cadastroPaciente.getCpf(), cadastroPaciente.getSenha(), null);
            userService.salvarPaciente(usuario);
            
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            
            Paciente paciente = objectMapper.readValue(objectMapper.writeValueAsString(cadastroPaciente),Paciente.class);
            
            pacienteService.inserirPaciente(paciente);
            modelAndView.addObject("successMessage", "Usuario foi cadastrado com sucesso");
            modelAndView.setViewName("login");

        }
        return modelAndView;
    }

//    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
//    public ModelAndView home(){
//        ModelAndView modelAndView = new ModelAndView();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        Usuario user = userService.findUserByCpf(auth.getName());
//        modelAndView.addObject("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
//        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
//        modelAndView.setViewName("admin/home");
//        return modelAndView;
//    }       
    
}
