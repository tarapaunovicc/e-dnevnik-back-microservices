/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 8.0.31 : Database - e-dnevnik
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`e-dnevnik` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `e-dnevnik`;

/*Table structure for table `absence` */

DROP TABLE IF EXISTS `absence`;

CREATE TABLE `absence` (
  `classid` int NOT NULL,
  `teacherusername` varchar(255) NOT NULL,
  `studentusername` varchar(255) NOT NULL,
  `excused` tinyint(1) NOT NULL DEFAULT '0',
  `lessonid` int NOT NULL,
  `isfinal` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`classid`,`teacherusername`,`studentusername`,`lessonid`),
  KEY `fk9` (`studentusername`),
  CONSTRAINT `fk_lesson` FOREIGN KEY (`classid`, `teacherusername`, `lessonid`)
      REFERENCES `lesson` (`classid`, `username`, `lessonid`),
  CONSTRAINT `fk9` FOREIGN KEY (`studentusername`) REFERENCES `student` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `absence` */

insert  into `absence`(`classid`,`teacherusername`,`studentusername`,`excused`,`lessonid`,`isfinal`) values 
(1,'prof1','student1',1,159,1),
(1,'prof1','student1',1,402,1),
(1,'prof1','student1',0,756,1),
(1,'prof1','student1',0,802,1),
(1,'prof1','student1',0,803,1),
(1,'prof1','student1',0,852,1),
(1,'prof1','student1',0,853,1),
(1,'prof1','student1',0,952,1),
(1,'prof1','student1',0,953,1),
(1,'prof1','student1',0,1403,1),
(1,'prof1','student1',0,1452,1),
(1,'prof1','student1',0,1553,1),
(1,'prof1','student1',0,1602,1),
(1,'prof1','student1',0,1603,1),
(1,'prof1','student1',0,1622,1),
(1,'prof1','student1',0,1631,1),
(1,'prof1','student1',1,1803,1),
(1,'prof1','student1',1,1857,1),
(1,'prof1','student1',0,2052,0),
(1,'prof1','student2',0,802,0),
(1,'prof1','student2',0,803,0),
(1,'prof1','student2',0,953,0),
(1,'prof1','student2',0,1403,0),
(1,'prof1','student2',0,1623,0),
(1,'prof1','student2',0,1624,0),
(1,'prof1','student2',0,1625,0),
(1,'prof1','student2',0,1652,0),
(1,'prof1','student2',0,1703,0),
(1,'prof1','student2',0,1704,0),
(1,'prof1','student2',0,1705,0),
(1,'prof1','student2',0,1752,0),
(1,'prof1','student2',0,1902,0),
(1,'prof1','student2',0,2052,0),
(1,'prof1','student3',0,803,0),
(1,'prof1','student3',0,952,0),
(1,'prof1','student3',0,953,0),
(1,'prof1','student3',0,1403,0),
(1,'prof1','student3',0,1706,0),
(1,'prof1','student3',0,2052,0),
(1,'prof1','student4',1,2052,0),
(1,'prof3','student1',0,854,1),
(1,'prof3','student1',0,855,1),
(1,'prof3','student2',0,856,0),
(1,'prof3','student2',0,859,0),
(1,'prof3','student2',0,902,0),
(1,'prof3','student2',0,903,0),
(1,'prof3','student2',0,904,0),
(1,'prof3','student3',0,855,0),
(1,'prof3','student3',0,856,0),
(1,'prof3','student3',0,857,0),
(1,'prof3','student3',0,859,0),
(1,'prof3','student3',0,902,0),
(1,'prof3','student3',0,904,0),
(2,'prof2','student4',0,806,0),
(2,'prof2','student5',0,806,0),
(2,'prof3','student4',0,805,0);

/*Table structure for table `absence_seq` */

DROP TABLE IF EXISTS `absence_seq`;

