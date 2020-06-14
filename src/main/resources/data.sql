INSERT INTO cid VALUES
    ('A16.1','Tuberculose pulmonar, sem realizacao de exame bacteriologico ou histologico'),
    ('E11.5','Diabetes mellitus nao-insulino-dependente - com complicacoes circulatorias perifericas'),
    ('I10','Hipertensao essencial (primaria)'),
    ('J15.9','Pneumonia nao especificada'),
    ('O82.1','Parto cesariana de emergencia'),
    ('K35.0','Apendicite aguda com peritonite generalizada'),
    ('R51','Cefaleia'),
    ('Z00.0','Exame medico geral');

INSERT INTO medico VALUES
    ("12300012300","931819191","medico1@medico.com","","","1999-01-01","01770-000","Lapa","Barueri","Rua abc","20","Casado","Marcos Souza","M","100","SP"),
    ("12300012311","941819191","medico2@medico.com","","","1985-06-01","01770-000","Santana","Sao Paulo","Rua def","10","Solteiro","Marcia Silva","F","200","SP"),
    ("12300012322","951819191","medico3@medico.com","","","1990-04-01","01770-000","Pinheiros","Sao Paulo","Rua ghi","30","Casado","Cristina Oliveira","F","150","SP"),
    ("12300012333","961819191","medico4@medico.com","","","1991-02-01","01770-000","Mooca","Sao Paulo","Rua jkl","40","Casado","Felipe Rocha","M","341","SP"),

    ("12300012444","961819191","medico5@medico.com","","","1992-02-01","01770-000","Centro","Barueri","Rua jkl","50","Solteiro","Olivia Souza","F","865","SP"),
    ("12300012555","961819191","medico6@medico.com","","","1981-02-01","01770-000","Centro","Jundiai","Rua jkl","60","Casado","Maria Cristovao","F","947","SP"),
    ("12300012666","961819191","medico7@medico.com","","","1976-02-01","01770-000","Centro","Jundiai","Rua jkl","70","Viuvo","Paulo Roberto","M","927","SP"),
    ("12300012777","961819191","medico8@medico.com","","","1950-02-01","01770-000","Centro","Jundiai","Rua jkl","80","Casado","Antonio Prates","M","189","SP");

INSERT INTO medico_disponibilidade
    (medico_cpf, disponibilidade)
VALUES
    ("12300012300", 2),
    ("12300012300", 3),
    ("12300012300", 5),
    ("12300012300", 6),
    ("12300012300", 7),
    ("12300012311", 1),
    ("12300012311", 2),
    ("12300012311", 4),
    ("12300012311", 6),
    ("12300012311", 7),
    ("12300012322", 1),
    ("12300012322", 3),
    ("12300012322", 7),
    ("12300012333", 4),
    ("12300012333", 5),
    ("12300012444",1),
    ("12300012555",1),
    ("12300012666",1),
    ("12300012777",1);

INSERT INTO especialidade VALUES
    (1,"Clinica Medica"),
    (2,"Ginecologia e Obstetricia"),
    (3,"Endocrinologia"),
    (4,"Dermatologia"),
    (5,"Geriatria"),
    (6,"Otorrinolaringologia"),
    (7,"Pediatria"),
    (8,"Cardiologia"),
    (9,"Urologia"),
    (10,"Oftalmologia");

INSERT INTO medico_especialidades VALUES
    ("12300012300",1),
    ("12300012311",1),
    ("12300012311",2),
    ("12300012322",1),
    ("12300012322",4),
    ("12300012322",1),
    ("12300012322",7),
    ("12300012322",1),
    ("12300012322",10),
    ("12300012333",1),
    ("12300012333",3),
    ("12300012333",1),
    ("12300012333",5),
    ("12300012444",2),
    ("12300012555",2),
    ("12300012666",5),
    ("12300012777",8);

INSERT INTO unidade VALUES
    ("999", "99090-9999","unidadeclinica@unidade.com",NULL,"1234-5678","08870-000","Barueri","Centro","Avenida abc","1111","Clinica PEG");

