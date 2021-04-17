DROP PROCEDURE IF EXISTS get_answers;
DELIMITER //
CREATE PROCEDURE `get_answers`(
	academic_year VARCHAR(10), 
	semester VARCHAR(10), 
	faculty VARCHAR(10), 
	program VARCHAR(10), 
	module VARCHAR(10), 
	lecturer VARCHAR(10), 
	class VARCHAR(10))
BEGIN
	-- Check invalid parameter (Para NOT null but not in database)
	IF (class not in (Select Ccode from class)) AND (class is not NULL) THEN
		SET class := NULL;
	END IF;

	IF (lecturer not in (Select Lcode from lecturer)) AND (lecturer is not NULL) THEN
		SET lecturer := NULL;
	END IF;

	IF (module not in (Select Mcode from module)) AND (module is not NULL) THEN
		SET module := NULL;
	END IF;

	IF (program not in (Select Pcode from program)) AND (program is not NULL) THEN
		SET program := NULL;
	END IF;

	IF (faculty not in (Select Fcode from faculty)) AND (faculty is not NULL) THEN
		SET faculty := NULL;
	END IF;

	IF (semester not in (Select Scode from semester)) AND (semester is not NULL) THEN
		SET semester := NULL;
	END IF;

	IF (academic_year not in (Select AYcode from academic_year)) AND (academic_year is not NULL) THEN
		SET academic_year := NULL;
	END IF;

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
		( lecturer is null
		or q.Lcode = lecturer
		)
	AND
		( class is null
		or q.Ccode = class
		)
	AND
    	( academic_year is null
		or ay.AYcode = academic_year
		)
	AND
		( semester is null
		or s.Scode = semester
		)
	AND
		( faculty is null
		or f.Fcode = faculty
		)
	AND
		( program is null
		or p.Pcode = program
		)
	AND
		( module is null
		or m.Mcode = module
		);
END
//
