DROP SCHEMA IF EXISTS frota_viagens;
CREATE SCHEMA IF NOT EXISTS frota_viagens DEFAULT CHARACTER SET utf8 ;
USE frota_viagens;

CREATE TABLE IF NOT EXISTS carro (
  id INT NOT NULL AUTO_INCREMENT,
  modelo VARCHAR(45) NOT NULL,
  km_atual FLOAT NOT NULL,
  placa VARCHAR(45) NOT NULL,
  ativo INT NOT NULL DEFAULT 1,
  PRIMARY KEY (id))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS viagem (
  id INT NOT NULL AUTO_INCREMENT,
  destino VARCHAR(100) NOT NULL,
  km_inicial FLOAT NOT NULL,
  km_final FLOAT NULL,
  ativo INT NOT NULL DEFAULT 1,
  carro_id INT NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_viagem_carro_idx (carro_id ASC),
  CONSTRAINT fk_viagem_carro
    FOREIGN KEY (carro_id)
    REFERENCES carro (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



USE frota_viagens;
INSERT INTO 
	carro(modelo, km_atual, placa, ativo) 
VALUES
	('Palio', 33000, 'PAC-1234', 1),
	('Corsa', 110000, 'FOG-1234', 1),
	('Prisma', 17000, 'MIB-1234', 1),
	('Clio', 65319, 'MAC-1234', 1),
	('Sandeiro', 44030, 'BIG-1234', 1),
	('Gol', 117978, 'BOB-1234', 1),
	('Siena', 12000, 'POO-1234', 0);


select * from viagem;

select * from carro