CREATE TABLE `absence_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `absence_seq` */

insert  into `absence_seq`(`next_val`) values 
(301);

/*Table structure for table `class` */

DROP TABLE IF EXISTS `class`;

CREATE TABLE `class` (
  `classid` int NOT NULL,
  `grade` int NOT NULL,
  `number` int NOT NULL,
  `classteacher` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`classid`),
  KEY `fk2` (`classteacher`),
  CONSTRAINT `fk2` FOREIGN KEY (`classteacher`) REFERENCES `teacher` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `class` */

insert  into `class`(`classid`,`grade`,`number`,`classteacher`) values 
(1,4,2,'prof1'),
(2,3,1,'prof2');

/*Table structure for table `grade` */

DROP TABLE IF EXISTS `grade`;

CREATE TABLE `grade` (
  `studentusername` varchar(255) NOT NULL,
  `teacherusername` varchar(255) NOT NULL,
  `date` date NOT NULL DEFAULT '2024-02-02',
  `gradeid` int NOT NULL AUTO_INCREMENT,
  `grade` int NOT NULL DEFAULT '-1',
  PRIMARY KEY (`studentusername`,`teacherusername`,`gradeid`),
  KEY `fk4` (`teacherusername`),
  KEY `gradeid` (`gradeid`),
  CONSTRAINT `fk3` FOREIGN KEY (`studentusername`) REFERENCES `student` (`username`),
  CONSTRAINT `fk4` FOREIGN KEY (`teacherusername`) REFERENCES `teacher` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=703 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `grade` */

insert  into `grade`(`studentusername`,`teacherusername`,`date`,`gradeid`,`grade`) values 
('student1','prof1','2024-07-24',1,6),
('student1','prof1','2025-02-07',452,3),
('student1','prof1','2025-02-17',502,2),
('student1','prof1','2025-02-17',503,2),
('student1','prof1','2025-02-17',504,5),
('student1','prof1','2025-02-17',505,3),
('student1','prof1','2025-02-17',506,3),
('student1','prof1','2025-02-17',509,3),
('student1','prof1','2025-02-17',511,1),
('student1','prof1','2025-03-01',552,2),
('student1','prof1','2025-03-01',553,5),
('student1','prof1','2025-03-01',554,2),
('student1','prof1','2025-03-01',602,5),
('student1','prof1','2025-05-31',702,5),
('student1','prof2','2024-07-16',3,-1),
('student1','prof3','2024-07-17',4,-1),
('student2','prof1','2024-07-27',2,-1),
('student2','prof1','2025-02-17',507,4),
('student2','prof1','2025-02-17',508,3),
('student2','prof1','2025-02-17',510,2),
('student2','prof1','2025-03-09',652,2),
('student2','prof2','2024-07-09',5,-1),
('student2','prof3','2024-07-16',6,-1),
('student3','prof1','2024-07-09',8,-1),
('student3','prof1','2025-02-17',512,4),
('student3','prof2','2024-07-16',7,-1),
('student3','prof3','2024-02-02',10,-1),
('student4','prof1','2024-07-10',9,-1),
('student4','prof2','2024-02-02',11,-1),
('student4','prof3','2024-02-02',12,-1);

/*Table structure for table `grade_seq` */

DROP TABLE IF EXISTS `grade_seq`;

CREATE TABLE `grade_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `grade_seq` */

insert  into `grade_seq`(`next_val`) values 
(801);

/*Table structure for table `lesson` */

DROP TABLE IF EXISTS `lesson`;

CREATE TABLE `lesson` (
  `classid` int NOT NULL,
  `username` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `classordinalnumber` int NOT NULL,
  `lessonid` int NOT NULL AUTO_INCREMENT,
  `curriculum` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`classid`,`username`,`lessonid`),
  KEY `fk6` (`username`),
  KEY `lessonid` (`lessonid`),
  CONSTRAINT `fk5` FOREIGN KEY (`classid`) REFERENCES `class` (`classid`),
  CONSTRAINT `fk6` FOREIGN KEY (`username`) REFERENCES `teacher` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2053 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `lesson` */

