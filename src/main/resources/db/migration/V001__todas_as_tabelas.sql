CREATE TABLE `beneficiario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `cpf` varchar(30) NOT NULL UNIQUE,
  `rg` varchar(30) NOT NULL,
  `perfil` varchar(15) NOT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


CREATE TABLE `processo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `processo_pae` varchar(15) NOT NULL,
  `processo_original` varchar(15) NOT NULL,
  `numero_lote` varchar(10) NOT NULL,
  `status_lote` varchar(255) NOT NULL,
  `status_processo` varchar(255) NOT NULL,
  `localizacao` bigint(20) NOT NULL,
  `beneficiarios` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE `laudo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `versao_laudo` varchar(30) NOT NULL,
  `item_avaliado` varchar(30) NOT NULL,
  `instituicao_avaliadora` varchar(40) NOT NULL,
  `data_avaliacao` date NOT NULL,
  `processos` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


CREATE TABLE `dados_bancarios` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `agencia` varchar(10) NOT NULL,
  `banco` varchar(50) NOT NULL,
  `conta` varchar(20) NOT NULL,
  `tipo_conta` varchar(10) NOT NULL,
  `beneficiarios` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


CREATE TABLE `orcamento` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `elemento_despesa` varchar(100) DEFAULT NULL,
  `fonte` varchar(100) DEFAULT NULL,
  `ptres` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;



CREATE TABLE `indenizacao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nl` varchar(15) NOT NULL,
  `data_nl` date NOT NULL,
  `ne` varchar(15) NOT NULL,
  `data_ne` date NOT NULL,
  `ob` varchar(15) NOT NULL,
  `data_ob` date NOT NULL,
  `data_acordo` date NOT NULL,
  `status_pagamento` varchar(100) NOT NULL,
  `valor` decimal(19,2) NOT NULL,
  `conta_deposito` bigint(20) NOT NULL,
  `processos` bigint(20) NOT NULL,
  `orcamento` bigint(20)NOT NULL,
  PRIMARY KEY (`id`)
  ) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE `localizacao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `municiopio` varchar(50) NOT NULL,
  `bairro` varchar(60) NOT NULL,
  `logradouro` varchar(100) NOT NULL,
  `status_logradouro` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE `imagens` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `imagem` longblob,
  `processos` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;