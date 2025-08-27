-- MySQL 8.x

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
SET NAMES utf8mb4;
SET time_zone = '+00:00';

CREATE DATABASE /*!32312 IF NOT EXISTS*/  `attendance_db`
  /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;

USE `attendance_db`;

-- Drop & create tables
DROP TABLE IF EXISTS `absence`;
DROP TABLE IF EXISTS `lesson`;

CREATE TABLE `lesson` (
  `lesson_id` INT NOT NULL AUTO_INCREMENT,
  `classid` INT NOT NULL,
  `teacherusername` VARCHAR(255) NOT NULL,
  `date` DATE NOT NULL,
  `classordinalnumber` INT NOT NULL,
  `curriculum` VARCHAR(1024) NULL,
  PRIMARY KEY (`lesson_id`),
  KEY `idx_lesson_class_teacher_date` (`classid`, `teacherusername`, `date`),
  KEY `idx_lesson_class_ord` (`classid`, `date`, `classordinalnumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `absence` (
  `studentusername` VARCHAR(255) NOT NULL,
  `lesson_id` INT NOT NULL,
  `excused` TINYINT(1) NOT NULL,
  `isfinal` TINYINT(1) NOT NULL,
  PRIMARY KEY (`studentusername`, `lesson_id`),
  KEY `idx_absence_lesson` (`lesson_id`),
  KEY `idx_absence_student` (`studentusername`),
  CONSTRAINT `fk_absence_lesson`
    FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`lesson_id`)
    ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------
-- LESSON DATA (Matematika / Hemija / Fizika)
-- --------------------------------------------
INSERT INTO `lesson`
(`lesson_id`,`classid`,`teacherusername`,`date`,`classordinalnumber`,`curriculum`) VALUES
-- prof1 (Matematika)
(155,1,'prof1','2024-07-27',1,'Matematika: Kvadratne jednačine i binom'),
(159,1,'prof1','2024-07-27',3,'Matematika: Analitička geometrija – pravac i rastojanje'),
(161,1,'prof1','2024-07-27',2,'Matematika: Zadaci iz kombinatorike'),
(165,1,'prof1','2024-07-27',2,'Matematika: Potencije i stepenovanje polinoma'),
(252,1,'prof1','2024-07-27',1,'Matematika: Racionalni izrazi – sređivanje'),
(253,1,'prof1','2024-07-27',2,'Matematika: Racionalni izrazi – jednadžbe'),
(302,1,'prof1','2024-07-28',2,'Matematika: Trigonometrijske osnovne relacije'),
(352,1,'prof1','2024-07-28',2,'Matematika: Kontrolni zadatak – trigonometrija'),
(402,1,'prof1','2024-07-28',2,'Matematika: Funkcije – uvod'),
(403,1,'prof1','2024-07-28',2,'Matematika: Monotonost i nultočke funkcije'),
(404,1,'prof1','2024-07-28',1,'Matematika: Linearne funkcije'),
(405,1,'prof1','2024-07-28',2,'Matematika: Kvadratne funkcije – graf'),
(406,1,'prof1','2024-07-28',2,'Matematika: Kvadratne funkcije – ekstremi'),
(408,1,'prof1','2024-07-28',2,'Matematika: Sistemi linearnih jednačina'),
(409,1,'prof1','2024-07-28',2,'Matematika: Nejednačine prvog reda'),
(452,1,'prof1','2024-07-28',1,'Matematika: Logaritmi – definicija'),
(502,1,'prof1','2024-07-28',1,'Matematika: Logaritamske osobine'),
(503,1,'prof1','2024-07-28',4,'Matematika: Eksponencijalne funkcije'),
(552,1,'prof1','2024-07-28',1,'Matematika: Geometrijska interpretacija jednačina'),
(602,1,'prof1','2024-07-28',3,'Matematika: Trigonometrijske jednačine'),
(652,1,'prof1','2024-07-29',1,'Matematika: Sličnost trouglova'),
(702,1,'prof1','2024-07-27',1,'Matematika: Kvadratne jednačine – ponavljanje'),
(752,1,'prof1','2024-07-29',2,'Matematika: Aritmetička i geometrijska sredina'),
(753,1,'prof1','2024-07-29',1,'Matematika: Skupovi – operacije'),
(754,1,'prof1','2024-07-29',2,'Matematika: Kombinatorika – aranžmani'),
(755,1,'prof1','2024-07-29',1,'Matematika: Vektori u ravni'),
(756,1,'prof1','2024-07-29',1,'Matematika: Svojstva kvadratnih funkcija'),
(802,1,'prof1','2024-09-21',2,'Matematika: Racionalne funkcije – uvod'),
(803,1,'prof1','2024-09-21',3,'Matematika: Asimptote racionalnih funkcija'),
(807,1,'prof1','2024-09-21',3,'Matematika: Odbrana zadataka (usmeno)'),
(808,1,'prof1','2024-09-21',2,'Matematika: Logičke formule i istinosne tabele'),
(852,1,'prof1','2024-09-21',5,'Matematika: Kombinatorika – permutacije'),
(853,1,'prof1','2024-09-21',4,'Matematika: Kombinatorika – varijacije'),
(952,1,'prof1','2025-02-05',1,'Matematika: Opis časa – uvod u diferencijalni račun'),
(953,1,'prof1','2025-02-05',3,'Matematika: Derivacije – osnovne formule'),
(954,1,'prof1','2025-02-05',7,'Matematika: Primene derivacija – ekstremi'),
(1002,1,'prof1','2025-02-07',3,'Matematika: Integral – uvod'),
(1003,1,'prof1','2025-02-07',2,'Matematika: Neodređeni integral'),
(1004,1,'prof1','2025-02-07',2,'Matematika: Određeni integral'),
(1005,1,'prof1','2025-02-07',1,'Matematika: Primitivne funkcije'),
(1006,1,'prof1','2025-02-07',2,'Matematika: Parcijalna integracija'),
(1052,1,'prof1','2025-02-07',4,'Matematika: Substitucija u integralu'),
(1102,1,'prof1','2025-02-07',6,'Matematika: Trigonometrijski integrali'),
(1152,1,'prof1','2025-02-07',6,'Matematika: Numerička integracija'),
(1202,1,'prof1','2025-02-07',3,'Matematika: Sume i nizovi – uvod'),
(1203,1,'prof1','2025-02-07',1,'Matematika: Konvergencija nizova'),
(1252,1,'prof1','2025-02-07',1,'Matematika: Redovi – osnovni pojmovi'),
(1253,1,'prof1','2025-02-07',1,'Matematika: Alternirajući redovi'),
(1302,1,'prof1','2025-02-07',1,'Matematika: Granice funkcija – uvod'),
(1352,1,'prof1','2025-02-07',1,'Matematika: L’Hôpitalovo pravilo'),
(1402,1,'prof1','2025-02-07',4,'Matematika: Kompleksni brojevi – uvod'),
(1403,1,'prof1','2025-02-07',2,'Matematika: Trigonometrijski oblik kompleksnog broja'),
(1404,1,'prof1','2025-02-07',2,'Matematika: Moivre-ova formula'),
(1452,1,'prof1','2025-02-12',2,'Matematika: Logaritamske funkcije – graf'),
(1453,1,'prof1','2025-02-12',3,'Matematika: Funkcionalne jednačine – uvod'),
(1502,1,'prof1','2025-02-12',7,'Matematika: Deteminante i matrice'),
(1552,1,'prof1','2025-02-12',5,'Matematika: Skalarni i vektorski proizvod'),
(1553,1,'prof1','2025-02-12',6,'Matematika: Analitička geometrija – kružnica'),
(1602,1,'prof1','2025-02-17',2,'Matematika: Pravougaoni trougao – primene'),
(1603,1,'prof1','2025-02-17',3,'Matematika: Jednačine pravca'),
(1604,1,'prof1','2025-02-17',5,'Matematika: Elipsa – svojstva'),
(1605,1,'prof1','2025-02-17',2,'Matematika: Hiperbola – osnovno'),
(1606,1,'prof1','2025-02-17',1,'Matematika: Parabola – fokus i direktrisa'),
(1607,1,'prof1','2025-02-17',1,'Matematika: Ugao između pravaca'),
(1608,1,'prof1','2025-02-17',3,'Matematika: Cevasti zadaci – vežba'),
(1609,1,'prof1','2025-02-17',1,'Matematika: Ravan i prava u prostoru'),
(1610,1,'prof1','2025-02-17',6,'Matematika: Zapremina tela – metode'),
(1611,1,'prof1','2025-02-17',4,'Matematika: Površina tela obrtanja'),
(1612,1,'prof1','2025-02-17',4,'Matematika: Projekcije u ravni'),
(1613,1,'prof1','2025-02-17',2,'Matematika: Trigonometrija – napredni zadaci'),
(1614,1,'prof1','2025-02-17',4,'Matematika: Složenije logaritamske jednačine'),
(1615,1,'prof1','2025-02-17',4,'Matematika: Eksponencijalne jednačine'),
(1616,1,'prof1','2025-02-17',5,'Matematika: Asimptotsko ponašanje funkcija'),
(1617,1,'prof1','2025-02-17',4,'Matematika: Inverzna funkcija'),
(1618,1,'prof1','2025-02-17',5,'Matematika: Kompozicija funkcija'),
(1619,1,'prof1','2025-02-17',5,'Matematika: Trigonometrijski identiteti – vežba'),
(1620,1,'prof1','2025-02-17',7,'Matematika: Geometrijske konstrukcije'),
(1621,1,'prof1','2025-02-17',5,'Matematika: Racionalne nejednačine'),
(1622,1,'prof1','2025-02-17',5,'Matematika: Kvadratne nejednačine'),
(1623,1,'prof1','2025-02-17',6,'Matematika: Logaritamske nejednačine'),
(1624,1,'prof1','2025-02-17',5,'Matematika: Eksponencijalne nejednačine'),
(1625,1,'prof1','2025-02-17',5,'Matematika: Trigonometrijske nejednačine'),
(1626,1,'prof1','2025-02-17',5,'Matematika: Sistemi nejednačina'),
(1629,1,'prof1','2025-02-17',1,'Matematika: Vektori u prostoru'),
(1630,1,'prof1','2025-02-17',5,'Matematika: Skalarni proizvod – primene'),
(1631,1,'prof1','2025-02-17',3,'Matematika: Ugao između vektora'),
(1652,1,'prof1','2025-02-18',5,'Matematika: Krive drugog reda – pregled'),
(1702,1,'prof1','2025-03-01',2,'Matematika: Polinomi – operacije'),
(1703,1,'prof1','2025-03-01',6,'Matematika: Kontrolni zadatak – polinomi'),
(1704,1,'prof1','2025-03-01',0,'Matematika: Pitagorina teorema – obnova'),
(1705,1,'prof1','2025-03-01',5,'Matematika: Polinomi – deljenje'),
(1706,1,'prof1','2025-03-01',4,'Matematika: Talesova teorema – primene'),
(1752,1,'prof1','2025-03-01',5,'Matematika: Kombinatorika – kombinacije'),
(1802,1,'prof1','2025-03-03',5,'Matematika: Razredni čas – organizacioni'),
(1803,1,'prof1','2025-03-03',2,'Matematika: Realne funkcije – pregled'),
(1804,1,'prof1','2025-03-03',2,'Matematika: Limite – zadaci'),
(1852,1,'prof1','2025-03-09',5,'Matematika: Statistika – srednja vrednost'),
(1853,1,'prof1','2025-03-09',0,'Matematika: Statistika – varijansa'),
(1854,1,'prof1','2025-03-09',3,'Matematika: Standardna devijacija'),
(1855,1,'prof1','2025-03-09',0,'Matematika: Odeljenski zadaci'),
(1856,1,'prof1','2025-03-09',0,'Matematika: Dodatne vežbe'),
(1857,1,'prof1','2025-03-09',0,'Matematika: Konsultacije'),
(1902,1,'prof1','2025-03-09',5,'Matematika: Uvod u verovatnoću'),
(2052,1,'prof1','2024-05-31',5,'Matematika: Završni pregled gradiva'),

-- prof3 (Fizika)
(2,1,'prof3','2024-07-25',0,'Fizika: Uvod u merenja'),
(3,1,'prof3','2024-07-25',0,'Fizika: Fizičke veličine i jedinice'),
(52,1,'prof3','2024-07-25',0,'Fizika: Greške merenja'),
(102,1,'prof3','2024-07-26',1,'Fizika: Kinematika – pravolinijsko kretanje'),
(152,1,'prof3','2024-07-27',1,'Fizika: Prvi Njutnov zakon'),
(153,1,'prof3','2024-07-27',1,'Fizika: Kontrolni zadatak – dinamika'),
(157,1,'prof3','2024-07-27',2,'Fizika: Ubrzanje i pređeni put'),
(158,1,'prof3','2024-07-27',3,'Fizika: Usmeno ispitivanje – mehanika'),
(163,1,'prof3','2024-07-27',3,'Fizika: Rad i energija – vežbe'),
(164,1,'prof3','2024-07-27',0,'Fizika: Impuls i količina kretanja'),
(255,1,'prof3','2024-07-27',2,'Fizika: Hidrostatika – pritisak'),
(854,1,'prof3','2024-09-21',5,'Fizika: Talasi – uvod'),
(855,1,'prof3','2024-09-21',4,'Fizika: Mehanički talasi'),
(856,1,'prof3','2024-09-21',1,'Fizika: Oscilacije – harmonijsko oscilovanje'),
(857,1,'prof3','2024-09-21',4,'Fizika: Interferencija talasa'),
(858,1,'prof3','2024-09-21',4,'Fizika: Difrakcija'),
(859,1,'prof3','2024-09-21',2,'Fizika: Brzina talasa'),
(902,1,'prof3','2024-09-21',2,'Fizika: Električno polje – uvod'),
(903,1,'prof3','2024-09-21',4,'Fizika: Kulsnov zakon'),
(904,1,'prof3','2024-09-21',4,'Fizika: Električni potencijal'),

-- prof2 (Hemija)
(154,2,'prof2','2024-07-27',1,'Hemija: Alkaloidi – pregled'),
(160,2,'prof2','2024-07-27',3,'Hemija: Sublimacija – primeri'),
(162,2,'prof2','2024-07-27',1,'Hemija: Hemijske veze – obnova'),
(202,2,'prof2','2024-07-27',2,'Hemija: Reakcije – tipovi'),
(254,2,'prof2','2024-07-27',2,'Hemija: Kiseline i baze – uvod'),
(407,2,'prof2','2024-07-28',2,'Hemija: Oksidi i hidroksidi'),
(703,2,'prof2','2024-07-29',2,'Hemija: Rastvori – koncentracija'),
(804,2,'prof2','2024-09-21',1,'Hemija: Uvod u organsku hemiju'),
(806,2,'prof2','2024-09-21',1,'Hemija: Laboratorijska sigurnost'),

-- prof3 u 2. odeljenju (Fizika)
(156,2,'prof3','2024-07-27',2,'Fizika: Bernulijeva jednačina'),
(805,2,'prof3','2024-09-21',7,'Fizika: Električna struja – osnove');

-- --------------------------------------------
-- ABSENCE DATA (koristi se samo: studentusername, lesson_id, excused, isfinal)
-- --------------------------------------------
INSERT INTO `absence`
(`studentusername`,`lesson_id`,`excused`,`isfinal`) VALUES
('student1',159,1,1),
('student1',402,1,1),
('student1',756,0,1),
('student1',802,0,1),
('student1',803,0,1),
('student1',852,0,1),
('student1',853,0,1),
('student1',952,0,1),
('student1',953,0,1),
('student1',1403,0,1),
('student1',1452,0,1),
('student1',1553,0,1),
('student1',1602,0,1),
('student1',1603,0,1),
('student1',1622,0,1),
('student1',1631,0,1),
('student1',1803,1,1),
('student1',1857,1,1),
('student1',2052,0,0),

('student2',802,0,0),
('student2',803,0,0),
('student2',953,0,0),
('student2',1403,0,0),
('student2',1623,0,0),
('student2',1624,0,0),
('student2',1625,0,0),
('student2',1652,0,0),
('student2',1703,0,0),
('student2',1704,0,0),
('student2',1705,0,0),
('student2',1752,0,0),
('student2',1902,0,0),
('student2',2052,0,0),

('student3',803,0,0),
('student3',952,0,0),
('student3',953,0,0),
('student3',1403,0,0),
('student3',1706,0,0),
('student3',2052,0,0),

('student4',2052,1,0),

('student1',854,0,1),
('student1',855,0,1),

('student2',856,0,0),
('student2',859,0,0),
('student2',902,0,0),
('student2',903,0,0),
('student2',904,0,0),

('student3',855,0,0),
('student3',856,0,0),
('student3',857,0,0),
('student3',859,0,0),
('student3',902,0,0),
('student3',904,0,0),

('student4',806,0,0),
('student5',806,0,0),

('student4',805,0,0);

-- Restore session
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
