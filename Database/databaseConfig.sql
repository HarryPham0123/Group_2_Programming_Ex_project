-- MySQL Script generated by MySQL Workbench
-- Fri Mar 19 11:27:12 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

-- -----------------------------------------------------
-- Schema PE2018
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema PE2018
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `PE2018` DEFAULT CHARACTER SET utf8 ;
USE `PE2018` ;

-- -----------------------------------------------------
-- Table `PE2018`.`Academic Year`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PE2018`.`Academic Year` ;

CREATE TABLE IF NOT EXISTS `PE2018`.`Academic Year` (
  `AYCode` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`AYCode`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PE2018`.`Semester`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PE2018`.`Semester` ;

CREATE TABLE IF NOT EXISTS `PE2018`.`Semester` (
  `Scode` VARCHAR(50) NOT NULL,
  `AYCode` VARCHAR(10) NULL,
  PRIMARY KEY (`Scode`),
  CONSTRAINT `AYCode`
    FOREIGN KEY (`AYCode`)
    REFERENCES `PE2018`.`Academic Year` (`AYCode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PE2018`.`Faculty`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PE2018`.`Faculty` ;

CREATE TABLE IF NOT EXISTS `PE2018`.`Faculty` (
  `Fcode` VARCHAR(50) NOT NULL,
  `Fname` VARCHAR(50) NULL,
  `AYCode` VARCHAR(10) NULL,
  PRIMARY KEY (`Fcode`),
  CONSTRAINT `AYCode`
    FOREIGN KEY (`AYCode`)
    REFERENCES `PE2018`.`Academic Year` (`AYCode`)
    ON DELETE NO ACTION
    ON UPDATE SET NULL)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PE2018`.`Program`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PE2018`.`Program` ;

CREATE TABLE IF NOT EXISTS `PE2018`.`Program` (
  `Pcode` VARCHAR(50) NOT NULL,
  `Pname` VARCHAR(50) NULL,
  `Fcode` VARCHAR(50) NULL,
  PRIMARY KEY (`Pcode`),
  CONSTRAINT `Fcode`
    FOREIGN KEY (`Fcode`)
    REFERENCES `PE2018`.`Faculty` (`Fcode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PE2018`.`Module`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PE2018`.`Module` ;

CREATE TABLE IF NOT EXISTS `PE2018`.`Module` (
  `Mcode` VARCHAR(50) NOT NULL,
  `Mname` VARCHAR(50) NULL,
  `Pcode` VARCHAR(50) NULL,
  PRIMARY KEY (`Mcode`),
  CONSTRAINT `Pcode`
    FOREIGN KEY (`Pcode`)
    REFERENCES `PE2018`.`Program` (`Pcode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PE2018`.`Class`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PE2018`.`Class` ;

CREATE TABLE IF NOT EXISTS `PE2018`.`Class` (
  `Ccode` VARCHAR(50) NOT NULL,
  `size` INT NULL,
  `Scode` VARCHAR(50) NULL,
  `Mcode` VARCHAR(50) NULL,
  PRIMARY KEY (`Ccode`),
  CONSTRAINT `Scode`
    FOREIGN KEY (`Scode`)
    REFERENCES `PE2018`.`Semester` (`Scode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Mcode`
    FOREIGN KEY (`Mcode`)
    REFERENCES `PE2018`.`Module` (`Mcode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PE2018`.`Lecturer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PE2018`.`Lecturer` ;

CREATE TABLE IF NOT EXISTS `PE2018`.`Lecturer` (
  `Lcode` VARCHAR(50) NOT NULL,
  `Lname` VARCHAR(50) NULL,
  PRIMARY KEY (`Lcode`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PE2018`.`Questionaire`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PE2018`.`Questionaire` ;

CREATE TABLE IF NOT EXISTS `PE2018`.`Questionaire` (
  `Ccode` VARCHAR(50) NOT NULL,
  `Lcode` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`Ccode`, `Lcode`),
  INDEX `Lcode_idx` (`Lcode` ASC) VISIBLE,
  CONSTRAINT `Ccode`
    FOREIGN KEY (`Ccode`)
    REFERENCES `PE2018`.`Class` (`Ccode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Lcode`
    FOREIGN KEY (`Lcode`)
    REFERENCES `PE2018`.`Lecturer` (`Lcode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PE2018`.`Class_has_Lecturer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PE2018`.`Class_has_Lecturer` ;

CREATE TABLE IF NOT EXISTS `PE2018`.`Class_has_Lecturer` (
  `Ccode` VARCHAR(50) NOT NULL,
  `Lcode` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`Ccode`, `Lcode`),
  CONSTRAINT `Ccode`
    FOREIGN KEY (`Ccode`)
    REFERENCES `PE2018`.`Class` (`Ccode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Lcode`
    FOREIGN KEY (`Lcode`)
    REFERENCES `PE2018`.`Lecturer` (`Lcode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PE2018`.`Module_Program_in_AY`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PE2018`.`Module_Program_in_AY` ;

CREATE TABLE IF NOT EXISTS `PE2018`.`Module_Program_in_AY` (
  `AYCode` VARCHAR(10) NOT NULL,
  `Mcode` VARCHAR(50) NOT NULL,
  `Pcode` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`AYCode`, `Mcode`, `Pcode`),
  CONSTRAINT `AYCode`
    FOREIGN KEY (`AYCode`)
    REFERENCES `PE2018`.`Academic Year` (`AYCode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Mcode`
    FOREIGN KEY (`Mcode`)
    REFERENCES `PE2018`.`Module` (`Mcode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Pcode`
    FOREIGN KEY (`Pcode`)
    REFERENCES `PE2018`.`Program` (`Pcode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


