CREATE DEFINER=`root`@`localhost` PROCEDURE `summary_comment`(input_Ccode VARCHAR(10), input_Lcode VARCHAR(10))
BEGIN
IF EXISTs (SELECT lc.Ccode, lc.Lcode
			FROM lecturer_in_class lc
            WHERE lc.Ccode = input_Ccode
            AND lc.Lcode = input_Lcode
            )
	THEN
		SELECT question_18 as 'Comment'
        FROM questionnaire q
        WHERE q.Ccode = input_Ccode
            AND q.Lcode = input_Lcode;
ELSE SELECT 'No' as 'valid';
END IF;
END
