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
  FOREIGN KEY (AYcode) REFERENCES Academic_year (AYcode))
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
  FOREIGN KEY (Scode) REFERENCES Semester (Scode),
  FOREIGN KEY (Mcode) REFERENCES Module (Mcode)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Lecturer
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Lecturer (
  Lcode VARCHAR(10) NOT NULL,
  Lname VARCHAR(50) NULL,
  PRIMARY KEY (Lcode))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table questionnaire
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS questionnaire (
  Acode VARCHAR(10) NOT NULL,
  Ccode VARCHAR(10) NOT NULL,
  Lcode VARCHAR(10) NOT NULL,
  Answers json,
  PRIMARY KEY (Acode),
	FOREIGN KEY (Ccode) REFERENCES Class (Ccode),
	FOREIGN KEY (Lcode) REFERENCES Lecturer (Lcode)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Program_Module relationship
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Program_Module (
  PMcode VARCHAR(10) NOT NULL,
  Pcode VARCHAR(10) NOT NULL,
  Mcode VARCHAR(10) NOT NULL,
  PRIMARY KEY (PMcode),
	FOREIGN KEY (Mcode) REFERENCES Module (Mcode),
	FOREIGN KEY (Pcode) REFERENCES Program (Pcode)
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
    FOREIGN KEY (AYCode) REFERENCES Academic_year (AYCode),
    FOREIGN KEY (Fcode) REFERENCES Faculty (Fcode),
    FOREIGN KEY (PMcode) REFERENCES Program_Module (PMcode)
)
ENGINE = InnoDB;
	      
	       
-- -----------------------------------------------------
-- Table lecturer_in_class
-- -----------------------------------------------------
	       
CREATE TABLE IF NOT EXISTS lecturer_in_class (
  Ccode VARCHAR(10),
  Lcode VARCHAR(10),
  PRIMARY KEY (Ccode, Lcode),
  FOREIGN KEY (Ccode) REFERENCES class (Ccode),
  FOREIGN KEY (Lcode) REFERENCES lecturer (Lcode)
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
	       
	       
INSERT INTO Program (Pcode, Pname) VALUES
	('P001C', 'Computer Science'),
	('P002B', 'Business Administration'),
	('P003F', 'Finance and Accounting'),
	('P004E', 'Electrical Engineering'),
	('P005M', 'Mechanical Engineering'),
	('P006A', 'Architecture'),
	('P007C', 'Civil Engineering')
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
INSERT INTO Program_Module (PMcode, Pcode, Mcode) VALUES
		('P001M', 'P001C', 'M010B'),
    	('P002M', 'P001C', 'M011N'),
    	('P003M', 'P001C', 'M012B'),
    	('P004M', 'P001C', 'M013R'),
    	('P005M', 'P001C', 'M014O'),
    	('P006M', 'P001C', 'M015I'),
    	('P007M', 'P001C', 'M016P'),
        
        ('P008M', 'P002B', 'M001A'),
        ('P009M', 'P002B', 'M004M'),
        ('P010M', 'P002B', 'M005E'),
        
    	('P011M', 'P003F', 'M002F'),
    	('P012M', 'P003F', 'M003F'),
    	('P013M', 'P003F', 'M006E'),
        
    	('P014M', 'P004E', 'M007A'),
    	('P015M', 'P004E', 'M008C'),
    	('P016M', 'P004E', 'M009D')
;     
	       

	       
INSERT INTO AY_Faculty_PM (FAYcode, AYcode, Fcode, PMcode) VALUES
    ('A014M', 'A2019C', 'F001I', 'P001M'),
    ('A015M', 'A2019C', 'F001I', 'P002M'),
	('A016M', 'A2019C', 'F002E', 'P008M'),

	('A001M', 'A2020Y', 'F001I', 'P001M'),
    ('A002M', 'A2020Y', 'F001I', 'P002M'),
    ('A003M', 'A2020Y', 'F001I', 'P003M'),
    ('A004M', 'A2020Y', 'F001I', 'P004M'),
    ('A005M', 'A2020Y', 'F001I', 'P014M'),
    ('A006M', 'A2020Y', 'F001I', 'P015M'),
    ('A007M', 'A2020Y', 'F001I', 'P016M'),
	('A008M', 'A2020Y', 'F002E', 'P008M'),
    ('A009M', 'A2020Y', 'F002E', 'P009M'),
    ('A010M', 'A2020Y', 'F002E', 'P010M'),
    ('A011M', 'A2020Y', 'F002E', 'P011M'),
    ('A012M', 'A2020Y', 'F002E', 'P012M'),
    ('A013M', 'A2020Y', 'F002E', 'P013M')
;
	       

-- There are only 4 semesters in each aca year	 
-- A semester belongs to exactly one academic year.    
INSERT INTO Semester (Scode, AYcode) VALUES 
	('S001o', 'A2020Y'),
	('S002q', 'A2020Y'),
	('S003g', 'A2020Y'),
	('S004x', 'A2020Y'),
    
	('S005z', 'A2019C')
;

	       
-- Students in each semester only learn 4 modules  	
-- A class belongs to exactly one module (Data constrain)
-- -> A class can NOT associate to 2 or more modules
-- A class is offered in exactly one semester (Data constrain)
-- -> A class can NOT associate to 2 or more diff semesters
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
	('C016q', '37', 'S004x', 'M016P'),
    
	('C017t', '30', 'S005z', 'M010B'),
    ('C018t', '31', 'S005z', 'M001A')
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

	       
-- A questionnaire is filled for exactly one class and exactly one lecturer. (Constrain)
-- -> Questionaire is relationship between class and lecturer    
 Insert into Questionnaire (Acode, Ccode, Lcode, Answers)
 Values 
 (
 	'A0001n', 'C002h', 'L001v',
     '{ "attendance" : "often", "gender" : "male",
     "question_1" : 3, "question_2" : 2, "question_3" : 2, 
     "question_4" : 4, "question_5" : 5, "question_6" : 3, 
     "question_7" : 4, "question_8" : 2, "question_9" : 5, 
     "question_10" : 3, "question_11" : 1, "question_12" : 4, 
     "question_13" : 5, "question_14" : 2, "question_15" : 4, 
     "question_16" : 4, "question_17" : 5, 
     "question_18" : "" }'
 );

 Insert into Questionnaire (Acode, Ccode, Lcode, Answers)
 Values 
 (
 	'A0002n', 'C005u', 'L001v',
     '{ "attendance" : "rarely", "gender" : "female",
     "question_1" : 5, "question_2" : 2, "question_3" : 2, 
     "question_4" : 4, "question_5" : 3, "question_6" : 1, 
     "question_7" : 4, "question_8" : 2, "question_9" : 5, 
     "question_10" : 1, "question_11" : 3, "question_12" : 4, 
     "question_13" : 5, "question_14" : 5, "question_15" : 2, 
     "question_16" : 4, "question_17" : 5, 
     "question_18" : "" }'
 );

 Insert into Questionnaire (Acode, Ccode, Lcode, Answers)
 Values 
 (
 	'A0003n', 'C003a', 'L002o',
     '{ "attendance" : "sometimes", "gender" : "female",
     "question_1" : 1, "question_2" : 2, "question_3" : 3, 
     "question_4" : 5, "question_5" : 3, "question_6" : 4, 
     "question_7" : 4, "question_8" : 4, "question_9" : 3, 
     "question_10" : 1, "question_11" : 1, "question_12" : 5, 
     "question_13" : 5, "question_14" : 2, "question_15" : 4, 
     "question_16" : 4, "question_17" : 1, 
     "question_18" : "" }'
 );
-- A class has any number of lecturers. (Data constrain)
-- This case, we asume a class can have 1, 2 or 3 lecturers
-- -> Lecturers in each class must be unique     
-- A lecturer can teach any number of classes? -> Yes
INSERT INTO lecturer_in_class (Ccode, Lcode) VALUES 
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
	('C016q', 'L014a'),
    
    ('C017t', 'L014a'),
    ('C017t', 'L015m'),
    
    ('C018t', 'L009f')
;
