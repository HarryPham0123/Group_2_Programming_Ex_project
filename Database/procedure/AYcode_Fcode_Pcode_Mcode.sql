USE pe2018;
DROP PROCEDURE IF EXISTS AYcode_Fcode_Pcode_Mcode;

DELIMITER //
CREATE PROCEDURE `AYcode_Fcode_Pcode_Mcode`(
	input_AYcode Varchar(9),
    input_Fcode Varchar(10),
    input_Pcode Varchar(10),
    input_Mcode Varchar(10),
    status_case Varchar(50))
sp: BEGIN

-- Check DATA EXIST when DELETE case
	CASE
		-- Check relationship exist in database or not
		WHEN (NOT EXISTS (SELECT AYcode, Fcode, Pcode, Mcode FROM ay_fac NATURAL JOIN ay_fac_p NATURAL JOIN ay_fac_pm 
							WHERE AYcode = input_AYcode AND Pcode = input_Pcode
								AND Fcode = input_Fcode AND Mcode = input_Mcode) AND (status_case = 'delete'))
			THEN SELECT 'no' as 'valid', 'The relationship do not exist in database' as 'message';
			LEAVE sp;
            
		WHEN (status_case = 'delete') THEN
        -- Perform the DELETE case
			SET @test = CONCAT("DELETE FROM ay_fac_pm 
								WHERE Mcode = '", input_Mcode,"'
									AND AYFPcode = (Select ayfp.AYFPcode
														From ay_fac_p ayfp
													Where ayfp.Pcode = '", input_Pcode,"' AND ayfp.AYFcode = (Select ayf.AYFcode
																												From ay_fac ayf
																											 Where ayf.AYcode = '", input_AYcode,"' AND ayf.Fcode = '", input_Fcode,"'));");
                            
			PREPARE stmt FROM @test;
			EXECUTE stmt;
			DEALLOCATE PREPARE stmt;
            LEAVE sp;

-- Check DATA CONSTAINT when INSERT case
		-- Check in each academic year, a program is offered by at most one faculty 
		WHEN EXISTS (SELECT AYcode, Pcode FROM ay_fac NATURAL JOIN ay_fac_p NATURAL JOIN ay_fac_pm 
						WHERE AYcode = input_AYcode AND Pcode = input_Pcode
							AND Fcode <> input_Fcode) AND (status_case = 'insert')
			THEN SELECT 'no' as 'valid', 'This program is already offered in this academic year' as 'message';
			LEAVE sp;
        
		-- Check in each academic year, a module is offered by at most one program
		WHEN EXISTS (SELECT AYcode, Mcode FROM ay_fac NATURAL JOIN ay_fac_p NATURAL JOIN ay_fac_pm 
						WHERE AYcode = input_AYcode AND Mcode = input_Mcode
                        AND Pcode <> input_Pcode) AND (status_case = 'insert')
			THEN SELECT 'no' as 'valid', 'This module is already offered in this academic year' as 'message';
			LEAVE sp;
            
		-- Check relationship between AYcode, Pcode and Fcode
		 WHEN NOT EXISTS (SELECT AYcode, Pcode, Fcode FROM ay_fac NATURAL JOIN ay_fac_p NATURAL JOIN ay_fac_pm 
							WHERE Pcode = input_Pcode AND AYcode = input_AYcode AND Fcode = input_Fcode) AND (status_case = 'insert')
	 	THEN SELECT 'no' as 'valid', 'There is no relationship between AYcode, Pcode and Fcode' as 'message';
			LEAVE sp;
            
            -- Check the already in database relationship added to database
		 WHEN EXISTS (SELECT AYcode, Pcode, Fcode, Mcode FROM ay_fac NATURAL JOIN ay_fac_p NATURAL JOIN ay_fac_pm 
							WHERE Pcode = input_Pcode AND AYcode = input_AYcode AND Fcode = input_Fcode AND Mcode = input_Mcode) 
								AND (status_case = 'insert')
	 	THEN SELECT 'no' as 'valid', 'The relationship is already contained in database' as 'message';
			LEAVE sp;
    
		-- Case when insert
		WHEN (status_case = 'insert') THEN
			SET @test = CONCAT("INSERT INTO ay_fac_pm (AYFPcode, Mcode)
								VALUES ((Select ayfp.AYFPcode
											From ay_fac_p ayfp
										Where ayfp.Pcode = '", input_Pcode,"' AND ayfp.AYFcode = (Select ayf.AYFcode
																									From ay_fac ayf
																								 Where ayf.AYcode = '", input_AYcode,"' AND ayf.Fcode = '", input_Fcode,"')), '", input_Mcode,"');");
                        
			PREPARE stmt FROM @test;
			EXECUTE stmt;
			DEALLOCATE PREPARE stmt;
            LEAVE sp;
	END CASE;
END
//
