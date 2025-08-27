/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 8.0.31 : Database - user_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
-- Kreiranje baze (ako ne postoji)
CREATE DATABASE docker compose exec -T mysql mysql -uroot -proot -e "USE user_db; SELECT * FROM user LIMIT 10;"
`user_db`
  /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `user_db`;

-- Bezbedno brisanje (zbog FK)
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='';
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;

DROP TABLE IF EXISTS `teacher`;
DROP TABLE IF EXISTS `student`;
DROP TABLE IF EXISTS `subject`;

-- SUBJECT
CREATE TABLE `subject` (
                           `subjectid` INT NOT NULL AUTO_INCREMENT,
                           `name` VARCHAR(255) NOT NULL,
                           PRIMARY KEY (`subjectid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- STUDENT
CREATE TABLE `student` (
                           `username` VARCHAR(255) NOT NULL,
                           `firstname` VARCHAR(255) DEFAULT NULL,
                           `lastname` VARCHAR(255) DEFAULT NULL,
                           `umcn` VARCHAR(255) DEFAULT NULL,
                           `studentclass` INT NOT NULL,
                           PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- TEACHER (FK -> subject.subjectid)
CREATE TABLE `teacher` (
                           `username` VARCHAR(255) NOT NULL,
                           `firstname` VARCHAR(255) DEFAULT NULL,
                           `lastname` VARCHAR(255) DEFAULT NULL,
                           `subject` INT NOT NULL,
                           PRIMARY KEY (`username`),
                           CONSTRAINT `fk_teacher_subject`
                               FOREIGN KEY (`subject`) REFERENCES `subject` (`subjectid`)
                                   ON UPDATE RESTRICT ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- PODACI: SUBJECT
INSERT INTO `subject` (`subjectid`, `name`) VALUES
                                                (1,'Matematika'),
                                                (2,'Hemija'),
                                                (3,'Fizika');

-- PODACI: STUDENT
INSERT INTO `student` (`username`,`firstname`,`lastname`,`umcn`,`studentclass`) VALUES
                                                                                    ('student08','Marija','Đorđević','123456789087',1),
                                                                                    ('student09','Petar','Lukić','12345678909',1),
                                                                                    ('student1','Tara','Paunovic','0408000715366',1),
                                                                                    ('student10','Ana','Mitrović','12345678910',1),
                                                                                    ('student11','Marko','Radovanović','12345678911',1),
                                                                                    ('student12','Jelena','Tadić','12345678912',1),
                                                                                    ('student13','Miloš','Stanković','12345678913',1),
                                                                                    ('student14','Tanja','Savić','12345678914',1),
                                                                                    ('student15','Vladimir','Ristić','12345678915',1),
                                                                                    ('student16','Jelena','Aleksić','12345678916',1),
                                                                                    ('student17','Filip','Đukić','12345678917',1),
                                                                                    ('student18','Katarina','Bošković','12345678918',1),
                                                                                    ('student19','Nemanja','Pavlović','12345678919',1),
                                                                                    ('student2','Milica','Papic','08080028677935',1),
                                                                                    ('student20','Ivana','Simić','12345678920',1),
                                                                                    ('student21','Maja','Jovanović','22345678921',2),
                                                                                    ('student22','Marko','Vasić','22345678922',2),
                                                                                    ('student23','Jovana','Nikolić','22345678923',2),
                                                                                    ('student24','Nemanja','Petrović','22345678924',2),
                                                                                    ('student25','Milica','Đorđević','22345678925',2),
                                                                                    ('student26','Aleksandar','Kovačević','22345678926',2),
                                                                                    ('student27','Marina','Lukić','22345678927',2),
                                                                                    ('student28','Stefan','Milošević','22345678928',2),
                                                                                    ('student29','Tanja','Popović','22345678929',2),
                                                                                    ('student3','Stefan','Simic','0303002172734',1),
                                                                                    ('student30','Petar','Mitrović','22345678930',2),
                                                                                    ('student31','Ivana','Radovanović','22345678931',2),
                                                                                    ('student32','Miloš','Tadić','22345678932',2),
                                                                                    ('student33','Jelena','Stanković','22345678933',2),
                                                                                    ('student34','Filip','Savić','22345678934',2),
                                                                                    ('student35','Katarina','Ristić','22345678935',2),
                                                                                    ('student36','Vladimir','Aleksić','22345678936',2),
                                                                                    ('student37','Jelena','Đukić','22345678937',2),
                                                                                    ('student38','Nemanja','Bošković','22345678938',2),
                                                                                    ('student39','Maja','Pavlović','22345678939',2),
                                                                                    ('student4','Marko','Petrovic','0305000123543',2),
                                                                                    ('student40','Marina','Simić','22345678940',2),
                                                                                    ('student5','Kosta','Adzic','1512999853229',2),
                                                                                    ('student6','Andrijana','Dimitrovska','12089981345263',2);

-- PODACI: TEACHER (subject -> FK)
INSERT INTO `teacher` (`username`,`firstname`,`lastname`,`subject`) VALUES
                                                                        ('prof1','Milan','Sretenovic',1),
                                                                        ('prof2','Snezana','Krstic',2),
                                                                        ('prof3','Ana','Mijatovic',3);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
