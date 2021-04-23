USE pe2018;
DROP PROCEDURE IF EXISTS GetTotalClassesSize;

DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetTotalClassesSize`(
	input_AYcode VARCHAR(9), 
	input_Scode VARCHAR(10), 
	input_Fcode VARCHAR(10), 
	input_Pcode VARCHAR(10), 
	input_Mcode VARCHAR(10), 
	input_Lcode VARCHAR(10), 
	input_Ccode VARCHAR(10))
sp: BEGIN
-- Check invalid parameter (Para NOT null but not in database)
CASE
	WHEN (input_AYcode not in (Select AYcode from academic_year)) AND (input_AYcode is not NULL) THEN
		SELECT 'invalid academic year' as 'message';
        LEAVE sp;
        
	WHEN (input_Scode not in (Select Scode from semester)) AND (input_Scode is not NULL) THEN
		SELECT 'invalid semester' as 'message';
        LEAVE sp;

	WHEN (input_Fcode not in (Select Fcode from faculty)) AND (input_Fcode is not NULL) THEN
		SELECT 'invalid faculty' as 'message';
        LEAVE sp;

	WHEN (input_Pcode not in (Select Pcode from program)) AND (input_Pcode is not NULL) THEN
		SELECT 'invalid program' as 'message';
        LEAVE sp;

	WHEN (input_Mcode not in (Select Mcode from module)) AND (input_Mcode is not NULL) THEN
		SELECT 'invalid module' as 'message';
        LEAVE sp;

	WHEN (input_Lcode not in (Select Lcode from lecturer)) AND (input_Lcode is not NULL) THEN
		SELECT 'invalid lecturer' as 'message';
        LEAVE sp;
        
	WHEN (input_Ccode not in (Select Ccode from class)) AND (input_Ccode is not NULL) THEN
		SELECT 'invalid class' as 'message';
        LEAVE sp;
	ELSE
-- Query for getting the class's size
SELECT SUM(c.size) as 'sum'
FROM class c
   NATURAL JOIN lecturer_in_class NATURAL JOIN lecturer lec 
   NATURAL JOIN semester s
   NATURAL JOIN academic_year ay
   NATURAL JOIN ay_fac NATURAL JOIN faculty f
   NATURAL JOIN ay_fac_p NATURAL JOIN program p
   NATURAL JOIN ay_fac_pm NATURAL JOIN module m

WHERE 
-- Check WHEN parameter NULL or NOT, WHEN yes, query based on the other parameters
	( input_AYcode is null
		or ay.AYcode = input_AYcode
	)
	AND
	( input_Scode is null
		or s.Scode = input_Scode
	)
	AND
	( input_Fcode is null
		or f.Fcode = input_Fcode
	)
	AND
	( input_Pcode is null
		or p.Pcode = input_Pcode
	)
	AND
	( input_Mcode is null
		or m.Mcode = input_Mcode
	)
	AND
	( input_Lcode is null
		or lec.Lcode = input_Lcode
	)
	AND
	( input_Ccode is null
		or c.Ccode = input_Ccode
	);
END CASE;
END
//
