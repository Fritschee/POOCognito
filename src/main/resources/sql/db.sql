-- 1) Se existir, remove a database anterior
DROP DATABASE IF EXISTS `aluguelCarros`;

-- 2) Cria a database com charset utf8mb4 (suporta emojis e caracteres especiais)
CREATE DATABASE `aluguelCarros`
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

-- 3) Seleciona a database para uso
USE `aluguelCarros`;

-- 4) Cria a tabela de reservas
CREATE TABLE `reserva` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `modelo` VARCHAR(255) NOT NULL,
  `inicio` DATE NOT NULL,
  `fim`   DATE NOT NULL,
  `total` DOUBLE NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;


