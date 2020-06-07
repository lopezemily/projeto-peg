package br.com.prontomed.peg.controllers;

import br.com.prontomed.peg.dto.CadastroPaciente;
import br.com.prontomed.peg.models.Consulta;
import br.com.prontomed.peg.models.Paciente;
import br.com.prontomed.peg.models.Usuario;
import br.com.prontomed.peg.services.ConsultaService;
import br.com.prontomed.peg.services.EspecialidadeService;
import br.com.prontomed.peg.services.MedicoService;
import br.com.prontomed.peg.services.PacienteService;
import br.com.prontomed.peg.services.RecepcionistaService;
import br.com.prontomed.peg.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
        modelAndView.setViewName("recepcionista/home");
        modelAndView.addObject("nome", recepcionistaService.obterRecepcionista(cpf).getNome());
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
    public String salvarNovaConsulta(Consulta consulta) {
        consultaService.criarConsulta(consulta);
        return "redirect:/recepcionista/home?mensagem=Consulta criada com sucesso.";
    }

    @RequestMapping(value = {"/confirmar/{numAtendimento}"}, method = RequestMethod.POST)
    public String confirmar(@PathVariable long numAtendimento) {
        consultaService.confirmarConsulta(numAtendimento);
        return "redirect:/recepcionista/home?mensagem=Consulta confirmada com sucesso.";
    }
    
    @RequestMapping(value = "/novoPaciente", method = RequestMethod.GET)
    public ModelAndView novoPaciente() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("recepcionista/novoPaciente");
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
            cadastroPaciente.setCpf(cadastroPaciente.getCpf().replace(".", ""));
            cadastroPaciente.setCpf(cadastroPaciente.getCpf().replace("-", ""));
            Usuario usuario = new Usuario(cadastroPaciente.getCpf(), cadastroPaciente.getSenha(), null);
            userService.salvarPaciente(usuario);

            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Paciente paciente = objectMapper.readValue(objectMapper.writeValueAsString(cadastroPaciente), Paciente.class);
            pacienteService.inserirPaciente(paciente);

            return "redirect:/home?mensagem=Usuario foi cadastrado com sucesso.";
        }
    }

    @RequestMapping(value = "/novoMedico", method = RequestMethod.GET)
    public ModelAndView novoMedico() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("recepcionista/novoMedico");
        
        return modelAndView;
    }

    // TODO: Post Medico

}
