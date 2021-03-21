-- Created by Nguyen Dang Khoa --
CREATE DATABASE IF NOT EXISTS PE2018;
USE PE2018;

-- -----------------------------------------------------
-- Table Academic_year
-- -----------------------------------------------------
DROP TABLE IF EXISTS Academic_year ;

CREATE TABLE IF NOT EXISTS Academic_year (
  AYcode VARCHAR(10) NOT NULL,
  PRIMARY KEY (AYcode))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Semester
-- -----------------------------------------------------
DROP TABLE IF EXISTS Semester ;

CREATE TABLE IF NOT EXISTS Semester (
  Scode VARCHAR(10) NOT NULL,
  AYcode VARCHAR(10) NULL,
  PRIMARY KEY (Scode),
  FOREIGN KEY (AYcode) REFERENCES Academic_year (AYcode))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Faculty
-- -----------------------------------------------------
DROP TABLE IF EXISTS Faculty ;

CREATE TABLE IF NOT EXISTS Faculty (
  Fcode VARCHAR(10) NOT NULL,
  Fname VARCHAR(50) NULL,
  AYcode VARCHAR(10) NULL,
  PRIMARY KEY (Fcode),
  FOREIGN KEY (AYcode) REFERENCES Academic_year (AYcode))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Program
-- -----------------------------------------------------
DROP TABLE IF EXISTS Program ;

