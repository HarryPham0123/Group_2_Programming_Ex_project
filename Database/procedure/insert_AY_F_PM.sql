CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_AY_F_PM`(input_AYcode VARCHAR(10), input_Fcode VARCHAR(10), input_Pcode VARCHAR(10), input_Mcode VARCHAR(10))
sp: BEGIN
-- Check invalid cases
CASE
	-- Check in each academic year, a program is offered by at most one faculty 
    WHEN EXISTS (SELECT AYcode, Pcode FROM ay_faculty_pm NATURAL JOIN program_module WHERE AYcode = input_AYcode AND Pcode = input_Pcode)
		THEN SELECT 'No' as 'Valid', 'This program is already offered in this academic year' as 'Error message';
        LEAVE sp;
        
	-- Check in each academic year, a module is offered by at most one program
    WHEN EXISTS (SELECT AYcode, Mcode FROM ay_faculty_pm NATURAL JOIN program_module WHERE AYcode = input_AYcode AND Mcode = input_Mcode)
		THEN SELECT 'No' as 'Valid', 'This module is already offered in this academic year' as 'Error message';
        LEAVE sp;
	
    -- If satisfy the above contraints, then insert
    ELSE INSERT INTO ay_faculty_pm (AYcode, Fcode, PMcode) VALUES 
		(input_AYcode, input_Fcode, (SELECT PMcode FROM program_module WHERE Pcode = input_Pcode AND Mcode = input_Mcode));

END CASE;
END
