CREATE PROCEDURE `get_role`(input_login VARCHAR(50), input_password VARCHAR(50))
BEGIN
SET @valid = 0;
IF NOT EXISTS(SELECT * FROM account WHERE login = input_login AND password = input_password)
	THEN SELECT @valid as 'valid';
END IF;
IF EXISTS(SELECT * FROM account WHERE login = input_login AND password = input_password)
	THEN SET @valid = 1;
	SELECT @valid as 'valid', lc.Lcode as 'lecturer', GROUP_CONCAT(DISTINCT lic.Ccode SEPARATOR ', ') as 'class_code',
		pc.PCcode as 'coordinator', GROUP_CONCAT(DISTINCT p.Pname SEPARATOR ', ') as 'program',
        d.Dcode as 'dean', GROUP_CONCAT(DISTINCT f.Fname SEPARATOR ', ') as 'faculty'
	FROM account a
	NATURAL JOIN user u
	LEFT JOIN lecturer lc ON u.user_id = lc.user_id LEFT JOIN lecturer_in_class lic on lc.Lcode = lic.Lcode
	LEFT JOIN program_coordinator pc ON u.user_id = pc.user_id LEFT JOIN program p on pc.Pcode = p.Pcode
	LEFT JOIN dean d ON u.user_id = d.user_id LEFT JOIN faculty f ON d.Fcode = f.Fcode
    WHERE a.login = input_login AND a.password = input_password
;
END IF;
END
