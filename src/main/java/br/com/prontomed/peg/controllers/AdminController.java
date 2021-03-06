package br.com.prontomed.peg.controllers;

import br.com.prontomed.peg.dto.CadastroAdmin;
import br.com.prontomed.peg.dto.CadastroMedico;
import br.com.prontomed.peg.dto.CadastroPaciente;
import br.com.prontomed.peg.dto.CadastroRecepcionista;
import br.com.prontomed.peg.models.Administrador;
import br.com.prontomed.peg.models.Consulta;
import br.com.prontomed.peg.models.Medico;
import br.com.prontomed.peg.models.Paciente;
import br.com.prontomed.peg.models.Recepcionista;
import br.com.prontomed.peg.models.Usuario;
import br.com.prontomed.peg.services.AdministradorService;
import br.com.prontomed.peg.services.ConsultaService;
import br.com.prontomed.peg.services.EspecialidadeService;
import br.com.prontomed.peg.services.MedicoService;
import br.com.prontomed.peg.services.PacienteService;
import br.com.prontomed.peg.services.RecepcionistaService;
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
@RequestMapping(path = "/admin")
public class AdminController {
    @Autowired
    private AdministradorService admService;
    
    @Autowired
    private RecepcionistaService recepcionistaService;

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private MedicoService medicoService;
    
    @Autowired
    private PacienteService pacienteService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EspecialidadeService especialidadeService;

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public ModelAndView home(Authentication autenticacao) {
        String cpf = autenticacao.getName();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/home");
        modelAndView.addObject("nome", admService.obterAdmin(cpf).getNome());
        modelAndView.addObject("proximasConsultas", consultaService.obterConsultasDia());

        return modelAndView;
    }

