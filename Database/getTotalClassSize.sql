CREATE PROCEDURE `GetTotalClassesSize`(
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
-- Join into one big table
FROM class c
	NATURAL JOIN lecturer_in_class lc
    NATURAL JOIN semester s
    NATURAL JOIN faculty_in_ay fay
    NATURAL JOIN module_in_ay may
    NATURAL JOIN program_in_ay pay

WHERE
-- Check if parameter NULL or NOT, if yes, query based on the other parameters
	( academic_year is null
      or fay.AYcode = academic_year
	)
AND
	( semester is null
      or s.Scode = semester
	)
AND
	( faculty is null
      or fay.Fcode = faculty
	)
AND
	( program is null
      or pay.Pcode = program
	)
AND
	( module is null
      or may.Mcode = module
	)
AND
	( lecturer is null
      or lc.Lcode = lecturer
	)
AND
	( class is null
      or c.Ccode = class
	);
End
