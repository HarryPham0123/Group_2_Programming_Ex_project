CREATE PROCEDURE `insert_questionnaire`(input_Ccode VARCHAR(10), input_Lcode VARCHAR(10), question_1_input Varchar(10), question_2_input Varchar(10), question_3_input Varchar(10),
				       question_4_input Varchar(10), question_5_input Varchar(10), question_6_input Varchar(10), question_7_input Varchar(10), question_8_input Varchar(10), 
					question_9_input Varchar(10), question_10_input Varchar(10), question_11_input Varchar(10), question_12_input Varchar(10), question_13_input Varchar(10),
					question_14_input Varchar(10), question_15_input Varchar(10), question_16_input Varchar(10), question_17_input Varchar(10), question_18_input Varchar(10))

BEGIN
	INSERT INTO questionnaire
		(Ccode, Lcode, question_1, question_2, question_3, question_4,
		question_5, question_6, question_7, question_8, question_9, question_10,
		question_11, question_12, question_13, question_14, question_15, question_16,
		question_17, question_18) 
	VALUES (input_Ccode, input_Lcode, CAST(question_1_input AS NCHAR), CAST(question_2_input AS NCHAR), CAST(question_3_input AS NCHAR), CAST(question_4_input AS NCHAR),
		CAST(question_5_input AS NCHAR), CAST(question_6_input AS NCHAR), CAST(question_7_input AS NCHAR), CAST(question_8_input AS NCHAR), CAST(question_9_input AS NCHAR), 
		CAST(question_10_input AS NCHAR), CAST(question_11_input AS NCHAR),
		CAST(question_12_input AS NCHAR), CAST(question_13_input AS NCHAR), CAST(question_14_input AS NCHAR), CAST(question_15_input AS NCHAR),
		CAST(question_16_input AS NCHAR), CAST(question_17_input AS NCHAR), CAST(question_18_input AS NCHAR));
END
