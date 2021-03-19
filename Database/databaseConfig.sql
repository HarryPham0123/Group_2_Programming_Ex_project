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


