use group2_database;

-- ALL parameters valid
-- call GetTotalClassesSize('A2020Y', 'S002q', 'F001I', 'P001C', 'M007A', 'L001v', 'C007q') -- 35

-- Exist 1 or more null parameters
-- call GetTotalClassesSize('A2020Y', 'S003g', 'F001I', 'P006A', 'M010B', NULL, 'C010i') -- 64
-- call GetTotalClassesSize('A2020Y', 'S003g', NULL, 'P006A', 'M010B', NULL, 'C010i') -- 128

-- All parameters are null parameters
-- call GetTotalClassesSize(NULL, NULL, NULL, NULL, NULL, NULL, NULL) -- 12278


-- Exist 1 or more invalid parameters
-- call GetTotalClassesSize('A2020Y', 'S002q', 'F001I', 'P001C', 'M007A', 'L001v', 'C007') -- 35
-- call GetTotalClassesSize('A2020Y', 'S002q', 'F001I', 'P001C', 'M007A', 'L001v', NULL) -- 35
-- call GetTotalClassesSize('A2020Y', 'S002q', 'F001I', 'P001C', 'M007', 'L001', 'C007') -- 204
-- call GetTotalClassesSize('A2020Y', 'S002q', 'F001I', 'P001C', NULL, NULL, NULL) -- 204

-- All parameters are invalid
-- call GetTotalClassesSize('A202Y', '002q', 'F00I', 'P00C', 'M07A', 'L00v', 'C00q') -- 12278
-- call GetTotalClassesSize(NULL, NULL, NULL, NULL, NULL, NULL, NULL) -- 12278

