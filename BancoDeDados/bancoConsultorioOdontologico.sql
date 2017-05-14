-- MySQL Script generated by MySQL Workbench
-- 10/08/16 17:48:19
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema consultorioodontologico
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema consultorioodontologico
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `consultorioodontologico` DEFAULT CHARACTER SET utf8 ;
USE `consultorioodontologico` ;

-- -----------------------------------------------------
-- Table `consultorioodontologico`.`Pessoa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `consultorioodontologico`.`Pessoa` (
  `cpf` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `endereco` VARCHAR(45) NOT NULL,
  `id` INT(11) NOT NULL,
  `idade` INT(11) NOT NULL,
  `nascimento` VARCHAR(45) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `sexo` VARCHAR(45) NOT NULL,
  `telefone` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idPessoa_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