insert  into `lesson`(`classid`,`username`,`date`,`classordinalnumber`,`lessonid`,`curriculum`) values 
(1,'prof1','2024-07-27',1,155,'Kvadratne jednacine i binomi'),
(1,'prof1','2024-07-27',3,159,'Analiticka geometrija'),
(1,'prof1','2024-07-27',2,161,'Svasta'),
(1,'prof1','2024-07-27',2,165,'sdfghjkljhgfc'),
(1,'prof1','2024-07-27',1,252,'dfghjk'),
(1,'prof1','2024-07-27',2,253,'dfklkjhghj'),
(1,'prof1','2024-07-28',2,302,'sdfghjk'),
(1,'prof1','2024-07-28',2,352,'Kontrolni zadatak'),
(1,'prof1','2024-07-28',2,402,'Svasta nesto\n'),
(1,'prof1','2024-07-28',2,403,'dfghj'),
(1,'prof1','2024-07-28',1,404,'sdfghjk'),
(1,'prof1','2024-07-28',2,405,'dfghujklk'),
(1,'prof1','2024-07-28',2,406,'dfghj'),
(1,'prof1','2024-07-28',2,408,'dfghjk'),
(1,'prof1','2024-07-28',2,409,'dfrtgyhjn'),
(1,'prof1','2024-07-28',1,452,'wertyui'),
(1,'prof1','2024-07-28',1,502,'qwertyuiop'),
(1,'prof1','2024-07-28',4,503,'dfghjk'),
(1,'prof1','2024-07-28',1,552,'dfghj'),
(1,'prof1','2024-07-28',3,602,'swedfrtyui'),
(1,'prof1','2024-07-29',1,652,'qwertyuio'),
(1,'prof1','2024-07-27',1,702,'Kvadratne jednacine i binomi'),
(1,'prof1','2024-07-29',2,752,'qwsertyujb'),
(1,'prof1','2024-07-29',1,753,'ttttttttttttt'),
(1,'prof1','2024-07-29',2,754,''),
(1,'prof1','2024-07-29',1,755,'ccc'),
(1,'prof1','2024-07-29',1,756,'dfgh'),
(1,'prof1','2024-09-21',2,802,'Svasta nestooo'),
(1,'prof1','2024-09-21',3,803,'sofija'),
(1,'prof1','2024-09-21',3,807,'ubicu se\n'),
(1,'prof1','2024-09-21',2,808,'opop'),
(1,'prof1','2024-09-21',5,852,'ricecakes'),
(1,'prof1','2024-09-21',4,853,'uhja'),
(1,'prof1','2025-02-05',1,952,'Opis casa'),
(1,'prof1','2025-02-05',3,953,'Novi casic'),
(1,'prof1','2025-02-05',7,954,'casic'),
(1,'prof1','2025-02-07',3,1002,'Matematika'),
(1,'prof1','2025-02-07',2,1003,'adfgear'),
(1,'prof1','2025-02-07',2,1004,'agaeg'),
(1,'prof1','2025-02-07',1,1005,'aggagagag'),
(1,'prof1','2025-02-07',2,1006,'asrgarg'),
(1,'prof1','2025-02-07',4,1052,'ujrfkdsl'),
(1,'prof1','2025-02-07',6,1102,'sdfghj'),
(1,'prof1','2025-02-07',6,1152,'asdfghj'),
(1,'prof1','2025-02-07',3,1202,'sdgfb'),
(1,'prof1','2025-02-07',1,1203,'fawgag'),
(1,'prof1','2025-02-07',1,1252,'yrjytf'),
(1,'prof1','2025-02-07',1,1253,'ttfcnhg'),
(1,'prof1','2025-02-07',1,1302,'agawrg'),
(1,'prof1','2025-02-07',1,1352,'agearg'),
(1,'prof1','2025-02-07',4,1402,'sdfghjkl'),
(1,'prof1','2025-02-07',2,1403,'dfg'),
(1,'prof1','2025-02-07',2,1404,'sdfghjk'),
(1,'prof1','2025-02-12',2,1452,'This aint Texas'),
(1,'prof1','2025-02-12',3,1453,'Da li je bio problem ili neeeee'),
(1,'prof1','2025-02-12',7,1502,'fghjkl.'),
(1,'prof1','2025-02-12',5,1552,'zalomio'),
(1,'prof1','2025-02-12',6,1553,'AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA'),
(1,'prof1','2025-02-17',2,1602,'nestooo'),
(1,'prof1','2025-02-17',3,1603,'sdfghjmn'),
(1,'prof1','2025-02-17',5,1604,'g'),
(1,'prof1','2025-02-17',2,1605,'asdfrgtyhj'),
(1,'prof1','2025-02-17',1,1606,'dfgh'),
(1,'prof1','2025-02-17',1,1607,'sdfghj'),
(1,'prof1','2025-02-17',3,1608,'dgf'),
(1,'prof1','2025-02-17',1,1609,'g'),
(1,'prof1','2025-02-17',6,1610,'asdf'),
(1,'prof1','2025-02-17',4,1611,'drsf'),
(1,'prof1','2025-02-17',4,1612,'fcd'),
(1,'prof1','2025-02-17',2,1613,'scfs'),
(1,'prof1','2025-02-17',4,1614,'rdf'),
(1,'prof1','2025-02-17',4,1615,'fvd'),
(1,'prof1','2025-02-17',5,1616,'cn'),
(1,'prof1','2025-02-17',4,1617,'dfghj'),
(1,'prof1','2025-02-17',5,1618,'f'),
(1,'prof1','2025-02-17',5,1619,'fghjui'),
(1,'prof1','2025-02-17',7,1620,'sdfghj'),
(1,'prof1','2025-02-17',5,1621,'dfgdf'),
(1,'prof1','2025-02-17',5,1622,'grfd'),
(1,'prof1','2025-02-17',6,1623,'hgbf'),
(1,'prof1','2025-02-17',5,1624,'azsdfvvg'),
(1,'prof1','2025-02-17',5,1625,'sdfghjkl'),
(1,'prof1','2025-02-17',5,1626,'sdfghjkl'),
(1,'prof1','2025-02-17',1,1629,'cdfgbhn'),
(1,'prof1','2025-02-17',5,1630,'dfgh'),
(1,'prof1','2025-02-17',3,1631,'dfghj'),
(1,'prof1','2025-02-18',5,1652,'jhgf'),
(1,'prof1','2025-03-01',2,1702,'dfghj'),
(1,'prof1','2025-03-01',6,1703,'Kontrolni zadatak'),
(1,'prof1','2025-03-01',0,1704,'Pitagorina teorema'),
(1,'prof1','2025-03-01',5,1705,'Polinomi'),
(1,'prof1','2025-03-01',4,1706,'Talesova teorema'),
(1,'prof1','2025-03-01',5,1752,'nesto'),
(1,'prof1','2025-03-03',5,1802,'Razredni čas'),
(1,'prof1','2025-03-03',2,1803,'fgh'),
(1,'prof1','2025-03-03',2,1804,'xcfvgbhn'),
(1,'prof1','2025-03-09',5,1852,'dfghjkl'),
(1,'prof1','2025-03-09',0,1853,'cvgbhnj'),
(1,'prof1','2025-03-09',3,1854,'ghjkl'),
(1,'prof1','2025-03-09',0,1855,'dfghj'),
(1,'prof1','2025-03-09',0,1856,'cvbn'),
(1,'prof1','2025-03-09',0,1857,'cvbn'),
(1,'prof1','2025-03-09',5,1902,'12345678'),
(1,'prof1','2024-05-31',5,2052,'Matematika'),
(1,'prof3','2024-07-25',0,2,NULL),
(1,'prof3','2024-07-25',0,3,NULL),
(1,'prof3','2024-07-25',0,52,NULL),
(1,'prof3','2024-07-26',1,102,NULL),
(1,'prof3','2024-07-27',1,152,'Prvi Njutnov zakon'),
(1,'prof3','2024-07-27',1,153,'Kontrolni zadatak'),
(1,'prof3','2024-07-27',2,157,'Ubrzanje i predjeni put'),
(1,'prof3','2024-07-27',3,158,'Usmeno odgovaranje'),
(1,'prof3','2024-07-27',3,163,'edrtgyhujkjhbv'),
(1,'prof3','2024-07-27',0,164,'dfghjkl'),
(1,'prof3','2024-07-27',2,255,'dtyuiolkjh'),
(1,'prof3','2024-09-21',5,854,'hajde da vidimo'),
(1,'prof3','2024-09-21',4,855,'prvo odeljenje\n'),
(1,'prof3','2024-09-21',1,856,'gbhjmk'),
(1,'prof3','2024-09-21',4,857,'nesto'),
(1,'prof3','2024-09-21',4,858,'dfghjk,m'),
(1,'prof3','2024-09-21',2,859,'jejej'),
(1,'prof3','2024-09-21',2,902,'oiuytre'),
(1,'prof3','2024-09-21',4,903,'erer'),
(1,'prof3','2024-09-21',4,904,'fghj'),
(2,'prof2','2024-07-27',1,154,'Alkaloidi'),
(2,'prof2','2024-07-27',3,160,'Sublimacija'),
(2,'prof2','2024-07-27',1,162,'sdfghj'),
(2,'prof2','2024-07-27',2,202,'dftgyhujikl,kmjh'),
(2,'prof2','2024-07-27',2,254,'dcfghjkl,mnbv'),
(2,'prof2','2024-07-28',2,407,'dcfghjkl,mnb'),
(2,'prof2','2024-07-29',2,703,'Svasta'),
(2,'prof2','2024-09-21',1,804,'hemija\n'),
(2,'prof2','2024-09-21',1,806,'ccc'),
(2,'prof3','2024-07-27',2,156,'Bernulijeva jednacina'),
(2,'prof3','2024-09-21',7,805,'poslednji cas');

