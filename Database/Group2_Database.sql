CREATE TABLE `academic_year` (
  `PK_AYCode` int NOT NULL,
  PRIMARY KEY (`PK_AYCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `class` (
  `PK_Ccode` varchar(50) NOT NULL,
  `size` int DEFAULT NULL,
  `FK_Scode` varchar(50) DEFAULT NULL,
  `FK_Mcode` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`PK_Ccode`),
  KEY `class_semester_Scode` (`FK_Scode`),
  KEY `class_module_Mcode` (`FK_Mcode`),
  CONSTRAINT `class_module_Mcode` FOREIGN KEY (`FK_Mcode`) REFERENCES `module` (`PK_Mcode`),
  CONSTRAINT `class_semester_Scode` FOREIGN KEY (`FK_Scode`) REFERENCES `semester` (`PK_Scode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `class_has_lecturer` (
  `FK_Ccode` varchar(50) NOT NULL,
  `FK_Lcode` varchar(50) NOT NULL,
  PRIMARY KEY (`FK_Ccode`,`FK_Lcode`),
  KEY `cl_lecturer_Lcode` (`FK_Lcode`),
  CONSTRAINT `cl_class_Ccode` FOREIGN KEY (`FK_Ccode`) REFERENCES `class` (`PK_Ccode`),
  CONSTRAINT `cl_lecturer_Lcode` FOREIGN KEY (`FK_Lcode`) REFERENCES `lecturer` (`PK_Lcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `faculty` (
  `PK_Fcode` varchar(50) NOT NULL,
  `Fname` varchar(50) DEFAULT NULL,
  `FK_AYCode` int DEFAULT NULL,
  PRIMARY KEY (`PK_Fcode`),
  KEY `faculty_academicyear_AYCode` (`FK_AYCode`),
  CONSTRAINT `faculty_academicyear_AYCode` FOREIGN KEY (`FK_AYCode`) REFERENCES `academic_year` (`PK_AYCode`) ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `lecturer` (
  `PK_Lcode` varchar(50) NOT NULL,
  `Lname` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`PK_Lcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `module` (
  `PK_Mcode` varchar(50) NOT NULL,
  `Mname` varchar(50) DEFAULT NULL,
  `FK_Pcode` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`PK_Mcode`),
  KEY `module_program_Pcode` (`FK_Pcode`),
  CONSTRAINT `module_program_Pcode` FOREIGN KEY (`FK_Pcode`) REFERENCES `program` (`PK_Pcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `module_program_in_ay` (
  `FK_AYCode` int NOT NULL,
  `FK_Mcode` varchar(50) NOT NULL,
  `FK_Pcode` varchar(50) NOT NULL,
  PRIMARY KEY (`FK_AYCode`,`FK_Mcode`,`FK_Pcode`),
  KEY `mpa_module_Mcode` (`FK_Mcode`),
  KEY `mpa_program_Pcode` (`FK_Pcode`),
  CONSTRAINT `mpa_academicyear_AYCode` FOREIGN KEY (`FK_AYCode`) REFERENCES `academic_year` (`PK_AYCode`),
  CONSTRAINT `mpa_module_Mcode` FOREIGN KEY (`FK_Mcode`) REFERENCES `module` (`PK_Mcode`),
  CONSTRAINT `mpa_program_Pcode` FOREIGN KEY (`FK_Pcode`) REFERENCES `program` (`PK_Pcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `program` (
  `PK_Pcode` varchar(50) NOT NULL,
  `Pname` varchar(50) DEFAULT NULL,
  `FK_Fcode` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`PK_Pcode`),
  KEY `program_faculty_Fcode` (`FK_Fcode`),
  CONSTRAINT `program_faculty_Fcode` FOREIGN KEY (`FK_Fcode`) REFERENCES `faculty` (`PK_Fcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `questionaire` (
  `FK_Ccode` varchar(50) NOT NULL,
  `FK_Lcode` varchar(50) NOT NULL,
  PRIMARY KEY (`FK_Ccode`,`FK_Lcode`),
  KEY `questionaire_lecturer_Lcode` (`FK_Lcode`),
  CONSTRAINT `questionaire_class_Ccode` FOREIGN KEY (`FK_Ccode`) REFERENCES `class` (`PK_Ccode`),
  CONSTRAINT `questionaire_lecturer_Lcode` FOREIGN KEY (`FK_Lcode`) REFERENCES `lecturer` (`PK_Lcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `semester` (
  `PK_Scode` varchar(50) NOT NULL,
  `FK_AYCode` int DEFAULT NULL,
  PRIMARY KEY (`PK_Scode`),
  KEY `semester_academicyear_AYCode` (`FK_AYCode`),
  CONSTRAINT `semester_academicyear_AYCode` FOREIGN KEY (`FK_AYCode`) REFERENCES `academic_year` (`PK_AYCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
