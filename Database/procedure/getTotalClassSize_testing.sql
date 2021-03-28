use group2_database;

-- ALL parameters valid
-- call pe2018.GetTotalClassesSize('A2020Y', 'S002q', 'F001I', 'P004E', 'M007A', 'L001v', 'C007q') -- 35

-- Exist 1 or more null parameters
-- call pe2018.GetTotalClassesSize('A2020Y', 'S002q', 'F001I', 'P004E', 'M007A', NULL , 'C007q') -- class code is null -- 35
-- call pe2018.GetTotalClassesSize('A2020Y', 'S002q', NULL , 'P004E', 'M007A', NULL , 'C007q') -- null faculty code and class code -- 35

-- All parameters are null parameters
-- call pe2018.GetTotalClassesSize(NULL, NULL, NULL, NULL, NULL, NULL, NULL) -- return the sum(c.size) -- 802


-- Exist 1 or more invalid parameters
-- call pe2018.GetTotalClassesSize('A2020Y', 'S002q', 'F001I', 'random', 'M007A', 'L001v', 'C007q') -- the program code is not in DB -- 35
-- call pe2018.GetTotalClassesSize('A2020Y', 'S002q', 'F001I', 'random', 'M007A', 'L001v', 'test') -- the program and class code is not in DB -- 35


-- All parameters are invalid
-- call pe2018.GetTotalClassesSize('this', 'is', 'just', 'a', 'random', 'test', 'nice') -- return the sum(c.size) -- 802