INSERT INTO paciente
    (cpf, nome, sexo,email,celular, ca, dm, dpoc, etilista, has, ic, tabagista, cidade, dt_nascimento)
VALUES
    ("12312312312", "Diego da Silva", "M", "diego@gmail.com", "99999-8888", 0, 0, 0, 0, 0, 0, 1,"Sao Paulo", "1999-04-01"),
    ("12312312322", "Helena Holanda", "F", "helena@gmail.com", "91010-9090", 0, 1, 0, 0, 0, 0, 0,"Jundiai", "1994-03-07"),
    ("12312312333", "Roberta Mendes", "F", "roberta@gmail.com", "98283-4040", 0, 1, 0, 0, 1, 0, 0,"Sao Paulo", "1997-04-09"),
    ("57833322211", "Gustavo Silva", "M", "gustavo@gmail.com", "98352-4560", 0, 1, 0, 0, 1, 0, 0,"Sao Paulo", "1994-03-01"),
    ("22211133344", "Gabriela Augusta", "F", "gabriela@gmail.com", "99528-5562", 0, 1, 0, 0, 1, 0, 0,"Sao Paulo", "1992-03-05"),
    ("55566688811", "Mariana Jesus", "F", "mariana@gmail.com", "95542-5566", 0, 1, 0, 0, 1, 0, 0,"Campinas", "1970-03-01"),
    ("77788899910", "Jose Roberto", "M", "jose@gmail.com", "99778-5263", 0, 1, 0, 0, 1, 0, 0,"Campinas", "1952-04-09"),
    ("10012312300", "Solange Pereira", "F", "solange@gmail.com", "99999-8888", 0, 0, 0, 0, 0, 0, 0,"Guarulhos", "1993-04-20"),
    ("10012312301", "Debora Souza", "F", "debora@gmail.com", "91010-9090", 0, 0, 0, 0, 0, 0, 0,"Osasco", "1988-04-07"),
    ("10012312302", "Ivan Souza", "M", "ivan@gmail.com", "98283-4040", 0, 0, 0, 0, 0, 0, 0,"Osasco", "1990-04-09"),
    ("10033322203", "Jhonatas", "M", "jhonatas@gmail.com", "98352-4560", 0, 0, 0, 0, 0, 0, 0,"Osasco", "1990-05-01"),
    ("10011133304", "Gabriel Fernandes", "M", "gabriel@gmail.com", "99528-5562", 0, 1, 0, 0, 1, 0, 1,"Carapicuiba", "2001-05-05"),
    ("10066688805", "Kelly Mendes", "F", "kelly@gmail.com", "95542-5566", 0, 0, 0, 0, 0, 0, 0,"Cajamar", "2000-06-01"),
    ("10088899906", "Anderson Araujo", "M", "anderson@gmail.com", "99778-5263", 0, 0, 0, 0, 0, 0, 0,"Sao Paulo", "1995-06-02"),
    ("10011133307", "Eduarda Maria", "F", "eduarda@gmail.com", "99528-5562", 0, 1, 0, 0, 1, 0, 1,"Cotia", "1992-05-05"),
    ("10066688808", "Daiane Menezes", "F", "daiane@gmail.com", "95542-5566", 0, 0, 0, 0, 0, 0, 0,"Sao Paulo", "1991-05-01"),
    ("10088899909", "Thais Goncalves", "F", "thais@gmail.com", "99778-5263", 0, 0, 0, 0, 0, 0, 0,"Barueri", "1989-05-09"),
    ("98744455520", "Marina Oliveira", "F", "marina@gmail.com", "99878-5385", 0, 0, 0, 0, 1, 0, 0,"Carapicuiba", "1950-06-09");

INSERT INTO recepcionista
    (cpf, nome, sexo, dt_nascimento, celular, email, bairro, cidade)
VALUES
    ("12355510001", "Daniela Oliveira", "F", "1940-01-01", "(11) 93030-4040", "recep@recep.com", "Vila Nova", "Jandira");

