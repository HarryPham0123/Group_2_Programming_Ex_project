USE pe2018;
DROP PROCEDURE IF EXISTS AYcode_Fcode_Pcode;

DELIMITER //
CREATE PROCEDURE `AYcode_Fcode_Pcode`(
	input_AYcode Varchar(9),
    input_Fcode Varchar(10),
    input_Pcode Varchar(10),
    status_case Varchar(50))
sp: BEGIN
			
    -- Check data constraint 
	CASE
		-- Check invalid parameter (Para NOT null but not in database)
		WHEN (AYcode_input not in (Select AYcode from academic_year)) AND (AYcode_input is not NULL) THEN
			SELECT 'invalid academic year' as 'message';
			LEAVE sp;

		WHEN (Fcode_input not in (Select Fcode from faculty)) AND (Fcode_input is not NULL) THEN
			SELECT 'invalid faculty' as 'message';
			LEAVE sp;

		WHEN (Pcode_input not in (Select Pcode from program)) AND (Pcode_input is not NULL) THEN
			SELECT 'invalid program' as 'message';
			LEAVE sp;	
		
		-- Check relationship exist in database or not
		WHEN NOT EXISTS (SELECT AYcode, Fcode, Pcode FROM ay_fac NATURAL JOIN ay_fac_p
						WHERE AYcode = input_AYcode AND Pcode = input_Pcode) AND (status_case = 'delete')
			THEN SELECT 'no' as 'valid', 'The relationship between AYcode, Fcode, Pcode do not exist in database' as 'message';
			LEAVE sp;
	
	-- Case when Delete 
    WHEN (status_case = 'delete') THEN
		SET @test = CONCAT("DELETE FROM ay_fac_p
							WHERE Pcode = '", input_Pcode,"'
								AND AYFcode = (Select ayf.AYFcode
												From ay_fac ayf
												Where ayf.AYcode = '", input_AYcode,"' AND ayf.Fcode = '", input_Fcode,"');");
                            
		PREPARE stmt FROM @test;
        EXECUTE stmt;
        DEALLOCATE PREPARE stmt;
			LEAVE sp;
    
-- Check DATA CONSTAINT when INSERT case
	-- Check in each academic year, a program is offered by at most one faculty 
	WHEN EXISTS (SELECT AYcode, Pcode FROM ay_fac NATURAL JOIN ay_fac_p
					WHERE AYcode = input_AYcode AND Pcode = input_Pcode
						AND Fcode <> input_Fcode) AND (status_case = 'insert')
		THEN SELECT 'no' as 'valid', 'This program is already offered in this academic year' as 'message';
		LEAVE sp;
	
    -- Check relationship between AYcode and Fcode
		 WHEN NOT EXISTS (SELECT AYcode, Fcode FROM ay_fac NATURAL JOIN ay_fac_p
							WHERE Fcode = input_Fcode AND AYcode = input_AYcode) AND (status_case = 'insert')
	 	THEN SELECT 'no' as 'valid', 'There is no relationship between AYcode and Fcode' as 'message';
			LEAVE sp;
            
	-- Check the already in database relationship added to database
		 WHEN EXISTS (SELECT AYcode, Pcode, Fcode FROM ay_fac NATURAL JOIN ay_fac_p
							WHERE Pcode = input_Pcode AND AYcode = input_AYcode AND Fcode = input_Fcode) 
								AND (status_case = 'insert')
	 	THEN SELECT 'no' as 'valid', 'The relationship is already contained in database' as 'message';
			LEAVE sp;
            
    -- Case when insert (Auto throw error if code NOT in their original table)
    WHEN (status_case = 'insert') THEN
		SET @test = CONCAT("INSERT INTO ay_fac_p (Pcode, AYFcode)
								VALUES ('", input_Pcode,"', (Select ayf.AYFcode
																From ay_fac ayf
															 Where ayf.AYcode = '", input_AYcode,"' AND ayf.Fcode = '", input_Fcode,"'));");
                        
		PREPARE stmt FROM @test;
        EXECUTE stmt;
        DEALLOCATE PREPARE stmt;
			LEAVE sp;
	END CASE;

END
//
