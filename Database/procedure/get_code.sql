USE pe2018;
DROP PROCEDURE IF EXISTS get_code;
DELIMITER //
CREATE PROCEDURE `get_code`()
BEGIN
-- Join all to create a big table
-- Then return all information
	SELECT ay.AYcode,
		s.Scode,
        f.Fcode,
        p.Pcode,
        m.Mcode,
        c.Ccode,
        lec.Lcode,
        c.size
	FROM class c
   NATURAL JOIN lecturer_in_class NATURAL JOIN lecturer lec 
   NATURAL JOIN semester s
   NATURAL JOIN academic_year ay
   NATURAL JOIN ay_fac NATURAL JOIN faculty f
   NATURAL JOIN ay_fac_p NATURAL JOIN program p
   NATURAL JOIN ay_fac_pm NATURAL JOIN module m
;
END
//