CREATE TABLE IF NOT EXISTS Program (
  Pcode VARCHAR(10) NOT NULL,
  Pname VARCHAR(50) NULL,
  Fcode VARCHAR(10) NULL,
  PRIMARY KEY (Pcode),
  FOREIGN KEY (Fcode) REFERENCES Faculty (Fcode)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Module
-- -----------------------------------------------------
DROP TABLE IF EXISTS Module ;

CREATE TABLE IF NOT EXISTS Module (
  Mcode VARCHAR(10) NOT NULL,
  Mname VARCHAR(50) NULL,
  PRIMARY KEY (Mcode)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Class
-- -----------------------------------------------------
DROP TABLE IF EXISTS Class ;

CREATE TABLE IF NOT EXISTS Class (
  Ccode VARCHAR(10) NOT NULL,
  size INT NULL,
  Scode VARCHAR(10) NULL,
  Mcode VARCHAR(10) NULL,
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
  Lcode VARCHAR(10) NOT NULL,
  Lname VARCHAR(50) NULL,
  PRIMARY KEY (Lcode))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table questionnaire
-- -----------------------------------------------------
DROP TABLE IF EXISTS questionnaire ;

CREATE TABLE IF NOT EXISTS questionnaire (
  Ccode VARCHAR(10) NOT NULL,
  Lcode VARCHAR(10) NOT NULL,
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
  Ccode VARCHAR(10) NOT NULL,
  Lcode VARCHAR(10) NOT NULL,
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
  AYcode VARCHAR(10) NOT NULL,
  Mcode VARCHAR(10) NOT NULL,
  Pcode VARCHAR(10) NOT NULL,
  PRIMARY KEY (AYcode, Mcode, Pcode),
    FOREIGN KEY (AYcode) REFERENCES Academic_year (AYcode),
    FOREIGN KEY (Mcode) REFERENCES Module (Mcode),
    FOREIGN KEY (Pcode) REFERENCES Program (Pcode)
)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table Faculty_in_AY
-- -----------------------------------------------------
DROP TABLE IF EXISTS Faculty_in_AY;

CREATE TABLE IF NOT EXISTS Faculty_in_AY (
  AYcode VARCHAR(10) NOT NULL,
  Fcode VARCHAR(10) NOT NULL,
  PRIMARY KEY (AYcode, Fcode),	
    FOREIGN KEY (AYCode) REFERENCES Academic_year (AYCode),
    FOREIGN KEY (Fcode) REFERENCES Faculty (Fcode)
)
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Mock data session
-- -----------------------------------------------------
INSERT INTO Academic_year (AYcode) VALUES
('A2016C'),
('A2017B'),
('A2018R'),
('A2019C'),
('A2020Y')
;
	       
INSERT INTO Faculty (Fcode, Fname) VALUES 
	('F001I', 'Faculty of Engineering'),
	('F002E', 'Faculty of Economics and Mangement')
;
	       
INSERT INTO Program (Pcode, Pname, Fcode) VALUES
	('P001C', 'Computer Science', 'F001I'),
	('P002B', 'Business Administration', 'F002E'),
	('P003F', 'Finance and Accounting', 'F002E'),
	('P004E', 'Electrical Engineering', 'F001I'),
	('P005M', 'Mechanical Engineering', 'F001I'),
	('P006A', 'Architecture', 'F001I'),
	('P007C', 'Civil Engineering', 'F001I')
;
	       
INSERT INTO Module (Mcode, Mname) VALUES 
    	('M001A', 'Introductory Accounting'),
    	('M002F', 'Introductory Finance'),
    	('M003F', 'Investment Finance'),
    	('M004M', 'Business Management'),
    	('M005E', 'Microeconomics'),
    	('M006E', 'Macroeconomics'),
    	('M007A', 'Algebra'),
    	('M008C', 'Calculus'),
    	('M009D', 'Discrete Mathematics'),
    	('M010B', 'Databases'),
    	('M011N', 'Computer Networks'),
    	('M012B', 'Business Administration'),
    	('M013R', 'Realtime systems'),
    	('M014O', 'Operating systems'),
    	('M015I', 'IT Security'),
    	('M016P', 'Programming Exercises')
;

-- In each academic year, a module is offered by at most one program.
-- (A module may not be offered in a program in some academic years)  
-- -> Modules in different programs must be different in each academic year (*) (Can not be expressed)
-- Modules in 1 program must be unique -> Yes (b.c (AYcode, Mcode, Pcode) is primary key)
INSERT INTO Module_Program_in_AY (AYcode, Mcode, PCode) VALUES
	('A2020Y', 'M007A', 'P001C'),
	('A2020Y', 'M008C', 'P001C'),

	('A2020Y', 'M011N', 'P005M'), 
	('A2020Y', 'M012B', 'P005M'),
	('A2020Y', 'M013R', 'P005M'),

	('A2020Y', 'M014O', 'P003F'),
	('A2020Y', 'M015I', 'P003F'),
	('A2020Y', 'M016P', 'P003F'),

	('A2020Y', 'M001A', 'P002B'),
	('A2020Y', 'M002F', 'P002B'),

	('A2020Y', 'M003F', 'P007C'),
	('A2020Y', 'M004M', 'P007C'),

	('A2020Y', 'M005E', 'P004E'),
	('A2020Y', 'M006E', 'P004E'),

	('A2020Y', 'M010B', 'P006A'),
	('A2020Y', 'M009D', 'P006A')
;

-- In year 2020, there are 2 faculties	       
INSERT INTO Faculty_in_AY (AYcode, Fcode) VALUES
	('A2020Y', 'F001I'),
	('A2020Y', 'F002E')
;

-- There are only 4 semesters in each aca year	 
-- A semester belongs to exactly one academic year.    
INSERT INTO Semester (Scode, AYcode) VALUES 
	('S001o', 'A2020Y'),
	('S002q', 'A2020Y'),
	('S003g', 'A2020Y'),
	('S004x', 'A2020Y')
;

-- Students in each semester only learn 4 modules  	
-- A class belongs to exactly one module
-- -> A class can NOT belong to 2 or more modules
-- A class is offered in exactly one semester 
-- -> A class can NOT be offered in 2 or more diff semesters
INSERT INTO Class (Ccode, size, Scode, Mcode) VALUES 
	('C001t', '35', 'S001o', 'M001A'),
	('C002h', '40', 'S001o', 'M002F'),
	('C003a', '32', 'S001o', 'M003F'),
	('C004m', '37', 'S001o', 'M004M'),

	('C005u', '33', 'S002q', 'M005E'),
	('C006y', '33', 'S002q', 'M006E'),
	('C007q', '35', 'S002q', 'M007A'),
	('C008n', '37', 'S002q', 'M008C'),

	('C009u', '35', 'S003g', 'M009D'),
	('C010i', '32', 'S003g', 'M010B'),
	('C011z', '38', 'S003g', 'M011N'),
	('C012c', '39', 'S003g', 'M012B'),

	('C013x', '33', 'S004x', 'M013R'),
	('C014d', '30', 'S004x', 'M014O'),
	('C015q', '31', 'S004x', 'M015I'),
	('C016q', '37', 'S004x', 'M016P')
;
	       
INSERT INTO Lecturer (Lcode, Lname) VALUES 
	('L001v', 'Lona Perrigan'),
	('L002o', 'Marian Sirois'),
	('L003r', 'Izora Dickerson'),
	('L004v', 'Sophia Frankel'),
	('L005a', 'Octavia Mariska'),
	('L006e', 'Rella Gruis'),
	('L007u', 'Nona Tineo'),
	('L008h', 'Anna Adkison'),
	('L009f', 'Gwendolyn Edwards'),
	('L010m', 'Lou Hinds'),
	('L011g', 'Floy Blanco'),
	('L012m', 'Tena Mee'),
	('L013l', 'Mabelle Fusco'),
	('L014a', 'Dicie Mcbride'),
	('L015m', 'Theresa Buhmann')
;

-- A questionnaire is filled for exactly one class and exactly one lecturer.
-- -> Questionaire is relationship between class and lecturer

-- A class has any number of lecturers.
-- This case, we asume a class can have 1, 2 or 3 lecturers
-- -> Lecturers in each class must be unique     
-- A lecturer can teach any number of classes? -> Yes
INSERT INTO Questionnaire (Ccode, Lcode) VALUES 
	('C001t', 'L015m'),
	('C001t', 'L002o'),

	('C002h', 'L001v'),
	('C002h', 'L003r'),
	('C002h', 'L004v'),

	('C003a', 'L002o'),
	('C004m', 'L003r'),

	('C005u', 'L001v'),
	('C005u', 'L004v'),
	('C005u', 'L005a'),

	('C006y', 'L004v'),
	('C007q', 'L001v'),
	('C008n', 'L005a'),
	('C009u', 'L006e'),

	('C010i', 'L006e'),
	('C010i', 'L001v'),

	('C011z', 'L007u'),

	('C012c', 'L008h'),
	('C012c', 'L009f'),

	('C013x', 'L007u'),
	('C014d', 'L010m'),
	('C015q', 'L011g'),
	('C015q', 'L012m'),

	('C016q', 'L013l'),
	('C016q', 'L014a')
;