    @RequestMapping(value = {"/novaConsulta"}, method = RequestMethod.GET)
    public ModelAndView novaConsulta() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/novaConsulta");
        modelAndView.addObject("especialidades", especialidadeService.obterEspecialidadesComMedicosAssociados());
        return modelAndView;
    }

    
    @RequestMapping(value = { "/novaConsulta/horariosDisponiveis" }, method = RequestMethod.GET)
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
    public String salvarNovaConsulta(Consulta consulta) {
        consultaService.criarConsulta(consulta);
        return "redirect:/admin/home?mensagem=Consulta criada com sucesso.";
    }

    @RequestMapping(value = {"/confirmar/{numAtendimento}"}, method = RequestMethod.POST)
    public String confirmar(@PathVariable long numAtendimento) {
        consultaService.confirmarConsulta(numAtendimento);
        return "redirect:/admin/home?mensagem=Consulta confirmada com sucesso.";
    }
    
    @RequestMapping(value = {"/confirmarAgenda/{numAtendimento}"}, method = RequestMethod.POST)
    public String confirmarAgenda(@PathVariable long numAtendimento) {
        consultaService.confirmarConsulta(numAtendimento);
        return "redirect:/admin/agenda?mensagem=Consulta confirmada com sucesso.";
    }
    
    @RequestMapping(value = { "/cancelarConsulta/{numAtendimento}" }, method = RequestMethod.POST)
    public String cancelarConsulta(@PathVariable long numAtendimento) {
        consultaService.cancelarConsulta(numAtendimento);
        return "redirect:/admin/home?mensagem=Consulta cancelada com sucesso.";
    }
    
    @RequestMapping(value = { "/cancelarConsultaAgenda/{numAtendimento}" }, method = RequestMethod.POST)
    public String cancelarConsultaAgenda(@PathVariable long numAtendimento) {
        consultaService.cancelarConsulta(numAtendimento);
        return "redirect:/admin/agenda?mensagem=Consulta cancelada com sucesso.";
    }
    
    @RequestMapping(value = { "/paciente/{cpf}" }, method = RequestMethod.GET)
    public ModelAndView buscarPaciente(@PathVariable String cpf) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("fragments/pacienteNome :: pacienteNome");
        modelAndView.addObject("paciente", pacienteService.obterPacientePorCpf(cpf).orElse(new Paciente("Não Encontrado")));
        return modelAndView;
    }
    
    @RequestMapping(value = "/novoPaciente", method = RequestMethod.GET)
    public ModelAndView novoPaciente() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/novoPaciente");
        return modelAndView;
    }
    
    @RequestMapping(value = "/novoPaciente", method = RequestMethod.POST)
    public String criarNovoPaciente(@Valid CadastroPaciente cadastroPaciente, BindingResult bindingResult) throws JsonProcessingException {
        Optional<Usuario> userExists = userService.findUserByCpf(cadastroPaciente.getCpf());
        if (userExists.isPresent()) {
            bindingResult.rejectValue("userName", "error.user",
                    "There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()) {
            return "novoPaciente";
        } else {
            Usuario usuario = new Usuario(cadastroPaciente.getCpf(), cadastroPaciente.getSenha(), null);
            userService.salvarPaciente(usuario);

            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Paciente paciente = objectMapper.readValue(objectMapper.writeValueAsString(cadastroPaciente), Paciente.class);
            pacienteService.inserirPaciente(paciente);

            return "redirect:/admin/home?mensagem=Paciente cadastrado com sucesso.";
        }
    }
    
    @RequestMapping(value = "/novoMedico", method = RequestMethod.GET)
    public ModelAndView novoMedico() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/novoMedico");
        modelAndView.addObject("especialidades", especialidadeService.obterTodasEspecialidades());
        return modelAndView;
    }
    
    @RequestMapping(value = { "/agenda" }, method = RequestMethod.GET)
    public ModelAndView visualizarAgenda(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/agenda");
        
        modelAndView.addObject("proximasConsultas", consultaService.obterConsultasProximasTodas());

        return modelAndView;
    }

    @RequestMapping(value = "/novoMedico", method = RequestMethod.POST)
    public String criarNovoMedico(@Valid CadastroMedico cadastroMedico, BindingResult bindingResult) throws JsonProcessingException {
        Optional<Usuario> userExists = userService.findUserByCpf(cadastroMedico.getCpf());
        if (userExists.isPresent()) {
            bindingResult.rejectValue("userName", "error.user",
                    "There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()) {
            return "novoMedico";
        } else {
            Usuario usuario = new Usuario(cadastroMedico.getCpf(), cadastroMedico.getSenha(), null);
            userService.salvarMedico(usuario);

            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Medico medico = objectMapper.readValue(objectMapper.writeValueAsString(cadastroMedico), Medico.class);
            medico.setDisponibilidade(cadastroMedico.getDaysOfWeek());
            medicoService.inserirMedico(medico);

            return "redirect:/admin/home?mensagem=Medico cadastrado com sucesso.";
        }
    }
    
    @RequestMapping(value = "/novaRecepcionista", method = RequestMethod.POST)
    public String criarNovoRecepcionista(@Valid CadastroRecepcionista cadastroRecepcionista, BindingResult bindingResult) throws JsonProcessingException {
        Optional<Usuario> userExists = userService.findUserByCpf(cadastroRecepcionista.getCpf());
        if (userExists.isPresent()) {
            bindingResult.rejectValue("userName", "error.user",
                    "There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()) {
            return "novaRecepcionista";
        } else {
            Usuario usuario = new Usuario(cadastroRecepcionista.getCpf(), cadastroRecepcionista.getSenha(), null);
            userService.salvarRecepcionista(usuario);

            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Recepcionista recepcionista = objectMapper.readValue(objectMapper.writeValueAsString(cadastroRecepcionista), Recepcionista.class);
            recepcionistaService.inserirRecepcionista(recepcionista);

            return "redirect:/admin/home?mensagem=Recepcionista cadastrado com sucesso.";
        }
    }
    
    @RequestMapping(value = "/novaRecepcionista", method = RequestMethod.GET)
    public ModelAndView novaRecepcionista() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/novaRecepcionista");
        return modelAndView;
    }
    
    @RequestMapping(value = { "/dashboard" }, method = RequestMethod.GET)
    public ModelAndView visualizarDash() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/dashboard");
        return modelAndView;
    }
    
    @RequestMapping(value = { "/meusDados" }, method = RequestMethod.GET)
    public ModelAndView visualizarDados(Authentication autenticacao) {
        String cpf = autenticacao.getName();
        
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/meusDados");
        modelAndView.addObject("admin", admService.obterAdmin(cpf));
        return modelAndView;
    }
    
    @RequestMapping(value = "/meusDados", method = RequestMethod.POST)
    public String atualizarAdmin(@Valid CadastroAdmin cadastroAdmin, BindingResult bindingResult, Authentication autenticacao) throws JsonProcessingException {
        String cpf = autenticacao.getName();

        if (bindingResult.hasErrors()) {
            return "admin/meusDados";
        } else {
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Administrador admin = objectMapper.readValue(objectMapper.writeValueAsString(cadastroAdmin), Administrador.class);
            admService.atualizarAdmin(cpf, admin);

            return "redirect:/admin/home?mensagem=Dados atualizados com sucesso.";
        }
    }
}
