CREATE PROCEDURE GetTotalClassesSize
	(academic_year VARCHAR(50), 
	semester VARCHAR(50), 
	faculty VARCHAR(50), 
	program VARCHAR(50), 
	module VARCHAR(50), 
	lecturer VARCHAR(50), 
	class VARCHAR(50)) 
BEGIN
SELECT SUM(c.size)
FROM Class c
	JOIN Semester s ON ( c.Scode = s.Scode)
    JOIN Academic_Year ay ON ( ay.AYCode = s.AYCode)
    JOIN Module m ON ( c.Mcode = m.Mcode)
    JOIN Program p ON (m.Pcode = p.Pcode)
    JOIN Faculty f ON (p.Fcode = f.Fcode)
    JOIN Lecturer lec ON ( lec.Lcode = (SELECT clec.Lcode
										FROM Class_has_Lecturer clec 
										WHERE c.Ccode = clec.Ccode))
WHERE 
	( academic_year is null
      or ay.AYCode = academic_year
	)
AND
	( semester is null
      or s.Scode = semester
	)
AND
	( faculty is null
      or f.Fcode = faculty
	)
AND
	( program is null
      or p.Pcode = program
	)
AND
	( module is null
      or m.Mcode = module
	)
AND
	( lecturer is null
      or lec.Lcode = lecturer
	)
AND
	( class is null
      or c.Ccode = class
	)
End;

    



