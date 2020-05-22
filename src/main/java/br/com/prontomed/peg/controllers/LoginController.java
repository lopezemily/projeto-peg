package br.com.prontomed.peg.controllers;

import br.com.promomed.peg.dto.CadastroPaciente;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.prontomed.peg.models.Usuario;
import br.com.prontomed.peg.services.UserService;

@Controller
public class LoginController {
    
    @Autowired
    private UserService userService;

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
    public ModelAndView createNewUser(@Valid CadastroPaciente cadastroPaciente, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Usuario userExists = userService.findUserByUserName(cadastroPaciente.getCpf());
        if (userExists != null) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new Usuario());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }

    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario user = userService.findUserByUserName(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }       
    
}
