CREATE PROCEDURE `get_question`(
	Class_code VARCHAR(10),
    Lecturer_code VARCHAR(10),
    Question VARCHAR(50))
BEGIN
	-- Check invalid parameter (Para NOT null but not in database)
	IF (class_code not in (Select Ccode from class)) AND (class_code is not NULL) THEN
		SET class_code := NULL;
	END IF;

	IF (lecturer_code not in (Select Lcode from lecturer)) AND (lecturer_code is not NULL) THEN
		SET lecturer_code := NULL;
	END IF;

	-- Query for summary appropriate requirements
	CREATE TEMPORARY TABLE IF NOT EXISTS temp (Class_c VARCHAR(10), Lecturer_c VARCHAR(10), Answer_key VARCHAR(50));
	SET @a = Class_code;
	SET @b = Lecturer_code;
	SET @case1 = 'Attendance';
	SET @case2 = 'Gender';

	CASE Question
		WHEN @case1 THEN
			INSERT INTO temp (Class_c, Lecturer_c, Answer_key) values
				(@a,@b,'never'), (@a,@b,'rarely'), (@a,@b,'sometimes'), (@a,@b,'often'), (@a,@b,'always');
		WHEN @case2 THEN
			INSERT INTO temp (Class_c, Lecturer_c, Answer_key) values
				(@a,@b,'male'), (@a,@b,'female'), (@a,@b,'other');
		ELSE
			INSERT INTO temp (Class_c, Lecturer_c, Answer_key) values
				(@a,@b,'not'), (@a,@b,'1'), (@a,@b,'2'), (@a,@b,'3'), (@a,@b,'4'), (@a,@b,'5');
	END CASE;
    
    SET @test = CONCAT('SELECT t.Answer_key, IFNULL(COUNT(q.', Question, '), 0) AS total
						FROM questionnaire q RIGHT JOIN temp t 
							ON q.', Question, ' = t.Answer_key 
                            AND q.Ccode = t.Class_c 
                            AND q.Lcode = t.Lecturer_c
						GROUP BY t.Answer_key');
    
	PREPARE stmt FROM @test;
    EXECUTE stmt;
	DEALLOCATE PREPARE stmt;
    DROP TEMPORARY TABLE IF EXISTS temp;
END