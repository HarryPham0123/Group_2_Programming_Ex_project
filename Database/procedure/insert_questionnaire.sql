CREATE PROCEDURE `insert_questionnaire`(input_Ccode VARCHAR(10), input_Lcode VARCHAR(10), input_Answers JSON)

BEGIN
	INSERT INTO questionnaire(Ccode, Lcode, Answers) VALUES (input_Ccode, input_Lcode, input_Answers);
END
