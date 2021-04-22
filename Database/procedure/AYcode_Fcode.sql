USE pe2018;
DROP PROCEDURE IF EXISTS AYcode_Fcode;

DELIMITER //
CREATE PROCEDURE `AYcode_Fcode`(
	AYcode_input Varchar(9),
    Fcode_input Varchar(10),
    status_case Varchar(50))
sp: BEGIN
	CASE 
		-- Check relationship exist in database or not
		WHEN NOT EXISTS (SELECT AYcode, Fcode FROM ay_fac
						WHERE AYcode = AYcode_input AND Fcode = Fcode_input) AND (status_case = 'delete')
			THEN SELECT 'No' as 'Valid', 'The relationship between AYcode, Fcode do not exist in database' as 'Error message';
			LEAVE sp;
        
		-- Case when Delete 
		WHEN (status_case = 'delete') THEN
			SET @test = CONCAT("DELETE FROM ay_fac 
								WHERE AYcode = '", AYcode_input,"' AND Fcode = '", Fcode_input,"';");
                            
		PREPARE stmt FROM @test;
        EXECUTE stmt;
        DEALLOCATE PREPARE stmt;
			LEAVE sp;
    
    -- Check the already in database relationship added to database
		 WHEN EXISTS (SELECT AYcode, Fcode FROM ay_fac
							WHERE AYcode = AYcode_input AND Fcode = Fcode_input) 
								AND (status_case = 'insert')
	 	THEN SELECT 'No' as 'Valid', 'The relationship is already contained in database' as 'Error message';
			LEAVE sp;
    
    -- Case when Insert
		WHEN (status_case = 'insert') THEN
			SET @test = CONCAT("INSERT INTO ay_fac (AYcode, Fcode)
								VALUES ('", AYcode_input,"', '", Fcode_input,"');");
                            
			PREPARE stmt FROM @test;
			EXECUTE stmt;
			DEALLOCATE PREPARE stmt;
				LEAVE sp;
	END CASE;
END
//