/*Table structure for table `lesson_seq` */

DROP TABLE IF EXISTS `lesson_seq`;

CREATE TABLE `lesson_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `lesson_seq` */

insert  into `lesson_seq`(`next_val`) values 
(2151);

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `username` varchar(255) NOT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `umcn` varchar(255) DEFAULT NULL,
  `studentclass` int NOT NULL,
  `username1` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `fk10` (`studentclass`),
  KEY `pk2` (`username1`),
  CONSTRAINT `fk10` FOREIGN KEY (`studentclass`) REFERENCES `class` (`classid`),
  CONSTRAINT `pk2` FOREIGN KEY (`username1`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `student` */

insert  into `student`(`username`,`firstname`,`lastname`,`umcn`,`studentclass`,`username1`) values 
('student1','Tara','Paunovic','0408000715366',1,'student1'),
('student2','Milica','Papic','08080028677935',1,'student2'),
('student3','Stefan','Simic','0303002172734',1,'student3'),
('student4','Marko','Petrovic','0305000123543',2,'student4'),
('student5','Kosta','Adzic','1512999853229',2,'student5'),
('student6','Andrijana','Dimitrovska','12089981345263',2,'student6');

/*Table structure for table `subject` */

DROP TABLE IF EXISTS `subject`;

CREATE TABLE `subject` (
  `subjectid` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`subjectid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `subject` */

