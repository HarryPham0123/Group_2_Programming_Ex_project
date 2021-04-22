USE pe2018;
DROP PROCEDURE IF EXISTS AYcode_Fcode;

DELIMITER //
CREATE PROCEDURE `AYcode_Fcode`(
	input_AYcode Varchar(9),
    input_Fcode Varchar(10),
    status_case Varchar(50))
sp: BEGIN
	CASE 
		-- Check invalid parameter (Para NOT null but not in database)
		WHEN (input_AYcode not in (Select AYcode from academic_year)) AND (input_AYcode is not NULL) THEN
			SELECT 'invalid academic year' as 'message';
			LEAVE sp;

		WHEN (input_Fcode not in (Select Fcode from faculty)) AND (input_Fcode is not NULL) THEN
			SELECT 'invalid faculty' as 'message';
			LEAVE sp;
			
		-- Check relationship exist in database or not
		WHEN NOT EXISTS (SELECT AYcode, Fcode FROM ay_fac
						WHERE AYcode = input_AYcode AND Fcode = input_Fcode) AND (status_case = 'delete')
			THEN SELECT 'no' as 'valid', 'The relationship between AYcode, Fcode do not exist in database' as 'message';
			LEAVE sp;
        
		-- Case when Delete 
		WHEN (status_case = 'delete') THEN
			SET @test = CONCAT("DELETE FROM ay_fac 
								WHERE AYcode = '", input_AYcode,"' AND Fcode = '", input_Fcode,"';");
                            
		PREPARE stmt FROM @test;
        EXECUTE stmt;
        DEALLOCATE PREPARE stmt;
			LEAVE sp;
    
    -- Check the already in database relationship added to database
		 WHEN EXISTS (SELECT AYcode, Fcode FROM ay_fac
							WHERE AYcode = input_AYcode AND Fcode = input_Fcode) 
								AND (status_case = 'insert')
	 	THEN SELECT 'no' as 'valid', 'The relationship is already contained in database' as 'message';
			LEAVE sp;
    
    -- Case when Insert
		WHEN (status_case = 'insert') THEN
			SET @test = CONCAT("INSERT INTO ay_fac (AYcode, Fcode)
								VALUES ('", input_AYcode,"', '", input_Fcode,"');");
                            
			PREPARE stmt FROM @test;
			EXECUTE stmt;
			DEALLOCATE PREPARE stmt;
				LEAVE sp;
	END CASE;
END
//
