package br.com.prontomed.peg.models;

import java.time.LocalDateTime;

public class Consulta {

    private long numAtendimento;
    private Medico medico;
    private Paciente paciente;
    private Unidade unidade;
    private LocalDateTime dtHrConsulta;
    private Especialidade especialidade;
    private Receita receita;
    private Prontuario prontuario;
    
    private Consulta consultaAnterior;
}
