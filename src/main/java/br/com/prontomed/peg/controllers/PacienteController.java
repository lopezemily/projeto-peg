package br.com.prontomed.peg.controllers;

import br.com.prontomed.peg.models.Paciente;
import br.com.prontomed.peg.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/paciente")
public class PacienteController {
    
    @Autowired
    private PacienteService pacienteService;
    
    @PostMapping
    public void criarPaciente(@RequestBody Paciente paciente){
        pacienteService.inserirPaciente(paciente);
    }
    
}
