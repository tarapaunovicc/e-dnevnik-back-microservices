CREATE DATABASE IF NOT EXISTS `grade_db`
  /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;

USE `grade_db`;

SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='';
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;

DROP TABLE IF EXISTS `grade`;

CREATE TABLE `grade` (
  `id`               INT NOT NULL AUTO_INCREMENT,
  `studentusername`  VARCHAR(255) NOT NULL,
  `teacherusername`  VARCHAR(255) NOT NULL,
  `date`             DATE NOT NULL,
  `grade`            INT  NOT NULL,
  CONSTRAINT `pk_grade` PRIMARY KEY (`id`),
  CONSTRAINT `chk_grade_range` CHECK (`grade` BETWEEN 1 AND 5),
  KEY `idx_grade_student` (`studentusername`),
  KEY `idx_grade_teacher` (`teacherusername`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `grade` (`studentusername`,`teacherusername`,`date`,`grade`) VALUES
('student1','prof1','2024-07-24',5),
('student1','prof1','2025-02-07',3),
('student1','prof1','2025-02-17',2),
('student1','prof1','2025-02-17',2),
('student1','prof1','2025-02-17',5),
('student1','prof1','2025-02-17',3),
('student1','prof1','2025-02-17',3),
('student1','prof1','2025-02-17',3),
('student1','prof1','2025-02-17',1),
('student1','prof1','2025-03-01',2),
('student1','prof1','2025-03-01',5),
('student1','prof1','2025-03-01',2),
('student1','prof1','2025-03-01',5),
('student1','prof1','2025-05-31',5),
('student1','prof2','2024-07-16',3),
('student1','prof3','2024-07-17',3),
('student2','prof1','2024-07-27',3),
('student2','prof1','2025-02-17',4),
('student2','prof1','2025-02-17',3),
('student2','prof1','2025-02-17',2),
('student2','prof1','2025-03-09',2),
('student2','prof2','2024-07-09',3),
('student2','prof3','2024-07-16',3),
('student3','prof1','2024-07-09',3),
('student3','prof1','2025-02-17',4),
('student3','prof2','2024-07-16',3),
('student3','prof3','2024-02-02',3),
('student4','prof1','2024-07-10',3),
('student4','prof2','2024-02-02',3),
('student4','prof3','2024-02-02',3);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