INSERT INTO administrador
    (cpf, nome, sexo, dt_nascimento, celular, email, bairro, cidade)
VALUES
    ("12355510002", "Melinda Souza", "F", "1980-01-01","(11) 92020-3030","adm@adm.com", "Sao Paulo", "Sao Paulo");

INSERT INTO prontuario
    (id,aval_medico,fr,pa,peso,queixa,temperatura,fc,cid_codigo)
VALUES
    (1,"Paciente apresenta dor em regiao occiptal",70,"12x80",80,"Dor",36.5,60,"R51"),
    (2,"Paciente presente no consultorio para realizacao de exames de rotina",70,"12x80",80,"Avaliacao medica",36.5,70, "Z00.0"),
    (3,"Paciente presente no consultorio para realizacao de exames de rotina",70,"12x80",80,"Avaliacao medica",36.5,70, "Z00.0"),
    (4,"Paciente presente no consultorio para realizacao de exames de rotina",70,"12x80",80,"Avaliacao medica",36.5,70, "Z00.0"),
    (5,"Paciente presente no consultorio para realizacao de exames de rotina",70,"12x80",80,"Avaliacao medica",36.5,70, "Z00.0"),
    (6,"Paciente presente no consultorio para realizacao de exames de rotina",70,"12x80",80,"Avaliacao medica",36.5,70, "Z00.0");

INSERT INTO consulta
    (paciente_cpf, medico_cpf, especialidade_id, unidade_cnpj, confirmada, realizada, data, hora_inicio, hora_fim, prontuario_id, convenio, tipo_atendimento)
VALUES
    ("12312312312", "12300012300", 1, "999", 0, 0,"2020-06-13", "09:00", "09:30", NULL, "Convenio", "Consulta"),
    ("12312312322", "12300012333", 3, "999", 0, 0,"2020-06-15", "09:00", "09:30", NULL, "Particular", "Retorno"),
    ("12312312312", "12300012322", 4, "999", 1, 1,"2020-05-03", "09:00", "09:30", 1, "Convenio", "Retorno"),
    ("12312312333", "12300012311", 2, "999", 0, 0, CURDATE(), "09:00", "09:30", NULL, "Particular", "Consulta"),
    ("12312312312", "12300012322", 4, "999", 1, 0, "2020-05-15", "10:00", "10:30", NULL, "Convenio", "Retorno"),
    ("57833322211", "12300012322", 4, "999", 1, 0, "2020-07-15", "14:30", "15:00", NULL, "Particular", "Consulta"),
    ("22211133344", "12300012322", 4, "999", 1, 0, "2019-07-20", "12:30", "13:00", NULL, "Particular", "Retorno"),
    ("55566688811", "12300012322", 4, "999", 1, 0, "2019-05-08", "09:00", "09:30", NULL, "Particular", "Consulta"),
    ("77788899910", "12300012333", 5, "999", 1, 0, "2020-06-18", "09:30", "10:00", NULL, "Convenio", "Retorno"),
    ("98744455520", "12300012333", 5, "999", 1, 0, "2020-04-12", "16:30", "17:00", NULL, "Convenio", "Consulta"),
    ("55566688811", "12300012322", 10, "999", 1, 0, "2020-05-12", "10:30", "11:00", NULL, "Convenio", "Consulta"),
    ("55566688811", "12300012322", 10, "999", 1, 1, "2020-04-13", "09:30", "10:00", 2, "Convenio", "Retorno"),
    ("22211133344", "12300012322", 10, "999", 1, 1, "2020-05-06", "15:00", "15:30", 4, "Convenio", "Consulta"),
    ("22211133344", "12300012322", 10, "999", 1, 1, "2020-06-08", "09:00", "09:30", 6, "Convenio", "Retorno"),
    ("98744455520", "12300012300", 1, "999", 1, 1, "2020-06-01",  "13:00", "13:30", 5, "Particular", "Consulta"),
    ("98744455520", "12300012300", 1, "999", 1, 0, "2020-07-01",  "13:30", "14:00", NULL, "Particular", "Retorno"),
    ("12312312322", "12300012311", 2, "999", 1, 1, "2020-04-24", "17:00", "17:30", 3, "Convenio", "Consulta"),
    ("12312312333", "12300012311", 2, "999", 1, 0, "2020-06-24", "17:30", "18:00", NULL, "Convenio", "Consulta"),
    ("57833322211", "12300012333", 3, "999", 1, 0, CURDATE(), "15:00", "15:30", NULL, "Particular", "Consulta"),
    ("10066688808", "12300012333", 3, "999", 1, 0, "2020-05-10", "11:00", "11:30", NULL, "Particular", "Consulta");
    

