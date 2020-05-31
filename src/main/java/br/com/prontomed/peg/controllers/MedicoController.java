 package br.com.prontomed.peg.controllers;

import br.com.prontomed.peg.models.Consulta;
import br.com.prontomed.peg.models.Prontuario;
import br.com.prontomed.peg.services.ConsultaService;
import br.com.prontomed.peg.services.MedicoService;
import java.text.DecimalFormat;
import java.text.ParseException;
import javax.swing.text.MaskFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/medico")
public class MedicoController {

    @Autowired
    private ConsultaService consultaService;
    
    @Autowired
    private MedicoService medicoService;
    
    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public ModelAndView home(Authentication autenticacao) {
        String cpf = autenticacao.getName();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("medico/home");

        modelAndView.addObject("proximasConsultas", consultaService.obterConsultasProximasMedico(cpf));
        
        modelAndView.addObject("nome", medicoService.obterMedico(cpf).getNome());

        return modelAndView;
    }
    
    @RequestMapping(value = {"/atender/{numAtendimento}"}, method = RequestMethod.GET)
    public ModelAndView realizarConsulta(@PathVariable long numAtendimento) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("numeroAtendimento", numAtendimento);
        modelAndView.setViewName("medico/registrarConsulta");
        return modelAndView;
    }
    
    @RequestMapping(value = {"/atender/{numAtendimento}"}, method = RequestMethod.POST)
    public String salvarConsulta(@PathVariable long numAtendimento, Prontuario prontuario) {
        consultaService.registrarProntuario(numAtendimento, prontuario);
        return "redirect:/medico/home?mensagem=Prontuario salvo com sucesso.";
    }
    
    @RequestMapping(value = {"/atestado/{numAtendimento}"}, method = RequestMethod.GET)
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

}
