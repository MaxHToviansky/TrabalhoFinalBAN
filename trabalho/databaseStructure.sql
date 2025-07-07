CREATE TABLE Pessoa(
    matricula SERIAL NOT NULL PRIMARY KEY,
    nome varchar(255) NOT NULL,
    diaNasc int NOT NULL,
    mesNasc int NOT NULL,
    anoNasc int NOT NULL,
    nacionalidade varchar(100) NOT NULL,
    sexo char NOT NULL,
    CPF bigint UNIQUE NOT NULL
);

CREATE TABLE LocalEvento(
    id_local SERIAL NOT NULL PRIMARY KEY,
    rua varchar(255) NOT NULL,
    numero varchar(20) NOT NULL,
    cidade varchar(100) NOT NULL,
    pais varchar(100) NOT NULL,
    complemento varchar(255)
);

CREATE TABLE Email(
    email varchar(255) UNIQUE NOT NULL PRIMARY KEY,
    id_local int,
    id_pessoa int,
    FOREIGN KEY (id_local) REFERENCES LocalEvento,
    FOREIGN KEY (id_pessoa) REFERENCES Pessoa
);

CREATE TABLE Telefone(
    telefone varchar(20) UNIQUE NOT NULL PRIMARY KEY,
    id_local int,
    id_pessoa int,
    FOREIGN KEY (id_local) REFERENCES LocalEvento,
    FOREIGN KEY (id_pessoa) REFERENCES Pessoa
);

CREATE TABLE Departamento(
    id_departamento SERIAL NOT NULL PRIMARY KEY,
    nome varchar(100)
);

CREATE TABLE Curso(
    id_curso SERIAL NOT NULL PRIMARY KEY,
    nome varchar(100) NOT NULL,
    id_departamento int NOT NULL,
    FOREIGN KEY (id_departamento) REFERENCES Departamento
);

CREATE TABLE Aluno(
    matricula int NOT NULL,
    id_curso int NOT NULL,
    semestre int,
    PRIMARY KEY (matricula),
    FOREIGN KEY (matricula) REFERENCES Pessoa,
    FOREIGN KEY (id_curso) REFERENCES Curso
);

CREATE TABLE Professor(
    matricula int NOT NULL,
    id_departamento int NOT NULL,
    formacao varchar(100) NOT NULL,
    PRIMARY KEY (matricula),
    FOREIGN KEY (matricula) REFERENCES Pessoa,
    FOREIGN KEY (id_departamento) REFERENCES Departamento
);

CREATE TABLE Palestra(
    id_palestra SERIAL NOT NULL PRIMARY KEY,
    titulo varchar(255) NOT NULL,
    -- dataEvento date NOT NULL,
    -- hora time NOT NULL, 
    identificadorSala varchar(50) NOT NULL,
    id_local int NOT NULL,
    id_departamento int NOT NULL,
    FOREIGN KEY (id_local) REFERENCES LocalEvento,
    FOREIGN KEY (id_departamento) REFERENCES Departamento
);

CREATE TABLE Palestrante(
    id_pessoa int NOT NULL,
    id_palestra int NOT NULL,
    PRIMARY KEY (id_pessoa,id_palestra),
    FOREIGN KEY (id_pessoa) REFERENCES Pessoa,
    FOREIGN KEY (id_palestra) REFERENCES Palestra
);

CREATE TABLE Participante(
    id_pessoa int NOT NULL,
    id_palestra int NOT NULL,
    PRIMARY KEY (id_pessoa,id_palestra),
    FOREIGN KEY (id_pessoa) REFERENCES Pessoa,
    FOREIGN KEY (id_palestra) REFERENCES Palestra
);


