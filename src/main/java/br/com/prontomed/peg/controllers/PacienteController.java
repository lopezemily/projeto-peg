package br.com.prontomed.peg.controllers;

import br.com.prontomed.peg.dto.CadastroPaciente;
import br.com.prontomed.peg.models.Consulta;
import br.com.prontomed.peg.models.Paciente;
import br.com.prontomed.peg.services.ConsultaService;
import br.com.prontomed.peg.services.EspecialidadeService;
import br.com.prontomed.peg.services.PacienteService;
import br.com.prontomed.peg.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.Optional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/paciente")
public class PacienteController {

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private EspecialidadeService especialidadeService;

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public ModelAndView home(Authentication autenticacao) {
        String cpf = autenticacao.getName();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("paciente/home");

        modelAndView.addObject("proximasConsultas", consultaService.obterConsultasProximasPaciente(cpf));
        modelAndView.addObject("consultasAnteriores", consultaService.obterConsultasAnterioresPaciente(cpf));
        modelAndView.addObject("nome", pacienteService.obterPaciente(cpf).getNome());

        return modelAndView;
    }

    @RequestMapping(value = {"/confirmar/{numAtendimento}"}, method = RequestMethod.POST)
    public String confirmar(@PathVariable long numAtendimento) {
        consultaService.confirmarConsulta(numAtendimento);
        return "redirect:/paciente/home?mensagem=Consulta confirmada com sucesso.";
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
        modelAndView.addObject("especialidades", especialidadeService.obterEspecialidadesComMedicosAssociados());
        return modelAndView;
    }

    @RequestMapping(value = {"/novaConsulta/horariosDisponiveis"}, method = RequestMethod.GET)
    public Object horariosDisponiveis(@RequestParam("data") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data,
            @RequestParam("especialidadeId") int especialidadeId) throws Exception {
        try {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("fragments/horariosDisponiveis :: horariosSelect");
            modelAndView.addObject("disponibilidades", consultaService.obterHorariosDisponiveis(data, especialidadeId));
            return modelAndView;
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = {"/novaConsulta"}, method = RequestMethod.POST)
    public String salvarNovaConsulta(Authentication autenticacao, Consulta consulta) {
        String cpf = autenticacao.getName();
        consultaService.criarConsulta(cpf, consulta);
        return "redirect:/paciente/home?mensagem=Consulta criada com sucesso.";
    }

    @RequestMapping(value = {"/cancelarConsulta/{numAtendimento}"}, method = RequestMethod.POST)
    public String cancelarConsulta(@PathVariable long numAtendimento) {
        consultaService.cancelarConsulta(numAtendimento);
        return "redirect:/paciente/home?mensagem=Consulta cancelada com sucesso.";
    }

    @RequestMapping(value = {"/meusDados"}, method = RequestMethod.GET)
    public ModelAndView visualizarDados(Authentication autenticacao) {
        String cpf = autenticacao.getName();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("paciente/meusDados");
        modelAndView.addObject("paciente", pacienteService.obterPaciente(cpf));
        return modelAndView;
    }

    @RequestMapping(value = "/meusDados", method = RequestMethod.POST)
    public String atualizarPaciente(@Valid CadastroPaciente cadastroPaciente, BindingResult bindingResult, Authentication autenticacao) throws JsonProcessingException {
        String cpf = autenticacao.getName();

        if (bindingResult.hasErrors()) {
            return "paciente/meusDados";
        } else {
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Paciente paciente = objectMapper.readValue(objectMapper.writeValueAsString(cadastroPaciente), Paciente.class);
            pacienteService.atualizarPaciente(cpf, paciente);

            return "redirect:/paciente/home?mensagem=Dados atualizados com sucesso.";
        }
    }
}
