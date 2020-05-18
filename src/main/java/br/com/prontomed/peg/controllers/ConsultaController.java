package br.com.prontomed.peg.controllers;

import br.com.prontomed.peg.models.Consulta;
import br.com.prontomed.peg.models.Prontuario;
import br.com.prontomed.peg.services.ConsultaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/consulta")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    public void criarConsulta(@RequestBody Consulta consulta) {
        consultaService.criarConsulta(consulta);
    }
    
    @GetMapping(path = "/paciente/{cpf}/anteriores")
    public @ResponseBody List<Consulta> obterConsultasAnterioresPaciente(@PathVariable String cpf){
        return consultaService.obterConsultasAnterioresPaciente(cpf);
    }
    
    @GetMapping(path = "/paciente/{cpf}/proximas")
    public @ResponseBody List<Consulta> obterConsultasProximasPaciente(@PathVariable String cpf){
        return consultaService.obterConsultasProximasPaciente(cpf);
    }
    
    @GetMapping(path = "/medico/{cpf}/anteriores")
    public @ResponseBody List<Consulta> obterConsultasAnterioresMedico(@PathVariable String cpf){
        return consultaService.obterConsultasAnterioresMedico(cpf);
    }
    
    @GetMapping(path = "/medico/{cpf}/proximas")
    public @ResponseBody List<Consulta> obterConsultasProximasMedico(@PathVariable String cpf){
        return consultaService.obterConsultasProximasMedico(cpf);
    }
    
    @PatchMapping(path = "/{numAtendimento}/prontuario")
    public void registrarProntuario(@PathVariable long numAtendimento,@RequestBody Prontuario prontuario){
        consultaService.registrarProntuario(numAtendimento, prontuario);
    }

}
