CREATE DEFINER=`HoangPham`@`localhost` PROCEDURE `GetTotalClassesSize`(
	academic_year VARCHAR(50), 
	semester VARCHAR(50), 
	faculty VARCHAR(50), 
	program VARCHAR(50), 
	module VARCHAR(50), 
	lecturer VARCHAR(50), 
	class VARCHAR(50))
BEGIN
-- Check invalid parameter (Para NOT null but not in database)
IF (class not in (Select Ccode from class)) AND (class is not NULL) THEN
	SET class := NULL;
END IF;

IF (lecturer not in (Select Lcode from lecturer)) AND (lecturer is not NULL) THEN
	SET lecturer := NULL;
END IF;

IF (module not in (Select Mcode from module)) AND (module is not NULL) THEN
	SET module := NULL;
END IF;

IF (program not in (Select Pcode from program)) AND (program is not NULL) THEN
	SET program := NULL;
END IF;

IF (faculty not in (Select Fcode from faculty)) AND (faculty is not NULL) THEN
	SET faculty := NULL;
END IF;

IF (semester not in (Select Scode from semester)) AND (semester is not NULL) THEN
	SET semester := NULL;
END IF;

IF (academic_year not in (Select AYcode from academic_year)) AND (academic_year is not NULL) THEN
	SET academic_year := NULL;
END IF;

-- Query for getting the class's size
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
	p.Pcode IN (SELECT pay.Pcode
		        FROM program_in_ay pay
				WHERE pay.AYcode = ay.AYcode)
AND
	m.Mcode IN (SELECT may.Mcode
		        FROM module_in_ay may
				WHERE may.AYcode = ay.AYcode)
AND
-- Check if parameter NULL or NOT, if yes, query based on the other parameters
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
