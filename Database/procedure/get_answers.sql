DROP PROCEDURE IF EXISTS get_answers;
DELIMITER //
CREATE PROCEDURE `get_answers`(
	input_AYcode VARCHAR(9), 
	input_Scode VARCHAR(10), 
	input_Fcode VARCHAR(10), 
	input_Pcode VARCHAR(10), 
	input_Mcode VARCHAR(10), 
	input_Lcode VARCHAR(10), 
	input_Ccode VARCHAR(10))
sp: BEGIN
-- Check invalid parameter (Para NOT null but not in database)
-- If invalid, stop the procedure and output the error message
CASE
	WHEN (input_AYcode not in (Select AYcode from academic_year)) AND (input_AYcode is not NULL) THEN
		SELECT 'invalid academic year' as 'message';
        LEAVE sp;
        
	WHEN (input_Scode not in (Select Scode from semester)) AND (input_Scode is not NULL) THEN
		SELECT 'invalid semester' as 'message';
        LEAVE sp;

	WHEN (input_Fcode not in (Select Fcode from faculty)) AND (input_Fcode is not NULL) THEN
		SELECT 'invalid faculty' as 'message';
        LEAVE sp;

	WHEN (input_Pcode not in (Select Pcode from program)) AND (input_Pcode is not NULL) THEN
		SELECT 'invalid program' as 'message';
        LEAVE sp;

	WHEN (input_Mcode not in (Select Mcode from module)) AND (input_Mcode is not NULL) THEN
		SELECT 'invalid module' as 'message';
        LEAVE sp;

	WHEN (input_Lcode not in (Select Lcode from lecturer)) AND (input_Lcode is not NULL) THEN
		SELECT 'invalid lecturer' as 'message';
        LEAVE sp;
        
	WHEN (input_Ccode not in (Select Ccode from class)) AND (input_Ccode is not NULL) THEN
		SELECT 'invalid class' as 'message';
        LEAVE sp;
	-- In the case of all parameters are valid
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
        NATURAL JOIN ay_fac NATURAL JOIN faculty f
        NATURAL JOIN ay_fac_p NATURAL JOIN program p
        NATURAL JOIN ay_fac_pm NATURAL JOIN module m
	-- Check if parameter NULL or NOT, if yes, query based on the other parameters (filter)
    WHERE
	( input_AYcode is null
		or ay.AYcode = input_AYcode
	)
	AND
	( input_Scode is null
		or s.Scode = input_Scode
	)
	AND
	( input_Fcode is null
		or f.Fcode = input_Fcode
	)
	AND
	( input_Pcode is null
		or p.Pcode = input_Pcode
	)
	AND
	( input_Mcode is null
		or m.Mcode = input_Mcode
	)
	AND
	( input_Lcode is null
		or lec.Lcode = input_Lcode
	)
	AND
	( input_Ccode is null
		or c.Ccode = input_Ccode
	);
END CASE;
END
//