INSERT INTO roles
    (role_id, role)
VALUES
    (1,"ADMIN"),
    (2,"PACIENTE"),
    (3,"MEDICO"),
    (4,"RECEPCIONISTA");

INSERT INTO usuarios
    (cpf, senha)
VALUES
   ("12312312312", "$2a$10$447FMn5/uklcAESzwoeJoe2tebZQ4GAiqX8gw.SULdF.Cma/NcmT6"), -- user: 12312312312, senha: senha  (paciente)
   ("12312312322", "$2a$10$447FMn5/uklcAESzwoeJoe2tebZQ4GAiqX8gw.SULdF.Cma/NcmT6"), -- user: 12312312322, senha: senha  (paciente)
   ("12312312333", "$2a$10$447FMn5/uklcAESzwoeJoe2tebZQ4GAiqX8gw.SULdF.Cma/NcmT6"), -- user: 12312312333, senha: senha  (paciente)
   ("57833322211", "$2a$10$447FMn5/uklcAESzwoeJoe2tebZQ4GAiqX8gw.SULdF.Cma/NcmT6"), -- user: 57833322211, senha: senha  (paciente)
   ("22211133344", "$2a$10$447FMn5/uklcAESzwoeJoe2tebZQ4GAiqX8gw.SULdF.Cma/NcmT6"), -- user: 22211133344, senha: senha  (paciente)
   ("55566688811", "$2a$10$447FMn5/uklcAESzwoeJoe2tebZQ4GAiqX8gw.SULdF.Cma/NcmT6"), -- user: 55566688811, senha: senha  (paciente)
   ("77788899910", "$2a$10$447FMn5/uklcAESzwoeJoe2tebZQ4GAiqX8gw.SULdF.Cma/NcmT6"), -- user: 77788899910, senha: senha  (paciente)
   ("98744455520", "$2a$10$447FMn5/uklcAESzwoeJoe2tebZQ4GAiqX8gw.SULdF.Cma/NcmT6"), -- user: 98744455520, senha: senha  (paciente)
    ("10012312300", "$2a$10$447FMn5/uklcAESzwoeJoe2tebZQ4GAiqX8gw.SULdF.Cma/NcmT6"), -- user: 98744455520, senha: senha  (paciente)
    ("10012312301", "$2a$10$447FMn5/uklcAESzwoeJoe2tebZQ4GAiqX8gw.SULdF.Cma/NcmT6"), -- user: 10012312301, senha: senha  (paciente)
    ("10012312302", "$2a$10$447FMn5/uklcAESzwoeJoe2tebZQ4GAiqX8gw.SULdF.Cma/NcmT6"), -- user: 10012312302, senha: senha  (paciente)
    ("10033322203", "$2a$10$447FMn5/uklcAESzwoeJoe2tebZQ4GAiqX8gw.SULdF.Cma/NcmT6"), -- user: 10033322203, senha: senha  (paciente)
    ("10011133304", "$2a$10$447FMn5/uklcAESzwoeJoe2tebZQ4GAiqX8gw.SULdF.Cma/NcmT6"), -- user: 10011133304, senha: senha  (paciente)
    ("10066688805", "$2a$10$447FMn5/uklcAESzwoeJoe2tebZQ4GAiqX8gw.SULdF.Cma/NcmT6"), -- user: 10066688805, senha: senha  (paciente)
    ("10088899906", "$2a$10$447FMn5/uklcAESzwoeJoe2tebZQ4GAiqX8gw.SULdF.Cma/NcmT6"), -- user: 10088899906, senha: senha  (paciente)
    ("10011133307", "$2a$10$447FMn5/uklcAESzwoeJoe2tebZQ4GAiqX8gw.SULdF.Cma/NcmT6"), -- user: 10011133307, senha: senha  (paciente)
    ("10066688808", "$2a$10$447FMn5/uklcAESzwoeJoe2tebZQ4GAiqX8gw.SULdF.Cma/NcmT6"), -- user: 10066688808, senha: senha  (paciente)
    ("10088899909",  "$2a$10$447FMn5/uklcAESzwoeJoe2tebZQ4GAiqX8gw.SULdF.Cma/NcmT6"), -- user: 10088899909, senha: senha  (paciente)
   ("12300012300", "$2a$10$447FMn5/uklcAESzwoeJoe2tebZQ4GAiqX8gw.SULdF.Cma/NcmT6"), -- user: 12300012300, senha: senha  (medico)
   ("12300012311", "$2a$10$447FMn5/uklcAESzwoeJoe2tebZQ4GAiqX8gw.SULdF.Cma/NcmT6"), -- user: 12300012311, senha: senha  (medico)
   ("12300012322", "$2a$10$447FMn5/uklcAESzwoeJoe2tebZQ4GAiqX8gw.SULdF.Cma/NcmT6"), -- user: 12300012322, senha: senha  (medico)
   ("12300012333", "$2a$10$447FMn5/uklcAESzwoeJoe2tebZQ4GAiqX8gw.SULdF.Cma/NcmT6"), -- user: 12300012333, senha: senha  (medico)
    ("12300012444", "$2a$10$447FMn5/uklcAESzwoeJoe2tebZQ4GAiqX8gw.SULdF.Cma/NcmT6"), -- user: 12300012444, senha: senha  (medico)
    ("12300012555", "$2a$10$447FMn5/uklcAESzwoeJoe2tebZQ4GAiqX8gw.SULdF.Cma/NcmT6"), -- user: 12300012555, senha: senha  (medico)
    ("12300012666", "$2a$10$447FMn5/uklcAESzwoeJoe2tebZQ4GAiqX8gw.SULdF.Cma/NcmT6"), -- user: 12300012666, senha: senha  (medico)
    ("12300012777", "$2a$10$447FMn5/uklcAESzwoeJoe2tebZQ4GAiqX8gw.SULdF.Cma/NcmT6"), -- user: 12300012777, senha: senha  (medico)
   ("12355510001", "$2a$10$447FMn5/uklcAESzwoeJoe2tebZQ4GAiqX8gw.SULdF.Cma/NcmT6"), -- user: 12355510001, senha: senha  (recepcionista)
   ("12355510002", "$2a$10$447FMn5/uklcAESzwoeJoe2tebZQ4GAiqX8gw.SULdF.Cma/NcmT6"); -- user: 12355510002, senha: senha  (admin)

INSERT INTO user_role
    (cpf, role_id)
VALUES
   ("12312312312", 2),
   ("12312312322", 2),
   ("12312312333", 2),
   ("57833322211", 2),
   ("22211133344", 2),
   ("55566688811", 2),
   ("77788899910", 2),
   ("98744455520", 2),
   ("10012312300",2),
    ("10012312301",2),
    ("10012312302",2),
    ("10033322203",2),
    ("10011133304",2),
    ("10066688805",2),
    ("10088899906",2),
    ("10011133307",2),
    ("10066688808",2),
    ("10088899909", 2),
   ("12300012300", 3),
   ("12300012311", 3),
   ("12300012322", 3),
   ("12300012333", 3),
    ("12300012444",3),
    ("12300012555",3),
    ("12300012666",3),
    ("12300012777",3),
   ("12355510001", 4),
   ("12355510002", 1);
