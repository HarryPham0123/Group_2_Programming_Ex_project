USE pe2018;
DROP PROCEDURE IF EXISTS get_role;
DELIMITER //
CREATE PROCEDURE `get_role`(input_login VARCHAR(50), input_password VARCHAR(50))
BEGIN
-- Initialize the variable 'valid', which is the outcome confirmation
-- 0 represents FALSE
SET @valid = 0;
-- Check if the login and password pair exists in DB
IF NOT EXISTS(SELECT * FROM employee_account WHERE login = input_login AND employee_password = input_password)
	-- If not then output 0
    THEN SELECT @valid as 'valid';
END IF;
-- Check if the pair exists in DB
IF EXISTS(SELECT * FROM employee_account WHERE login = input_login AND employee_password = input_password)
	-- If yes, set value of 'valid' to 1, which represents TRUE
    THEN SET @valid = 1;
    -- Query all roles that associates with the input account, and its relevant information
	SELECT @valid as 'valid', lc.Lcode as 'lecturer', GROUP_CONCAT(DISTINCT lic.Ccode SEPARATOR ', ') as 'class_code',
		pc.PCcode as 'coordinator', GROUP_CONCAT(DISTINCT p.Pname SEPARATOR ', ') as 'program',
        d.Dcode as 'dean', GROUP_CONCAT(DISTINCT f.Fname SEPARATOR ', ') as 'faculty'
	FROM employee_account a
	NATURAL JOIN employee_user u
    -- Left join to represent NULL values, because users may not have all three roles Lecturer, Dean and Coordinator
	LEFT JOIN lecturer lc ON u.user_id = lc.user_id LEFT JOIN lecturer_in_class lic on lc.Lcode = lic.Lcode
	LEFT JOIN program_coordinator pc ON u.user_id = pc.user_id LEFT JOIN program p on pc.Pcode = p.Pcode
	LEFT JOIN dean d ON u.user_id = d.user_id LEFT JOIN faculty f ON d.Fcode = f.Fcode
    -- Filter based on the inputs
    WHERE a.login = input_login AND a.employee_password = input_password
;
END IF;
END
//
