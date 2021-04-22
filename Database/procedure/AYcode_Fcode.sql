USE pe2018;
DROP PROCEDURE IF EXISTS AYcode_Fcode;

DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `AYcode_Fcode`(
	input_AYcode Varchar(9),
    input_Fcode Varchar(10),
    status_case Varchar(50))
sp: BEGIN
	CASE 
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
