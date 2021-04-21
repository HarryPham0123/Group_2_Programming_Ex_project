DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetTotalClassesSize`(
	input_academic_year VARCHAR(10), 
	input_semester VARCHAR(10), 
	input_faculty VARCHAR(10), 
	input_program VARCHAR(10), 
	input_module VARCHAR(10), 
	input_lecturer VARCHAR(10), 
	input_class VARCHAR(10))
sp: BEGIN
-- Check invalid parameter (Para NOT null but not in database)
CASE
	WHEN (input_academic_year not in (Select AYcode from academic_year)) AND (input_academic_year is not NULL) THEN
		SELECT 'Invalid academic year' as 'Error_message';
        LEAVE sp;
        
	WHEN (input_semester not in (Select Scode from semester)) AND (input_semester is not NULL) THEN
		SELECT 'Invalid semester' as 'Error_message';
        LEAVE sp;

	WHEN (input_faculty not in (Select Fcode from faculty)) AND (input_faculty is not NULL) THEN
		SELECT 'Invalid faculty' as 'Error_message';
        LEAVE sp;

	WHEN (input_program not in (Select Pcode from program)) AND (input_program is not NULL) THEN
		SELECT 'Invalid program' as 'Error_message';
        LEAVE sp;

	WHEN (input_module not in (Select Mcode from module)) AND (input_module is not NULL) THEN
		SELECT 'Invalid module' as 'Error_message';
        LEAVE sp;

	WHEN (input_lecturer not in (Select Lcode from lecturer)) AND (input_lecturer is not NULL) THEN
		SELECT 'Invalid lecturer' as 'Error_message';
        LEAVE sp;
        
	WHEN (input_class not in (Select Ccode from class)) AND (input_class is not NULL) THEN
		SELECT 'Invalid class' as 'Error_message';
        LEAVE sp;
	ELSE
-- Query for getting the class's size
SELECT SUM(c.size)
FROM class c
	NATURAL JOIN lecturer_in_class NATURAL JOIN lecturer lec 
    NATURAL JOIN semester s
    NATURAL JOIN academic_year ay
    NATURAL JOIN module m
    NATURAL JOIN program_module 
    NATURAL JOIN program p
    NATURAL JOIN ay_faculty_pm 
    NATURAL JOIN faculty f

WHERE 
-- Check WHEN parameter NULL or NOT, WHEN yes, query based on the other parameters
	( input_academic_year is null
		or ay.AYcode = input_academic_year
	)
	AND
	( input_semester is null
		or s.Scode = input_semester
	)
	AND
	( input_faculty is null
		or f.Fcode = input_faculty
	)
	AND
	( input_program is null
		or p.Pcode = input_program
	)
	AND
	( input_module is null
		or m.Mcode = input_module
	)
	AND
	( input_lecturer is null
		or lec.Lcode = input_lecturer
	)
	AND
	( input_class is null
		or c.Ccode = input_class
	);
END CASE;
END
//
DELIMITER ;
