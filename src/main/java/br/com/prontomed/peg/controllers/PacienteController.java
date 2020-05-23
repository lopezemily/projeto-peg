package br.com.prontomed.peg.controllers;

import br.com.prontomed.peg.models.Paciente;
import br.com.prontomed.peg.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path="/paciente")
public class PacienteController {
    
//    @Autowired
//    private PacienteService pacienteService;
    
    @RequestMapping(value={"/principal"}, method = RequestMethod.GET)
    public ModelAndView principal(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("paciente/principal");
        return modelAndView;
    }
    
}

