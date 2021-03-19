-- Created by Nguyen Dang Khoa --
CREATE DATABASE IF NOT EXISTS PE2018;
USE PE2018;

-- -----------------------------------------------------
-- Table Academic_year
-- -----------------------------------------------------
DROP TABLE IF EXISTS Academic_year ;

CREATE TABLE IF NOT EXISTS Academic_year (
  AYCode VARCHAR(10) NOT NULL,
  PRIMARY KEY (AYCode))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Semester
-- -----------------------------------------------------
DROP TABLE IF EXISTS Semester ;

CREATE TABLE IF NOT EXISTS Semester (
  Scode VARCHAR(50) NOT NULL,
  AYCode VARCHAR(10) NULL,
  PRIMARY KEY (Scode),
  FOREIGN KEY (AYCode) REFERENCES Academic_year (AYCode))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Faculty
-- -----------------------------------------------------
DROP TABLE IF EXISTS Faculty ;

CREATE TABLE IF NOT EXISTS Faculty (
  Fcode VARCHAR(50) NOT NULL,
  Fname VARCHAR(50) NULL,
  AYCode VARCHAR(10) NULL,
  PRIMARY KEY (Fcode),
  FOREIGN KEY (AYCode) REFERENCES Academic_year (AYCode))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Program
-- -----------------------------------------------------
DROP TABLE IF EXISTS Program ;

CREATE TABLE IF NOT EXISTS Program (
  Pcode VARCHAR(50) NOT NULL,
  Pname VARCHAR(50) NULL,
  Fcode VARCHAR(50) NULL,
  PRIMARY KEY (Pcode),
  FOREIGN KEY (Fcode) REFERENCES Faculty (Fcode)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Module
-- -----------------------------------------------------
DROP TABLE IF EXISTS Module ;

CREATE TABLE IF NOT EXISTS Module (
  Mcode VARCHAR(50) NOT NULL,
  Mname VARCHAR(50) NULL,
  Pcode VARCHAR(50) NULL,
  PRIMARY KEY (Mcode), 
  FOREIGN KEY (Pcode) REFERENCES Program (Pcode)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Class
-- -----------------------------------------------------
DROP TABLE IF EXISTS Class ;

CREATE TABLE IF NOT EXISTS Class (
  Ccode VARCHAR(50) NOT NULL,
  size INT NULL,
  Scode VARCHAR(50) NULL,
  Mcode VARCHAR(50) NULL,
  PRIMARY KEY (Ccode),
  FOREIGN KEY (Scode) REFERENCES Semester (Scode),
  FOREIGN KEY (Mcode) REFERENCES Module (Mcode)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Lecturer
-- -----------------------------------------------------
DROP TABLE IF EXISTS Lecturer ;

CREATE TABLE IF NOT EXISTS Lecturer (
  Lcode VARCHAR(50) NOT NULL,
  Lname VARCHAR(50) NULL,
  PRIMARY KEY (Lcode))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Questionaire
-- -----------------------------------------------------
DROP TABLE IF EXISTS Questionaire ;

CREATE TABLE IF NOT EXISTS Questionaire (
  Ccode VARCHAR(50) NOT NULL,
  Lcode VARCHAR(50) NOT NULL,
  PRIMARY KEY (Ccode, Lcode),
  FOREIGN KEY (Ccode) REFERENCES Class (Ccode),
  FOREIGN KEY (Lcode) REFERENCES Lecturer (Lcode)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Class_has_Lecturer
-- -----------------------------------------------------
DROP TABLE IF EXISTS Class_has_Lecturer ;

CREATE TABLE IF NOT EXISTS Class_has_Lecturer (
  Ccode VARCHAR(50) NOT NULL,
  Lcode VARCHAR(50) NOT NULL,
  PRIMARY KEY (Ccode, Lcode),
  FOREIGN KEY (Ccode) REFERENCES Class (Ccode),
  FOREIGN KEY (Lcode) REFERENCES Lecturer (Lcode)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Module_Program_in_AY
-- -----------------------------------------------------
DROP TABLE IF EXISTS Module_Program_in_AY ;

CREATE TABLE IF NOT EXISTS Module_Program_in_AY (
  AYCode VARCHAR(10) NOT NULL,
  Mcode VARCHAR(50) NOT NULL,
  Pcode VARCHAR(50) NOT NULL,
  PRIMARY KEY (AYCode, Mcode, Pcode),
    FOREIGN KEY (AYCode) REFERENCES Academic_year (AYCode),
    FOREIGN KEY (Mcode) REFERENCES Module (Mcode),
    FOREIGN KEY (Pcode) REFERENCES Program (Pcode)
)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Mock data session
-- -----------------------------------------------------
INSERT INTO Academic_year (Acode) VALUES (
('A441C'),
('A985B'),
('A689R'),
('A112C'),
('A266Y'),
('A721G'),
('A484G'),
('A660A'),
('A399S'),
('A133Y'),
('A332H'),
('A769O'),
('A960H'),
('A678O'),
('A786R'),
('A425C'),
('A705C'),
('A252X'),
('A900D'),
('A627O')
);
INSERT INTO Faculty (Fcode, Fname, Acode) VALUES (
	('F023I', 'Faculty of Engineering', 'A441C'),
    ('F546E', 'Faculty of Economics and Mangement', 'A705C')
);
INSERT INTO Program (Pcode, Pname, Fcode) VALUES (
	('P100C', 'Computer Science', 'F023I'),
	('P210B', 'Business Administration', 'F546E'),
	('P153F', 'Finance and Accounting', 'F546E'),
	('P547E', 'Electrical Engineering', 'F023I'),
	('P736M', 'Mechanical Engineering', 'F023I'),
	('P493A', 'Architecture', 'F023I'),
	('P603C', 'Civil Engineering', 'F023I')
);
INSERT INTO Module (Mcode, Mname, Pcode) VALUES (
	('M015A', 'Introductory Accounting'),
    ('M005F', 'Introductory Finance'),
    ('M235F', 'Investment Finance'),
    ('M834M', 'Business Management'),
    ('M567E', 'Microeconomics'),
    ('M923E', 'Macroeconomics'),
    ('M539A', 'Algebra'),
    ('M256C', 'Calculus'),
    ('M426D', 'Discrete Mathematics'),
    ('M351B', 'Databases'),
    ('M142N', 'Computer Networks'),
    ('M152B', 'Business Administration'),
    ('M154R', 'Realtime systems'),
    ('M945O', 'Operating systems'),
    ('M516I', 'IT Security'),
    ('M516P', 'Programming Exercises')
);
INSERT INTO Module_Program_in_AY (Acode, Mcode, PCode) VALUES (
	('A441C', 'M539A', 'P153F'),
	('A705C', 'M015A', 'P210B'),
	('A705C', 'M015A', 'P153F'),
	('A441C', 'M923E', 'P210B'),
	('A705C', 'M005F', 'P153F'),
	('A705C', 'M015A', 'P210B'), 
	('A441C', 'M834M', 'P210B'),
	('A705C', 'M834M', 'P153F'),
	('A441C', 'M235F', 'P153F'),
	('A705C', 'M256C', 'P153F'),
	('A705C', 'M539A', 'P547E'),
	('A705C', 'M256C', 'P736M'),
	('A705C', 'M426D', 'P547E'),
	('A441C', 'M351B', 'P100C'),
	('A705C', 'M426D', 'P736M'),
	('A705C', 'M015A', 'P547E'),
	('A705C', 'M154R', 'P100C'),
	('A441C', 'M015A', 'P736M'),
	('A441C', 'M256C', 'P547E'),
	('A705C', 'M516I', 'P100C')
);