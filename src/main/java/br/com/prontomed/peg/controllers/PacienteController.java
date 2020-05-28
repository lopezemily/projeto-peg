package br.com.prontomed.peg.controllers;

import br.com.prontomed.peg.models.Consulta;
import br.com.prontomed.peg.models.Paciente;
import br.com.prontomed.peg.services.ConsultaService;
import br.com.prontomed.peg.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/paciente")
public class PacienteController {

    @Autowired
    private ConsultaService consultaService;

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public ModelAndView home(Authentication autenticacao) {
        String cpf = autenticacao.getName();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("paciente/home");

        modelAndView.addObject("proximasConsultas", consultaService.obterConsultasProximasPaciente(cpf));

        modelAndView.addObject("consultasAnteriores", consultaService.obterConsultasAnterioresPaciente(cpf));

        return modelAndView;
    }

    @RequestMapping(value = {"/confirmar/{numAtendimento}"}, method = RequestMethod.POST)
    public String confirmar(@PathVariable long numAtendimento) {
        consultaService.confirmarConsulta(numAtendimento);
        return "redirect:/paciente/home";
    }

    @RequestMapping(value = {"/consulta/{numAtendimento}"}, method = RequestMethod.GET)
    public ModelAndView visualizarConsulta(@PathVariable long numAtendimento) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("paciente/detalhesConsulta");
        modelAndView.addObject("consulta", consultaService.obterConsulta(numAtendimento));
        return modelAndView;
    }

    @RequestMapping(value = {"/novaConsulta"}, method = RequestMethod.GET)
    public ModelAndView novaConsulta() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("paciente/novaConsulta");
        return modelAndView;
    }

    @RequestMapping(value = {"/novaConsulta"}, method = RequestMethod.POST)
    public String salvarNovaConsulta(Authentication autenticacao, Consulta consulta) {
        String cpf = autenticacao.getName();
        
        consultaService.criarConsulta(cpf, consulta);
        return "redirect:/paciente/home";
    }
}
