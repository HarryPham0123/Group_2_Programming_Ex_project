CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_questionnaire`(input_Ccode VARCHAR(10), input_Lcode VARCHAR(10), input_gender VARCHAR(10), input_attendance VARCHAR(10),
					question_1_input INT, question_2_input INT, question_3_input INT,
				       question_4_input INT, question_5_input INT, question_6_input INT, question_7_input INT, question_8_input INT, 
					question_9_input INT, question_10_input INT, question_11_input INT, question_12_input INT, question_13_input INT,
					question_14_input INT, question_15_input INT, question_16_input INT, question_17_input INT, question_18_input Varchar(100))
BEGIN
IF EXISTs (SELECT lc.Ccode, lc.Lcode
			FROM lecturer_in_class lc
            WHERE lc.Ccode = input_Ccode
            AND lc.Lcode = input_Lcode
            )
	THEN
	INSERT INTO questionnaire
		(Ccode, Lcode, gender, attendance, question_1, question_2, question_3, question_4,
		question_5, question_6, question_7, question_8, question_9, question_10,
		question_11, question_12, question_13, question_14, question_15, question_16,
		question_17, question_18) 
	VALUES (input_Ccode, input_Lcode, input_gender, input_attendance, 
    question_1_input , question_2_input, question_3_input , question_4_input , question_5_input , question_6_input , 
    question_7_input , question_8_input , question_9_input , question_10_input , question_11_input , question_12_input , 
    question_13_input , question_14_input , question_15_input , question_16_input , question_17_input , question_18_input);
END IF;
END
