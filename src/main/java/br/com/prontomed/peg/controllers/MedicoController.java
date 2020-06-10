package br.com.prontomed.peg.controllers;

import br.com.prontomed.peg.dto.CadastroMedico;
import br.com.prontomed.peg.models.CID;
import br.com.prontomed.peg.models.Consulta;
import br.com.prontomed.peg.models.Medico;
import br.com.prontomed.peg.models.Prontuario;
import br.com.prontomed.peg.services.CIDService;
import br.com.prontomed.peg.services.ConsultaService;
import br.com.prontomed.peg.services.EspecialidadeService;
import br.com.prontomed.peg.services.MedicoService;
import br.com.prontomed.peg.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.ParseException;
import javax.swing.text.MaskFormatter;
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
@RequestMapping(path = "/medico")
public class MedicoController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private MedicoService medicoService;
    
    @Autowired
    private EspecialidadeService especialidadeService;

    @Autowired
    private CIDService cidService;

    @RequestMapping(value = { "/home" }, method = RequestMethod.GET)
    public ModelAndView home(Authentication autenticacao) {
        String cpf = autenticacao.getName();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("medico/home");

        modelAndView.addObject("nome", medicoService.obterMedico(cpf).getNome());

        modelAndView.addObject("proximasConsultas", consultaService.obterConsultasProximasMedico(cpf));

        modelAndView.addObject("pacientesAgendados", medicoService.obterContagemPacientesAgendadosMes(cpf));
        modelAndView.addObject("pacientesAtendidos", medicoService.obterContagemPacientesAtendidosMes(cpf));
        modelAndView.addObject("pacientesConfirmados", medicoService.obterContagemPacientesConfirmadosMes(cpf));
        modelAndView.addObject("pacientesAusentes", medicoService.obterContagemPacientesAusentesMes(cpf));

        return modelAndView;
    }

    @RequestMapping(value = { "/atender/{numAtendimento}" }, method = RequestMethod.GET)
    public ModelAndView realizarConsulta(@PathVariable long numAtendimento) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("medico/registrarConsulta");
        Consulta consulta = consultaService.obterConsulta(numAtendimento);

        modelAndView.addObject("numeroAtendimento", numAtendimento);

        modelAndView.addObject("consulta", consulta);

        return modelAndView;
    }

    @RequestMapping(value = { "/cid/{cidId}" }, method = RequestMethod.GET)
    public ModelAndView realizarConsulta(@PathVariable String cidId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("fragments/cidDescricao :: cidDescricao");
        modelAndView.addObject("cid", cidService.obterPorId(cidId).orElse(new CID("", "Não Encontrado")));
        return modelAndView;
    }

    @RequestMapping(value = { "/atender/{numAtendimento}" }, method = RequestMethod.POST)
    public String salvarConsulta(@PathVariable long numAtendimento, Prontuario prontuario) {
        consultaService.registrarProntuario(numAtendimento, prontuario);
        return "redirect:/medico/home?mensagem=Prontuario salvo com sucesso.";
    }

    @RequestMapping(value = { "/atestado/{numAtendimento}" }, method = RequestMethod.GET)
    public ModelAndView atestar(@PathVariable long numAtendimento) throws ParseException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("medico/atestado");

        Consulta consulta = consultaService.obterConsulta(numAtendimento);

        MaskFormatter mask = new MaskFormatter("###.###.###-##");
        mask.setValueContainsLiteralCharacters(false);

        modelAndView.addObject("cpf", mask.valueToString(consulta.getPaciente().getCpf()));
        modelAndView.addObject("consulta", consulta);
        return modelAndView;
    }

    @RequestMapping(value = { "/dadosPaciente/{numAtendimento}" }, method = RequestMethod.GET)
    public ModelAndView visualizarPaciente(@PathVariable long numAtendimento) throws ParseException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("medico/dadosPaciente");

        Consulta consulta = consultaService.obterConsulta(numAtendimento);

        modelAndView.addObject("nome", consulta.getPaciente());
        modelAndView.addObject("contato", consulta.getPaciente().getContato());
        modelAndView.addObject("consulta", consulta);
        return modelAndView;
    }

    @RequestMapping(value = { "/novaReceita/{numAtendimento}" }, method = RequestMethod.GET)
    public ModelAndView novaReceita(@PathVariable long numAtendimento) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("numeroAtendimento", numAtendimento);
        modelAndView.addObject("consulta", consultaService.obterConsulta(numAtendimento));
        modelAndView.setViewName("medico/novaReceita");
        return modelAndView;
    }
    
    @RequestMapping(value = { "/agenda" }, method = RequestMethod.GET)
    public ModelAndView visualizarAgenda(Authentication autenticacao){
        String cpf = autenticacao.getName();
        
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("medico/agenda");
        
        modelAndView.addObject("proximasConsultas", consultaService.obterConsultasProximasTodasMedico(cpf));

        return modelAndView;
    }
    
    @RequestMapping(value = { "/meusDados" }, method = RequestMethod.GET)
    public ModelAndView visualizarDados(Authentication autenticacao) {
        String cpf = autenticacao.getName();
        
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("medico/meusDados");
        modelAndView.addObject("medico", medicoService.obterMedico(cpf));
        modelAndView.addObject("especialidades", especialidadeService.obterTodasEspecialidades());
        return modelAndView;
    }
    
    @RequestMapping(value = "/meusDados", method = RequestMethod.POST)
    public String atualizarMedico(@Valid CadastroMedico cadastroMedico, BindingResult bindingResult, Authentication autenticacao) throws JsonProcessingException {
        String cpf = autenticacao.getName();

        if (bindingResult.hasErrors()) {
            return "medico/meusDados";
        } else {
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Medico medico = objectMapper.readValue(objectMapper.writeValueAsString(cadastroMedico), Medico.class);
            medicoService.atualizarmedico(cpf, medico);

            return "redirect:/medico/home?mensagem=Dados atualizados com sucesso.";
        }
    }
}
