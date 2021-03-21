CREATE PROCEDURE `GetTotalClassesSize`(
	academic_year VARCHAR(50), 
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
    	JOIN Academic_Year ay ON ( ay.AYcode = s.AYcode)
    	JOIN Module m ON ( c.Mcode = m.Mcode), 
     Program p, Lecturer lec, Faculty f
WHERE 
	f.Fcode IN (SELECT fay.Fcode
		     FROM faculty_in_ay fay
                    WHERE fay.AYcode = ay.AYcode)
AND
	lec.Lcode IN (SELECT q.Lcode
		       FROM Questionnaire q 
		      WHERE c.Ccode = q.Ccode)
AND
	p.Pcode IN (SELECT mpa.Pcode
		     FROM module_program_in_ay mpa
		    WHERE ay.AYcode = mpa.AYcode)
AND
	( academic_year is null
      or ay.AYcode = academic_year
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
	);
End
