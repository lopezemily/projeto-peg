package br.com.prontomed.peg.controllers;

import br.com.prontomed.peg.services.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/medico")
public class MedicoController {

    @Autowired
    private ConsultaService consultaService;
    
    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public ModelAndView home(Authentication autenticacao) {
        String cpf = autenticacao.getName();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("medico/home");

        modelAndView.addObject("proximasConsultas", consultaService.obterConsultasProximasMedico(cpf));

        return modelAndView;
    }

}
