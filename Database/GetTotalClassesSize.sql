DELIMITER $$
CREATE PROCEDURE `GetTotalClassesSize`(AYCode INT, 
									   Scode VARCHAR(50), 
								       Fcode VARCHAR(50), 
									   Pcode VARCHAR(50), 
									   Mcode VARCHAR(50), 
									   Lcode VARCHAR(50), 
									   Ccode VARCHAR(50))
BEGIN
SELECT SUM(c.size)
FROM class c
	JOIN semester s ON ( c.FK_Scode = s.PK_Scode)
    JOIN academic_year ay1 ON ( ay.PK_AYCode = s.FK_AYCode)
    JOIN module m ON ( c.FK_Mcode = m.PK_Mcode)
    JOIN program p ON (m.FK_Pcode = p.PK_Pcode)
    JOIN faculty f ON (p.FK_Fcode = f.PK_Fcode)
    JOIN academic_year ay2 ON ( ay.PK_AYCode = f.FK_AYCode)
    JOIN lecturer lec ON ( lec.Lcode = (SELECT cl.FK_Lcode
										FROM class_has_lecturer cl 
										WHERE c.PK_Ccode = cl.FK_Ccode))
WHERE 
	( academic_year is null
      or ay.PK_AYCode = AYCode
	)
AND
	( semester is null
      or s.PK_Scode = Scode
	)
AND
	( faculty is null
      or f.PK_Fcode = Fcode
	)
AND
	( program is null
      or p.PK_Pcode = Pcode
	)
AND
	( module is null
      or m.PK_Mcode = Mcode
	)
AND
	( lecturer is null
      or lec.PK_Lcode = Lcode
	)
AND
	( class is null
      or c.PK_Ccode = Ccode
	)
;
END$$
DELIMITER ;
