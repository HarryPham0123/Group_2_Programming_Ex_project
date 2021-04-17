USE pe2018;
DROP PROCEDURE IF EXISTS summary_attendance;
DELIMITER //
CREATE PROCEDURE `summary_attendance`(
	input_academic_year VARCHAR(50), 
	input_semester VARCHAR(50), 
	input_faculty VARCHAR(50), 
	input_program VARCHAR(50), 
	input_module VARCHAR(50), 
	input_lecturer VARCHAR(50), 
	input_class VARCHAR(50))
BEGIN
	-- Check invalid parameter (Para NOT null but not in database)
IF (input_class not in (Select Ccode from class)) AND (input_class is not NULL) THEN
	SET input_class := NULL;
END IF;

IF (input_lecturer not in (Select Lcode from lecturer)) AND (input_lecturer is not NULL) THEN
	SET input_lecturer := NULL;
END IF;

IF (input_module not in (Select Mcode from module)) AND (input_module is not NULL) THEN
	SET input_module := NULL;
END IF;

IF (input_program not in (Select Pcode from program)) AND (input_program is not NULL) THEN
	SET input_program := NULL;
END IF;

IF (input_faculty not in (Select Fcode from faculty)) AND (input_faculty is not NULL) THEN
	SET input_faculty := NULL;
END IF;

IF (input_semester not in (Select Scode from semester)) AND (input_semester is not NULL) THEN
	SET input_semester := NULL;
END IF;

IF (input_academic_year not in (Select AYcode from academic_year)) AND (input_academic_year is not NULL) THEN
	SET input_academic_year := NULL;
END IF;

-- Query to result the table
SELECT qsa.attendance, 
	IFNULL(count(q.answer_id), 0) AS total
	FROM questionnaire q
		NATURAL JOIN class c
		NATURAL JOIN lecturer_in_class
		NATURAL JOIN lecturer lec 
		NATURAL JOIN semester s
		NATURAL JOIN academic_year ay
		NATURAL JOIN module m
		NATURAL JOIN program_module 
		NATURAL JOIN program p
		NATURAL JOIN ay_faculty_pm 
		NATURAL JOIN faculty f
		RIGHT JOIN question_support_attendance qsa on q.attendance = qsa.attendance
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
END
//