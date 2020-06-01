INSERT INTO cid VALUES
    ('J15.9','Pneumonia nao especificada'),
    ('O82.1','Parto cesariana de emergencia'),
    ('K35.0','Apendicite aguda com peritonite generalizada'),
    ('R51','Cefaléia');

INSERT INTO medico VALUES
    ("12300012300","931819191","medico1@medico.com","","","1999-01-01","01770-000","Lapa","Barueri","Rua abc","20","Casado","Medico1","F","100","SP");

INSERT INTO medico_disponibilidade
    (medico_cpf, disponibilidade)
VALUES
    ("12300012300", 2),
    ("12300012300", 3),
    ("12300012300", 5),
    ("12300012300", 6),
    ("12300012300", 7);

INSERT INTO especialidade VALUES
    (1,"Clinica Medica"),
    (2,"Ginecologia"),
    (3,"Endocrinologia");

INSERT INTO medico_especialidades VALUES
    ("12300012300",1);

INSERT INTO unidade VALUES
    ("999", "99999-9999","unidadeclinica@unidade.com",NULL,"1234-5678","08870-000","Barueri","Centro","Avenida abc","1111","Clinica PEG");

INSERT INTO paciente
    (cpf, nome, sexo, ca, dm, dpoc, etilista, has, ic, tabagista)
VALUES
    ("12312312312", "Diego da Silva", "M", 0, 0, 0, 0, 0, 0, 0);

INSERT INTO recepcionista
    (cpf, nome, sexo)
VALUES
    ("12355510001", "Daniela Oliveira", "F");


INSERT INTO prontuario
    (id,aval_medico,fr,pa,queixa,temperatura)
VALUES
    (1,"teste",70,"12x80","dor",36.5);

INSERT INTO prontuario_cids
    (prontuario_id,cids_codigo)
VALUES
    (1,"J15.9");

INSERT INTO consulta
    (paciente_cpf, medico_cpf, especialidade_id, unidade_cnpj, confirmada, realizada, data, hora_inicio, hora_fim, prontuario_id)
VALUES
    ("12312312312", "12300012300", 1, "999", 0, 0,"2020-05-01", "09:00", "09:30", NULL),
    ("12312312312", "12300012300", 1, "999", 1, 0,"2020-05-02", "09:00", "09:30", NULL),
    ("12312312312", "12300012300", 1, "999", 1, 1,"2020-05-03", "09:00", "09:30", 1),
    ("12312312312", "12300012300", 1, "999", 0, 0, CURDATE(), "09:00", "09:30", NULL),
    ("12312312312", "12300012300", 1, "999", 1, 0, CURDATE(), "09:30", "10:00", NULL);

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
   ("12300012300", "$2a$10$447FMn5/uklcAESzwoeJoe2tebZQ4GAiqX8gw.SULdF.Cma/NcmT6"), -- user: 12300012300, senha: senha  (medico)
   ("12355510001", "$2a$10$447FMn5/uklcAESzwoeJoe2tebZQ4GAiqX8gw.SULdF.Cma/NcmT6"); -- user: 12355510001, senha: senha  (recepcionista)

INSERT INTO user_role
    (cpf, role_id)
VALUES
   ("12312312312", 2),
   ("12300012300", 3),
   ("12355510001", 4);
