CREATE PROCEDURE `get_answers`(
	academic_year VARCHAR(50), 
	semester VARCHAR(50), 
	faculty VARCHAR(50), 
	program VARCHAR(50), 
	module VARCHAR(50), 
	lecturer VARCHAR(50), 
	class VARCHAR(50))
    
BEGIN
	SELECT q.answer_id as 'ID', 
		q.Answers->>'$.attendance' Attendance, 
		q.Answers->>'$.gender' Gender,
		q.Answers->>'$.question_1' Question1,
            q.Answers->>'$.question_2' Question2,
            q.Answers->>'$.question_3' Question3,
            q.Answers->>'$.question_4' Question4,
            q.Answers->>'$.question_5' Question5,
            q.Answers->>'$.question_6' Question6,
            q.Answers->>'$.question_7' Question7,
            q.Answers->>'$.question_8' Question8,
            q.Answers->>'$.question_9' Question9,
            q.Answers->>'$.question_10' Question10,
            q.Answers->>'$.question_11' Question11,
            q.Answers->>'$.question_12' Question12,
            q.Answers->>'$.question_13' Question13,
            q.Answers->>'$.question_14' Question14,
            q.Answers->>'$.question_15' Question15,
            q.Answers->>'$.question_16' Question16,
            q.Answers->>'$.question_17' Question17,
            q.Answers->>'$.question_18' Question18
            
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
		)
	AND
		( lecturer is null
		or lec.Lcode = lecturer
		)
	AND
		( class is null
		or c.Ccode = class
		);
END
