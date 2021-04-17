-- Created by Nguyen Dang Khoa, Pham Viet Hoang, Vuong Chi Hieu --
DROP DATABASE IF EXISTS PE2018;
CREATE DATABASE IF NOT EXISTS PE2018;
USE PE2018;

-- -----------------------------------------------------
-- Table Academic_year
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Academic_year (
  AYcode VARCHAR(10) NOT NULL,
  PRIMARY KEY (AYcode))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table Semester
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Semester (
  Scode VARCHAR(10) NOT NULL,
  AYcode VARCHAR(10) NULL,
  PRIMARY KEY (Scode),
  FOREIGN KEY (AYcode) REFERENCES Academic_year (AYcode) ON UPDATE CASCADE ON DELETE RESTRICT) 
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Faculty
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Faculty (
  Fcode VARCHAR(10) NOT NULL,
  Fname VARCHAR(50) NULL,
  PRIMARY KEY (Fcode))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Program
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Program (
  Pcode VARCHAR(10) NOT NULL,
  Pname VARCHAR(50) NOT NULL,
  PRIMARY KEY (Pcode)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Module
-- -----------------------------------------------------


CREATE TABLE IF NOT EXISTS Module (
  Mcode VARCHAR(10) NOT NULL,
  Mname VARCHAR(50) NOT NULL, 
  PRIMARY KEY (Mcode)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Class
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Class (
  Ccode VARCHAR(10) NOT NULL,
  size INT NULL,
  Scode VARCHAR(10) NULL,
  Mcode VARCHAR(10) NULL,
  PRIMARY KEY (Ccode),
  FOREIGN KEY (Scode) REFERENCES Semester (Scode) ON UPDATE CASCADE ON DELETE RESTRICT,
  FOREIGN KEY (Mcode) REFERENCES Module (Mcode) ON UPDATE CASCADE ON DELETE RESTRICT
)
ENGINE = InnoDB;

-- Table for user
Create table if not exists user (
	user_id Varchar(10) NOT NULL,
    full_name Varchar(50),
    gender Varchar(50),
    email Varchar(50),
    Primary key (user_id))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table Lecturer
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Lecturer (
  Lcode VARCHAR(10) NOT NULL,
  Lname VARCHAR(50) NULL,
  user_id Varchar(10) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user (user_id) ON UPDATE CASCADE ON DELETE RESTRICT,
  PRIMARY KEY (Lcode))
ENGINE = InnoDB;

-- Table account
Create table if not exists account (
	account_id INT NOT NULL AUTO_INCREMENT,
    login Varchar(50) NOT NULL,
    password Varchar(50) NOT NULL,
    user_id Varchar(10) NOT NULL,
    UNIQUE (login, password),
    Primary key (account_id),
    Foreign key (user_id) references user (user_id) On update CASCADE ON DELETE RESTRICT
)
ENGINE = InnoDB;

-- Table program_coordinator
Create table if not exists program_coordinator (
	PCcode INT NOT NULL AUTO_INCREMENT,
    start_date Date,
    end_date Date,
    user_id Varchar(10),
    Pcode varchar(10),
    Primary key (PCcode),
    Foreign key (user_id) references user (user_id) On update CASCADE ON DELETE RESTRICT,
    Foreign key (Pcode) references program (Pcode) On update CASCADE ON DELETE RESTRICT
)
ENGINE = InnoDB;

-- Table dean
Create table if not exists dean (
	Dcode INT NOT NULL AUTO_INCREMENT,
    start_date Date,
    end_date Date,
    user_id Varchar(10),
    Fcode varchar(10),
    Primary key (Dcode),
    Foreign key (user_id) references user (user_id) On update CASCADE ON DELETE RESTRICT,
    Foreign key (Fcode) references faculty (Fcode) On update CASCADE ON DELETE RESTRICT
)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table Questionnaire
-- -----------------------------------------------------
	       
Create table If not exists questionnaire 
(
    answer_id INT NOT NULL AUTO_INCREMENT,
    Ccode Varchar(10),
    Lcode Varchar(10),
    PRIMARY KEY (answer_id),
    Foreign key (Ccode) References Class (Ccode) ON UPDATE CASCADE ON DELETE RESTRICT,
    Foreign key (Lcode) References Lecturer (Lcode) ON UPDATE CASCADE ON DELETE RESTRICT,
	
    gender Varchar(10), CHECK (gender IN ('male', 'female', 'other')),
    attendance Varchar(10), CHECK (attendance IN ('never', 'rarely', 'sometimes', 'often', 'always')),
    question_1 Varchar(10), CHECK (question_1 IN ('1', '2', '3', '4', '5', 'not')),
    question_2 Varchar(10), CHECK (question_2 IN ('1', '2', '3', '4', '5', 'not')),
    question_3 Varchar(10), CHECK (question_3 IN ('1', '2', '3', '4', '5', 'not')),
    question_4 Varchar(10), CHECK (question_4 IN ('1', '2', '3', '4', '5', 'not')),
    question_5 Varchar(10), CHECK (question_5 IN ('1', '2', '3', '4', '5', 'not')),
    question_6 Varchar(10), CHECK (question_6 IN ('1', '2', '3', '4', '5', 'not')),
    question_7 Varchar(10), CHECK (question_7 IN ('1', '2', '3', '4', '5', 'not')),
    question_8 Varchar(10), CHECK (question_8 IN ('1', '2', '3', '4', '5', 'not')),
    question_9 Varchar(10), CHECK (question_9 IN ('1', '2', '3', '4', '5', 'not')),
    question_10 Varchar(10), CHECK (question_10 IN ('1', '2', '3', '4', '5', 'not')),
    question_11 Varchar(10), CHECK (question_11 IN ('1', '2', '3', '4', '5', 'not')),
    question_12 Varchar(10), CHECK (question_12 IN ('1', '2', '3', '4', '5', 'not')),
    question_13 Varchar(10), CHECK (question_13 IN ('1', '2', '3', '4', '5', 'not')),
    question_14 Varchar(10), CHECK (question_14 IN ('1', '2', '3', '4', '5', 'not')),
    question_15 Varchar(10), CHECK (question_15 IN ('1', '2', '3', '4', '5', 'not')),
    question_16 Varchar(10), CHECK (question_16 IN ('1', '2', '3', '4', '5', 'not')),
    question_17 Varchar(10), CHECK (question_17 IN ('1', '2', '3', '4', '5', 'not')),
    question_18 Varchar(100) DEFAULT ('')
)
ENGINE = InnoDB;
				    

-- -----------------------------------------------------
-- Table question_support_gender
-- -----------------------------------------------------
				    
Create table if not exists question_support_gender(
     gender Varchar(10),
	PRIMARY KEY (gender)
);
				    

-- -----------------------------------------------------
-- Table question_support_attendance
-- -----------------------------------------------------
				    
Create table if not exists question_support_attendance(
     attendance Varchar(10),
	PRIMARY KEY (attendance)
);
				    
-- -----------------------------------------------------
-- Table question_support_number
-- -----------------------------------------------------
				    
Create table if not exists question_support_number(
     answer_key Varchar(10),
	PRIMARY KEY (answer_key)
);


-- -----------------------------------------------------
-- Table Program_Module relationship
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Program_Module (
  PMcode VARCHAR(10) NOT NULL,
  Pcode VARCHAR(10) NOT NULL,
  Mcode VARCHAR(10) NOT NULL,
  PRIMARY KEY (PMcode),
	FOREIGN KEY (Mcode) REFERENCES Module (Mcode) ON UPDATE CASCADE ON DELETE RESTRICT,
	FOREIGN KEY (Pcode) REFERENCES Program (Pcode) ON UPDATE CASCADE ON DELETE RESTRICT
)
ENGINE = InnoDB;

	       
-- -----------------------------------------------------
-- Table AY_Faculty and program_module
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS AY_Faculty_PM (
  FAYcode VARCHAR(10) NOT NULL,
  AYcode VARCHAR(10) NOT NULL,
  Fcode VARCHAR(10) NOT NULL,
  PMcode VARCHAR(10) NOT NULL,
  PRIMARY KEY (FAYcode),	
    FOREIGN KEY (AYCode) REFERENCES Academic_year (AYCode) ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (Fcode) REFERENCES Faculty (Fcode) ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (PMcode) REFERENCES Program_Module (PMcode) ON UPDATE CASCADE ON DELETE RESTRICT
)
ENGINE = InnoDB;
	      
	       
-- -----------------------------------------------------
-- Table lecturer_in_class
-- -----------------------------------------------------
	       
CREATE TABLE IF NOT EXISTS lecturer_in_class (
  Ccode VARCHAR(10),
  Lcode VARCHAR(10),
  PRIMARY KEY (Ccode, Lcode),
  FOREIGN KEY (Ccode) REFERENCES class (Ccode) ON UPDATE CASCADE ON DELETE RESTRICT,
  FOREIGN KEY (Lcode) REFERENCES lecturer (Lcode) ON UPDATE CASCADE ON DELETE RESTRICT
)
ENGINE = InnoDB;
