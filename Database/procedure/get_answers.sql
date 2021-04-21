DROP PROCEDURE IF EXISTS get_answers;
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_answers`(
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
	-- Query for getting the questionnaire's answer
	SELECT q.answer_id AS Answer_id,
		q.attendance AS attendance,
        q.gender AS gender,
        q.question_1 AS question_1,
		q.question_2 AS question_2,
		q.question_3 AS question_3,
        q.question_4 AS question_4,
        q.question_5 AS question_5,
        q.question_6 AS question_6,
        q.question_7 AS question_7,
        q.question_8 AS question_8,
        q.question_9 AS question_9,
        q.question_10 AS question_10,
        q.question_11 AS question_11,
        q.question_12 AS question_12,
        q.question_13 AS question_13,
        q.question_14 AS question_14,
        q.question_15 AS question_15,
        q.question_16 AS question_16,
        q.question_17 AS question_17,
        q.question_18 AS question_18
    FROM questionnaire q
		NATURAL JOIN class c
		NATURAL JOIN lecturer_in_class NATURAL JOIN lecturer lec 
		NATURAL JOIN semester s
		NATURAL JOIN academic_year ay
		NATURAL JOIN module m
		NATURAL JOIN program_module 
		NATURAL JOIN program p
		NATURAL JOIN ay_faculty_pm 
		NATURAL JOIN faculty f
    WHERE
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
