INSERT INTO cid VALUES
    ('J15.9','Pneumonia não especificada'),
    ('O82.1','Parto cesariana de emergencia'),
    ('K35.0','Apendicite aguda com peritonite generalizada'),
    ('R51','Cefaléia');

INSERT INTO medico VALUES
    ("12300012300","931819191","medico1@medico.com","","","1999-01-01","01770-000","Lapa","São Paulo","Rua abc","21","Medico1","F","100","SP",0,1,1,0,1,1,1),
    ("22222222200","91111-2222","medico2@medico.com","","","1999-02-02","03750-000","Lapa","São Paulo","Rua def","50","Medico2","M","200","SP",0,0,1,1,1,1,1);;

INSERT INTO especialidade VALUES
    (1,"Clinica Medica"),
    (2,"Ginecologia"),
    (3,"Endocrinologia");

INSERT INTO medico_especialidades VALUES
    ("12300012300",1);

INSERT INTO unidade VALUES
    ("999", "99999-9999","unidadeclinica@unidade.com",NULL,"1234-5678","08870-000","São Paulo","Centro","Avenida abc","Clinica PEG");