package br.com.prontomed.peg.models;

import java.util.List;

public class Medico extends Pessoa {

    private RegistroMedico crm;
    private Disponibilidade disponibilidade;
    private List<Especialidade> especialidades;
}
