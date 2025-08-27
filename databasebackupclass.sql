-- databasebackupclass.sql

-- Bezbedno obriši i kreiraj bazu
DROP DATABASE IF EXISTS `class_db`;
CREATE DATABASE `class_db` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `class_db`;

-- Privremeno isključi provere (radi lakšeg DROP/INSERT)
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO';

-- Tabele
DROP TABLE IF EXISTS `teachersclasses`;
DROP TABLE IF EXISTS `class`;

-- Glavna tabela class (da, MySQL podnosi ime class, koristimo backticks)
CREATE TABLE `class` (
  `classid` INT NOT NULL AUTO_INCREMENT,
  `grade` INT NOT NULL,
  `number` INT NOT NULL,
  `classteacher` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`classid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Povezna tabela: više nastavnika na jedan razred
CREATE TABLE `teachersclasses` (
  `classid` INT NOT NULL,
  `teacherusername` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`classid`, `teacherusername`),
  CONSTRAINT `fk_teachersclasses_class`
    FOREIGN KEY (`classid`) REFERENCES `class`(`classid`)
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Podaci
INSERT INTO `class`(`classid`,`grade`,`number`,`classteacher`) VALUES
  (1,4,2,'prof1'),
  (2,3,1,'prof2');

INSERT INTO `teachersclasses`(`classid`,`teacherusername`) VALUES
  (1,'prof1'),
  (2,'prof2'),
  (1,'prof3'),
  (2,'prof3');

-- Vrati podešavanja
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
