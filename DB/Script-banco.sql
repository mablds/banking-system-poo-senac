CREATE TABLE clientes(
	id INT AUTO_INCREMENT,
    registro VARCHAR(14) NOT NULL,
    nome VARCHAR(50) NOT NULL,
    telefone VARCHAR(11) NOT NULL,
    email VARCHAR(50) NOT NULL,
    endereco VARCHAR(100) NOT NULL,
    tipo INT NOT NULL,
    ativo BOOLEAN NOT NULL,
    
    PRIMARY KEY(id)
);

CREATE TABLE contas (
	id INT AUTO_INCREMENT,
    id_cliente INT NOT NULL,
    criacao DATE NOT NULL,
    saldo DOUBLE NOT NULL,
    tipo INT NOT NULL,
    ativo BOOLEAN NOT NULL,
    
    PRIMARY KEY(id),
    FOREIGN KEY(id_cliente) REFERENCES clientes (id)
);

CREATE TABLE transacoes (
	id INT AUTO_INCREMENT,
    tr_date DATE NOT NULL,
    valor DOUBLE NOT NULL,
    pago_por INT,
    receptor INT,
    
    PRIMARY KEY(id),
    FOREIGN KEY(pago_por) REFERENCES clientes(id),
    FOREIGN KEY(receptor) REFERENCES clientes(id)
);

CREATE TABLE cobrancas (
	id INT AUTO_INCREMENT,
    criacao DATE NOT NULL,
    vencimento DATE NOT NULL,
    valor DOUBLE NOT NULL,
    receptor INT NOT NULL,
    pago BOOLEAN NOT NULL,
    
    PRIMARY KEY(id),
    FOREIGN KEY(receptor) REFERENCES clientes(id)
);

INSERT INTO clientes (registro, nome, telefone, email, endereco, tipo, ativo)
VALUES
("12345678911", "Arthur Sakemi", "912341234", "arthur@mail.com", "Rua rua, 100", 1, true),
("12345678912", "Marcelo Arthur", "912341234", "marcelo@mail.com", "Rua rua, 200", 2, true),
("12345678913", "Paty Oliveira", "912341234", "paty@mail.com", "Rua rua, 300", 3, true);

INSERT INTO contas (id_cliente, criacao, saldo, tipo, ativo)
VALUES
(1, "2020-06-06", 239.98, 1, true),
(1, "2020-06-06", 2000.98, 2, true),
(2, "2020-06-06", 257.94, 1, true),
(2, "2020-06-06", 3000.98, 2, true),
(3, "2020-06-06", 256.96, 1, true),
(3, "2020-06-06", 4000.98, 2, true);

CREATE VIEW contas_view AS
SELECT contas.id , clientes.nome AS nome_cliente, clientes.registro, contas.criacao, contas.saldo, contas.tipo, contas.ativo FROM contas
INNER JOIN clientes ON contas.id_cliente = clientes.id;