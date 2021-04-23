USE pe2018;
DROP PROCEDURE IF EXISTS AYcode_Fcode_Pcode_Mcode;

DELIMITER //
CREATE PROCEDURE `AYcode_Fcode_Pcode_Mcode`(
	input_academic_year Varchar(9),
    input_faculty Varchar(10),
    input_program Varchar(10),
    input_module Varchar(10),
    status_case Varchar(50))
sp: BEGIN

-- Check DATA EXIST when DELETE case
	CASE
		-- Check invalid parameter (Para NOT null but not in database)
		WHEN (input_academic_year not in (Select AYcode from academic_year)) AND (input_academic_year is not NULL) THEN
			SELECT 'invalid academic year' as 'message';
			LEAVE sp;

		WHEN (input_faculty not in (Select Fcode from faculty)) AND (input_faculty is not NULL) THEN
			SELECT 'invalid faculty' as 'message';
			LEAVE sp;

		WHEN (input_program not in (Select Pcode from program)) AND (input_program is not NULL) THEN
			SELECT 'invalid program' as 'message';
			LEAVE sp;

		WHEN (input_module not in (Select Mcode from module)) AND (input_module is not NULL) THEN
			SELECT 'invalid module' as 'message';
			LEAVE sp;	
		
		-- Check relationship exist in database or not
		WHEN (NOT EXISTS (SELECT AYcode, Fcode, Pcode, Mcode FROM ay_fac NATURAL JOIN ay_fac_p NATURAL JOIN ay_fac_pm 
							WHERE AYcode = input_academic_year AND Pcode = input_program
								AND Fcode = input_faculty AND Mcode = input_module) AND (status_case = 'delete'))
			THEN SELECT 'no' as 'valid', 'The relationship do not exist in database' as 'message';
			LEAVE sp;
            
		WHEN (status_case = 'delete') THEN
        -- Perform the DELETE case
			SET @test = CONCAT("DELETE FROM ay_fac_pm 
								WHERE Mcode = '", input_module,"'
									AND AYFPcode = (Select ayfp.AYFPcode
														From ay_fac_p ayfp
													Where ayfp.Pcode = '", input_program,"' AND ayfp.AYFcode = (Select ayf.AYFcode
																												From ay_fac ayf
																											 Where ayf.AYcode = '", input_academic_year,"' AND ayf.Fcode = '", input_faculty,"'));");
                            
			PREPARE stmt FROM @test;
			EXECUTE stmt;
			DEALLOCATE PREPARE stmt;
            LEAVE sp;

-- Check DATA CONSTAINT when INSERT case
		-- Check in each academic year, a program is offered by at most one faculty 
		WHEN EXISTS (SELECT AYcode, Pcode FROM ay_fac NATURAL JOIN ay_fac_p NATURAL JOIN ay_fac_pm 
						WHERE AYcode = input_academic_year AND Pcode = input_program
							AND Fcode <> input_faculty) AND (status_case = 'insert')
			THEN SELECT 'no' as 'valid', 'This program is already offered in this academic year' as 'message';
			LEAVE sp;
        
		-- Check in each academic year, a module is offered by at most one program
		WHEN EXISTS (SELECT AYcode, Mcode FROM ay_fac NATURAL JOIN ay_fac_p NATURAL JOIN ay_fac_pm 
						WHERE AYcode = input_academic_year AND Mcode = input_module
                        AND Pcode <> input_program) AND (status_case = 'insert')
			THEN SELECT 'no' as 'valid', 'This module is already offered in this academic year' as 'message';
			LEAVE sp;
            
		-- Check relationship between AYcode, Pcode and Fcode
		 WHEN NOT EXISTS (SELECT AYcode, Pcode, Fcode FROM ay_fac NATURAL JOIN ay_fac_p NATURAL JOIN ay_fac_pm 
							WHERE Pcode = input_program AND AYcode = input_academic_year AND Fcode = input_faculty) AND (status_case = 'insert')
	 	THEN SELECT 'no' as 'valid', 'There is no relationship between AYcode, Pcode and Fcode' as 'message';
			LEAVE sp;
            
            -- Check the already in database relationship added to database
		 WHEN EXISTS (SELECT AYcode, Pcode, Fcode, Mcode FROM ay_fac NATURAL JOIN ay_fac_p NATURAL JOIN ay_fac_pm 
							WHERE Pcode = input_program AND AYcode = input_academic_year AND Fcode = input_faculty AND Mcode = input_module) 
								AND (status_case = 'insert')
	 	THEN SELECT 'no' as 'valid', 'The relationship is already contained in database' as 'message';
			LEAVE sp;
    
		-- Case when insert
		WHEN (status_case = 'insert') THEN
			SET @test = CONCAT("INSERT INTO ay_fac_pm (AYFPcode, Mcode)
								VALUES ((Select ayfp.AYFPcode
											From ay_fac_p ayfp
										Where ayfp.Pcode = '", input_program,"' AND ayfp.AYFcode = (Select ayf.AYFcode
																									From ay_fac ayf
																								 Where ayf.AYcode = '", input_academic_year,"' AND ayf.Fcode = '", input_faculty,"')), '", input_module,"');");
                        
			PREPARE stmt FROM @test;
			EXECUTE stmt;
			DEALLOCATE PREPARE stmt;
            LEAVE sp;
	END CASE;
END
//