insert  into `subject`(`subjectid`,`name`) values 
(1,'Matematika'),
(2,'Hemija'),
(3,'Fizika');

/*Table structure for table `teacher` */

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
  `username` varchar(255) NOT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `subject` int NOT NULL,
  `username1` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `fk` (`subject`),
  KEY `pk` (`username1`),
  CONSTRAINT `fk` FOREIGN KEY (`subject`) REFERENCES `subject` (`subjectid`),
  CONSTRAINT `pk` FOREIGN KEY (`username1`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `teacher` */

insert  into `teacher`(`username`,`firstname`,`lastname`,`subject`,`username1`) values 
('prof1','Milan','Sretenovic',1,'prof1'),
('prof2','Snezana','Krstic',2,'prof2'),
('prof3','Ana','Mijatovic',3,'prof3');

/*Table structure for table `teachersclasses` */

DROP TABLE IF EXISTS `teachersclasses`;

CREATE TABLE `teachersclasses` (
  `classid` int NOT NULL,
  `teacherusername` varchar(255) NOT NULL,
  PRIMARY KEY (`classid`,`teacherusername`),
  KEY `sp1` (`teacherusername`),
  CONSTRAINT `sp1` FOREIGN KEY (`teacherusername`) REFERENCES `teacher` (`username`),
  CONSTRAINT `sp2` FOREIGN KEY (`classid`) REFERENCES `class` (`classid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `teachersclasses` */

insert  into `teachersclasses`(`classid`,`teacherusername`) values 
(1,'prof1'),
(2,'prof2'),
(1,'prof3'),
(2,'prof3');

/*Table structure for table `token` */

DROP TABLE IF EXISTS `token`;

CREATE TABLE `token` (
  `id` int NOT NULL AUTO_INCREMENT,
  `token` varchar(255) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL,
  `expired` bit(1) NOT NULL,
  `revoked` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user` (`user`),
  CONSTRAINT `fk_user` FOREIGN KEY (`user`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `token` */

insert  into `token`(`id`,`token`,`type`,`user`,`expired`,`revoked`) values 
(1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTQzNDQyMiwiZXhwIjoxNzQyODc0NDIyfQ.2VN6BXTssDFwMQaTO9Sstn-0uYymVOBX--XThhz1M_g','BEARER','prof1','',''),
(2,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTQzNDQyNCwiZXhwIjoxNzQyODc0NDI0fQ.ZLUqg-67mwnMMpf8Nn_Jv3PqLrBOeSAsQOZtEwB7KZ0','BEARER','prof1','',''),
(3,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTQzNDg5NSwiZXhwIjoxNzQyODc0ODk1fQ.1fF46xjWxRkTUCs0zYyIpo6LdfMo8Z_tokqmpTvuSb8','BEARER','prof1','',''),
(4,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTQzNTEzNCwiZXhwIjoxNzQyODc1MTM0fQ.lcPTwY3qXj5wKpgVGICMol4YcRdUKv25WRC8YCoQ3Is','BEARER','prof1','',''),
(5,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTQzNTI0MiwiZXhwIjoxNzQyODc1MjQyfQ.IdSK8gL00RhNv7cHHOvDiuiFcTGglmm7yYB-7Eb2uB4','BEARER','prof1','',''),
(6,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTQzNTQzMCwiZXhwIjoxNzQyODc1NDMwfQ.Ai2o_lel0bwI2gO_0N40S3So1snLYThlx0jP83Z8T28','BEARER','prof1','',''),
(7,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTQzNjUxNywiZXhwIjoxNzQyODc2NTE3fQ.eCL1lv7Ktaxg4mfkDdzQldi7w-7tKtchQfa-l1Jpenc','BEARER','prof1','',''),
(8,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTQzNjU2MywiZXhwIjoxNzQyODc2NTYzfQ.goT4Wy4qGdEoPQ5068mQdCiwwVdl-6eCKHZN6NUb0fM','BEARER','prof1','',''),
(9,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTUzNTE0MCwiZXhwIjoxNzQyOTc1MTQwfQ.deQvn8w8elzcDAjzTvyINhkS3AqTOWv4cxyEopqVbf8','BEARER','prof1','',''),
(10,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTUzNTI0NywiZXhwIjoxNzQyOTc1MjQ3fQ._NyR1-_zeIkvNrz4IYZOg5VGyg_h14AgWkvmteV6m4s','BEARER','prof1','',''),
(11,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTUzNTU1OSwiZXhwIjoxNzQyOTc1NTU5fQ.pja3ANgeyNqCIcM7pVYmGE3x8Q_Mzt8xwYVeE_oUkD4','BEARER','prof1','',''),
(12,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTUzNjM1MywiZXhwIjoxNzQyOTc2MzUzfQ.PnOw2EH6dkQ4ehE6VZ7JxduRnr-XrtYjjtGtOEfhxhA','BEARER','prof1','',''),
(13,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTUzNjQ4NiwiZXhwIjoxNzQyOTc2NDg2fQ.G8QDZS0r8jxX6vxn00Wx1ZwRLucVriq_d3ytyY-cIMM','BEARER','prof1','',''),
(14,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTUzNjg1MSwiZXhwIjoxNzQyOTc2ODUxfQ.plAtepu9LeDLcyphOl3hCoGRLLNJy4OO3l9dDn_O5Ww','BEARER','prof1','',''),
(15,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTUzNzA0MywiZXhwIjoxNzQyOTc3MDQzfQ.G69wxhhqhnX7tfob8Z7ozQwG53jAzpl-gUpeCxanDig','BEARER','prof1','',''),
(16,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTUzNzI4OSwiZXhwIjoxNzQyOTc3Mjg5fQ.UhErbTrlYgwGR2meJLi_FUMnM0_McnqdZw56XTc4QI8','BEARER','prof1','',''),
(17,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTUzNzQ1MywiZXhwIjoxNzQyOTc3NDUzfQ.ufIAxX8zj0Bz2-cQlwo8YfzAhm28oHCvnvcJpxxXWOI','BEARER','prof1','',''),
(18,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTUzNzcxMSwiZXhwIjoxNzQyOTc3NzExfQ.fov5eF1btDJZE0lY_e4k4CEapT5EhOYVj_OaOW6BiNA','BEARER','prof1','',''),
(19,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTUzNzgwNSwiZXhwIjoxNzQyOTc3ODA1fQ.nu1rV7mlkRx7dxeXQFhtPs_E_vQfFZACxO-kVRz5uaQ','BEARER','prof1','',''),
(20,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTUzODIxNSwiZXhwIjoxNzQyOTc4MjE1fQ.zNTk1PipGR9uZI1M0iosfz3kWBSDpJb3k4_8fBKBS5c','BEARER','prof1','',''),
(21,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTUzODM3MywiZXhwIjoxNzQyOTc4MzczfQ.9V3XBGdkh5oj6zvAGJPR9x-uvJbuElOI5ZC67uqeD-M','BEARER','prof1','',''),
(22,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTUzODQ2NSwiZXhwIjoxNzQyOTc4NDY1fQ.hizjz-0-bdL8mHE4iUraKqSpbZVuyW1Sho93C-PT2CU','BEARER','prof1','',''),
(23,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTUzODYyOCwiZXhwIjoxNzQyOTc4NjI4fQ.pEA7f1BSVzAAcYVIAr81YuGiny-nf_J9pJt0wr2sMUY','BEARER','prof1','',''),
(24,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTUzODg1OSwiZXhwIjoxNzQyOTc4ODU5fQ.S2GNTZKifHyVuBrjExfWPqTp_O0fjdfy1XR9pW1kcIk','BEARER','prof1','',''),
(25,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTU0MjA1NSwiZXhwIjoxNzQyOTgyMDU1fQ.GylA4Qpgj2xzNX2FVCmcKNhuFFbiDuCnbdPZBl0yVRs','BEARER','prof1','',''),
(26,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTU0MjIyNSwiZXhwIjoxNzQyOTgyMjI1fQ.DkdlQR99C9bK8J2D9Ck35W4g29lYaK2N4pLGq3zI9z4','BEARER','prof1','',''),
(27,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTU0MzE5NCwiZXhwIjoxNzQyOTgzMTk0fQ.oRQok8cV8X9XX3a0_vjwlQEysdIG84fzu3SoDZcEIjk','BEARER','prof1','',''),
(28,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTU0OTE2NCwiZXhwIjoxNzQyOTg5MTY0fQ.jzkzpNFeImu1ZpnRYeMpQCwGVli1vlhrzq7_tsqZiYE','BEARER','prof1','',''),
(29,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTU0OTE2NSwiZXhwIjoxNzQyOTg5MTY1fQ.xi9TEouBwER2OEepElwa2TeZtC8-wd3i26Dl_-xI2TQ','BEARER','prof1','',''),
(30,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTU0OTg3NSwiZXhwIjoxNzQyOTg5ODc1fQ.DBBwjAOduJZ6cnw-2W_TVWQmp_Pv4R_XilocfrKANoA','BEARER','prof1','',''),
(31,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTU1MTMzMCwiZXhwIjoxNzQyOTkxMzMwfQ.-EEW-CPKPDu1ebyRIPjE4xRXZzXPq8GpLiisqDFezWM','BEARER','prof1','',''),
(32,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTU1MTkzMCwiZXhwIjoxNzQyOTkxOTMwfQ.BsZOKhFClah4AWyw7iDl_5dJXxsw6azvezQl5IwvNZo','BEARER','prof1','',''),
(33,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTU1MjYzNiwiZXhwIjoxNzQyOTkyNjM2fQ.DBCtB93KMLNJuEQRSiFFHKx-Tup3J5N1C4yVaVHv1gI','BEARER','prof1','',''),
(34,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTU1Mjg4OSwiZXhwIjoxNzQyOTkyODg5fQ.UTVYlnGKlx5PUDRRjEI0j970hNCGXwkMdxmHq4FV4cw','BEARER','prof1','',''),
(35,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTU1MzU3MSwiZXhwIjoxNzQyOTkzNTcxfQ.ZY0iGWarLPSIryCmIelUNOPDsNl9msXy6dXfG8Y2sJY','BEARER','prof1','',''),
(36,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTU1NDE2MSwiZXhwIjoxNzQyOTk0MTYxfQ.tobEbQLX9y89GXfWWU-QTxz679x5lXtZceLREwAhFaU','BEARER','prof1','',''),
(37,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTU1NDU2MSwiZXhwIjoxNzQyOTk0NTYxfQ.C6ltZYJUxD2GHgGlzdc4Wb1mGeo-XY9SorB6zZ2tYuY','BEARER','prof1','',''),
(38,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTU1NTAyOCwiZXhwIjoxNzQyOTk1MDI4fQ.zI_uXSHtwdjSuwLwqR2hgA5AAo5wRl_sTWT-NkTvCrY','BEARER','prof1','',''),
(39,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTU1NTUzNCwiZXhwIjoxNzQyOTk1NTM0fQ.sB2LW4VgBFsvq15XRN1jr8fYMe2Lb0-Y38VfPzZJ1AY','BEARER','prof1','',''),
(40,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTU1NTgyOSwiZXhwIjoxNzQyOTk1ODI5fQ.deC_-F9B1u7H44Y0BdSgVmY8C1EnZZjYt1uZx6_UGWY','BEARER','prof1','',''),
(41,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMSIsImlhdCI6MTc0MTU1NTkyNCwiZXhwIjoxNzQyOTk1OTI0fQ.Xkhq-kiVWnqlUGXY8dyUbKimLrqsPFMXEcxcXpcJSAo','BEARER','prof1','',''),
(42,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMyIsImlhdCI6MTc0MTU1NjA2MCwiZXhwIjoxNzQyOTk2MDYwfQ.Rr_y3xalQPl9YfkbGHATC5l6JdypHrPMq148h-sVcus','BEARER','prof3','\0','\0'),
(43,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mMiIsImlhdCI6MTc0ODAzNzI1OSwiZXhwIjoxNzQ5NDc3MjU5fQ.BpXBflwnTjG2lUOMzJZBYdmiRIt-GeuWBV4Ldl1xGPs','BEARER','prof2','',''),
(44,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9URUFDSEVSIiwic3ViIjoicHJvZjIiLCJpYXQiOjE3NDgyMDc5NDksImV4cCI6MTc0ODI5NDM0OX0.0_7UXkHiaXuGTtJdpC_MkGlEmn4jfNwrJfjuqaCIb6E','BEARER','prof2','',''),
(45,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9URUFDSEVSIiwic3ViIjoicHJvZjIiLCJpYXQiOjE3NDgyMDg2MzksImV4cCI6MTc0ODI5NTAzOX0.JqgIFvij2m3TwXoqfZGnl2mQ5zFVtAVtq7wNJEoKusY','BEARER','prof2','',''),
(46,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9URUFDSEVSIiwic3ViIjoicHJvZjIiLCJpYXQiOjE3NDgyMDg2NDEsImV4cCI6MTc0ODI5NTA0MX0.eq03xLs7sYxd5FxxveDCXLPDa0J7GSS_0vbo5LcqozA','BEARER','prof2','',''),
(47,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9URUFDSEVSIiwic3ViIjoicHJvZjEiLCJpYXQiOjE3NDgyMDg2NzIsImV4cCI6MTc0ODI5NTA3Mn0.sC-L5noFqHuJar1QqttxXUla5aiZFY7YQR8kp3uRuPY','BEARER','prof1','',''),
(48,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9URUFDSEVSIiwic3ViIjoicHJvZjEiLCJpYXQiOjE3NDgyMDkwNzUsImV4cCI6MTc0ODI5NTQ3NX0.8_DOe7ZrSlkf2UQHBmiuHVYUhh6ljIXBpJQkPydR-C8','BEARER','prof1','',''),
(49,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9URUFDSEVSIiwic3ViIjoicHJvZjEiLCJpYXQiOjE3NDgyMTI1NzksImV4cCI6MTc0ODI5ODk3OX0.lQdzPN9jz7sh4pBx7WPhMtQ4u2vPeOaqCqz5R_eeHDw','BEARER','prof1','',''),
(50,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9URUFDSEVSIiwic3ViIjoicHJvZjEiLCJpYXQiOjE3NDgyMTI2ODAsImV4cCI6MTc0ODI5OTA4MH0.EL-zZtwpjk4hvmHv7vqNcrnJvGUy8JQt2pVl4sfYDyk','BEARER','prof1','',''),
(51,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9URUFDSEVSIiwic3ViIjoicHJvZjIiLCJpYXQiOjE3NDgyMTI3MjIsImV4cCI6MTc0ODI5OTEyMn0.1GZ7j67zSjqtA8mmkwGR2WwbtD5G54o-xwFFOjgkH9w','BEARER','prof2','\0','\0'),
(52,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9URUFDSEVSIiwic3ViIjoicHJvZjEiLCJpYXQiOjE3NDgyMTI3OTgsImV4cCI6MTc0ODI5OTE5OH0.c4Hd4GVVYrMjwBTR0mFHlib84aHL5IWDrJ9OgzXrS0E','BEARER','prof1','',''),
(53,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9URUFDSEVSIiwic3ViIjoicHJvZjEiLCJpYXQiOjE3NDg0NjI5MDYsImV4cCI6MTc0ODU0OTMwNn0.GfgVdpDeivpclI-fa4wzGp2cHkY_5PlJ_2st3lQHWbc','BEARER','prof1','',''),
(54,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9URUFDSEVSIiwic3ViIjoicHJvZjEiLCJpYXQiOjE3NDg1NDc4MzAsImV4cCI6MTc0ODYzNDIzMH0.ed1kj1z4EsHMLem9id1OYn7M69MHHDAShOrhxpeS28M','BEARER','prof1','',''),
(55,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9URUFDSEVSIiwic3ViIjoicHJvZjEiLCJpYXQiOjE3NDg3MDI5ODQsImV4cCI6MTc0ODc4OTM4NH0.t8c6q5EY5mjQfG6hfjwxhM6h3TrZQ6y7CCzRLu3ghiA','BEARER','prof1','\0','\0');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user` */

insert  into `user`(`username`,`password`,`role`) values 
('prof1','$2a$12$b.z.5kfsg9jptWIQCK4.zur8XoDlVtD4V0eWVDsGzZssFuyOjYAmK','ROLE_TEACHER'),
('prof2','$2a$12$lD/WX5oUfl8gvz/0n.aZxe1p1PARi/uZ8Mg0bnTaYYz8viS/aQvKW','ROLE_TEACHER'),
('prof3','$2a$12$WTEoKZHXRrm2tRNwI5bKnuq4qg8MOXph8QpiiGN1J0Wb5ZqMGg9zG','ROLE_TEACHER'),
('student1','$2a$12$VfSE66L0tEtyp.f7pD0kBubNtvAvPOHuztmcmFDXzIASNijoDm9iK','ROLE_STUDENT'),
('student2','student2','ROLE_STUDENT'),
('student3','student3','ROLE_STUDENT'),
('student4','student4','ROLE_STUDENT'),
('student5','student5','ROLE_STUDENT'),
('student6','student6','ROLE_STUDENT');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
