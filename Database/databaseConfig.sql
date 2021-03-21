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
  FOREIGN KEY (AYCode) REFERENCES Academic_year (AYCode))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Faculty
-- -----------------------------------------------------
DROP TABLE IF EXISTS Faculty ;

CREATE TABLE IF NOT EXISTS Faculty (
  Fcode VARCHAR(10) NOT NULL,
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
  PRIMARY KEY (AYCode, Mcode, Pcode),
    FOREIGN KEY (AYCode) REFERENCES Academic_year (AYCode),
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
INSERT INTO Faculty (Fcode, Fname) VALUES (
	('F023I', 'Faculty of Engineering'),
    ('F546E', 'Faculty of Economics and Mangement')
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
INSERT INTO Module (Mcode, Mname) VALUES (
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
INSERT INTO Faculty_in_AY (AYCode, Fcode) VALUES (
	('A441C', 'F023I'),
    ('A441C', 'F546E'),
    ('A660A', 'F546E'),
    ('A660A', 'F023I'),
	('A705C', 'F023I'),
	('A705C', 'F546E'),
    ('A266Y', 'F546E'),
    ('A332H', 'F023I')
);
INSERT INTO Semester (Scode, AYCode) VALUES (
	('S736o', 'A441C'),
	('S507q', 'A441C'),
	('S497g', 'A660A'),
	('S435x', 'A660A'),
	('S206p', 'A705C'),
	('S413z', 'A705C'),
	('S037x', 'A266Y'),
	('S241x', 'A266Y'),
	('S688c', 'A332H'),
	('S594y', 'A332H')
);
INSERT INTO Class (Ccode, size, Scode, Mcode) VALUES (
	('C161t', '35', 'S736o', 'M539A'),
	('C889h', '40', 'S413z', 'M426D'),
	('C406a', '32', 'S507q', 'M426D'),
	('C507u', '33', 'S206p', 'M015A'),
	('C546y', '33', 'S206p', 'M539A'),
	('C905q', '35', 'S736o', 'M516I'),
	('C722u', '35', 'S413z', 'M154R'),
	('C188i', '32', 'S206p', 'M834M'),
	('C990z', '38', 'S736o', 'M005F'),
	('C363x', '33', 'S206p', 'M256C'),
	('C383d', '30', 'S413z', 'M256C'),
	('C296q', '31', 'S507q', 'M426D'),
	('C130m', '37', 'S413z', 'M834M'),
	('C556n', '37', 'S206p', 'M923E'),
	('C256c', '39', 'S206p', 'M426D'),
	('C197q', '37', 'S206p', 'M923E'),
	('C387d', '38', 'S413z', 'M426D'),
	('C817z', '34', 'S507q', 'M426D'),
	('C973f', '40', 'S206p', 'M923E'),
	('C400a', '34', 'S507q', 'M834M'),
	('C443o', '38', 'S413z', 'M351B'),
	('C311p', '36', 'S736o', 'M351B'),
	('C958f', '40', 'S206p', 'M834M'),
	('C791m', '40', 'S206p', 'M351B'),
	('C732m', '37', 'S413z', 'M834M')
);
INSERT INTO Lecturer (Lcode, Lname) VALUES (
	('L668v', 'Lona Perrigan'),
	('L322o', 'Marian Sirois'),
	('L030r', 'Izora Dickerson'),
	('L500v', 'Sophia Frankel'),
	('L893a', 'Octavia Mariska'),
	('L824e', 'Rella Gruis'),
	('L515u', 'Nona Tineo'),
	('L960h', 'Anna Adkison'),
	('L436f', 'Gwendolyn Edwards'),
	('L192m', 'Lou Hinds'),
	('L876g', 'Floy Blanco'),
	('L664m', 'Tena Mee'),
	('L155l', 'Mabelle Fusco'),
	('L768a', 'Dicie Mcbride'),
	('L405m', 'Theresa Buhmann')
);
INSERT INTO Class_has_Lecturer (Ccode, Lcode) VALUES (
	('C161t', 'L668v'),
	('C889h', 'L030r'),
	('C406a', 'L030r'),
	('C507u', 'L322o'),
	('C546y', 'L824e'),
	('C905q', 'L960h'),
	('C722u', 'L436f'),
	('C188i', 'L322o'),
	('C990z', 'L876g'),
	('C363x', 'L960h'),
	('C383d', 'L515u'),
	('C296q', 'L192m'),
	('C130m', 'L155l'),
	('C556n', 'L668v'),
	('C256c', 'L192m'),
	('C197q', 'L664m'),
	('C387d', 'L893a'),
	('C817z', 'L876g'),
	('C973f', 'L515u'),
	('C400a', 'L876g'),
	('C443o', 'L030r'),
	('C311p', 'L155l'),
    ('C311p', 'L436f'),
	('C958f', 'L893a'),
	('C958f', 'L192m')
);
INSERT INTO Questionnaire (Ccode, Lcode) VALUES (
	('C363x', 'L960h'),
	('C383d', 'L515u'),
	('C296q', 'L192m'),
	('C130m', 'L155l'),
	('C556n', 'L668v'),
	('C256c', 'L192m'),
	('C197q', 'L664m'),
	('C387d', 'L893a'),
	('C817z', 'L876g'),
	('C973f', 'L515u'),
	('C400a', 'L876g'),
	('C443o', 'L030r'),
	('C311p', 'L155l'),
    ('C311p', 'L436f'),
	('C958f', 'L893a')
);
