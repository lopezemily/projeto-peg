package br.com.prontomed.peg.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.prontomed.peg.dto.Disponibilidade;
import br.com.prontomed.peg.services.ConsultaService;

@RestController
@RequestMapping(path = "/debug")
public class DebugController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping("/")
    public @ResponseBody List<Disponibilidade> debug() throws Exception {
        return consultaService.obterHorariosDisponiveis(LocalDate.parse("2020-01-01"), "Clinica Medica");
    }
}