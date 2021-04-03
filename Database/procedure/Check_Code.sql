-- Stored procedure to check Lecturer code in DB
CREATE PROCEDURE `check_Lcode`(IN Lecturer_code VARCHAR(10), OUT Lcode_check INT)
BEGIN
	SELECT COUNT(l.Lcode) INTO Lcode_check
    FROM lecturer l
    WHERE l.Lcode = Lecturer_code;
END

-- Stored procedure to check Class code in DB
CREATE PROCEDURE `check_Ccode`(IN Class_code VARCHAR(10), OUT Ccode_check INT)
BEGIN
	SELECT COUNT(c.Ccode) INTO Ccode_check
	FROM Class c
	WHERE c.Ccode = Class_code;
END

-- Check Lecturer code in DB example
call pe2018.check_Lcode('L001v', @Lcode_check);
select @Lcode_check as Lcode_check;

-- Check Class code in DB example
call pe2018.check_Ccode('C001t', @Ccode_check);
select @Ccode_check as Ccode_check;

-- Code explanation:
-- The stored procedure will output an int, which in this case the count of Ccode or Lcode
-- If there exists a code in DB already, the count will return 1, otherwise 0
-- Call the procedure alone will return nothing, so select is needed
