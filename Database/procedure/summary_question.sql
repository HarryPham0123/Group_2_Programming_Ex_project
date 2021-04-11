USE pe2018;
DROP PROCEDURE IF EXISTS summary_question;
DELIMITER //
CREATE PROCEDURE `summary_question`(
	input_academic_year VARCHAR(50), 
	input_semester VARCHAR(50), 
	input_faculty VARCHAR(50), 
	input_program VARCHAR(50), 
	input_module VARCHAR(50), 
	input_lecturer VARCHAR(50), 
	input_class VARCHAR(50),
    input_question VARCHAR(50))
BEGIN
	-- Check invalid parameter (Para NOT null but not in database)
IF (input_class not in (Select Ccode from class)) OR (input_class is NULL) THEN
	SET input_class = '';
END IF;

IF (input_lecturer not in (Select Lcode from lecturer)) OR (input_lecturer is  NULL) THEN
	SET input_lecturer = '';
END IF;

IF (input_module not in (Select Mcode from module)) OR (input_module is  NULL) THEN
	SET input_module = '';
END IF;

IF (input_program not in (Select Pcode from program)) OR (input_program is  NULL) THEN
	SET input_program = '';
END IF;

IF (input_faculty not in (Select Fcode from faculty)) OR (input_faculty is  NULL) THEN
	SET input_faculty = '';
END IF;

IF (input_semester not in (Select Scode from semester)) OR (input_semester is  NULL) THEN
	SET input_semester = '';
END IF;

IF (input_academic_year not in (Select AYcode from academic_year)) AND (input_academic_year is  NULL) THEN
	SET input_academic_year = '';
END IF;

	-- Flow control: depends on the type of input_question we will have different cases

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
							('",input_academic_year,"' = ''
							or ay.AYcode = '",input_academic_year,"'
                            )
						AND
                            ('",input_semester,"' = ''
                            or s.Scode = '",input_semester,"'
                            )
						AND
                            ('",input_faculty,"' = ''
                            or f.Fcode = '",input_faculty,"'
                            )
						AND
                            ('",input_program,"' = ''
                            or p.Pcode = '",input_program,"'
                            )
						AND
                            ('",input_module,"' = ''
                            or m.Mcode = '",input_module,"'
                            )
						AND
                            ('",input_lecturer,"' = ''
                            or lec.Lcode = '",input_lecturer,"'
                            )
						AND
                            ('",input_class,"' = ''
                            or c.Ccode = '",input_class,"'
                            )
						GROUP BY qs.answer_key");
    
	PREPARE stmt FROM @test;
    EXECUTE stmt ;
	DEALLOCATE PREPARE stmt;
END
//