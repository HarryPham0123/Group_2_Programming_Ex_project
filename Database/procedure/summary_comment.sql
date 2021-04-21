USE pe2018;
DROP PROCEDURE IF EXISTS summary_comment;

DELIMITER //
CREATE PROCEDURE `summary_comment`(input_Ccode VARCHAR(10), input_Lcode VARCHAR(10))
BEGIN
-- Check if the pair class_code and lecturer_code exists in DB (the class is taught by the lecturer or not)
-- If yes, then query all corresponding comments
IF EXISTs (SELECT lc.Ccode, lc.Lcode
			FROM lecturer_in_class lc
            WHERE lc.Ccode = input_Ccode
            AND lc.Lcode = input_Lcode
            )
	THEN
		SELECT question_18 as 'comment'
        FROM questionnaire q
        WHERE q.Ccode = input_Ccode
            AND q.Lcode = input_Lcode;
            
-- Else, return error message
ELSE SELECT 'no' as 'message';
END IF;
END
//
