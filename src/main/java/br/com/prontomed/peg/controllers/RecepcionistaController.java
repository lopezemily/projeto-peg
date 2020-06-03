package br.com.prontomed.peg.controllers;

import br.com.prontomed.peg.models.Consulta;
import br.com.prontomed.peg.services.ConsultaService;
import br.com.prontomed.peg.services.EspecialidadeService;
import br.com.prontomed.peg.services.MedicoService;
import br.com.prontomed.peg.services.RecepcionistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/recepcionista")
public class RecepcionistaController {

    @Autowired
    private RecepcionistaService recepcionistaService;

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private EspecialidadeService especialidadeService;

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public ModelAndView home(Authentication autenticacao) {
        String cpf = autenticacao.getName();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("recepcionista/home");

        modelAndView.addObject("nome", recepcionistaService.obterRecepcionista(cpf));

        modelAndView.addObject("proximasConsultas", consultaService.obterConsultasDia());

        return modelAndView;
    }

    @RequestMapping(value = {"/novaConsulta"}, method = RequestMethod.GET)
    public ModelAndView novaConsulta() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("recepcionista/novaConsulta");
        modelAndView.addObject("especialidades", especialidadeService.obterEspecialidadesComMedicosAssociados());
        return modelAndView;
    }

    @RequestMapping(value = {"/novaConsulta"}, method = RequestMethod.POST)
    public String salvarNovaConsulta(Authentication autenticacao, Consulta consulta) {
        String cpf = autenticacao.getName();
        consultaService.criarConsulta(cpf, consulta);
        return "redirect:/recepcionista/home?mensagem=Consulta criada com sucesso.";
    }

    @RequestMapping(value = {"/confirmar/{numAtendimento}"}, method = RequestMethod.POST)
    public String confirmar(@PathVariable long numAtendimento) {
        consultaService.confirmarConsulta(numAtendimento);
        return "redirect:/recepcionista/home?mensagem=Consulta confirmada com sucesso.";
    }

}
