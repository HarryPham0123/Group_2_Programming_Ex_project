-- Created by Nguyen Dang Khoa, Pham Viet Hoang, Vuong Chi Hieu --
DROP DATABASE IF EXISTS PE2018;
CREATE DATABASE IF NOT EXISTS PE2018;
USE PE2018;

-- -----------------------------------------------------
-- Table Academic_year
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS academic_year (
  AYcode VARCHAR(9),
  Check (AYcode REGEXP '[0-9]{4}-[0-9]{4}'),
  PRIMARY KEY (AYcode))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table Semester
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS semester (
  Scode VARCHAR(10),
  AYcode VARCHAR(9) NOT NULL,
  Check (Scode REGEXP 'S[0-9]{3}[a-z]'),
  PRIMARY KEY (Scode),
  FOREIGN KEY (AYcode) REFERENCES academic_year (AYcode) ON UPDATE CASCADE ON DELETE RESTRICT) 
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Faculty
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS faculty (
  Fcode VARCHAR(10),
  Fname VARCHAR(50) NOT NULL,
  Check (Fcode REGEXP 'F[0-9]{3}[A-Z]'),
  PRIMARY KEY (Fcode))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Program
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS program (
  Pcode VARCHAR(10),
  Pname VARCHAR(50) NOT NULL,
  Check (Pcode REGEXP 'P[0-9]{3}[A-Z]'),
  PRIMARY KEY (Pcode))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Module
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS module (
  Mcode VARCHAR(10),
  Mname VARCHAR(50) NOT NULL,
  Check (Mcode REGEXP 'M[0-9]{3}[A-Z]'),
  PRIMARY KEY (Mcode))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Class
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS class (
  Ccode VARCHAR(10),
  size INT NOT NULL,
  Scode VARCHAR(10) NOT NULL,
  Mcode VARCHAR(10) NOT NULL,
  Check (Ccode REGEXP 'C[0-9]{3}[a-z]'),
  PRIMARY KEY (Ccode),
  FOREIGN KEY (Scode) REFERENCES semester (Scode) ON UPDATE CASCADE ON DELETE RESTRICT,
  FOREIGN KEY (Mcode) REFERENCES module (Mcode) ON UPDATE CASCADE ON DELETE RESTRICT
)
ENGINE = InnoDB;

-- Table for user
Create table if not exists employee_user (
	user_id Varchar(10),
    full_name Varchar(50),
    gender Varchar(50),
    email Varchar(50),
    Primary key (user_id))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table Lecturer
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS lecturer (
  Lcode VARCHAR(10),
  Lname VARCHAR(50) NOT NULL,
  user_id Varchar(10) NOT NULL,
  Check (Lcode REGEXP 'L[0-9]{3}[a-z]'),
  UNIQUE (user_id),
  PRIMARY KEY (Lcode),
  FOREIGN KEY (user_id) REFERENCES employee_user (user_id) ON UPDATE CASCADE ON DELETE RESTRICT)
ENGINE = InnoDB;

-- Table account
Create table if not exists employee_account (
	account_id INT AUTO_INCREMENT,
    login Varchar(50) NOT NULL,
    employee_password Varchar(50) NOT NULL,
    user_id Varchar(10) NOT NULL,
    UNIQUE (login, employee_password),
    Primary key (account_id),
    Foreign key (user_id) references employee_user (user_id) On update CASCADE ON DELETE RESTRICT)
ENGINE = InnoDB;

-- Table program_coordinator
Create table if not exists program_coordinator (
	PCcode INT AUTO_INCREMENT,
    start_date Date,
    end_date Date,
    user_id Varchar(10) NOT NULL,
    Pcode varchar(10) NOT NULL,
    -- constraint for end_date > start_date?
    Primary key (PCcode),
    Foreign key (user_id) references employee_user (user_id) On update CASCADE ON DELETE RESTRICT,
    Foreign key (Pcode) references program (Pcode) On update CASCADE ON DELETE RESTRICT)
ENGINE = InnoDB;

-- Table dean
Create table if not exists dean (
	Dcode INT AUTO_INCREMENT,
    start_date Date,
    end_date Date,
    user_id Varchar(10) NOT NULL,
    Fcode varchar(10) NOT NULL,
	-- constraint for end_date > start_date?
    Primary key (Dcode),
    Foreign key (user_id) references employee_user (user_id) On update CASCADE ON DELETE RESTRICT,
    Foreign key (Fcode) references faculty (Fcode) On update CASCADE ON DELETE RESTRICT)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table Questionnaire
-- -----------------------------------------------------
	       
Create table If not exists questionnaire 
(
    answer_id INT AUTO_INCREMENT,
    Ccode Varchar(10) NOT NULL,
    Lcode Varchar(10) NOT NULL,
    PRIMARY KEY (answer_id),
    Foreign key (Ccode) References class (Ccode) ON UPDATE CASCADE ON DELETE RESTRICT,
    Foreign key (Lcode) References lecturer (Lcode) ON UPDATE CASCADE ON DELETE RESTRICT,
	
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
-- Table ay_faculty
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS ay_fac (
	AYFcode INT AUTO_INCREMENT,
	AYcode VARCHAR(9) NOT NULL,
	Fcode VARCHAR(10) NOT NULL,
    UNIQUE (AYcode, Fcode),
	PRIMARY KEY (AYFcode),
    FOREIGN KEY (AYCode) REFERENCES academic_year (AYCode) ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (Fcode) REFERENCES faculty (Fcode) ON UPDATE CASCADE ON DELETE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table ay_Faculty and program
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS ay_fac_p (
	AYFPcode INT AUTO_INCREMENT,
	AYFcode INT NOT NULL,
	Pcode VARCHAR(10) NOT NULL,
    UNIQUE(AYFcode, Pcode),
	PRIMARY KEY (AYFPcode),	
    FOREIGN KEY (AYFCode) REFERENCES ay_fac (AYFCode) ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (Pcode) REFERENCES program (Pcode) ON UPDATE CASCADE ON DELETE RESTRICT)
ENGINE = InnoDB;

	       
-- -----------------------------------------------------
-- Table ay_faculty_program and module
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS ay_fac_pm (
	AYFPMcode INT AUTO_INCREMENT,
	AYFPcode INT NOT NULL,
	Mcode VARCHAR(10) NOT NULL,
    UNIQUE(AYFPcode, Mcode),
	PRIMARY KEY (AYFPMcode),	
    FOREIGN KEY (AYFPCode) REFERENCES ay_fac_p (AYFPCode) ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (Mcode) REFERENCES module (Mcode) ON UPDATE CASCADE ON DELETE RESTRICT)
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
