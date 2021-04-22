USE pe2018;
DROP PROCEDURE IF EXISTS summary_gender;
DELIMITER //
CREATE PROCEDURE `summary_gender`(
	input_academic_year VARCHAR(9), 
	input_semester VARCHAR(10), 
	input_faculty VARCHAR(10), 
	input_program VARCHAR(10), 
	input_module VARCHAR(10), 
	input_lecturer VARCHAR(10), 
	input_class VARCHAR(10))
sp: BEGIN
-- Check invalid parameter (Para NOT null but not in database)
-- If invalid, stop the procedure and output the error message
CASE
	WHEN (input_academic_year not in (Select AYcode from academic_year)) AND (input_academic_year is not NULL) THEN
		SELECT 'invalid academic year' as 'message';
        LEAVE sp;
        
	WHEN (input_semester not in (Select Scode from semester)) AND (input_semester is not NULL) THEN
		SELECT 'invalid semester' as 'message';
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

	WHEN (input_lecturer not in (Select Lcode from lecturer)) AND (input_lecturer is not NULL) THEN
		SELECT 'invalid lecturer' as 'message';
        LEAVE sp;
        
	WHEN (input_class not in (Select Ccode from class)) AND (input_class is not NULL) THEN
		SELECT 'invalid class' as 'message';
        LEAVE sp;
	-- In the case of all parameters are valid
	ELSE
-- Query to get the result table
SELECT qsg.gender, 
	IFNULL(count(q.answer_id), 0) AS total
	FROM questionnaire q
		NATURAL JOIN class c
        NATURAL JOIN lecturer_in_class NATURAL JOIN lecturer lec 
        NATURAL JOIN semester s
        NATURAL JOIN academic_year ay
        NATURAL JOIN ay_fac NATURAL JOIN faculty f
        NATURAL JOIN ay_fac_p NATURAL JOIN program p
        NATURAL JOIN ay_fac_pm NATURAL JOIN module m
        -- Use the respective support table, taking into account the possible '0' outcome (when no one chooses an answer option)
		RIGHT JOIN question_support_gender qsg on q.gender = qsg.gender
-- Check if parameter NULL or NOT, if yes, query based on the other parameters (filter)
AND
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
	)
GROUP BY 1;
END CASE;
END
//
