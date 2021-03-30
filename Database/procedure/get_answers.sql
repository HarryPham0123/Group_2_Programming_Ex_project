CREATE PROCEDURE `get_answers`(
                  Acode VARCHAR(50), 
                  class VARCHAR(50), 
                  lecturer VARCHAR(50))
                  
BEGIN
	SELECT q.Acode as 'Code', 
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
    
    WHERE 
		( Acode is null
		or q.Acode = Acode
		)
	AND
		( lecturer is null
		or q.Lcode = lecturer
		)
	AND
		( class is null
		or q.Ccode = class
		);
END
