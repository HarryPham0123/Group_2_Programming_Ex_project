USE pe2018;
DROP PROCEDURE IF EXISTS get_code;
DELIMITER //
CREATE PROCEDURE `get_code`()
BEGIN
	SELECT ay.AYcode,
		s.Scode,
        f.Fcode,
        p.Pcode,
        m.Mcode,
        c.Ccode
	FROM class c
		NATURAL JOIN lecturer_in_class NATURAL JOIN lecturer lec 
		NATURAL JOIN semester s
		NATURAL JOIN academic_year ay
		NATURAL JOIN module m
		NATURAL JOIN program_module pm
		NATURAL JOIN program p
		NATURAL JOIN ay_faculty_pm 
		NATURAL JOIN faculty f;
END
//