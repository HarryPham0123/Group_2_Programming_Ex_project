USE pe2018;
DROP PROCEDURE IF EXISTS Ccode_Lcode;

DELIMITER //
CREATE PROCEDURE `Ccode_Lcode`(
	input_class Varchar(10),
    input_lecturer Varchar(10),
    status_case Varchar(50))
sp: BEGIN
	CASE 
	-- Check invalid parameter (Para NOT null but not in database)
		WHEN (input_lecturer not in (Select Lcode from lecturer)) AND (input_lecturer is not NULL) THEN
			SELECT 'invalid lecturer' as 'message';
			LEAVE sp;
        
		WHEN (input_class not in (Select Ccode from class)) AND (input_class is not NULL) THEN
			SELECT 'invalid class' as 'message';
			LEAVE sp;
			
	-- Check relationship exist in database or not
		WHEN NOT EXISTS (SELECT Ccode, Lcode FROM lecturer_in_class
						WHERE Ccode = input_class AND Lcode = input_lecturer) AND (status_case = 'delete')
			THEN SELECT 'no' as 'valid', 'The relationship between Ccode and Lcode do not exist in database' as 'message';
			LEAVE sp;
            
	-- Case when Delete 
		WHEN (status_case = 'delete') THEN
			SET @test = CONCAT("DELETE FROM lecturer_in_class 
								WHERE Ccode = '", input_class,"' AND Lcode = '", input_lecturer,"';");
                            
			PREPARE stmt FROM @test;
			EXECUTE stmt;
			DEALLOCATE PREPARE stmt;
			LEAVE sp;
    
    -- Check the already in database relationship added to database
		 WHEN EXISTS (SELECT Ccode, Lcode FROM lecturer_in_class
							WHERE Ccode = input_class AND Lcode = input_lecturer) 
								AND (status_case = 'insert')
	 	THEN SELECT 'no' as 'valid', 'The relationship is already contained in database' as 'message';
			LEAVE sp;
    
    -- Case when insert (Auto throw error if code not in their original table)
    WHEN (status_case = 'insert') THEN
		SET @test = CONCAT("INSERT INTO lecturer_in_class (Ccode, Lcode)
							VALUES ('", input_class,"', '", input_lecturer,"')");
                        
		PREPARE stmt FROM @test;
        EXECUTE stmt;
        DEALLOCATE PREPARE stmt;
        LEAVE sp;
	END CASE;
END
//
