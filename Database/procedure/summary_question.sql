USE pe2018;
DROP PROCEDURE IF EXISTS summary_question;
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `summary_question`(
	input_academic_year VARCHAR(10), 
	input_semester VARCHAR(10), 
	input_faculty VARCHAR(10), 
	input_program VARCHAR(10), 
	input_module VARCHAR(10), 
	input_lecturer VARCHAR(10), 
	input_class VARCHAR(10),
    input_question VARCHAR(10))
sp: BEGIN
-- Check invalid parameter (Para NOT null but not in database)
CASE
	WHEN (input_academic_year not in (Select AYcode from academic_year)) AND (input_academic_year is not NULL) THEN
		SELECT 'Invalid academic year' as 'Error_message';
        LEAVE sp;
        
	WHEN (input_semester not in (Select Scode from semester)) AND (input_semester is not NULL) THEN
		SELECT 'Invalid semester' as 'Error_message';
        LEAVE sp;

	WHEN (input_faculty not in (Select Fcode from faculty)) AND (input_faculty is not NULL) THEN
		SELECT 'Invalid faculty' as 'Error_message';
        LEAVE sp;

	WHEN (input_program not in (Select Pcode from program)) AND (input_program is not NULL) THEN
		SELECT 'Invalid program' as 'Error_message';
        LEAVE sp;

	WHEN (input_module not in (Select Mcode from module)) AND (input_module is not NULL) THEN
		SELECT 'Invalid module' as 'Error_message';
        LEAVE sp;

	WHEN (input_lecturer not in (Select Lcode from lecturer)) AND (input_lecturer is not NULL) THEN
		SELECT 'Invalid lecturer' as 'Error_message';
        LEAVE sp;
        
	WHEN (input_class not in (Select Ccode from class)) AND (input_class is not NULL) THEN
		SELECT 'Invalid class' as 'Error_message';
        LEAVE sp;
	ELSE
    -- Query for summary appropriate requirements
    SET @test = CONCAT("SELECT qs.answer_key, IFNULL(count(q.answer_id),0) AS TOTAL
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
                        RIGHT JOIN question_support_number qs on q.",input_question," = qs.answer_key
                        AND 
							('",IFNULL(input_academic_year, ''),"' = ''
							or ay.AYcode = '",IFNULL(input_academic_year, ''),"'
                            )
						AND
                            ('",IFNULL(input_semester, ''),"' = ''
                            or s.Scode = '",IFNULL(input_semester, ''),"'
                            )
						AND
                            ('",IFNULL(input_faculty, ''),"' = ''
                            or f.Fcode = '",IFNULL(input_faculty, ''),"'
                            )
						AND
                            ('",IFNULL(input_program, ''),"' = ''
                            or p.Pcode = '",IFNULL(input_program, ''),"'
                            )
						AND
                            ('",IFNULL(input_module, ''),"' = ''
                            or m.Mcode = '",IFNULL(input_module, ''),"'
                            )
						AND
                            ('",IFNULL(input_lecturer, ''),"' = ''
                            or lec.Lcode = '",IFNULL(input_lecturer, ''),"'
                            )
						AND
                            ('",IFNULL(input_class, ''),"' = ''
                            or c.Ccode = '",IFNULL(input_class, ''),"'
                            )
						GROUP BY qs.answer_key");
    
	PREPARE stmt FROM @test;
    EXECUTE stmt ;
	DEALLOCATE PREPARE stmt;
END CASE;
END
//
